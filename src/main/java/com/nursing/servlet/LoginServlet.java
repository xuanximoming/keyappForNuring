package com.nursing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.SQLStatement;

/**
 * Created by Jack on 2015/12/12.
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		boolean isByAppLogin = false;

		String name = req.getParameter("name");
		String password = req.getParameter("password");

		if (name == null && req.getParameter("uname") != null) {
			name = req.getParameter("uname");
			password = req.getParameter("upwd");
			isByAppLogin = true;
		}
		// 通过客户端请求登录的，则返回json数据
		if (isByAppLogin) {
			String json = DBDirectFormatJson.select(SQLStatement.loginSQL(name.toUpperCase(), password));
			if (json != null && !json.equals("[]")) {
				resp.setHeader("Content-type", "text/html;charset=UTF-8");
				String uid = "";
				String utname = "";
				String keshi = "";
				JSONArray array = new JSONArray(json);
				for (int i = 0; i < array.length(); i++) {
					JSONObject item = array.optJSONObject(i);
					uid = item.optString("EMP_NO");
					utname = item.optString("NAME");
					keshi = item.optString("DEPT_NAME");

				}
				JSONObject appJson = new JSONObject();
				appJson.put("uid", uid);
				appJson.put("utname", utname);
				appJson.put("keshi", keshi);
				appJson.put("status", 1);
				appJson.put("timeout", 35000);
				resp.getWriter().write(appJson.toString());
			}
		}
		// 通过jsp登录界面请求登录的,返回html
		else {
			if (name != null && password != null && name.equals("admin") && password.equals("admin")) {
				resp.sendRedirect("home.html");
			} else {
				resp.setHeader("Content-type", "text/html;charset=UTF-8");
				resp.getWriter().write("<script>alert(\"登录失败，请重试！\");window.location.href='index.jsp';</script>");
			}
		}

	}

}
