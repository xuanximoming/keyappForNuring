package com.nursing.servlet;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.SQLStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/16. 入院评估
 */
public class AdmissionAssessmentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		req.getParameter("bid");
		req.getParameter("vid");
		String itemid = req.getParameter("itemid");
		if (itemid != null && !itemid.equals("") && !itemid.equals("0")) {
			resp.getWriter()
					.write(DBDirectFormatJson.select(SQLStatement.admissionAssessmentSQL(itemid)).toLowerCase());
		} else {
			resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.admissionAssessmentSQL()).toLowerCase());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
