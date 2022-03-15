package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.ShoppingCart;
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
    public ShoppingCartController(ShoppingCartDao shoppingCartDAO) {
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
    @GetMapping("/{id}")
    public ResponseEntity<Product[]> getCart(@PathVariable Customer username) {
        try {
            ShoppingCart cartFound = shoppingCartDao.getShoppingCart(username);
            if (cartFound == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Product>(cartFound, HttpStatus.OK);
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
    @DeleteMapping("/{customer}/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Customer customer, @PathVariable int id) {
        LOG.info("DELETE /cart/customer=" + customer.getName + "/product/id=" + id);
        try {
            boolean deleted = shoppingCartDao.deleteProduct(customer, id);
            if (deleted) {
                return new ResponseEntity<Product>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{customer}")
    public ResonseEntity<Customer> clearCart(@PathVariable Customer customer){
        LOG.info("DELETE /cart/customer=" + customer.getName);
        try{
            boolean deleted = shoppingCartDAO.clearCart(customer);
            if(deleted){
                return new ResonseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResonseEntity<Customer>(customer, HttpStatus.NOT_FOUND);
            }
        }
        catch (IOException e){
            return new ResonseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{customer}/{product}")
    public ResponseEntity<Product> addProduct(@PathVariable Customer customer, @PathVariable Product product){
        try {
            Product result = shoppingCartDAO.addProduct(customer, product);
            if(result){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<Product>(product, HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}