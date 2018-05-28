package com.nursing.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.DBHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jack on 2015/12/22.
 * 护理表单提交
 */
public class NursingSaveServlet extends HttpServlet{

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
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String result="{\n" +
                "    \"status\": %s\n" +
                "}";

        String bid=req.getParameter("bid");
        String vid=req.getParameter("vid");
        String saveok=req.getParameter("saveok");
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

        int status=0;
        try {
            List<NursingEntity> nursingEntityList=new Gson().fromJson(saveok,new TypeToken<List<NursingEntity>>(){}.getType());
            for (int i = 0; i < nursingEntityList.size(); i++) {
                NursingEntity nursingEntity=nursingEntityList.get(i);
                String vital_signs=nursingEntity.HL_ITEM.split("（")[0];
                String insertSQL="insert into vital_signs_rec \n" +
                        "(patient_id," +
                        "visit_id," +
                        "recording_date," +
                        "time_point," +
                        "vital_signs," +
                        "units," +
                        "class_code," +
                        "vital_code," +
                        "vital_signs_cvalues," +
                        "ward_code," +
                        "nurse)\n" +
                        "   values('%s',%s,%s,%s,'%s','%s','%s','%s','%s','%s','%s')";
                insertSQL=String.format(insertSQL,
                        bid,
                        vid,
                        "to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')",
                        "to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS')",
                        vital_signs,
                        nursingEntity.UNITS,
                        nursingEntity.CLASS_CODE,
                        nursingEntity.NEW_CLASS_VITAL_CODE,
                        nursingEntity.HL_VALUE,
                        wardCode,
                        uid
                );
                System.out.println("insertSQL="+insertSQL);
                status= DBHelper.insert(insertSQL);
                System.out.println("status="+status);
            }
        }catch (Exception er){
            System.out.println("exception="+er.toString());
        }
        resp.getWriter().write(String.format(result,status));
    }

    /**
     * [
     {
     "ALERTMSG": "请输入20-100之间的值！",
     "CLASS_CODE": "B",
     "HL_ITEM": "身高（cm）",
     "HL_VALUE": "60",
     "hl_type": "D",
     "WARD_CODE": "080102H",
     "NEW_CLASS_VITAL_CODE": "2007",
     "UNITS": "cm",
     "VITAL_CODE": "18",
     "MIN_VALUE": 0,
     "MAX_VALUE": 10000
     }
     ]
     */
    public class NursingEntity{
        public String CLASS_CODE;
        public String HL_ITEM;
        public String HL_VALUE;
        public String WARD_CODE;
        public String NEW_CLASS_VITAL_CODE;
        public String UNITS;
    }

}
