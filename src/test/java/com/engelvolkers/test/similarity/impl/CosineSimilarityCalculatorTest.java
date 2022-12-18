package com.engelvolkers.test.similarity.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.engelvolkers.test.domain.entity.Property;
import com.engelvolkers.test.domain.entity.User;

/**
 * This class is used to test the LoginVO
 * 
 * @author Guilherme Vital
 *
 */
@ExtendWith(MockitoExtension.class)
class CosineSimilarityCalculatorTest {

    @Test
    void whenVectorsAreCorrect() {
        var vector1 = new int[] { 1, 2, 3, 4, 5 };
        var vector2 = new int[] { 1, 2, 3, 4, 1 };
        Double result = CosineSimilarityCalculator.calculateSimilarity(vector1, vector2);
        assertEquals(0.8476290894688449, result);
    }

    @Test
    void whenVectorsHaveDiffentLength() {
        var vector1 = new int[] { 1, 2, 3, 4, 5 };
        var vector2 = new int[] { 1, 2, 3, 4, 1, 1 };
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> CosineSimilarityCalculator.calculateSimilarity(vector1, vector2));
        assertEquals("Vectors must have the same size", result.getMessage());
    }

    @Test
    void whenVectorsHasNoElements() {
        var vector1 = new int[] {};
        var vector2 = new int[] {};
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> CosineSimilarityCalculator.calculateSimilarity(vector1, vector2));
        assertEquals("Vectors must have at least one element", result.getMessage());
    }

    @Test
    void whenVectorIsNull() {
        var vector1 = new int[] {};
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> CosineSimilarityCalculator.calculateSimilarity(vector1, null));
        assertEquals("Vectors must not be null", result.getMessage());
    }

    @Test
    void whenVectorIsBuiltSuccessfullyWithOneInAllValues() {
        Property property = Property.builder().id("property1")
                .build();
        Set<Property> setOfProperties = Set.of(property);
        List<User> users = List.of(User.builder().email("user1@junit.com").properties(setOfProperties).build(),
                User.builder().email("user2@junit.com").properties(setOfProperties).build(),
                User.builder().email("user3@junit.com").properties(setOfProperties).build());
        var vector = CosineSimilarityCalculator.buildVector(users, property);
        assertEquals(3, vector.length);
        assertEquals(1, vector[0]);
        assertEquals(1, vector[1]);
        assertEquals(1, vector[2]);
    }

    @Test
    void whenVectorIsBuiltSuccessfullyWithZeroInAllValues() {
        Property rightProp = Property.builder().id("property1")
                .build();
        Property wrongProp = Property.builder().id("wrongproperty")
                .build();
        Set<Property> setOfProperties = Set.of(wrongProp);
        List<User> users = List.of(User.builder().email("user1@junit.com").properties(setOfProperties).build(),
                User.builder().email("user2@junit.com").properties(setOfProperties).build(),
                User.builder().email("user3@junit.com").properties(setOfProperties).build());
        var vector = CosineSimilarityCalculator.buildVector(users, rightProp);
        assertEquals(3, vector.length);
        assertEquals(0, vector[0]);
        assertEquals(0, vector[1]);
        assertEquals(0, vector[2]);
    }

    @Test
    void whenUsersAreNull() {
        Property property = Property.builder().id("property1")
                .build();
        List<User> users = null;
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> CosineSimilarityCalculator.buildVector(users, property));
        assertEquals("Users must not be null", result.getMessage());
    }

    @Test
    void whenUsersHasNoElements() {
        Property property = Property.builder().id("property1")
                .build();
        List<User> users = List.of();
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> CosineSimilarityCalculator.buildVector(users, property));
        assertEquals("Users must have at least one element", result.getMessage());
    }

    @Test
    void whenPropertyIsNull() {
        Property property = null;
        Set<Property> setOfProperties = Set.of();
        List<User> users = List.of(User.builder().email("user1@junit.com").properties(setOfProperties).build());
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> CosineSimilarityCalculator.buildVector(users, property));
        assertEquals("Property must not be null", result.getMessage());
    }
}