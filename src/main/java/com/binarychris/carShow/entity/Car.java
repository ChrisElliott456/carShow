package com.binarychris.carShow.entity;

import jakarta.persistence.*;

@Entity
// @Table(name = "carTable") // changes name of table when converting
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // strategies on how to convert to tables in sql for database
    //AUTO how primary key should be generated, main one for the job
    //IDENTITY relies on auto_incrementation column mysql and postgres
    //SEQUENCE makes generator
    @Column(name = "car_Id") // changes name of column
    private Long id;
    private String make;
    private String model;
    private String color;
    private String registerNumber;
    private int year;
    private double price;

    public Car() {
    } // Hibernate will use this

    public Car(String make, String model, String color, String registerNumber, int year, double price) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
