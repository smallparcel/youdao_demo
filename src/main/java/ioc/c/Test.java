package ioc.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2015/12/19.
 */
public class Test {
    public static void main(String[] args) {
       /*
       //��ʽһ��������
       Business business=new Business(new USBWriter());
        business.write();*/

        //��ʽ����getter����
        ApplicationContext applicationContext =new ClassPathXmlApplicationContext("applicationContext.xml");
        Business business= (Business) applicationContext.getBean("business");
        business.write();
    }
}
