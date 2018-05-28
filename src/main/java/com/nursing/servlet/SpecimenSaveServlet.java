package com.nursing.servlet;

import java.io.IOException;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.DBHelper;
import com.nursing.data.db.SQLStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SpecimenListSaveServlet
 */
public class SpecimenSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SpecimenSaveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String result = "{\n" + "    \"status\": %s\n" + "}";

		int status = 0;
		String testno = request.getParameter("tsno");
		String uid = request.getParameter("uid");
		String act = request.getParameter("act");
		try {
			String sql = "SELECT * FROM SPECIMENT_FLOW_REC where TEST_NO = '%s'";
			String pgSelectJson = DBDirectFormatJson.select(String.format(sql, testno));

			if (pgSelectJson.equals("[]") && act.equals("1")) {
				status = DBHelper.insert(SQLStatement.specimenSaveSQL(testno, uid, act));
			} else if (act.equals("0")) {
				status = DBHelper.delete(String.format("delete from SPECIMENT_FLOW_REC where TEST_NO = '%s'", testno));
			} else if (act.equals("2")) {
				DBHelper.delete(String.format("delete from SPECIMENT_FLOW_REC where TEST_NO = '%s'", testno));
				status = DBHelper.insert(SQLStatement.specimenSaveSQL(testno, uid, act));
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("SpecimenListSaveServlet=" + e.toString());
		} finally {

		}

		response.getWriter().write(String.format(result, status));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
