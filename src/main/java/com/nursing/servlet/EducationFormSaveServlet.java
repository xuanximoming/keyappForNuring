package com.nursing.servlet;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.DBHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/22.
 * 教育表单保存
 */
public class EducationFormSaveServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String result="{\n" +
                "    \"status\": %s\n" +
                "}";

        String bid=req.getParameter("bid");
        String vid=req.getParameter("vid");
        String eduid=req.getParameter("eduid");
        String postsave=req.getParameter("postsave");
        String uid=req.getParameter("uid");

        String wardCodeJson= DBDirectFormatJson.select(String.format("select ward_code from pats_in_hospital where patient_id='%s'",bid));
        String wardCode="";
        try {
            JSONArray jsonArray=new JSONArray(wardCodeJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item=jsonArray.optJSONObject(i);
                wardCode=item.optString("WARD_CODE");
            }
        }catch (Exception e){

        }

        String EDU_ITEMJson= DBDirectFormatJson.select(String.format("select * from HIS_DICT_ITEM where dict_id=07 and item_id='%s'",eduid));
        String EDU_ITEM="";
        try {
            JSONArray jsonArray=new JSONArray(EDU_ITEMJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item=jsonArray.optJSONObject(i);
                EDU_ITEM=item.optString("ITEM_NAME");
            }
        }catch (Exception e){

        }

        String ITEMTYPEJson= DBDirectFormatJson.select(String.format("select * from HIS_DICT_ITEM where dict_id=07 and item_id='%s'",eduid.substring(0,eduid.length()-2)));
        String ITEMTYPE="";
        try {
            JSONArray jsonArray=new JSONArray(ITEMTYPEJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item=jsonArray.optJSONObject(i);
                ITEMTYPE=item.optString("ITEM_NAME");
            }
        }catch (Exception e){

        }

        String EDU_OBJECT="";
        String PRECONDITION="";
        String EDU_METHOD="";
        String MASTERED_DEGREE="";
        String EDU_CLOG="";
        String MEMO="";
        try {
            JSONArray jsonArray=new JSONArray(postsave);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item=jsonArray.optJSONObject(i);
                String name=item.optString("oname");
                if (name.contains("教育对象")){
                    EDU_OBJECT=item.optString("ovalue");
                }
                if (name.contains("准备情况")){
                    PRECONDITION=item.optString("ovalue");
                }
                if (name.contains("教育方法")){
                    EDU_METHOD=item.optString("ovalue");
                }
                if (name.contains("掌握情况")){
                    MASTERED_DEGREE=item.optString("ovalue");
                }
                if (name.contains("学习障碍")){
                    EDU_CLOG=item.optString("ovalue");
                }
                if (name.contains("备注")){
                    MEMO=item.optString("ovalue");
                }
            }
        }catch (Exception e){

        }

        int status=0;
        try {
            String insertSQL="INSERT INTO HEALTH_EDU_REC (dept_code, patient_id, visit_id, ITEM_ID, edu_date, EDU_NURSE, ITEM_TYPE, EDU_ITEM, EDU_OBJECT, PRECONDITION, EDU_METHOD, MASTERED_DEGREE, EDU_CLOG, MEMO)\n" +
                    "VALUES ('%s','%s',\n" +
                    "                  %s,\n" +
                    "                  '%s',\n" +
                    "                  to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'),\n" +
                    "                  '%s',\n" +
                    "                  '%s',\n" +
                    "                  '%s',\n" +
                    "                  '%s',\n" +
                    "                  '%s',\n" +
                    "                  '%s',\n" +
                    "                  '%s',\n" +
                    "                  '%s','%s' )";
            insertSQL=String.format(insertSQL,
                    wardCode,
                    bid,
                    vid,
                    eduid,
                    uid,
                    ITEMTYPE,
                    EDU_ITEM,
                    EDU_OBJECT,
                    PRECONDITION,
                    EDU_METHOD,
                    MASTERED_DEGREE,
                    EDU_CLOG,
                    MEMO);
            System.out.println("insertSQL="+insertSQL);
            status= DBHelper.insert(insertSQL);
            System.out.println("status="+status);
        }catch (Exception ee){

        }

        resp.getWriter().write(String.format(result,status));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


}
