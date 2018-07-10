package com.rhea.common.base;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 实现BaseService抽象类
 * Created by ZhangShuzheng on 2017/01/07.
 */
public abstract class BaseServiceImpl<Record> implements BaseService<Record> {

	public BaseMapper<Record> mapper;

	@Override
	public int countByExample(Example example) {
		return 0;
	}

	@Override
	public int deleteByExample(Example example) {
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(Record record) {
		return 0;
	}

	@Override
	public int insertSelective(Record record) {
		return 0;
	}

	@Override
	public List<Record> selectByExampleWithBLOBs(Example example) {
		return null;
	}

	@Override
	public List<Record> selectByExample(Example example) {
		return null;
	}

	@Override
	public List<Record> selectByExampleWithBLOBsForStartPage(Example example, Integer pageNum, Integer pageSize) {
		return null;
	}

	@Override
	public List<Record> selectByExampleForStartPage(Example example, Integer pageNum, Integer pageSize) {
		return null;
	}

	@Override
	public List<Record> selectByExampleWithBLOBsForOffsetPage(Example example, Integer offset, Integer limit) {
		return null;
	}

	@Override
	public List<Record> selectByExampleForOffsetPage(Example example, Integer offset, Integer limit) {
		return null;
	}

	@Override
	public Record selectFirstByExample(Example example) {
		return null;
	}

	@Override
	public Record selectFirstByExampleWithBLOBs(Example example) {
		return null;
	}

	@Override
	public Record selectByPrimaryKey(Integer id) {
		return null;
	}

	@Override
	public int updateByExampleSelective(Record record, Example example) {
		return 0;
	}

	@Override
	public int updateByExampleWithBLOBs(Record record, Example example) {
		return 0;
	}

	@Override
	public int updateByExample(Record record, Example example) {
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(Record record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Record record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Record record) {
		return 0;
	}

	@Override
	public int deleteByPrimaryKeys(String ids) {
		return 0;
	}
}