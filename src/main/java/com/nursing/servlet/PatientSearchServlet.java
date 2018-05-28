package com.nursing.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2016/1/18.
 *
 */
public class PatientSearchServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String result="[\n" +
                "    {\n" +
                "        \"srhid\": 1,\n" +
                "        \"oname\": \"全部\",\n" +
                "        \"otype\": \"0\",\n" +
                "        \"ovalues\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"srhid\": 2,\n" +
                "        \"oname\": \"护理等级\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": {\n" +
                "            \"ovalue\": [\n" +
                "                \"特级护理\",\n" +
                "                \"一级护理\",\n" +
                "                \"二级护理\",\n" +
                "                \"三级护理\"\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"srhid\": 3,\n" +
                "        \"oname\": \"饮食\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": {\n" +
                "            \"ovalue\": [\n" +
                "                \"流质\",\n" +
                "                \"半流质\",\n" +
                "                \"普通\",\n" +
                "                \"禁\",\n" +
                "                \"软\",\n" +
                "                \"低盐低脂普通饮食\"\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"srhid\": 4,\n" +
                "        \"oname\": \"医嘱\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": {\n" +
                "            \"ovalue\": [\n" +
                "                \"测血压1/日\",\n" +
                "                \"血糖监测\",\n" +
                "                \"血糖系列\",\n" +
                "                \"青霉素皮试\",\n" +
                "                \"高压氧仓\",\n" +
                "                \"持续低流量吸氧\",\n" +
                "                \"理疗\"\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"srhid\": 5,\n" +
                "        \"oname\": \"病情\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": {\n" +
                "            \"ovalue\": [\n" +
                "                \"危\",\n" +
                "                \"重\",\n" +
                "                \"一般\"\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"srhid\": 6,\n" +
                "        \"oname\": \"护理常规\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": {\n" +
                "            \"ovalue\": [\n" +
                "                \"按内科\",\n" +
                "                \"按神经外科\",\n" +
                "                \"按眼科\",\n" +
                "                \"按耳鼻喉\",\n" +
                "                \"按肛肠科\",\n" +
                "                \"按骨科\",\n" +
                "                \"按五官科\"\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"srhid\": 7,\n" +
                "        \"oname\": \"72小时体温\",\n" +
                "        \"otype\": \"2\",\n" +
                "        \"ovalues\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"srhid\": 8,\n" +
                "        \"oname\": \"24小时体温\",\n" +
                "        \"otype\": \"2\",\n" +
                "        \"ovalues\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"srhid\": 9,\n" +
                "        \"oname\": \"三日内大便\",\n" +
                "        \"otype\": \"1\",\n" +
                "        \"ovalues\": {\n" +
                "            \"ovalue\": [\n" +
                "                \"正常\",\n" +
                "                \"无大便\"\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"srhid\": 10,\n" +
                "        \"oname\": \"术后\",\n" +
                "        \"otype\": \"2\",\n" +
                "        \"ovalues\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"srhid\": 11,\n" +
                "        \"oname\": \"入院满整周\",\n" +
                "        \"otype\": \"3\",\n" +
                "        \"ovalues\": \"checked\"\n" +
                "    }\n" +
                "]";
        resp.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
