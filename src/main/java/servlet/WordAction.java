package servlet;

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
        //��ʽһ��jdbc
        //��ʽ����mybatis
        SqlSession sqlSession=SqlSessionUtil.getSqlSession(true);
        sqlSession.delete("word.delete", getWord(req));
        //sqlSession.commit();
        sqlSession.close();
        resp.sendRedirect("word?action=query");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession=SqlSessionUtil.getSqlSession(true);
        sqlSession.update("word.update", getWord(req));
        //sqlSession.commit();
        sqlSession.close();
        resp.sendRedirect("word?action=query");
    }


    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= SqlSessionUtil.getSqlSession(true);
        sqlSession.insert("word.add",getWord(req));
        sqlSession.commit();
        sqlSession.close();
        resp.sendRedirect("word?action=query");
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession=SqlSessionUtil.getSqlSession(false);
        req.getSession().setAttribute("word",sqlSession.selectOne("word.search",getWord(req)));
        //sqlSession.commit();
        sqlSession.close();
        resp.sendRedirect("edit.jsp");
    }

    /*  1/List�п�������κζ��󣬰����Լ�������µ��ࡣ
        2��List��һ���ӿڣ�����ʵ��������Ҫʵ����һ��ArrayList����LinkedList  List myList = new ArrayList();
        3��ʹ��myList.add(�κζ���);�Ϳ��Խ�������ˡ�4��ȡֵ��ʱ��myList.get(����);ȡ������ֵ����Object��ʹ��ʱ��Ҫ����ת����
        ArrayList���Ǵ�˵�еĶ�̬���飺��̬�����Ӻͼ���Ԫ��
        List �����еĶ�����һ����˳���ŷţ���������ݿ����ظ���List�ӿ�ʵ�ֵ��ࣺArrayList(ʵ�ֶ�̬����)�� Vector��ʵ�ֶ�̬���飩 ��LinkedList��ʵ�������� Stack��ʵ�ֶ�ջ��*/
    private void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession=SqlSessionUtil.getSqlSession(false);//��ѯ���Բ���commit
        req.getSession().setAttribute("words",sqlSession.selectList("word.query"));
        sqlSession.close();
        resp.sendRedirect("index.jsp");
    }

    private Word getWord(HttpServletRequest req) {
        Integer id = null;
        if (req.getParameter("id") != null) {
            id = Integer.parseInt(req.getParameter("id"));
        }
        return new Word(id, req.getParameter("english"), req.getParameter("chinese"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
