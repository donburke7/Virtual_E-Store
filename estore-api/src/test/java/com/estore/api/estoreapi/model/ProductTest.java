package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Controller-tier")
public class ProductTest {

    @Test
    public void testsetAmount() {

        Product product = new Product("Green Beans", 0, 500, 2.00);

        int targetAmount = 200;
        product.setAmount(targetAmount);

        assertEquals(targetAmount, product.getAmount());

    }

    @Test
    public void testgetPrice() {

        double targetPrice = 2.00;

        Product product = new Product("Green Beans", 0, 500, targetPrice);

        assertEquals(targetPrice, product.getPrice());

    }

    @Test
    public void testsetPrice() {

        Product product = new Product("Green Beans", 0, 500, 2.00);
        double targetPrice = 2.00;

        product.setPrice(targetPrice);

        assertEquals(targetPrice, product.getPrice());

    }

}
