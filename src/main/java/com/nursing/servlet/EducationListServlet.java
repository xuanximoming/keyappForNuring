package com.nursing.servlet;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.SQLStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/22.
 * 教育选项列表
 */
public class EducationListServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String result="[\n" +
                "    {\n" +
                "        \"itemid\": \"0701\",\n" +
                "        \"itemname\": \"入院教育\",\n" +
                "        \"xiaji\": 1,\n" +
                "        \"selected\": 0\n" +
                "    },\n" +
                "    {\n" +
                "        \"itemid\": \"0702\",\n" +
                "        \"itemname\": \"住院教育\",\n" +
                "        \"xiaji\": 1,\n" +
                "        \"selected\": 0\n" +
                "    },\n" +
                "    {\n" +
                "        \"itemid\": \"0703\",\n" +
                "        \"itemname\": \"出院指导\",\n" +
                "        \"xiaji\": 1,\n" +
                "        \"selected\": 0\n" +
                "    }\n" +
                "]";
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write(result);*/
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String itemid=req.getParameter("itemid");
        if (itemid!=null&&!itemid.equals("")&&!itemid.equals("0")){
            resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.educationListByItemID(itemid)).toLowerCase());
        }else {
            resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.educationList()).toLowerCase());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
