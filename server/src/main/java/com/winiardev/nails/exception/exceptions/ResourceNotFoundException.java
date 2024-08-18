package com.winiardev.nails.exception.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    /**
     * Exception used in case of trying to access non existing data
     * 
     * @param resourceName - Name of wanted resource eg. "Account"
     * @param fieldName    - Name of data used to find resource
     * @param fieldValue   - Value of data used to find resource
     */
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
