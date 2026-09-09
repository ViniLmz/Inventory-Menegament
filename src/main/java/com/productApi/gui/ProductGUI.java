package com.productApi.gui;

import com.productApi.model.Product;
import com.productApi.service.ProductService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class ProductGUI extends Application {

    private ConfigurableApplicationContext springContext;
    private ProductService productService;

    private ObservableList<Product> products;
    private TableView<Product> tableView;
    private TextField nameInput, quantityInput, priceInput;
    private ComboBox<String> statusComboBox;

    public static void main(String[] args)
    {
        launch();
    }


    @Override
    public void init() {
        // Inicializa o contexto do Spring Boot
        springContext = new SpringApplicationBuilder(com.productApi.MyProjectSpringbootApplication.class).run();
        // Obtém o Bean do ProductService gerenciado pelo Spring
        productService = springContext.getBean(ProductService.class);
    }

    @Override
    public void start(Stage stage) {
        products = FXCollections.observableArrayList(productService.listAll());

        stage.setTitle("Inventory Management");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        // Formulário
        HBox nameProductBox = new HBox(10, new Label("Product: "), nameInput = new TextField());
        HBox quantityBox = new HBox(10, new Label("Quantity: "), quantityInput = new TextField());
        HBox priceBox = new HBox(10, new Label("Price: "), priceInput = new TextField());

        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Available", "Running Low");
        HBox statusBox = new HBox(10, new Label("Status: "), statusComboBox);

        // Botões de Ação
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            if (!validateInputs()) return;

            String priceText = priceInput.getText().replace(',', '.');
            Product newProduct = new Product(
                    nameInput.getText(),
                    Integer.parseInt(quantityInput.getText()),
                    Double.parseDouble(priceText),
                    statusComboBox.getValue()
            );

            productService.save(newProduct);
            refreshTable();
            clearSpace();
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null && validateInputs()) {
                String priceText = priceInput.getText().replace(',', '.');
                selectedProduct.setName(nameInput.getText());
                selectedProduct.setAmount(Integer.parseInt(quantityInput.getText()));
                selectedProduct.setPrice(Double.parseDouble(priceText));
                selectedProduct.setStatus(statusComboBox.getValue());

                productService.update(selectedProduct.getId(), selectedProduct);
                refreshTable();
                clearSpace();
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                productService.delete(selectedProduct.getId());
                refreshTable();
                clearSpace();
            }
        });

        Button cleanButton = new Button("Clear");
        cleanButton.setOnAction(e -> clearSpace());

        // Tabela
        tableView = new TableView<>();
        tableView.setItems(products);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        List<TableColumn<Product, ?>> columns = List.of(
                columnCreator("ID", "id"),
                columnCreator("Product", "name"),
                columnCreator("Quantity", "amount"),
                columnCreator("Price", "price"),
                columnCreator("Status", "status")
        );

        tableView.getColumns().addAll(columns);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameInput.setText(newSelection.getName());
                quantityInput.setText(String.valueOf(newSelection.getAmount()));
                priceInput.setText(String.valueOf(newSelection.getPrice()));
                statusComboBox.setValue(newSelection.getStatus());
            }
        });

        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton, cleanButton);
        vbox.getChildren().addAll(nameProductBox, quantityBox, priceBox, statusBox, buttonBox, tableView);

        Scene scene = new Scene(vbox, 800, 600);

        // Opcional: recarrega CSS se existir
        if (getClass().getResource("/styles-product.css") != null) {
            scene.getStylesheets().add(getClass().getResource("/styles-product.css").toExternalForm());
        }

        stage.setScene(scene);
        stage.show();
    }

    private <T> TableColumn<Product, T> columnCreator(String title, String property) {
        TableColumn<Product, T> col = new TableColumn<>(title);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        return col;
    }

    private void refreshTable() {
        products.setAll(productService.listAll());
    }

    private void clearSpace() {
        nameInput.clear();
        quantityInput.clear();
        priceInput.clear();
        statusComboBox.setValue(null);
        tableView.getSelectionModel().clearSelection();
    }

    private boolean validateInputs() {
        if (nameInput.getText().isBlank() || quantityInput.getText().isBlank()
                || priceInput.getText().isBlank() || statusComboBox.getValue() == null) {
            System.out.println("All fields are required.");
            return false;
        }

        try {
            int quantity = Integer.parseInt(quantityInput.getText());
            double price = Double.parseDouble(priceInput.getText().replace(',', '.'));

            if (quantity < 0 || price < 0) {
                System.out.println("Values cannot be negative.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
            return false;
        }

        return true;
    }

    @Override
    public void stop() {
        // Encerra o contexto do Spring Boot ao fechar a janela JavaFX
        springContext.close();
        Platform.exit();
    }
}