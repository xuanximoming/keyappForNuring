package com.nursing.servlet;


import com.nursing.data.SystemConfig;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/21.
 */
public class CheckVersionServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("appName", SystemConfig.getProperties().getProperty("version.appName"));
        jsonObject.put("appDescription",SystemConfig.getProperties().getProperty("version.appDescription"));
        jsonObject.put("packageName",SystemConfig.getProperties().getProperty("version.packageName"));
        jsonObject.put("versionCode",SystemConfig.getProperties().getProperty("version.versionCode"));
        jsonObject.put("versionName",SystemConfig.getProperties().getProperty("version.versionName"));
        jsonObject.put("apkUrl",SystemConfig.getProperties().getProperty("version.apkUrl"));
        resp.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


}
