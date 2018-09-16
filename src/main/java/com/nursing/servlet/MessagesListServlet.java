package com.nursing.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nursing.data.entity.PushMessage;

/**
 * Created by ukey on 2015/12/16.
 *
 * http://123.57.43.174:8080/messages.do?api=1qaz2wsx&uid=1142&curpage=1
 */
public class MessagesListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String curpage = req.getParameter("curpage");
		String srhkey = req.getParameter("srhkey");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");

		String messagesJson = "";

		if (curpage != null && !curpage.equals("1")) {
			messagesJson = "[]";
			resp.getWriter().write(messagesJson);
			return;
		}

		// 获取推送消息文件的路径
		String realPath = getServletContext().getRealPath("/") + "push";
		File filePush = new File(realPath);
		if (!filePush.exists()) {
			filePush.mkdir();
		}
		messagesJson = readFile(realPath + "/messages.txt");
		if (messagesJson == null || messagesJson.equals("")) {
			messagesJson = "[]";
		}
		List<PushMessage> messageList = new Gson().fromJson(messagesJson, new TypeToken<List<PushMessage>>() {
		}.getType());

		String act = req.getParameter("act");
		String mesID = req.getParameter("msgid");
		System.out.println("act=" + act + ",mesID=" + mesID);
		// 执行删除操作
		if (act != null && act.equals("del")) {
			if (mesID != null && !mesID.equals("")) {
				if (messageList != null) {
					for (int i = 0; i < messageList.size(); i++) {
						PushMessage pushMessage = messageList.get(i);
						if (mesID.equals(String.valueOf(pushMessage.msgID))) {
							messageList.remove(i);
							break;
						}
					}
				}
				String newPushJson = new Gson().toJson(messageList);
				System.out.println("newPushJson=" + newPushJson);
				writeFile(realPath + "/messages.txt", newPushJson);
				resp.sendRedirect("msg.html");
				return;
			}
		}

		// 执行保存操作或者修改操作
		if (act != null && act.equals("save")) {
			if (messageList == null) {
				messageList = new ArrayList<PushMessage>();
			}

			String save_msgID = req.getParameter("save_msgID");
			String save_msgtitle = req.getParameter("save_msgtitle");
			String save_msgmemo = req.getParameter("save_msgmemo");
			String save_datetime = req.getParameter("save_datetime");
			String save_msgtype = req.getParameter("save_msgtype");

			boolean isModify = false;
			// modify
			if (save_msgID != null && !save_msgID.equals("")) {
				for (int i = 0; i < messageList.size(); i++) {
					PushMessage item = messageList.get(i);
					if (save_msgID.equals(String.valueOf(item.msgID))) {
						item.msgTitle = save_msgtitle;
						item.msgmemo = save_msgmemo;
						item.msgTime = save_datetime;
						messageList.set(i, item);
						isModify = true;
						System.out.println("modify msg=" + messageList.get(i).msgTitle);
						break;
					}
				}
			} else {// save
				isModify = false;
				PushMessage pushMessage = new PushMessage();
				pushMessage.msgID = messageList.size() + 1;
				pushMessage.msgTitle = save_msgtitle;
				pushMessage.msgmemo = save_msgmemo;
				pushMessage.msgTime = save_datetime;
				pushMessage.msgType = Integer.parseInt(save_msgtype);
				messageList.add(pushMessage);
			}
			String newPushJson = new Gson().toJson(messageList);
			System.out.println("newPushJson=" + newPushJson);
			writeFile(realPath + "/messages.txt", newPushJson);

			/* response.sendRedirect("msg.jsp"); */
			if (isModify) {
				resp.getWriter().write("<script>alert(\"推送消息修改成功！\");window.location.href='msg.html';</script>");
				return;
			} else {
				resp.getWriter().write("<script>alert(\"推送消息成功发出！\");window.location.href='msg.html';</script>");
				return;
			}
		}
		// 执行查询操作
		if (act != null && act.equals("seach")) {
			if (mesID != null && !mesID.equals("")) {
				if (messageList != null) {
					for (int i = 0; i < messageList.size(); i++) {
						PushMessage pushMessage = messageList.get(i);
						if (mesID.equals(String.valueOf(pushMessage.msgID))) {
							resp.getWriter().write("[" + new Gson().toJson(pushMessage) + "]");
							return;
						}
					}
				}
			}
		}
		// 移动端申请
		if (srhkey != null && !srhkey.equals("")) {
			System.out.println("srhKey=" + srhkey);
			System.out.println("curpage=" + curpage);
			List<PushMessage> searchPushList = new ArrayList<PushMessage>();
			List<PushMessage> pushMessageList = new Gson().fromJson(messagesJson, new TypeToken<List<PushMessage>>() {
			}.getType());
			for (int i = 0; i < pushMessageList.size(); i++) {
				PushMessage item = pushMessageList.get(i);
				System.out.println("item=" + item.msgTitle);
				if (item.msgTitle.contains(srhkey) || item.msgmemo.contains(srhkey)) {
					searchPushList.add(item);
				}
			}
			String searchPushJson = new Gson().toJson(searchPushList);
			System.out.println("searchPushJson=" + searchPushJson);
			resp.getWriter().write(searchPushJson);
			return;
		}

		System.out.println("messages=" + messagesJson);
		resp.getWriter().write(messagesJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public static void writeFile(String fileName, String fileContent) {
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "gbk");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readFile(String fileName) {
		String fileContent = "";
		try {
			File f = new File(fileName);
			if (f.isFile() && f.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(f), "gbk");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					fileContent += line;
				}
				read.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileContent;
	}

}
