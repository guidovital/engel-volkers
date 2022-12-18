package com.engelvolkers.test.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import com.engelvolkers.test.controller.login.vo.PropertyVO;
import com.engelvolkers.test.controller.login.vo.PropertyWithRecomendationListVO;
import com.engelvolkers.test.domain.entity.Property;

/**
 * This class is used to map the Property entity to the PropertyVO or
 * PropertyWithRecomendationListVO
 * 
 * @author Guilherme Vital
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EngelVolkersPropertyMapper {

    PropertyVO map(Property prop);

    @Mapping(target = "properties", ignore = true)
    PropertyWithRecomendationListVO mapToRecomendation(Property prop);

    List<PropertyVO> map(List<Property> prop);

}
