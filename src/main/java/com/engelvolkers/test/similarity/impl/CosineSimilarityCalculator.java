/**
 * 
 */
package com.engelvolkers.test.similarity.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.engelvolkers.test.domain.entity.Property;
import com.engelvolkers.test.domain.entity.User;
import com.engelvolkers.test.service.IPropertyService;
import com.engelvolkers.test.service.IUserService;
import com.engelvolkers.test.similarity.ISimilarityCalculator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class calculates the cosine similarity between the main property and all
 * the other properties
 * 
 * @author Guilherme Vital
 *
 */
@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CosineSimilarityCalculator implements ISimilarityCalculator {

    private IPropertyService propertyService;
    private IUserService userService;

    /**
     * Calculates the cosine similarity between the main property and all the other
     * properties
     * 
     * @param property the main property
     * @return a list of properties sorted by the cosine similarity
     */
    @Override
    public List<Property> execute(Property property) {
        List<Property> otherProperties = propertyService.findAll();
        final List<User> users = userService.findAll();

        // remove the main property from the list of properties
        otherProperties.remove(property);

        // build the vector for the main property
        final int[] propertyVector = buildVector(users, property);

        // calculate the cosine similarity for each property, log the result, add it to
        // the property and sort the properties by the cosine similarity
        otherProperties.forEach(p -> {
            final int[] vector = buildVector(users, p);
            final Double similarity = calculateSimilarity(propertyVector, vector);
            log.info("Similarity between {} and {} is {}", property.getName(), p.getName(), similarity);
            p.setSimilarity(similarity);
        });
        Collections.sort(otherProperties, Comparator.comparing(Property::getSimilarity).reversed());

        return otherProperties;
    }

    /**
     * Builds a vector with the properties of the users
     * Property can not be null
     * Users can not be null
     * Users must have at least one value
     * 
     * @param users the list of users
     * @param prop  the property to be compared
     * @return the vector
     */
    protected static int[] buildVector(List<User> users, Property prop) {
        if (users == null) {
            throw new IllegalArgumentException("Users must not be null");
        }
        if (users.isEmpty()) {
            throw new IllegalArgumentException("Users must have at least one element");
        }
        if (prop == null) {
            throw new IllegalArgumentException("Property must not be null");
        }

        int[] vector = new int[users.size()];
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getProperties().contains(prop)) {
                vector[i] = 1;
            } else {
                vector[i] = 0;
            }
        }
        return vector;
    }

    /**
     * Calculates the cosine similarity between two vectors
     * vector1 and vector2 can not be null
     * vector1 must have the same size as vector2
     * vector1 and vector2 must have at least one value
     * 
     * @param vector1 the first vector
     * @param vector2 the second vector
     * @return the cosine similarity
     */
    protected static Double calculateSimilarity(int[] vector1, int[] vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Vectors must not be null");
        }
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must have the same size");
        }
        if (vector1.length == 0) {
            throw new IllegalArgumentException("Vectors must have at least one element");
        }

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            normA += Math.pow(vector1[i], 2);
            normB += Math.pow(vector2[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
