package com.estore.api.estoreapi.model.Users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.TreeMap;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-Tier")
public class CustomerTest {

    ShoppingCart testShoppingCart;
    ShoppingCart emptyShoppingCart;

    @BeforeEach
    public void setup() {

        TreeMap<Integer, Product> map = new TreeMap();
        Product expected = new Product("Green Beans", 0, 1, 1.00);
        map.put(0, expected);

        testShoppingCart = new ShoppingCart(map);
        emptyShoppingCart = new ShoppingCart(null);

    }

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
