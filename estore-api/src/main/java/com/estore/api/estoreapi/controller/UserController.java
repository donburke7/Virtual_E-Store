package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.model.Users.Customer;
import com.estore.api.estoreapi.model.Users.User;
import com.estore.api.estoreapi.persistence.User.UserDAO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the REST API requests for the Inventory resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST
 * API
 * method handler to the Spring framework
 * 
 * @author Team jadin
 */

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private UserDAO userDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param userDAO The {@link InventoryDAO Inventory Data Access Object} to
     *                     perform CRUD operations
     *                     <br>
     *                     This dependency is injected by the Spring Framework
     */
    public UserController(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    /**
     * Gets a {@link User} with the provided username
     * 
     * @param username A username that is used to get a user that corresponds to that name
     * 
     * @return A ResonseEntity with the {@link User user} that was obtained
     *         from the username
     *         and a HTTP status code OK
     *         A ResponseEntity with HTTP status of NOT_FOUND if no user was
     *         found
     *         A ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{username}")
    public ResponseEntity<String> getUser(@PathVariable String username) {
        try {
            User userFound = userDao.getUser(username);
            if (userFound == null) {
                return new ResponseEntity<String>(username, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Deletes a {@linkplain User user} with the given username
     * 
     * @param username The username of the {@linkplain User user} to be deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        LOG.info("DELETE /user=" + username);
        try {
            boolean deleted = userDao.deleteUser(username);
            if (deleted) {
                return new ResponseEntity<String>(username, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(username, HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{username}")
    public ResponseEntity<User> addUser(@PathVariable String username){
        try {
            User result = userDao.addUser(username);
            if(result != null){
                return new ResponseEntity<User>(result, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}