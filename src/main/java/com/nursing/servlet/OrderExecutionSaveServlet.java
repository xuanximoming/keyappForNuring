package com.nursing.servlet;

import com.nursing.data.db.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/22.
 * 执行医嘱执行单
 */
public class OrderExecutionSaveServlet extends HttpServlet{

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
        String uid=req.getParameter("uid");
        String decode=req.getParameter("decode");

        System.out.println("扫描结果是---------"+"\n decode="+decode);
        int status=0;
        try {
            String[] decodeArray=decode.split("T");
            String oid=decodeArray[1];
            String dotime=decodeArray[2];

            String[] newlyDotimeArr=dotime.split("-");
            String newlyYear=newlyDotimeArr[0];
            String newlyMonth=newlyDotimeArr[1];
            String newlyDay=newlyDotimeArr[2].substring(0,2);

            String newlyTimeAll=newlyDotimeArr[2].substring(2,newlyDotimeArr[2].length());
            String hour,minute,second;
            if (newlyTimeAll.length()==5){
                hour=newlyTimeAll.substring(0,1);
                minute=newlyTimeAll.substring(1,3);
                second=newlyTimeAll.substring(3,5);
            }else{
                hour=newlyTimeAll.substring(0,2);
                minute=newlyTimeAll.substring(2,4);
                second=newlyTimeAll.substring(4,6);
            }
            String qrcodeDate=newlyYear+"-"+newlyMonth+"-"+newlyDay;
            String qrcodeTime=hour+":"+minute+":"+second;
            dotime=qrcodeDate+" "+qrcodeTime;
            System.out.println("格式化后的时间是="+dotime);
            /**
             * UPDATE ORDERS_EXECUTE
             SET EXECUTE_DATE_TIME=to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'),
             EXECUTE_NURSE='yf01',
             IS_EXECUTE=1
             WHERE patient_id='00133083'
             AND visit_id=1
             AND order_no=10
             AND SCHEDULE_PERFORM_TIME=to_date('2015-4-5 8:00:00','yyyy-mm-dd hh24:mi:ss')
             */
            String updateSQL="UPDATE ORDERS_EXECUTE\n" +
                    "SET EXECUTE_DATE_TIME=to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'),\n" +
                    "                      EXECUTE_NURSE='%s',\n" +
                    "                                    IS_EXECUTE=1\n" +
                    "WHERE patient_id='%s'\n" +
                    "  AND visit_id=%s\n" +
                    "  AND order_no=%s\n" +
                    "  AND SCHEDULE_PERFORM_TIME=to_date('%s','yyyy-mm-dd hh24:mi:ss')";
            updateSQL=String.format(updateSQL,uid,bid,vid,oid,dotime);
            System.out.println("updateSQL="+updateSQL);
            status= DBHelper.update(updateSQL);
            System.out.println("status="+status);
            if (status>0){
                status=1;
            }
        }catch (Exception e){
            System.out.println("医嘱执行单错误信息="+e.toString());
        }

        resp.getWriter().write(String.format(result,status));
    }


}
