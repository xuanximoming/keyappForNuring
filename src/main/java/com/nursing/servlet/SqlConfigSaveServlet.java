package com.nursing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nursing.data.SqlConfig;

/**
 * Servlet implementation class SqlConfigSaveServlet
 */
public class SqlConfigSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SqlConfigSaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		String sql = request.getParameter("sql");
		String strdo = request.getParameter("strdo");
		String result = SqlConfig.setSql(sql, strdo);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().write("<script>alert(\"" + result + "\");window.location.href='apiset/sql.jsp';</script>");
	}

}
