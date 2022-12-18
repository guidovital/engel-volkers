package com.engelvolkers.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engelvolkers.test.domain.entity.Property;

/**
 * This class is used to access the Property table
 * 
 * @author Guilherme Vital
 *
 */
public interface IPropertyRepository extends JpaRepository<Property, String> {

}
