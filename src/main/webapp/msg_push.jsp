<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.nursing.servlet.MessagesListServlet"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.nursing.data.entity.PushMessage"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>医院信息化·移动护理通用接口API设置</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<style type="text/css">
<!--
body {
	margin: 20px;
}

body, td, th {
	border:1px solid black;
	font-family: Arial, Helvetica, microsoft yahei, sans-serif;
	font-size: 12px;
	color: #505050;
}

table {
	border-collapse: collapse;
}

#mainName {
	float: left;
	font-size: 28px;
	line-height: 60px;
	margin-left: 20px
}

#push_order {
	border-top: 3px solid #00a9ec
}

#push_order td {
	background: #f0f0f0;
	border-bottom: 1px solid #CCC
}

#th td {
	background: #1e7ca5;
	color: #FFF
}
-->
</style>
<link type="text/css"
	href="timepicker/css/ui-lightness/jquery-ui-1.7.2.custom.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="timepicker/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="timepicker/js/timepicker.js"></script>
<script type="text/javascript">
	$(function() {
		$('#datetime').datepicker({
			duration : '',
			showTime : true,
			constrainInput : false
		});
	});
</script>

</head>

<body>
	<div>
		<div id="mainName">消息推送管理</div>
		<div style="float: right; padding: 20px; width: 200px;">
			<div
				style="float: left; display: inline; padding: 5px; background: #00a9ec; color: #FFF; cursor: pointer"
				onClick="window.location.href='msg_push.jsp';">发布消息推送</div>
			<div
				style="float: right; margin-left: 20px; display: inline; padding: 5px; background: #00a9ec; color: #FFF; cursor: pointer"
				onClick="window.location.href='msg.jsp';">返回消息列表</div>
		</div>
	</div>

	<%
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		String act = request.getParameter("act");
		String msgID = request.getParameter("msgID");
		String msgType = request.getParameter("msgType");
		String msgTitle = request.getParameter("msgTitle");
		String msgmemo = request.getParameter("msgmemo");
		String msgTime = request.getParameter("msgTime");

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String dateResult = df.format(new Date());

		if (msgTime == null || msgTime.equals("")) {
			msgTime = dateResult;
		}
		if (msgTitle == null) {
			msgTitle = "";
		}
		if (msgmemo == null) {
			msgmemo = "";
		}
		if (msgID == null) {
			msgID = "";
		}

		String mesSelected = "";
		String taskSelected = "";
		if (msgType != null && !msgType.equals("")) {
			if (msgType.equals("0")) {
				mesSelected = "selected";
			} else if (msgType.equals("2")) {
				taskSelected = "selected";
			}
		}

		//执行保存操作或者修改操作
		if (act != null && act.equals("save")) {

			String realPath = request.getSession().getServletContext().getRealPath("/push");

			File filePush = new File(realPath);
			if (!filePush.exists()) {
				filePush.mkdir();
			}

			String messagesJson = MessagesListServlet.readFile(realPath + "/messages.txt");
			List<PushMessage> messageList = new Gson().fromJson(messagesJson, new TypeToken<List<PushMessage>>() {
			}.getType());
			if (messageList == null) {
				messageList = new ArrayList<PushMessage>();
			}

			String save_msgID = request.getParameter("save_msgID");
			String save_msgtitle = request.getParameter("save_msgtitle");
			String save_msgmemo = request.getParameter("save_msgmemo");
			String save_datetime = request.getParameter("save_datetime");

			String save_msgtype = request.getParameter("save_msgtype");

			boolean isModify = false;
			//modify
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
			} else {//save
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
			MessagesListServlet.writeFile(realPath + "/messages.txt", newPushJson);

			/*response.sendRedirect("msg.jsp");*/
			if (isModify) {
				response.getWriter().write("<script>alert(\"推送消息修改成功！\");window.location.href='msg.jsp';</script>");
			} else {
				response.getWriter().write("<script>alert(\"推送消息成功发出！\");window.location.href='msg.jsp';</script>");
			}
		}
	%>

	<div>
		<form name="form1" method="post" action="?act=save">

			<table style="width: 100%; border: 0;" id="push_order">
				<tr>
					<td width="24%" align="right">推送消息类型</td>
					<td width="68%"><select name="save_msgtype">
							<option value="0" <%=mesSelected%>>群发消息</option>
							<option value="2" <%=taskSelected%>>群发任务</option>
					</select></td>
					<td width="8%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">推送对象</td>
					<td><select name="save_msgtouser">
							<option value="0">所有护士</option>
					</select></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">消息标题</td>
					<td><input name="save_msgtitle" type="text" size="60"
						value="<%=msgTitle%>"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">消息内容</td>
					<td><textarea name="save_msgmemo" cols="60" rows="6"><%=msgmemo%></textarea></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">推送时间</td>
					<td><input type="text" name="save_datetime" id="datetime"
						value="<%=msgTime%>"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><input type="hidden" name="save_msgID"
						value="<%=msgID%>"></td>
					<td><input type="submit" name="Submit" value="提交"></td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
