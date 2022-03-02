package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit testing for the InventoryFileDAO class
 * 
 * @author Team jadin
 */
@Tag("Persistence-tier")
public class InventoryFileDAOTest {

    private InventoryDAO inventoryDAO;

    private Product[] testProducts = {
            // Initialize testProducts
            new Product("Green Beans", 0, 0, 0),
            new Product("Blue Beans", 1, 0, 0),
            new Product("Cow Beans", 2, 0, 0)
    };

    @BeforeEach
    public void InventoryFileDAOSetup() throws IOException {
        // Initialize a test inventory file
        String filepath = "testInventory.json";

        File file = new File(filepath);
        file.createNewFile();

        // create an object mapper as well as use it to write the array of test products
        // into a json file
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, testProducts);

        // load up an InventoryDAO with the newly created test inventory
        inventoryDAO = new InventoryFileDAO(filepath, new ObjectMapper());

    }

    @Test
    public void testgetProducts() throws IOException {

        Product[] products = inventoryDAO.getProducts();

        // we use Arrays.equals here instead of simply using assertEqual
        // since the two arrays that we ahve will have the same objects in them
        // but each object will have a different id (memory thing) thus a simple
        // object.equals
        // will fail in this case since the two arrays are not exactly the same
        assertTrue(Arrays.equals(products, testProducts));

    }

    @Test
    public void testsearchProducts() throws IOException {
        // Searches for 'Cow' in the products
        Product[] products = inventoryDAO.searchProducts("Cow");
        
        // Affirms that a found product is the same as the 'Cow Beans' product
        assertEquals(products[0], testProducts[2]);
    }

    @Test
    public void testupdateProductFail() throws IOException {
        
        // Product to be updated
        Product testProduct = new Product("Bean1", 4, 3, 5);
        
        // Result of the updateProduct function
        // Should return null if no product was found
        // Should return the updated product if one was updated
        Product result = inventoryDAO.updateProduct(testProduct);

        // Ensures the result is null
        assertEquals(null, result);
    }

    @Test
    public void testupdateProductPass() throws IOException {

        // Product to be updated
        Product testProduct = new Product("Bean1", 2, 4, 10);

        // Result of the updateProduct function
        // Should return null if no product was found
        // Should return the updated product if one was updated
        Product result = inventoryDAO.updateProduct(testProduct);

        // Ensures the result is the same as the product input
        assertEquals(testProduct, result);
    }
    @Test
    public void testCreateProducts() throws IOException {
        // Setup
        Product test = new Product("Green-bean",11, 30, 3.00);

        // Invoke
        Product result = assertDoesNotThrow(() -> inventoryDAO.createProduct(test),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Product actual = inventoryDAO.getProduct(test.getID());
        assertEquals(actual.getID(),test.getID());
        assertEquals(actual.getName(),test.getName());
        assertEquals(actual.getAmount(),test.getAmount());
        assertEquals(actual.getPrice(),test.getPrice());
    }


    @Test
    public void testdeleteProductFail() throws IOException{

        // Product to be deleted
        int testID = 4;

        // Result of the deleteProduct functino
        // Should return false if no product was found
        // Should return true if product was deleted
        Boolean result = inventoryDAO.deleteProduct(testID);

        // Ensures the result is false
        assertEquals(false, result);
    }

    @Test
    public void testdeleteProductPass() throws IOException{
        
        // Product to be deleted
        int testID = 0;

        // Result of the deleteProduct functino
        // Should return false if no product was found
        // Should return true if product was deleted
        Boolean result = inventoryDAO.deleteProduct(testID);
        
        // Ensures the result is true
        assertEquals(true, result);
    }
}
