package com.bdc.dao;

import java.io.Serializable;

/**
 * Created by BH00350 on 2015/12/29.
 */

    //���巺�ͽӿ�
public interface GenericDao<T extends Serializable,ID extends Serializable> {
    void add(T t);        //ע��һ���û�
    void remove(ID id);
    T queryOne(T t);
}
