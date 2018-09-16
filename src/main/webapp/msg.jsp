<%@ page import="com.nursing.servlet.MessagesListServlet"%>
<%@ page import="com.nursing.data.entity.PushMessage"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>医院信息化·移动护理通用接口API设置</title>
<style type="text/css">

body {
	margin: 20px;
}

body, td, th {
	font-family: Arial, Helvetica, microsoft yahei, sans-serif;
	font-size: 12px;
	color: #505050;
}

a {
	color: #505050;
	text-decoration: none;
}

#mainName {
	float: left;
	font-size: 28px;
	line-height: 60px;
	margin-left: 20px
}

#msgtable td {
	border-collapse: collapse;
	border-bottom: 1px solid #CCC
}

#th td {
	background: #1e7ca5;
	color: #FFF
}

</style>

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
	<div>
		<table style="width: 100%; border: 0" align="center" id="msgtable">
			<tr id="th">
				<td><input type="checkbox" name="checkbox" value="checkbox"></td>
				<td>信息ID</td>
				<td>信息类型</td>
				<td>信息标题</td>
				<td>信息内容</td>
				<td>信息对象</td>
				<td>推送时间</td>
				<td>阅读状态</td>
				<td>管理</td>
			</tr>

			<%
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
				Collections.reverse(messageList);
				for (int i = 0; i < messageList.size(); i++) {
					PushMessage pushMessage = messageList.get(i);
					String messageTypeText = "群发消息";
					if (pushMessage.msgType == 2) {
						messageTypeText = "群发任务";
					}
					String isRead = "已读";
					if (pushMessage.msgread == 0) {
						isRead = "未读";
					}
			%>

			<tr style="background: #FFF; border-bottom: 1px solid #CCC">
				<td><input type="checkbox" name="checkbox2" value="checkbox"></td>
				<td><%=pushMessage.msgID%></td>
				<td><%=messageTypeText%></td>
				<td><a
					href="msg_push.jsp?msgID=<%=pushMessage.msgID%>&msgType=<%=pushMessage.msgType%>&msgTitle=<%=pushMessage.msgTitle%>&msgmemo=<%=pushMessage.msgmemo%>&msgTime=<%=pushMessage.msgTime%>"><%=pushMessage.msgTitle%></a></td>
				<td><%=pushMessage.msgmemo%></td>
				<td><%="所有护士"%></td>
				<td><%=pushMessage.msgTime%></td>
				<td><%=isRead%></td>
				<td><a href="messages.do?act=del&msgid=<%=pushMessage.msgID%>">删除</a></td>
			</tr>

			<%
				}
			%>

		</table>
	</div>

</body>
</html>