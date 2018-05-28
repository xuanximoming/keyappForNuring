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
public class InspectionResultServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String pid=req.getParameter("bid");
        String vid=req.getParameter("vid");
        String jyid=req.getParameter("jyid");
        resp.getWriter().write(DBDirectFormatJson.select(SQLStatement.inspectionResultList(pid,vid,jyid)));

        String json="[\n" +
                "    {\n" +
                "        \"REITEM\": \"谷丙转氨酶 ↓\",\n" +
                "        \"RESULTOK\": \"5IU/L\",\n" +
                "        \"ZHENGCHANG\": \"9-50\",\n" +
                "        \"STATE\": \"L\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"谷草转氨酶\",\n" +
                "        \"RESULTOK\": \"11IU/L\",\n" +
                "        \"ZHENGCHANG\": \"9-60\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"总胆汁酸\",\n" +
                "        \"RESULTOK\": \"3.2umol/L\",\n" +
                "        \"ZHENGCHANG\": \"0-10\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"腺苷脱氨酶\",\n" +
                "        \"RESULTOK\": \"14U/L\",\n" +
                "        \"ZHENGCHANG\": \"0-20\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"总胆红素 ↓\",\n" +
                "        \"RESULTOK\": \"2.3umol/L\",\n" +
                "        \"ZHENGCHANG\": \"3.4-20.85\",\n" +
                "        \"STATE\": \"L\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"直接胆红素\",\n" +
                "        \"RESULTOK\": \"1.4umol/L\",\n" +
                "        \"ZHENGCHANG\": \"0-6.85\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"碱性磷酸酶\",\n" +
                "        \"RESULTOK\": \"76IU/L\",\n" +
                "        \"ZHENGCHANG\": \"40-125\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"谷酰转肽酶\",\n" +
                "        \"RESULTOK\": \"17IU/L\",\n" +
                "        \"ZHENGCHANG\": \"10-60\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"葡萄糖\",\n" +
                "        \"RESULTOK\": \"5.33mmol/L\",\n" +
                "        \"ZHENGCHANG\": \"3.88-6.11(空腹）\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"尿素\",\n" +
                "        \"RESULTOK\": \"3.5mmol/L\",\n" +
                "        \"ZHENGCHANG\": \"1.7-8.3\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"肌酐\",\n" +
                "        \"RESULTOK\": \"46umol/L\",\n" +
                "        \"ZHENGCHANG\": \"38-120\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"尿酸\",\n" +
                "        \"RESULTOK\": \"371umol/L\",\n" +
                "        \"ZHENGCHANG\": \"140-415\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"β2微球蛋白\",\n" +
                "        \"RESULTOK\": \"2.56mg/L\",\n" +
                "        \"ZHENGCHANG\": \"0.5-3.0\",\n" +
                "        \"STATE\": \"N\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"前白蛋白 ↓\",\n" +
                "        \"RESULTOK\": \"141mg/L\",\n" +
                "        \"ZHENGCHANG\": \"200-450\",\n" +
                "        \"STATE\": \"L\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"总蛋白 ↓\",\n" +
                "        \"RESULTOK\": \"63.4g/L\",\n" +
                "        \"ZHENGCHANG\": \"65-85\",\n" +
                "        \"STATE\": \"L\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"REITEM\": \"白蛋白 ↓\",\n" +
                "        \"RESULTOK\": \"35.9g/L\",\n" +
                "        \"ZHENGCHANG\": \"40-55\",\n" +
                "        \"STATE\": \"L\"\n" +
                "    }\n" +
                "]";

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
