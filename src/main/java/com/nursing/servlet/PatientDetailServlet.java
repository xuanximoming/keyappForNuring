package com.nursing.servlet;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.SQLStatement;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/14.
 */
public class PatientDetailServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid=req.getParameter("bid");
        String vid=req.getParameter("vid");
        String flag=req.getParameter("t");
        if (pid!=null&&vid!=null){
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
            if (flag!=null&&!flag.equals("")){
                String result=DBDirectFormatJson.select(SQLStatement.wardCodeAndBedNoSQL(pid,vid));
                String wradCode="";
                String bedNo="";
                JSONArray array=new JSONArray(result);
                if (array!=null){
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject item=array.optJSONObject(i);
                        wradCode=item.optString("WARD_CODE");
                        bedNo=item.optString("BED_NO");
                    }
                    if (flag.equals("next")){//获取下一个病床的病人详情
                        resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.nextPatientDetailSQL(wradCode,bedNo)));
                    }else if (flag.equals("up")){//获取前一个病床的病人详情
                        resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.previousPatientDetailSQL(wradCode,bedNo)));
                    }
                }
            }else {//获取病人详情
                resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.patientDetailSQL(pid,vid)));
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
