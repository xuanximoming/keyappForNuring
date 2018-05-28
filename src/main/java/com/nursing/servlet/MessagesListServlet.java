package com.nursing.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nursing.data.entity.PushMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2015/12/16.
 *
 * http://123.57.43.174:8080/messages.do?api=1qaz2wsx&uid=1142&curpage=1
 */
public class MessagesListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String curpage = req.getParameter("curpage");
		String srhkey = req.getParameter("srhkey");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");

		String result = "";

		if (curpage != null && !curpage.equals("1")) {
			result = "[]";
			resp.getWriter().write(result);
			return;
		}

		// 获取推送消息文件的路径
		String realPath = getServletContext().getRealPath("/") + "push";
		File filePush = new File(realPath);
		if (!filePush.exists()) {
			filePush.mkdir();
		}
		result = readFile(realPath + "/messages.txt");
		if (result == null || result.equals("")) {
			result = "[]";
		}

		String act = req.getParameter("act");
		String mesID = req.getParameter("msgid");
		System.out.println("act=" + act + ",mesID=" + mesID);
		// 执行删除操作
		if (act != null && act.equals("del")) {
			if (mesID != null && !mesID.equals("")) {
				List<PushMessage> pushMessageList = new Gson().fromJson(result, new TypeToken<List<PushMessage>>() {
				}.getType());
				if (pushMessageList != null) {
					for (int i = 0; i < pushMessageList.size(); i++) {
						PushMessage pushMessage = pushMessageList.get(i);
						if (mesID.equals(String.valueOf(pushMessage.msgID))) {
							pushMessageList.remove(i);
							break;
						}
					}
				}
				String newPushJson = new Gson().toJson(pushMessageList);
				System.out.println("newPushJson=" + newPushJson);
				writeFile(realPath + "/messages.txt", newPushJson);
				resp.sendRedirect("msg.jsp");
				return;
			}
		}

		if (srhkey != null && !srhkey.equals("")) {
			System.out.println("srhKey=" + srhkey);
			System.out.println("curpage=" + curpage);
			List<PushMessage> searchPushList = new ArrayList<PushMessage>();
			List<PushMessage> pushMessageList = new Gson().fromJson(result, new TypeToken<List<PushMessage>>() {
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

		System.out.println("messages=" + result);
		resp.getWriter().write(result);
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

	/**
	 * {"result":[{"id":1,"item1":[{"id":2}]}],"status":1}
	 */

}
