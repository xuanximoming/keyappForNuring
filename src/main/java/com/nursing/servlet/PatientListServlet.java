package com.nursing.servlet;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.SQLStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/14.
 * 病人列表
 */
public class PatientListServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid=req.getParameter("uid");
        String curpage=req.getParameter("curpage");
        if (uid!=null&&curpage!=null){
            if (curpage.equals("1")){
                resp.setHeader("Content-type", "text/html;charset=UTF-8");
                resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.patientListSQL(uid)));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
