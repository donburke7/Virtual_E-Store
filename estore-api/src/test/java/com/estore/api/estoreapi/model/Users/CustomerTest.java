package com.estore.api.estoreapi.model.Users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;

import org.junit.jupiter.api.Test;

public class CustomerTest {
    @Test
    public void testAddProduct() {
        // Setup
        Product expected = new Product("Green Beans", 0, 500, 2.00);
        Customer testCustomer = new Customer("Bart");

        // Invoke
        testCustomer.addProduct(expected);
        ShoppingCart cart = testCustomer.getCart();
        Product actual = cart.removeProduct(0); // Returns the product that was removed, null if not

        // Analysis
        assertEquals(expected, actual);
    }
}
