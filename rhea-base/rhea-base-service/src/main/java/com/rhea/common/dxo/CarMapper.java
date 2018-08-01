package com.rhea.common.dxo;

import org.mapstruct.Mapper;

@Mapper
public interface CarMapper {

    CarDto toDto(Car car);
}
