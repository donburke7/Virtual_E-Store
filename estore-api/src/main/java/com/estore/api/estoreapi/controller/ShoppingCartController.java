package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.persistence.User.ShoppingCartDAO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCartDAO shoppingCartDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param shoppingCartDAO The {@link InventoryDAO Inventory Data Access Object} to
     *                     perform CRUD operations
     *                     <br>
     *                     This dependency is injected by the Spring Framework
     */
    public ShoppingCartController(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDao = shoppingCartDAO;
    }

    /**
     * Gets a {@link Product} with the provided id
     * 
     * @param id An id that is used to get a product that corresponds to that id
     *           from the inventory
     * 
     * @return A ResonseEntity with the {@link Product product} that was obtained
     *         from the id
     *         and a HTTP status code OK
     *         A ResponseEntity with HTTP status of NOT_FOUND if no product was
     *         found
     *         A ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{username}")
    public ResponseEntity<Product[]> getCart(@PathVariable String username) {
        LOG.info("GET /shoppingcart/customer=" + username);
        try {
            Product[] cartFound = shoppingCartDao.getShoppingCart(username);
            if (cartFound == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Product[]>(cartFound, HttpStatus.OK);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Deletes a {@linkplain Product product} with the given id
     * 
     * @param id The id of the {@linkplain Product product}to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String username, @PathVariable int id) {
        LOG.info("DELETE /cart/customer=" + username + "/product/id=" + id);
        try {
            boolean deleted = shoppingCartDao.deleteProduct(username, id);
            if (deleted) {
                return new ResponseEntity<Product>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Boolean> clearCart(@PathVariable String username){
        LOG.info("DELETE /cart/customer=" + username);
        try{
            boolean deleted = shoppingCartDao.clearShoppingCart(username);
            if(deleted){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<Boolean>(deleted, HttpStatus.NOT_FOUND);
            }
        }
        catch (IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{username}")
    public ResponseEntity<Boolean> checkout(@PathVariable String username) {
        LOG.info("POST /shoppingcart/customer=" + username);
        try {
            boolean result = shoppingCartDao.checkout(username);
            if(result){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        catch (IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{username}/{amount}/{id}")
    public ResponseEntity<Integer> addProduct(@PathVariable String username, @PathVariable Integer amount, @PathVariable Integer id) {
        LOG.info("PUT /shoppingcart/customer=" + username + "/productID=" + id + "/amount=" + amount);
        try {
            Product result = shoppingCartDao.addProduct(username, id, amount);
            if(result != null){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<Integer>(id, HttpStatus.CONFLICT);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}