package com.engelvolkers.test.service;

import java.util.List;

import com.engelvolkers.test.domain.entity.Property;

/**
 * This class is the service layer for the Property entity
 * 
 * @author Guilherme Vital
 *
 */
public interface IPropertyService {

    /**
     * Returns all properties
     * 
     * @return List<Property>
     */
    List<Property> findAll();

    /**
     * Returns a property by its propertyId
     * If it does not exist, throws an exception
     * 
     * @param propertyId
     * @return Property
     */
    Property findPropertyById(String propertyId);
}
