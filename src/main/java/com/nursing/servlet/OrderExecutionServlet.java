package com.nursing.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.SQLStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2015/12/22.
 * 医嘱执行单
 * http://123.57.43.174:8080/yz_zhixingdan.do?api=1qaz2wsx&bid=00133083&vid=1&ot=all&zt=C&cl=all
 *
 * zt = "C";// A 服药单 B、注射单 C、输液单 D、治疗单 E、护理单
 */
public class OrderExecutionServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid=req.getParameter("bid");
        String vid=req.getParameter("vid");
        String ot=req.getParameter("ot");
        String zt=req.getParameter("zt");
        String cl=req.getParameter("cl");


        OrderExecution orderExecution=new OrderExecution();

        String bingrenJson= DBDirectFormatJson.select(SQLStatement.orderExecutionByBinren(pid,vid));

        String orderJson=DBDirectFormatJson.select(SQLStatement.orderExecutionByOrder(pid,vid,ot,zt,cl));

        orderExecution.Status=1;
        orderExecution.Failmsg="\"暂无此类执行单！\"";
        orderExecution.Reload=1;
        orderExecution.HEDUICODE=pid+"T*";

        if (bingrenJson.equals("[]")){
            orderExecution.Status=0;
            orderExecution.Failmsg="\"没有找到您要的病人！\"";
            orderExecution.Reload=0;
        }else {
            List<OrderExecutionByBingren> bingrenList=new Gson().fromJson(bingrenJson,new TypeToken<List<OrderExecutionByBingren>>(){}.getType());
            orderExecution.bingren=bingrenList;
        }

        if (orderJson.equals("[]")){
            orderExecution.Status=1;
            orderExecution.Failmsg="\"暂无此类执行单！\"";
            orderExecution.Reload=1;
        }else {
            orderExecution.Failmsg="";
            List<OrderExecutionByOrder> orderList=new Gson().fromJson(orderJson,new TypeToken<List<OrderExecutionByOrder>>(){}.getType());
            if (orderList!=null&&orderList.size()>0){
                orderExecution.TOP1YZOID=orderList.get(0).YZOID;
                int YZOID=0;
                int zxdgi=-1;
                for (int i = 0; i < orderList.size(); i++) {
                    OrderExecutionByOrder item=orderList.get(i);
                    if (item.ISEXECUTE!=null&&item.ISEXECUTE.equals("1")){
                        item.YZ_COLOR="#009900";
                    }else {
                        item.YZ_COLOR="#000000";
                    }
                    if (item.YZOID!=YZOID){
                        zxdgi=zxdgi+1;
                    }
                    YZOID=item.YZOID;
                    item.YZ_ITEM=getNumToLetter(zxdgi)+"."+item.YZ_ITEM;
                    if (item.YZ_JINUM.startsWith(".")){
                        item.YZ_JINUM="0"+item.YZ_JINUM;
                    }
                }
            }
            orderExecution.orderList=orderList;
        }

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.getWriter().write(new Gson().toJson(orderExecution));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public String getNumToLetter(int num){
        String[] letterArr={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String result;
        if (num<26){
            result=letterArr[num];
        }else {
            result=letterArr[num%26];
        }
        return result;
    }

    private final class OrderExecution{
        @SuppressWarnings("unused")
		public int Status;
        @SuppressWarnings("unused")
		public String Failmsg;
        @SuppressWarnings("unused")
		public int Reload;
        @SuppressWarnings("unused")
		public int TOP1YZOID;
        @SuppressWarnings("unused")
		public String HEDUICODE;
        @SuppressWarnings("unused")
		public List<OrderExecutionByBingren> bingren=new ArrayList<OrderExecutionByBingren>();
        @SuppressWarnings("unused")
		public List<OrderExecutionByOrder> orderList=new ArrayList<OrderExecutionByOrder>();

    }

    private final class OrderExecutionByBingren{

        @SuppressWarnings("unused")
		public String BRID;
        @SuppressWarnings("unused")
		public int ZYID;
        @SuppressWarnings("unused")
        public String BRNAME;
        @SuppressWarnings("unused")
        public int CHUANG;
        @SuppressWarnings("unused")
        public String SEX;
        @SuppressWarnings("unused")
        public String ZHUYI;
        @SuppressWarnings("unused")
        public String BIRTH;
        @SuppressWarnings("unused")
        public String ZYDATE;
        @SuppressWarnings("unused")
        public String BINGQU;
        @SuppressWarnings("unused")
        public String ZHENDUAN;
        @SuppressWarnings("unused")
        public String FEIBIE;
        @SuppressWarnings("unused")
        public double FEIYUE;
        @SuppressWarnings("unused")
        public String HULIDJ;

    }

    private final class OrderExecutionByOrder{
    	@SuppressWarnings("unused")
        public String BRID;
    	@SuppressWarnings("unused")
        public int ZYID;
        public int YZOID;
        public String YZ_ITEM;
        @SuppressWarnings("unused")
        public String YZ_TYPE;
        @SuppressWarnings("unused")
        public String ORDER_CLASS_NAME;
        @SuppressWarnings("unused")
        public int YZ_CHLIN;
        @SuppressWarnings("unused")
        public String YZ_COLOR;
        public String YZ_JINUM;
        @SuppressWarnings("unused")
        public String YZ_DANWEI;
        @SuppressWarnings("unused")
        public String ZX_TIME;
        public String ISEXECUTE;
    }


}
