package util;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * Created by BH00350 on 2015/12/7.
 */
public class DB {
    private static final String URL = "jdbc:mysql:///test";
    //private static final String URL = "jdbc:mysql://localhost:3306/test";     // URLָ��Ҫ���ʵ����ݿ���    ����̬�������ã�����Ҫʵ������
    private static final String USER = "root";          //MySQL����ʱ���û���    ctrl + shif + u ��Сдת��
    private static final String PASSWORD = "";          // MySQL����ʱ������

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {      //ctrl + alt + t  ��ȫ
            try {
                new Driver();           //������������  �׳��쳣���Ȳ��ӷֺţ�alt+enter���׳�����д�ֺ�
                connection=DriverManager.getConnection(URL,USER,PASSWORD); //�������ݿ�
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    // statement����ִ��SQL���   resultSet�����
    public static void close(ResultSet resultSet,PreparedStatement preparedStatement){
        if (resultSet != null) {
            try {
                resultSet.close();  //���쳣
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
