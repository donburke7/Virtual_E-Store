package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.Users.Customer;
import com.estore.api.estoreapi.model.Users.User;
import com.estore.api.estoreapi.persistence.User.UserFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the user file DAO
 * 
 * @author Alen Van
 */
@Tag("Persistence-tier")
public class UserFileDAOTest {

    UserFileDAO userFileDAO;

    //a mock shopping cart for a customer
    private TreeMap<Integer, Product> testProducts = new TreeMap<>(){{
        put(0, new Product("Green Beans", 0, 1, 1.00, new double[]{5.0}, 5.0));
        put(1,new Product("Red Beans", 1, 5, 2.00, new double[]{5.0}, 5.0));
        put(2,new Product("Pinto Beans", 2, 5, 1.50, new double[]{5.0}, 5.0));
    }};

    //a mock array of customers with an admin
    private Customer[] tCustomers = {
        new Customer("admin"),
        new Customer("empty"),
        new Customer("filled", new ShoppingCart(testProducts))
    };

    //the mock json file
    String filepath = "testUsers.json";
    
    /**
     * A setup before each test
     * create a new json file if it has not been created yet
     * populate the json file with mock data
     * initialize a new userFileDAO with the mock data
     * 
     * @throws IOException
     */
    @BeforeEach
    public void setup() throws IOException {

        //create the file if it is not present
        File file = new File(filepath);
        file.createNewFile();

        //serialize and initialize the userFileDAO
        JsonUtilities jsonUtilities = new JsonUtilities();
        jsonUtilities.SerializeObject(filepath, tCustomers);
        userFileDAO = new UserFileDAO(filepath, jsonUtilities);
    }

    @Test
    public void testaddUserCreated() throws IOException {

        //setup
        String expected = "testBob";
        
        //invoke
        User tUser = userFileDAO.addUser(expected);

        //analyze
        assertEquals(expected, tUser.getUsername());

    }

    @Test
    public void testgetUser() throws IOException {

        //setup
        Customer expected = tCustomers[0];
        
        //invoke
        User actual = userFileDAO.getUser(expected.getUsername());

        //analyze
        assertEquals(expected.getUsername(), actual.getUsername());
    }

    @Test
    public void testdeleteUser() throws IOException {

        //setup
        ObjectMapper obj = new ObjectMapper();
        ArrayList<Customer> testArray = new ArrayList<>();
        testArray.addAll(List.of(tCustomers));

        //invoke
        Customer deletedUser = testArray.get(1);
        //use this returned boolean to check that we received the correct boolean upon a successful deletion
        boolean success = userFileDAO.deleteUser(deletedUser.getUsername());
        testArray.remove(1);

        //setup analysis
        Customer[] expectedArray = new Customer[testArray.size()];
        testArray.toArray(expectedArray);

        //get expected and actual json string 
        String expectedString = obj.writeValueAsString(expectedArray);
        String actualString = Files.readString(Path.of(filepath));

        //analyze json strings
        assertEquals(expectedString, actualString);
        assertTrue(success);

    }
    
    @Test
    public void testdeleteUserFailed() throws IOException {

        //invoke
        //should return true if the deletion deleted something
        //should return false if deletion was impossible 
        boolean actual = userFileDAO.deleteUser("fail");

        //analyze 
        assertFalse(actual);

    }
}
