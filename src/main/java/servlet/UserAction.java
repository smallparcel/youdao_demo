package servlet;

import model.User;
import model.Word;
import org.apache.ibatis.session.SqlSession;
import util.DB;
import util.SqlSessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by BH00350 on 2015/12/7.
 */
@WebServlet("/user")
public class UserAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");     //req.getParameter������ȡ�û���д�ı�����
        if (action.equals("signup")) {
            signup(req, resp);                   //alt + enter��������
        }
        if (action.equals("login")) {
            login(req, resp);
        }
        if (action.equals("logout")) {
            logout(req,resp);
        }
        if (action.equals("check")) {
            check(req,resp);
        }

    }
    //����Ƿ�����ͬ���û�������
    private void check(HttpServletRequest req, HttpServletResponse resp) {
        String username=req.getParameter("username");
        System.out.println(username);
    }

    //�˳�
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect("default.jsp");
        /*String sql="select * from user where username=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            preparedStatement=DB.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,"username");
            resultSet=preparedStatement.executeQuery();
            resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(resultSet,preparedStatement);
        }*/
    }

    //��¼
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;     // resultSet�����
        try {
            preparedStatement=DB.getConnection().prepareStatement("SELECT * FROM user WHERE username=? and password=?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                req.getSession().setAttribute("username", username);
                resp.sendRedirect("word?action=query");
            } else {
                req.setAttribute("message","error.");
                req.getRequestDispatcher("default.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(resultSet,preparedStatement);
        }
        /*SqlSession sqlSession=SqlSessionUtil.getSqlSession();
        User user = new User(null,req.getParameter("username"),req.getParameter("password"));
        sqlSession.selectOne("user.login",user);
        sqlSession.commit();
        sqlSession.close();
        resp.sendRedirect("word?action=query");*/
    }

    //ע��
    private void signup(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
       /* String username = req.getParameter("username");  //req.getParameter������ȡ�û���д�ı�����
        String password=req.getParameter("password");
        PreparedStatement preparedStatement=null;         // statement����ִ��SQL���
        try {
            preparedStatement= DB.getConnection().prepareStatement("INSERT INTO user VALUES (NULL,?,?)");  //�������ݿ�
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
            resp.sendRedirect("default.jsp");           //���쳣
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.close(null,preparedStatement);       //�ر����ݿ�
        }*/

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        sqlSession.insert("user.signup", new User(null, req.getParameter("username"), req.getParameter("password")));
        sqlSession.commit();
        sqlSession.close();
        resp.sendRedirect("default.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
