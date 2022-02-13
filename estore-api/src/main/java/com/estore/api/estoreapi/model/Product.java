package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that represents an instance of a product
 * 
 * @author Alen Van
 */
public class Product {
    // The string format of this product, used in toString()
    static final String STRING_FORMAT = "Product [id=%d, name=%s, amount=%d]";

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private int id;
    @JsonProperty("amount")
    private int amount;

    /**
     * Constructor for the product class
     * Takes in attributes passed through by a Json file and creates a product based
     * off of the information given
     * 
     * @param name   The name of the product
     * @param id     The id of the product
     * @param amount How many of this product is currently present
     */
    public Product(@JsonProperty("name") String name, @JsonProperty("id") int id, @JsonProperty("amount") int amount) {

        if (amount <= 0) {
            this.amount = 1;
        } else {
            this.amount = amount;
        }

        this.name = name;
        this.id = id;
    }

    /**
     * Gets the id of this product
     * 
     * @return an int representing this product's id
     */
    public int getID() {
        return this.id;
    }

    /**
     * Gets the name of this product
     * 
     * @return a string representing this product's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the amount of this product
     * 
     * @return an int representing the amount of this product
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Sets the amount of this product
     * 
     * @param amount the new amount to set this product to
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * The overidden ToString() method
     * Creates a string that represents this instance
     * 
     * @return a string that represents this object
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, this.id, this.name, this.amount);
    }

}