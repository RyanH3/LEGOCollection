package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LEGOSet {
    @Id
    private int id;
    private String setName;
    private int piecesAmount;
    private float price;

    public LEGOSet() {}

    public LEGOSet(String setName, int piecesAmount, float price) {
        this.setName = setName;
        this.piecesAmount = piecesAmount;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetName() {
        return this.setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public int getPiecesAmount() {
        return this.piecesAmount;
    }

    public void setPiecesAmount(int piecesAmount) {
        this.piecesAmount = piecesAmount;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
