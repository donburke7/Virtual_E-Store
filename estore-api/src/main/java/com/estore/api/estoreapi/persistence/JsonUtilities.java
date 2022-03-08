package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

@Component
public class JsonUtilities {

    ObjectMapper objectMapper;

    /**
     * 
     */
    public JsonUtilities() {
        objectMapper = new ObjectMapper();
    }

    /**
     * 
     * @param <T>
     * @param content
     * @param classType
     * @return
     * @throws IOException
     */
    public <T> T DeserializeObject(String content, Class<T> classType) throws IOException {
        
        try {
            T obj = objectMapper.readValue(content, classType);
            return obj;
        } catch (JsonMappingException m) {
            return null;
        } catch (JsonProcessingException p) {
            return null;
        } 
        

    }
    
    /**
     * 
     * @param <T>
     * @param filename
     * @param content
     * @throws StreamWriteException
     * @throws DatabindException
     * @throws IOException
     */
    public <T> void SerializeObject(String filename, T content)
            throws StreamWriteException, DatabindException, IOException {
        try {
            objectMapper.writeValue(new File(filename), content);

        } catch (StreamWriteException s) {
        } catch (DatabindException d) {
        } catch (IOException o) {
        } 
    }

}
