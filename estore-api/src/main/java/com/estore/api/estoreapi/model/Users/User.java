package com.estore.api.estoreapi.model.Users;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The parents class of the admin and customer class
 * This class represents any user that interacts with the website
 * 
 * @author Alen Van
 */
public class User {

    public static final String ADMIN = "admin";
    @JsonProperty("username")
    private String username;

    /**
     * Json constructor to initialize a {@linkplain User user} from a json
     * string
     * 
     * @param username the username of the {@link User user}
     * 
     */
    @JsonCreator
    public User(@JsonProperty("username") String username) {
        this.username = username;
    }

    /**
     * returns the string that represents the user, also known as the username
     * 
     * @return a string representing this user's username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object.getClass() == this.getClass() || object.getClass() == Customer.class) {
            User other = (User) object;
            if (other.username.equals(this.username)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
