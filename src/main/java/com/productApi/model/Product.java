package com.productApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Enter the Name.")
    private String name;
    private int amount;
    private double price;
    private String status;

    public Product (String name, int amount, double price,String status )
    {
        this.name = name;
        this.amount =amount;
        this.price = price;
        this.status = status;
    }

    public Product () {}

    public long getId()
    {return  id;}

    public void setId (Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void  setName(String name)
    {
        this.name = name;
    }

    public  int getAmount()
    {
        return  amount;
    }

    public void setAmount(int amount)
    {
        this.amount= amount;
    }

    public double getPrice ()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price= price;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status =status;
    }

}
