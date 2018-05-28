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
 */
public class InspectionServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String pid=req.getParameter("bid");
        String vid=req.getParameter("vid");
        resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.inspectionList(pid,vid)));

/*        String json="[\n" +
                "    {\n" +
                "        \"JY_ID\": \"1505059404\",\n" +
                "        \"JY_BIAOBEN\": \"痰\",\n" +
                "        \"JY_ZHENDUAN\": \"痰-彭氏杯抗酸染色\",\n" +
                "        \"JY_DATE\": \"2015-05-05\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504282853\",\n" +
                "        \"JY_BIAOBEN\": \"尿\",\n" +
                "        \"JY_ZHENDUAN\": \"尿常规检查\",\n" +
                "        \"JY_DATE\": \"2015-04-28\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504282848\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"肝功，肾功，血清蛋白测定\",\n" +
                "        \"JY_DATE\": \"2015-04-28\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504282847\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"血沉\",\n" +
                "        \"JY_DATE\": \"2015-04-28\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504282846\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"血常规(住院)\",\n" +
                "        \"JY_DATE\": \"2015-04-28\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504152641\",\n" +
                "        \"JY_BIAOBEN\": \"灌洗液\",\n" +
                "        \"JY_ZHENDUAN\": \"刷片-常规涂片\",\n" +
                "        \"JY_DATE\": \"2015-04-15\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504152640\",\n" +
                "        \"JY_BIAOBEN\": \"灌洗液\",\n" +
                "        \"JY_ZHENDUAN\": \"肺泡灌洗液-彭氏杯抗酸染色\",\n" +
                "        \"JY_DATE\": \"2015-04-15\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504152638\",\n" +
                "        \"JY_BIAOBEN\": \"灌洗液\",\n" +
                "        \"JY_ZHENDUAN\": \"肺泡灌洗液-结核菌培养+快速药敏试验\",\n" +
                "        \"JY_DATE\": \"2015-04-15\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504120455\",\n" +
                "        \"JY_BIAOBEN\": \"尿\",\n" +
                "        \"JY_ZHENDUAN\": \"尿常规检查\",\n" +
                "        \"JY_DATE\": \"2015-04-12\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504120454\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"肝功，肾功，血清蛋白测定\",\n" +
                "        \"JY_DATE\": \"2015-04-12\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504120452\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"血沉\",\n" +
                "        \"JY_DATE\": \"2015-04-12\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504120451\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"血常规(住院)\",\n" +
                "        \"JY_DATE\": \"2015-04-12\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504033758\",\n" +
                "        \"JY_BIAOBEN\": \"痰\",\n" +
                "        \"JY_ZHENDUAN\": \"痰-彭氏杯抗酸染色\",\n" +
                "        \"JY_DATE\": \"2015-04-03\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504033754\",\n" +
                "        \"JY_BIAOBEN\": \"痰\",\n" +
                "        \"JY_ZHENDUAN\": \"痰-常规涂片\",\n" +
                "        \"JY_DATE\": \"2015-04-03\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504033752\",\n" +
                "        \"JY_BIAOBEN\": \"痰\",\n" +
                "        \"JY_ZHENDUAN\": \"痰-彭氏杯抗酸染色\",\n" +
                "        \"JY_DATE\": \"2015-04-03\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504033751\",\n" +
                "        \"JY_BIAOBEN\": \"尿\",\n" +
                "        \"JY_ZHENDUAN\": \"尿常规检查\",\n" +
                "        \"JY_DATE\": \"2015-04-03\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504033750\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"肺炎两项，免疫常规四项，血-结明三项\",\n" +
                "        \"JY_DATE\": \"2015-04-03\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504033749\",\n" +
                "        \"JY_BIAOBEN\": \"便\",\n" +
                "        \"JY_ZHENDUAN\": \"便常规+隐血\",\n" +
                "        \"JY_DATE\": \"2015-04-03\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504033748\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"肝功，肾功，血清蛋白测定，血心肌功能\",\n" +
                "        \"JY_DATE\": \"2015-04-03\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504033746\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"凝血四项\",\n" +
                "        \"JY_DATE\": \"2015-04-03\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504033732\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"血沉\",\n" +
                "        \"JY_DATE\": \"2015-04-03\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"JY_ID\": \"1504033731\",\n" +
                "        \"JY_BIAOBEN\": \"静脉血\",\n" +
                "        \"JY_ZHENDUAN\": \"血常规(住院)\",\n" +
                "        \"JY_DATE\": \"2015-04-03\"\n" +
                "    }\n" +
                "]";

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write(json);*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
