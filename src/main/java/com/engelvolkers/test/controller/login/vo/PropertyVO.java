/**
 * 
 */
package com.engelvolkers.test.controller.login.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used to return the property details
 * 
 * @author Guilherme Vital
 *
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PropertyVO {

    private String id;

    private String name;

    private String price;

    private String image;

}
