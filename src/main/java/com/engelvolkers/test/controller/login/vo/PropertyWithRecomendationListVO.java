/**
 * 
 */
package com.engelvolkers.test.controller.login.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Guilherme Vital
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class PropertyWithRecomendationListVO {
	
	private String name;

	private String details;

	private String price;

	private String image;
	
	private List<PropertyVO> properties;

}
