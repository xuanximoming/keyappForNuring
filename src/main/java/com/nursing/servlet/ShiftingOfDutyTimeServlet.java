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
 * 交接班时间表
 */
public class ShiftingOfDutyTimeServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*String result="[{\n" +
                "            \"BRID\": \"00133083\",\n" +
                "            \"ZYID\": \"1\",\n" +
                "            \"JIAOBANDATE\": \"2015-12-12\"\n" +
                "        }]";

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write(result);*/

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String pid=req.getParameter("bid");
        String vid=req.getParameter("vid");
        resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.shiftingOfDutyTimes(pid,vid)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
