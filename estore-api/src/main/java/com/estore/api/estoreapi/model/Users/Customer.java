package com.estore.api.estoreapi.model.Users;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer extends User {

    @JsonProperty("cart")
    ShoppingCart cart;

    /**
     * Json constructor to initialize a {@linkplain Customer customer} from a json
     * string
     * 
     * @param username the username of the {@link Customer customer}
     * @param cart     the {@link ShoppingCart cart} that is associated with the
     *                 {@link Customer customer}
     */
    @JsonCreator
    public Customer(@JsonProperty("username") String username, @JsonProperty("cart") ShoppingCart cart) {
        super(username);
        this.cart = cart;
    }

    /**
     * Create a brand new {@linkplain Customer customer} with an empty
     * {@link ShoppingCart cart}
     * 
     * @param username the username of the {@link Customer customer}
     */
    public Customer(String username) {
        super(username);
        this.cart = new ShoppingCart(null);
    }

    /**
     * Adds a product into this {@linkplain Customer customer}'s
     * {@linkplain ShoppingCart cart}
     * 
     * @param product the {@link Product product} to add to this {@link Customer
     *                customer}'s {@link ShoppingCart cart}
     * @return the {@link Product product} that was added
     */
    public Product addProduct(Product product) {
        return this.cart.addProduct(product);
    }

    /**
     * removes a {@linkplain Product product} from the {@link Customer customer}'s'
     * cart
     * 
     * @param id the id of the {@link Product product} to remove
     * @return the {@link Product product} that was removed
     */
    public Product removeProduct(int id) {
        return this.cart.removeProduct(id);
    }

    /**
     * clears the {@linkplain ShoppingCart cart}
     */
    public void clearCart() {
        this.cart.clearCart();
    }

    /**
     * Returns the users {@linkplain ShoppingCart cart}
     * 
     * @return the {@linkplain ShoppingCart cart}
     */
    public ShoppingCart getCart() {
        return this.cart;
    }

    /**
     * executes checkout methods once implemented
     */
    public void checkout() {
        // TODO
    }

    public String getUsername(){
        return this.username;
    }
}
