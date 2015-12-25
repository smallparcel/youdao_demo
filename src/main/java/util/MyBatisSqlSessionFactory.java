package util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by BH00350 on 2015/12/25.
 */
//MyBatisSqlSessionFactory����SqlSessionUtil������spring�����ļ�����SqlSessionUtil���ص���mybatis�����ļ�
public class MyBatisSqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSessionFactory getSqlSessionFactory(){
        if (sqlSessionFactory==null) {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
        }
        return  sqlSessionFactory;
    }

    public static SqlSession getSqlSession(boolean isAutoCommit){
        return getSqlSessionFactory().openSession(isAutoCommit);
    }
}
