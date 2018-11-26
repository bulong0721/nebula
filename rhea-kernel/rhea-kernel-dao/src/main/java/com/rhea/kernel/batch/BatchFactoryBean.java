package com.rhea.kernel.batch;

import com.rhea.kernel.mapper.BatchMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import tk.mybatis.spring.mapper.MapperFactoryBean;

/**
 * @author xubulong
 * @version V1.0 created at: 2018/11/5
 */
public class BatchFactoryBean<T> extends MapperFactoryBean<T> {
    private SqlSession batchSession;

    public BatchFactoryBean() {
    }

    public BatchFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
    }

    @Override
    public SqlSession getSqlSession() {
        if (null != batchSession) {
            return batchSession;
        }
        return super.getSqlSession();
    }

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
        if (needBatch()) {
            batchSession = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        }
    }

    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate template) {
        super.setSqlSessionTemplate(template);
        if (needBatch()) {
            batchSession = new SqlSessionTemplate(template.getSqlSessionFactory(), ExecutorType.BATCH);
        }
    }

    protected boolean needBatch() {
        return BatchMapper.class.isAssignableFrom(getMapperInterface());
    }
}
