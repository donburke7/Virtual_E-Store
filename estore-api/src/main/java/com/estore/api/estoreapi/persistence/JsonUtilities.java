package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

/**
 * A class that handles serialization and deserailization by utilizing generic methods
 * 
 * @author Alen Van
 */
@Component
public class JsonUtilities {

    ObjectMapper objectMapper;

    /**
     * The constructor of the json utilities class, it initializes an object mapper
     */
    public JsonUtilities() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Deserializes a json string into an inputted object type
     * 
     * @param <T> the object type to deserialize to
     * @param content the json string to deserialize
     * @param classType the object type to deserialize to
     * 
     * @return A deserialization of the json string
     * 
     * @throws IOException if there was an error 
     */
    public <T> T DeserializeObject(String content, Class<T> classType) throws IOException {
        
        try {
            T obj = objectMapper.readValue(content, classType);
            return obj;
        } catch (JsonMappingException m) {
            throw new IOException(m.getMessage());
        } catch (JsonProcessingException p) {
            throw new IOException(p.getMessage());
        } 
        

    }
    
    /**
     * Serializes a given object into a fle
     * 
     * @param <T> the objectype to serialize
     * @param filename the name of the file to serialize to
     * @param content the object to serialize
     * 
     * @throws IOException if an error occured
     */
    public <T> void SerializeObject(String filename, T content) throws IOException {
        try {
            objectMapper.writeValue(new File(filename), content);

        } catch (StreamWriteException s) {
            throw new IOException(s.getMessage());
        } catch (DatabindException d) {
            throw new IOException(d.getMessage());
        } catch (IOException o) {
            throw new IOException(o.getMessage());
        } 
    }

}
