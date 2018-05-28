package com.nursing.servlet;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/23.
 */
public class DBOperationServlet extends HttpServlet{

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
        String action=req.getParameter("action");
        String sql=req.getParameter("sql");
        System.out.println("-----------sql----------\n"+sql);
        String result="{\n" +
                "    \"result\": 0\n" +
                "}";
        try {
            if (action.equals("select")){
                result= DBDirectFormatJson.select(sql);
                result="{\n" +
                        "    \"result\":"+result+"}";
            }else if (action.equals("insert")){
                int insert = DBHelper.insert(sql);
                result="{"+"\"result\":"+insert+"}";
            }else if (action.equals("delete")){
                int delete = DBHelper.delete(sql);
                result="{"+"\"result\":"+delete+"}";
            }else if (action.equals("update")){
                int update = DBHelper.update(sql);
                result="{"+"\"result\":"+update+"}";
            }
        }catch (Exception e){

        }
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write(result);
    }
}
