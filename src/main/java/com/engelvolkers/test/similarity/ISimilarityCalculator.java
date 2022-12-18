package com.engelvolkers.test.similarity;

import java.util.List;

import com.engelvolkers.test.domain.entity.Property;

/**
 * This interface is used to calculate the similarity between properties
 * 
 * @author Guilherme Vital
 *
 */
public interface ISimilarityCalculator {

    /**
     * This method is used to calculate the similarity between properties
     * 
     * @param property The property to be compared
     * @return List<Property> The list of properties with the similarity
     */
    List<Property> execute(Property property);
}
