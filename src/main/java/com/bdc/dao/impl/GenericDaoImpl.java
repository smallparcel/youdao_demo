package com.bdc.dao.impl;

import com.bdc.dao.GenericDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by BH00350 on 2015/12/29.
 */
//ʵ�ַ��ͽӿ�GenericDao����д�ӿ�������з���
public class GenericDaoImpl<T extends Serializable,ID extends Serializable> implements GenericDao<T,ID> {

    //�˴�Ӧ�÷�����ƣ����ü���
    private String namespace;

    @Autowired
    protected SqlSessionFactory sqlSessionFactory;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl(){
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        namespace = modelClass.getSimpleName().toLowerCase().concat(".");
    }

    @Override
    public void add(T t) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        sqlSession.insert(namespace.concat("add"),t);
        sqlSession.close();
    }

    @Override
    public void remove(ID id) {
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        sqlSession.delete(namespace.concat("remove"),id);
        sqlSession.close();
    }

    @Override
    public T queryOne(T t) {
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        t=sqlSession.selectOne(namespace.concat("queryOne"),t);
        sqlSession.close();
        return t;                    //��������ΪT
    }
}
