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
 * 任务处理
 */
public class TaskHandleServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msgid=req.getParameter("msgid");//信息ID
        resp.setHeader("Content-type", "text/html;charset=UTF-8");

        String result="{\n" +
                "    \"status\": %s,\n" +
                "    \"msgread\": %s\n" +
                "}";

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

        String status="0";
        String msgread="0";
        try {
            List<PushMessage> messageList=new Gson().fromJson(resultListJson,new TypeToken<List<PushMessage>>(){}.getType());
            if (messageList==null){
                messageList=new ArrayList<PushMessage>();
            }
            if (msgid!=null&&!msgid.equals("")){
                for (int i = 0; i < messageList.size(); i++) {
                    PushMessage item=messageList.get(i);
                    if (msgid.equals(String.valueOf(item.msgID))){
                        if (item.msgType==2){//执行任务处理
                            System.out.println("状态变更前="+messageList.get(i).msgTitle);
                            item.msgread=1;
                            messageList.set(i,item);
                            System.out.println("状态变更后="+messageList.get(i).msgTitle);
                            status="1";
                            msgread="1";
                        }else {
                            System.out.println("消息这里则不做处理=");
                            //消息这里则不做处理
                        }
                        break;
                    }
                }
            }
            String newPushJson=new Gson().toJson(messageList);
            System.out.println("newPushJson="+newPushJson);
            MessagesListServlet.writeFile(realPath+"/messages.txt",newPushJson);
        }catch (Exception ee){

        }

        resp.getWriter().write(String.format(result,status,msgread));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
