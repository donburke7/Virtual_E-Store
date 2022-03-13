package com.estore.api.estoreapi.model.Users;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import com.estore.api.estoreapi.model.Product;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * unit tests for the customer class
 * 
 * @author Isaac Post
 */
@Tag("Model-Tier")
public class CustomerTest {

    @Test
    public void testAddProduct() {
        // Setup
        Product addedProduct = new Product("Green Beans", 0, 500, 2.00);
        Customer testCustomer = new Customer("Bart");

        // Invoke
        testCustomer.addProduct(addedProduct);
        Product[] actual = testCustomer.getCart();
        Product[] expected = {addedProduct};

        // Analysis
        assertTrue(Arrays.equals(expected, actual));
    }
}
