/**
 * 
 */
package com.engelvolkers.test.controller.login.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used to return the property details with the list of
 * recommended properties
 * 
 * @author Guilherme Vital
 *
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PropertyWithRecomendationListVO {

    private String name;

    private String details;

    private String price;

    private String image;

    private List<PropertyVO> properties;

}
