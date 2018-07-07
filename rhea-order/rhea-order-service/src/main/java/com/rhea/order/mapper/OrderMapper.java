package com.rhea.order.mapper;

import com.rhea.common.base.BaseMapper;
import com.rhea.order.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}