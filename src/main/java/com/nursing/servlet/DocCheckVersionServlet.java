package com.nursing.servlet;


import com.nursing.data.DocConfig;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/21.
 */
public class DocCheckVersionServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("appName", DocConfig.getProperties().getProperty("version.appName"));
        jsonObject.put("appDescription",DocConfig.getProperties().getProperty("version.appDescription"));
        jsonObject.put("packageName",DocConfig.getProperties().getProperty("version.packageName"));
        jsonObject.put("versionCode",DocConfig.getProperties().getProperty("version.versionCode"));
        jsonObject.put("versionName",DocConfig.getProperties().getProperty("version.versionName"));
        jsonObject.put("apkUrl",DocConfig.getProperties().getProperty("version.apkUrl"));
        resp.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


}
