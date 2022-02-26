package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class InventoryModelTest {
    @Test
    public void testCtor() {
        // Setup
        int expected_id = 99;
        String expected_name = "Magic Bean";
        int expected_amount = 100;
        double expected_price = 489.01;

        // Invoke
        Product bean = new Product(expected_name, expected_id, expected_amount, expected_price);

        // Analyze
        assertEquals(expected_id, bean.getID());
        assertEquals(expected_name, bean.getName());
    }

    @Test
    public void testGetName() {
        // Setup
        int id = 99;
        String expected_name = "Magic Bean";
        int amount = 100;
        double price = 489.01;
        Product bean = new Product(expected_name, id, amount, price);

        // Invoke
        bean.getName();

        // Analyze
        assertEquals(expected_name, bean.getName());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 99;
        String name = "Magic Bean";
        int amount = 100;
        double price = 489.01;
        String expected_string = String.format(Product.STRING_FORMAT,id,name);
        
        Product bean = new Product(name, id, amount, price);

        // Invoke
        String actual_string = bean.toString();

        // Analyze
        assertEquals(expected_string, actual_string);
    }
}
