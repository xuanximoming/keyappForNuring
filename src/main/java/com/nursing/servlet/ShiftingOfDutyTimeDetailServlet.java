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
 * Created by Jack on 2015/12/16.
 * 交接班详情表
 */
public class ShiftingOfDutyTimeDetailServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*String result="{\n" +
                "            \"status\": 1,\n" +
                "            \"jb_date\": \"2015-12-12\",\n" +
                "            \"jb_memos\": \"jb_memos\"\n" +
                "        }";

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write(result);*/

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String pid=req.getParameter("bid");
        String vid=req.getParameter("vid");
        String jbdate=req.getParameter("jbdate");
        String sqlResult=DBDirectFormatJson.select(SQLStatement.shiftingOfDutyTimesDetail(pid,vid,jbdate));

        StringBuffer buffer=new StringBuffer();
        buffer.append("{");
        buffer.append("\"status\":%s,");
        buffer.append("\"jb_date\":\"%s\",");
        buffer.append("\"jb_memos\":\"%s\"");
        buffer.append("}");

        String status="0";
        String jb_date="";
        String jb_memos="";

        if (sqlResult!=null&&!sqlResult.equals("[]")){
            JSONArray array=new JSONArray(sqlResult);
            if (array.length()>0){
                JSONObject jsonObject=array.optJSONObject(0);
                jb_date=jsonObject.optString("SHIFT_DATE");
                jb_memos=jsonObject.optString("MEMO");
                status="1";
            }
        }
        String result=String.format(buffer.toString(),status,jb_date,jb_memos);
        resp.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
