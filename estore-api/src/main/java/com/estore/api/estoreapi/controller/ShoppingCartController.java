package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.persistence.Inventory.InventoryDAO;

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

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCartDAO shoppingCartDao;
    
    /**
     * Deletes a {@linkplain Customer customer} with the given customer
     * 
     * @param customer The customer of the {@linkplain Customer Customer}to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{customer}")
    public ResponseEntity<Customer> clearShoppingCart(@PathVariable Customer customer){
        LOG.info("DELETE /cart/customer=" + customer.getUsername());
        try {
            boolean deleted = inventoryDao.deleteProduct(id);
            if (deleted) {
                return new ResponseEntity<Product>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Gets a {@link Product} with the provided username
     * 
     * @param username A username that is used to get a product that corresponds to that username
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
    public ResponseEntity<Product[]> getShoppingCart(@PathVariable Customer username) {
        try {
            Product[] cartGot = shoppingCartDao.getShoppingCart(username);
            if (cartGot == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        //Need to implement
    }
    /**
     * Deletes a {@linkplain Customer customer} with the given customer
     * 
     * @param customer The customer of the {@linkplain Customer Customer}to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{customer}/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Customer customer, @PathVariable int id) {
        LOG.info("DELETE /cart/customer=" + customer.getUsername() + "/product/id=" + id);
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
    
    
}
