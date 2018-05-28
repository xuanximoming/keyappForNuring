package com.nursing.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jack on 2015/12/22.
 * 教育表单
 */
public class EducationFormServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result="[\n" +
                "    {\n" +
                "        \"oname\": \"教育时间\",\n" +
                "        \"otype\": \"0\",\n" +
                "        \"ovalues\": \"%s\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"oname\": \"教育对象\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": [\n" +
                "            {\n" +
                "                \"itemname\": \"病人\",\n" +
                "                \"selected\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"itemname\": \"家属\",\n" +
                "                \"selected\": 0\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"oname\": \"准备情况\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": [\n" +
                "            {\n" +
                "                \"itemname\": \"首次宣教\",\n" +
                "                \"selected\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"itemname\": \"二次宣教\",\n" +
                "                \"selected\": 0\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"oname\": \"教育方法\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": [\n" +
                "            {\n" +
                "                \"itemname\": \"口述\",\n" +
                "                \"selected\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"itemname\": \"示范\",\n" +
                "                \"selected\": 0\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"oname\": \"掌握情况\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": [\n" +
                "            {\n" +
                "                \"itemname\": \"口述理解\",\n" +
                "                \"selected\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"itemname\": \"会演示\",\n" +
                "                \"selected\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"itemname\": \"需强化\",\n" +
                "                \"selected\": 0\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"oname\": \"学习障碍\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": [\n" +
                "            {\n" +
                "                \"itemname\": \"语言\",\n" +
                "                \"selected\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"itemname\": \"沟通\",\n" +
                "                \"selected\": 0\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"oname\": \"备注\",\n" +
                "        \"otype\": \"2\",\n" +
                "        \"ovalues\": \"\"\n" +
                "    }\n" +
                "]";

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
        String dateResult=df.format(new Date());

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write(String.format(result,dateResult));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
