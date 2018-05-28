package com.nursing.servlet;

import com.nursing.data.SystemConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Jack on 2015/12/15.
 * 系统设置
 */
public class SystemConfigServlet extends HttpServlet{

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
        String apicode=req.getParameter("apicode");
        String apptimeout=req.getParameter("apptimeout");
        String verinfos=req.getParameter("verinfos");

        OutputStream outputStream = new FileOutputStream(this.getClass().getResource("/config.properties").getPath());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));

        SystemConfig.getProperties().setProperty("system.apicode",apicode);
        SystemConfig.getProperties().setProperty("system.apptimeout",apptimeout);
        SystemConfig.getProperties().setProperty("system.verinfos",verinfos);
        SystemConfig.getProperties().store(bw,null);
        outputStream.close();

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write("<script>alert(\"系统设置成功！\");window.location.href='setup3.jsp';</script>");
    }

}
