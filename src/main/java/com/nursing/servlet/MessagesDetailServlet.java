package com.nursing.servlet;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nursing.data.entity.PushMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2015/12/16.
 *
 * 信息或者任务详情
 */
public class MessagesDetailServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msgid=req.getParameter("msgid");//信息ID
        resp.setHeader("Content-type", "text/html;charset=UTF-8");

        String result="";

        //获取推送消息文件的路径
        String realPath = getServletContext().getRealPath("/") + "push";
        File filePush = new File(realPath);
        if(!filePush.exists()){
            filePush.mkdir();
        }

        String resultListJson=MessagesListServlet.readFile(realPath+"/messages.txt");
        if (resultListJson==null||resultListJson.equals("")){
            resultListJson="[]";
        }

        try {
            List<PushMessage> messageList=new Gson().fromJson(resultListJson,new TypeToken<List<PushMessage>>(){}.getType());
            if (messageList==null){
                messageList=new ArrayList<PushMessage>();
            }
            if (msgid!=null&&!msgid.equals("")){
                for (int i = 0; i < messageList.size(); i++) {
                    PushMessage item=messageList.get(i);
                    if (msgid.equals(String.valueOf(item.msgID))){
                        if (item.msgType==0){//如果是消息需要执行已读
                            System.out.println("状态变更前="+messageList.get(i).msgTitle);
                            item.msgread=1;
                            messageList.set(i,item);
                            System.out.println("状态变更后="+messageList.get(i).msgTitle);
                        }else {
                            System.out.println("任务这里则不做处理=");
                            //任务这里则不做处理
                        }
                        result=item.toString();
                        break;
                    }
                }
            }
            String newPushJson=new Gson().toJson(messageList);
            System.out.println("newPushJson="+newPushJson);
            MessagesListServlet.writeFile(realPath+"/messages.txt",newPushJson);
        }catch (Exception ee){

        }


/*        //临时测试使用.
        String result="{\"status\":1,\"msgid\":35,\"msgType\":0,\"msgtitle\":\"ddddd\",\"msgtime\":\"2015/4/21 10:33:00\",\"msgMemo\":\"ddddd\",\"msgread\":1}";
        try {
            int mid=Integer.parseInt(msgid);
            if (mid%2==0){//信息详情
                result="{\"status\":1,\"msgid\":35,\"msgType\":0,\"msgtitle\":\"ddddd\",\"msgtime\":\"2015/4/21 10:33:00\",\"msgMemo\":\"ddddd\",\"msgread\":1}";
            }else {//任务详情
                result="{\n" +
                        "    \"status\": 1,\n" +
                        "    \"msgid\": 4,\n" +
                        "    \"msgType\": 3,\n" +
                        "    \"msgtitle\": \"3为一对一任务\",\n" +
                        "    \"msgtime\": \"2014/10/11 10:24:00\",\n" +
                        "    \"msgMemo\": \"3为一对一任务\",\n" +
                        "    \"msgread\": 1\n" +
                        "}";
            }
        }catch (Exception e){

        }*/

        resp.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
