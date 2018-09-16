package com.nursing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.SQLStatement;
import com.nursing.http.HttpUtil;

/**
 * Created by Jack on 2015/12/14. 病人列表
 */
public class PatientListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String curpage = req.getParameter("curpage");
		String Url = "http://api.yytianqi.com/forecast7d?city=CH010100&key=a3fv86n9tnvcl1bg";
		String result = null;
		result = HttpUtil.HttpGet(Url);
		if (uid != null && curpage != null) {
			if (curpage.equals("1")) {
				resp.setHeader("Content-type", "text/html;charset=UTF-8");
				resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.patientListSQL(uid)));
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
