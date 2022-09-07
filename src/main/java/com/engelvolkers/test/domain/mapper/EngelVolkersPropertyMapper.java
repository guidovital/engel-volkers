package com.engelvolkers.test.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.engelvolkers.test.controller.login.vo.PropertyVO;
import com.engelvolkers.test.controller.login.vo.PropertyWithRecomendationListVO;
import com.engelvolkers.test.domain.entity.Property;

@Mapper(componentModel = "spring")
public interface EngelVolkersPropertyMapper {
	
	PropertyVO map(Property prop);
	
	PropertyWithRecomendationListVO mapToRecomendation(Property prop);
	
	List<PropertyVO> map(List<Property> prop);

}
