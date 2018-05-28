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
 * 医嘱信息
 */
public class DocAdviceListServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String pid=req.getParameter("bid");
        String vid=req.getParameter("vid");
        String ot=req.getParameter("ot");
        String cl=req.getParameter("cl");

        if (ot!=null&&ot.equals("all")){
            ot=null;
        }
        if (cl!=null&&cl.equals("all")){
            cl=null;
        }
        StringBuffer jsonBuffer=new StringBuffer();
        String orderCLJson="[{\"name\":\"长期\",\"code\": 0,\"color\": \"#FF6600\"},{\"name\": \"临时\",\"code\": 1,\"color\": \"#000000\"}]";
        jsonBuffer.append("{");
        jsonBuffer.append("\"orderType\": ");
        jsonBuffer.append(DBDirectFormatJson.select(SQLStatement.orderTypeSQL()).toLowerCase());
        jsonBuffer.append(",\"orderCL\":");
        jsonBuffer.append(orderCLJson);
        jsonBuffer.append(",\"orderList\":");
        jsonBuffer.append(DBDirectFormatJson.select(SQLStatement.orderListSQL(pid,vid,ot,cl)));
        jsonBuffer.append("}");
        resp.getWriter().write(jsonBuffer.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
