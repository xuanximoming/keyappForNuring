package com.nursing.servlet;

import com.nursing.data.db.DBDirectFormatJson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/16.
 * 获取护理类别
 */
public class NursingTypeServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String cls=req.getParameter("cls");
        /*resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.nursingTypeNameSQL(cls)));*/
        String bid=req.getParameter("bid");

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

        String result=String.format(classAResult,wardCode,wardCode,wardCode,wardCode,wardCode,wardCode);
        if (cls.equals("A")){
            result=String.format(classAResult,wardCode,wardCode,wardCode,wardCode,wardCode,wardCode);
        }else if (cls.equals("B")){
            result=String.format(classBResult,wardCode,wardCode,wardCode,wardCode,wardCode,wardCode);
        }else if (cls.equals("C")){
            result=String.format(classCResult,wardCode);
        }else if (cls.equals("D")){
            result=String.format(classDResult,wardCode,wardCode);
        }
        resp.getWriter().write(result);
    }

    private static String classAResult="{\n" +
            "    \"subtime\": [\n" +
            "        {\n" +
            "            \"time\": \"06:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"08:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"12:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"14:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"18:00:00\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"orderitem\": [\n" +
            "        {\n" +
            "            \"hlid\": 4,\n" +
            "            \"hl_type\": \"A\",\n" +
            "            \"hl_item\": \"腋下体温（℃）\",\n" +
            "            \"units\": \"℃\",\n" +
            "            \"class_code\": \"A\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"1001\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 34,\n" +
            "            \"max_value\": 42,\n" +
            "            \"alertmsg\": \"请输入34-42之间的值！\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 1,\n" +
            "            \"hl_type\": \"A\",\n" +
            "            \"units\": \"次/分\",\n" +
            "            \"class_code\": \"A\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"1002\",\n" +
            "            \"hl_item\": \"脉搏（次/分）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 40,\n" +
            "            \"max_value\": 180,\n" +
            "            \"alertmsg\": \"请输入40-180之间的值！\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 5,\n" +
            "            \"hl_type\": \"A\",\n" +
            "            \"units\": \"次/分\",\n" +
            "            \"class_code\": \"E\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"1003\",\n" +
            "            \"hl_item\": \"呼吸（次/分）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 3,\n" +
            "            \"hl_type\": \"A\",\n" +
            "            \"units\": \"次/分\",\n" +
            "            \"class_code\": \"A\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"1004\",\n" +
            "            \"hl_item\": \"心率（次/分）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 40,\n" +
            "            \"max_value\": 180,\n" +
            "            \"alertmsg\": \"请输入40-180之间的值！\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 6,\n" +
            "            \"hl_type\": \"A\",\n" +
            "            \"units\": \"℃\",\n" +
            "            \"class_code\": \"A\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"1007\",\n" +
            "            \"hl_item\": \"物理降温（℃）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 7,\n" +
            "            \"alertmsg\": \"请输入0-7之间的值！\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 7,\n" +
            "            \"hl_type\": \"A\",\n" +
            "            \"units\": \"mmHg\",\n" +
            "            \"class_code\": \"B\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"2002\",\n" +
            "            \"hl_item\": \"血压（mmHg）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"请输入20-100之间的值！\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    private static String classBResult="{\n" +
            "    \"subtime\": [\n" +
            "        {\n" +
            "            \"time\": \"06:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"08:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"12:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"14:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"18:00:00\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"orderitem\": [\n" +
            "        {\n" +
            "            \"hlid\": 9,\n" +
            "            \"hl_type\": \"B\",\n" +
            "            \"units\": \"ml\",\n" +
            "            \"class_code\": \"B\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"2005\",\n" +
            "            \"hl_item\": \"引流量（ml）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"请输入20-100之间的值！\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 12,\n" +
            "            \"hl_type\": \"B\",\n" +
            "            \"units\": \"ml\",\n" +
            "            \"class_code\": \"B\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"2004\",\n" +
            "            \"hl_item\": \"总出量（ml）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"请输入20-100之间的值！\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 31,\n" +
            "            \"hl_type\": \"B\",\n" +
            "            \"units\": \"ml\",\n" +
            "            \"class_code\": \"B\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"2010\",\n" +
            "            \"hl_item\": \"总尿量（ml）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"请输入0-10000之间的值！\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 32,\n" +
            "            \"hl_type\": \"B\",\n" +
            "            \"units\": \"ml\",\n" +
            "            \"class_code\": \"B\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"2006\",\n" +
            "            \"hl_item\": \"咯血量（ml）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"请输入0-10000之间的值！\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 33,\n" +
            "            \"hl_type\": \"B\",\n" +
            "            \"units\": \"ml\",\n" +
            "            \"class_code\": \"B\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"4004\",\n" +
            "            \"hl_item\": \"总痰量（ml）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"请输入0-10000之间的值！\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 16,\n" +
            "            \"hl_type\": \"B\",\n" +
            "            \"units\": \"\",\n" +
            "            \"class_code\": \"B\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"2001\",\n" +
            "            \"hl_item\": \"大便次数\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"请输入20-100之间的值！\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    private static String classCResult="{\n" +
            "    \"subtime\": [\n" +
            "        {\n" +
            "            \"time\": \"06:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"08:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"12:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"14:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"18:00:00\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"orderitem\": [\n" +
            "        {\n" +
            "            \"hlid\": 13,\n" +
            "            \"units\": \"ml\",\n" +
            "            \"class_code\": \"B\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"2003\",\n" +
            "            \"hl_type\": \"C\",\n" +
            "            \"hl_item\": \"总入量（ml)\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"请输入20-100之间的值！\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    private static String classDResult="{\n" +
            "    \"subtime\": [\n" +
            "        {\n" +
            "            \"time\": \"06:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"08:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"12:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"14:00:00\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"time\": \"18:00:00\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"orderitem\": [\n" +
            "        {\n" +
            "            \"hlid\": 18,\n" +
            "            \"units\": \"cm\",\n" +
            "            \"class_code\": \"B\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"2007\",\n" +
            "            \"hl_type\": \"D\",\n" +
            "            \"hl_item\": \"身高（cm）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"请输入20-100之间的值！\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"hlid\": 17,\n" +
            "            \"units\": \"Kg\",\n" +
            "            \"class_code\": \"B\",\n" +
            "            \"ward_code\": \"%s\",\n" +
            "            \"new_class_vital_code\": \"2008\",\n" +
            "            \"hl_type\": \"D\",\n" +
            "            \"hl_item\": \"体重（kg）\",\n" +
            "            \"hl_value\": null,\n" +
            "            \"min_value\": 0,\n" +
            "            \"max_value\": 10000,\n" +
            "            \"alertmsg\": \"请输入20-100之间的值！\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

}
