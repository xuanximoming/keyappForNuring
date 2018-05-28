package com.nursing.servlet;

import com.nursing.data.SystemConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/22.
 * 版权信息
 */
public class CopyrightServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result="{\n" +
                "    \"status\": 1,\n" +
                "    \"verinfo\": \"%s\"\n" +
                "}";
        String copyright=SystemConfig.getProperties().getProperty("system.verinfos");
        if (copyright==null||copyright.equals("")){
            copyright="移动护理";
        }
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write(String.format(result,copyright));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
