package servlet;

import util.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BH00350 on 2015/12/9.
 */
@WebServlet(urlPatterns = "/word")
public class WordAction extends HttpServlet {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");  //req.getParameter������ȡ�û���д�ı�����
        if (action.equals("add")) {
            add(req,resp);
        }
        if (action.equals("query")) {
            query(req, resp);
        }

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sql = "INSERT INTO word VALUES(NULL, ?, ?)";
        try {
            preparedStatement= DB.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,req.getParameter("english"));
            preparedStatement.setString(2,req.getParameter("chinese"));
            preparedStatement.executeUpdate();
            resp.sendRedirect("word?action=query");
            //resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(null,preparedStatement);
        }
    }
    /*  1/List�п�������κζ��󣬰����Լ�������µ��ࡣ
        2��List��һ���ӿڣ�����ʵ��������Ҫʵ����һ��ArrayList����LinkedList  List myList = new ArrayList();
        3��ʹ��myList.add(�κζ���);�Ϳ��Խ�������ˡ�4��ȡֵ��ʱ��myList.get(����);ȡ������ֵ����Object��ʹ��ʱ��Ҫ����ת����
        ArrayList���Ǵ�˵�еĶ�̬���飺��̬�����Ӻͼ���Ԫ��
        List �����еĶ�����һ����˳���ŷţ���������ݿ����ظ���List�ӿ�ʵ�ֵ��ࣺArrayList(ʵ�ֶ�̬����)�� Vector��ʵ�ֶ�̬���飩 ��LinkedList��ʵ�������� Stack��ʵ�ֶ�ջ��*/
    private void query(HttpServletRequest req, HttpServletResponse resp) {
        String sql="select * from word";
        try {
            preparedStatement=DB.getConnection().prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(resultSet,preparedStatement);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
