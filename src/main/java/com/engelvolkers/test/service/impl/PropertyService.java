/**
 * 
 */
package com.engelvolkers.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engelvolkers.test.domain.entity.Property;
import com.engelvolkers.test.exception.ObjectNotFoundException;
import com.engelvolkers.test.repository.IPropertyRepository;
import com.engelvolkers.test.service.IPropertyService;

import lombok.AllArgsConstructor;

/**
 * @author Guilherme Vital
 *
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PropertyService implements IPropertyService {
	
	private IPropertyRepository repository;
	
	@Override
	public List<Property> findAll() {
		return repository.findAll();
	}

	@Override
	public Property findPropertyById(String propertyId) {
		return repository.findById(propertyId).orElseThrow(() -> new ObjectNotFoundException("No property with that propertyId"));
	}

}
