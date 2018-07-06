package com.rhea.common.base;

/**
 * 实现BaseService抽象类
 * Created by ZhangShuzheng on 2017/01/07.
 */
public abstract class BaseServiceImpl<Record> implements BaseService<Record> {

	public BaseMapper<Record> mapper;
}