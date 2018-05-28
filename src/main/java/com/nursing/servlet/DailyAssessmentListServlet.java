package com.nursing.servlet;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.DailyAssessmentSQL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/22.
 * 获取每日评估选项列表
 */
public class DailyAssessmentListServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        req.getParameter("bid");
        req.getParameter("vid");
        String itemid=req.getParameter("itemid");
        req.getParameter("pgdate");
        if (itemid!=null&&!itemid.equals("")&&!itemid.equals("0")){
            resp.getWriter().write(DBDirectFormatJson.select(DailyAssessmentSQL.dailyAssessmentLListByItemId(itemid)).toLowerCase());
            return;
        }
        resp.getWriter().write(DBDirectFormatJson.select(DailyAssessmentSQL.dailyAssessmentLList()).toLowerCase());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
