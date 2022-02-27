package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the Inventory File DAO class
 * 
 * @author Donald Burke
 */
public class InventoryFileDAOTest {
    InventoryFileDAO inventoryFileDAO;
    Product[] testInventory;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupHeroFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testInventory = new Product[3];
        testInventory[0] = new Product("Magic Bean", 99, 100, 489.10);
        testInventory[1] = new Product("Not Magic Bean", 100, 200, 489.20);
        testInventory[2] = new Product("Relatively Magic Bean", 101, 300, 489.30);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper.readValue(new File("doesnt_matter.txt"), Product[].class))
            .thenReturn(testInventory);
        inventoryFileDAO = new InventoryFileDAO("doesnt_matter.txt", mockObjectMapper);
    }

    @Test
    @Tag("Persistence-tier")
    public void testGetProduct() {
        // Invoke
        Product inventory;
        try {
            inventory = inventoryFileDAO.getProduct(100);
            // Analzye
            assertEquals(inventory, testInventory[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
