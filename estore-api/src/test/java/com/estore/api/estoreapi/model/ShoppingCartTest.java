package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShoppingCartTest {
    @Test
    public void testAddProduct() {
        // Setup
        Product expected = new Product("Green Beans", 0, 500, 2.00);
        ShoppingCart cart = new ShoppingCart(null);

        // Invoke
        cart.addProduct(expected);
        Product actual = cart.getItems().get(0);

        // Analysis
        assertEquals(expected, actual);
    }
}
