package servlet;

import model.Word;
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
 * Created by BH00350 on 2015/12/9.   ���EncodingFilter�࣬���������������������
 */
@WebServlet(urlPatterns = "/word")
public class WordAction extends HttpServlet {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");  //req.getParameter������ȡ�û���д�ı�����
        if (action.equals("add")) {
            add(req,resp);
        }
        if (action.equals("query")) {
            query(req, resp);
        }
        if (action.equals("search")) {
            search(req,resp);
        }
        if (action.equals("update")) {
            update(req,resp);
        }
        if (action.equals("delete")) {
            delete(req,resp);
        }

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sql="delete from word where id=?";
        try {
            preparedStatement=DB.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(req.getParameter("id")));
            preparedStatement.executeUpdate();
            resp.sendRedirect("word?action=query");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sql="update word set english=?,chinese=? where id=?";
        try {
            preparedStatement=DB.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,req.getParameter("english"));
            preparedStatement.setString(2,req.getParameter("chinese"));
            preparedStatement.setInt(3, Integer.parseInt(req.getParameter("id")));
            preparedStatement.executeUpdate();
            resp.sendRedirect("word?action=query");
        } catch (SQLException e) {
            e.printStackTrace();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(null,preparedStatement);
        }
    }
    private void search(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sql="select * from word where id=?";
        try {
            preparedStatement=DB.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(req.getParameter("id")));
            resultSet=preparedStatement.executeQuery();
            Word word=null;
            if (resultSet.next()) {
               word=new Word(resultSet.getInt("id"),resultSet.getString("english"),resultSet.getString("chinese"));
            }
            req.getSession().setAttribute("word",word); //request.getSession().setAttribute(��������,��ֵ);��δ������˼���ǣ���ȡsession����,Ȼ���Ҫ�󶨶���/ֵ �ﶨ��session�����ϣ��û���һ�λỰ����һ��session����
            resp.sendRedirect("edit.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet,preparedStatement);
        }
    }

    /*  1/List�п�������κζ��󣬰����Լ�������µ��ࡣ
        2��List��һ���ӿڣ�����ʵ��������Ҫʵ����һ��ArrayList����LinkedList  List myList = new ArrayList();
        3��ʹ��myList.add(�κζ���);�Ϳ��Խ�������ˡ�4��ȡֵ��ʱ��myList.get(����);ȡ������ֵ����Object��ʹ��ʱ��Ҫ����ת����
        ArrayList���Ǵ�˵�еĶ�̬���飺��̬�����Ӻͼ���Ԫ��
        List �����еĶ�����һ����˳���ŷţ���������ݿ����ظ���List�ӿ�ʵ�ֵ��ࣺArrayList(ʵ�ֶ�̬����)�� Vector��ʵ�ֶ�̬���飩 ��LinkedList��ʵ�������� Stack��ʵ�ֶ�ջ��*/
    private void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sql="select * from word";
        try {
            preparedStatement=DB.getConnection().prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            List<Word> words = new ArrayList<>();
            while (resultSet.next()) {
                Word word = new Word(resultSet.getInt("id"),resultSet.getString("english"),resultSet.getString("chinese"));
                words.add(word);
            }
            req.getSession().setAttribute("words",words);
            resp.sendRedirect("index.jsp");
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
