package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.estore.api.estoreapi.model.Users.User;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for the Product class
 * 
 * @author Team jadin
 */
@Tag("Persistence-tier")
public class ProductTest {

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
        String expected_string = String.format(Product.STRING_FORMAT, id, name, amount, price);

        Product bean = new Product(name, id, amount, price);

        // Invoke
        String actual_string = bean.toString();

        // Analyze
        assertEquals(expected_string, actual_string);
    }

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

        //setup
        Product newProduct = new Product("Green Beans", 0, 20, 1.00);
        Product testProduct = new Product("Green Beans", 0, 20, 1.00);

        //invoke and analyze, should return true
        //all properties of the two products are equal
        assertEquals(true, newProduct.equals(testProduct));

    }

    @Test
    public void testequalFail() {

        //setup
        Product newProduct = new Product("Green Beans", 0, 20, 1.00);
        Product testProduct = new Product("Green Beans", 1, 20, 1.00);

        //invoke and analyze, should return false
        //the two are 
        assertEquals(false, newProduct.equals(testProduct));

    }

    @Test
    public void testequalFailWrongObject() {
        Product test1 = new Product("Green Beans", 0, 1, 1.00);
        User test2 = new User("Billy");

        assertFalse(test1.equals(test2));
    }

    @Test
    public void testequalFailNull() {
        Product test1 = new Product("Green Beans", 0, 1, 1.00);

        assertFalse(test1.equals(null));
    }

}
