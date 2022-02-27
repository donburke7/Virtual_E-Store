package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Product")
public class ProductTest {

    @Test
    public void testsetAmount() {

        Product product = new Product("Green Beans", 0, 500, 2.00);

        // save and sets the amonut
        int targetAmount = 200;
        product.setAmount(targetAmount);

        // checks the amount given that the get amount method is friendly
        assertEquals(targetAmount, product.getAmount());

    }

    @Test
    public void testgetPrice() {

        double targetPrice = 2.00;
        Product product = new Product("Green Beans", 0, 500, targetPrice);

        // checks to see if the price of the product is correctly
        // returned by the getPrice() method
        assertEquals(targetPrice, product.getPrice());

    }

    @Test
    public void testsetPrice() {

        Product product = new Product("Green Beans", 0, 500, 2.00);

        // saves and sets the price of the product
        double targetPrice = 2.00;
        product.setPrice(targetPrice);

        // checks to see if the price was correctly set
        // given that the getPrice() method is friendly
        assertEquals(targetPrice, product.getPrice());

    }

    @Test
    public void testequal() {

        Product newProduct = new Product("Green Beans", 0, 20, 1.00);
        Product testProduct = new Product("Green Beans", 0, 20, 1.00);

        assertEquals(true, newProduct.equals(testProduct));

    }

    @Test
    public void testequalFail() {

        Product newProduct = new Product("Green Beans", 0, 20, 1.00);
        Product testProduct = new Product("Green Beans", 1, 20, 1.00);

        assertEquals(false, newProduct.equals(testProduct));

    }

}
