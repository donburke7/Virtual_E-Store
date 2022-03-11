package com.estore.api.estoreapi.persistence.User;

import java.io.IOException;

import com.estore.api.estoreapi.model.Users.User;

/**
 * The interface for the UserDAO class
 * This interface holds the abstracted methods for the UserFileDAO class.
 * 
 * @author Alen Van
 */
public interface UserDAO {

    /**
     * Adds a {@linkplain User user} based on a given string passed in from the
     * frontend. Typically, with this program,
     * the getUser would be called first to check if the {@link User user}
     * exists, if not then we automatically create
     * a new user with the given string as their username. This all occurs from the
     * login screen.
     * 
     * @param username The string the corresponds with the new {@link User user}
     * 
     * @return The newly created {@link User user} object
     * 
     * @throws IOException If an issue occured whilst accessing the json files
     */
    User addUser(String username) throws IOException;

    /**
     * Given a string, search the database for a {@linkplain User user} that
     * corresponds with the string.
     * If no {@link User user} is found, then create a new {@link User user} by
     * calling addUser and pass the string as the username.
     * 
     * @param username a string that is used to find a {@link User user} that
     *                 correlates with the
     *                 string
     * 
     * @return the {@link User user} upon finding them, or upon creating them
     * 
     * @throws IOException If an issue occured whilst accessing the json files
     */
    User getUser(String username) throws IOException;

    /**
     * Given a string, search and delete a {@linkplain User user} that correlates
     * with the given string.
     *
     * @param username a string that is passed in that is used to find a {@link User
     *                 user} to
     *                 delete from the database
     * 
     * @return a boolean indicating whether or not the deletion was successful
     * 
     * @throws IOException If an issue occured whilst accessing the json files
     */
    Boolean deleteUser(String username) throws IOException;

    /**
     * Saves all of the {@linkplain User users} in the file into the corresponding json file,
     * this is the public method that is called upon by the ShoppingCartDAO class 
     * whenever is needed.
     * 
     * @return returns a boolean indicating that the save was successful
     * 
     * @throws IOException If an issue occured whilst accessing the json files
     */
    Boolean saveUsers() throws IOException;

}
