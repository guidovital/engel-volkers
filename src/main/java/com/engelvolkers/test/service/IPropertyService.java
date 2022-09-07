package com.engelvolkers.test.service;

import java.util.List;

import com.engelvolkers.test.domain.entity.Property;

public interface IPropertyService {

	List<Property> findAll();

	Property findPropertyById(String propertyId);

}
