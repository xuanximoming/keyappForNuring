package com.nursing.servlet;

import com.nursing.data.DatabaseConfig;
import com.nursing.data.db.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/17.
 * 数据库配置
 */
public class DBConfigServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String url=req.getParameter("url");
        String user=req.getParameter("user");
        String password=req.getParameter("password");
        DatabaseConfig.getProperties().setProperty("db.url",url);
        DatabaseConfig.getProperties().setProperty("db.user",user);
        DatabaseConfig.getProperties().setProperty("db.password",password);
        //数据库连接信息修改后，重置连接
        DBHelper.resetConnection();

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write("<script>alert(\"设置成功！\");window.location.href='apiset/data_set.jsp';</script>");
    }
}
