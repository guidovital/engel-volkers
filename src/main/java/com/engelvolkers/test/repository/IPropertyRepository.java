package com.engelvolkers.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.engelvolkers.test.domain.entity.Property;

@Repository
public interface IPropertyRepository extends JpaRepository<Property, String> {

}
