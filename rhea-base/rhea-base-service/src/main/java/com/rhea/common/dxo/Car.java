package com.rhea.common.dxo;

import lombok.Data;
import lombok.ToString;
import org.mapstruct.factory.Mappers;

@Data
@ToString
public class Car {
    private String name;

    public static void main(String[] args) {
        Car car = new Car();
        car.setName("11111");
        CarMapper mapper = Mappers.getMapper(CarMapper.class);
        CarDto dto = mapper.toDto(car);
        System.out.println(dto);
    }
}
