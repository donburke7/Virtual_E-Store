package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that represents an instance of a product
 * 
 * @author Alen Van
 */
public class Product {
    // The string format of this product, used in toString()
    static final String STRING_FORMAT = "Product [id=%d, name=%s, amount=%d, price=%2f, average rating=%2f]";

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private int id;
    @JsonProperty("amount")
    private int amount;

    @JsonProperty("price")
    private double price;

    @JsonProperty("ratings")
    private double ratings[];

    @JsonProperty("avg_rating")
    private double avgRating;

    /**
     * Constructor for the product class
     * Takes in attributes passed through by a Json file and creates a product based
     * off of the information given
     * 
     * @param name   The name of the product
     * @param id     The id of the product
     * @param amount How many of this product is currently present
     * @param price  The price of this product
     * @param ratings An array of doubles that holds all of the ratings for this product
     * @param avgRating The average rating for this product
     */
    public Product(@JsonProperty("name") String name, @JsonProperty("id") int id,
            @JsonProperty("amount") int amount, @JsonProperty("price") double price, 
            @JsonProperty("ratings") double ratings[], @JsonProperty("avg_rating") double avgRating) {

        //Check to see if the priduct has any 0's or nulls present upon creation
        //if so then set a default
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

        if (ratings == null) {
            this.ratings = new double[]{};
            this.avgRating = 0;
        } else {
            this.avgRating = avgRating;
            this.ratings = ratings;
        }

        this.name = name;
        this.id = id;
    }

    /**
     * secondary constructor for the product class
     * this constructor specifically creates a clone of an already existing product
     * and save a new amount to the new product
     * 
     * @param original The original product to clone
     * @param amount The new int to set the amount to
     */
    public Product(Product original, int amount) {
        this.name = original.name;
        this.amount = amount;
        this.price = original.price;
        this.id = original.id;
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
     * Gets the price of this item
     * 
     * @return the price of this item as a double
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Gets all the ratings of this item
     * @return the ratings of the item as a double list
     */
    public double[] getRatings() {
        return this.ratings;
    }

    /**
     * Gets the current average rating of this item
     * @return the average rating of the item as a double
     */
    public double getAvgRating() {
        return this.avgRating;
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
     * Sets the price of this product
     * 
     * @param price the price to be set as the products current price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the average rating of this product to the passed in value
     * @param newAvgRating the new average rating to be set as the current average rating
     */
    public void updateAvgRating() {
        double sum = 0.0;

        for (double rating : this.ratings) {
            sum += rating;
        }

        double average = round((sum / this.ratings.length), 1);

        this.avgRating = average;
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, this.id, this.name, this.amount, this.price, this.avgRating);
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

}