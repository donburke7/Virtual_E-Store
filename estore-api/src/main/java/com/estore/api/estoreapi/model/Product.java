package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that represents an instance of a product
 * 
 * @author Alen Van
 */
public class Product {
    // The string format of this product, used in toString()
    static final String STRING_FORMAT = "Product [id=%d, name=%s, amount=%d, price=%2f]";

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private int id;
    @JsonProperty("amount")
    private int amount;

    @JsonProperty("price")
    private double price;

    /**
     * Constructor for the product class
     * Takes in attributes passed through by a Json file and creates a product based
     * off of the information given
     * 
     * @param name   The name of the product
     * @param id     The id of the product
     * @param amount How many of this product is currently present
     * @param price
     */
    public Product(@JsonProperty("name") String name, @JsonProperty("id") int id,
            @JsonProperty("amount") int amount, @JsonProperty("price") double price) {

        if (amount <= 0) {
            this.amount = 1;
        } else {
            this.amount = amount;
        }

        if (price <= 0) {
            this.price = 1.00;
        } else {
            this.price = price;
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
     * Gets the price of this item
     * 
     * @return the price of this item as a double
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the price of this product
     * 
     * @param price the price to be
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, this.id, this.name, this.amount, this.price);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        // check to see if the classes are the same
        if (obj.getClass() == Product.class) {
            final Product other = (Product) obj;

            // check to see if the attributes are the same
            if (other.name.equals(this.name) &&
                    other.price == this.price &&
                    other.amount == this.amount &&
                    other.id == this.id) {

                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return (this.name.hashCode() + this.amount + this.id);
    }

}