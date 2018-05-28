package com.nursing.servlet;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.SQLStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/16.
 * 护理历史
 */
public class NursingDescListServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String pid=req.getParameter("bid");
        String vid=req.getParameter("vid");
        String hldate=req.getParameter("hldate");
        String cls=req.getParameter("cls");
        resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.nursingDescSQL(pid,vid,hldate,cls)));
    }

}
