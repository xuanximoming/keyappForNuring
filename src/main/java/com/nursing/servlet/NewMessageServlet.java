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
 * Created by Jack on 2015/12/22.
 * 轮询推送接口
 */
public class NewMessageServlet extends HttpServlet{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html;charset=UTF-8");

        String result="[]";

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
            List<PushMessage> pushMessageList=new ArrayList<PushMessage>();
            List<PushMessage> messageList=new Gson().fromJson(resultListJson,new TypeToken<List<PushMessage>>(){}.getType());
            if (messageList==null){
                messageList=new ArrayList<PushMessage>();
            }
            for (int i = 0; i < messageList.size(); i++) {
                PushMessage item=messageList.get(i);
                if (item.msgread==0){//如果是未读消息则推送
                    System.out.println("如果是未读消息则推送="+item.toString());
                    pushMessageList.add(item);
                }
            }
            result=new Gson().toJson(pushMessageList);
            System.out.println("newPushJson="+result);
        }catch (Exception ee){

        }


        resp.getWriter().write(result);
    }
}
