<%@page import="com.nursing.data.SqlConfig"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Properties"%>
<%
	request.setCharacterEncoding("utf-8");
	String Strdo = request.getParameter("strdo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SQL设置</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$(":radio").click(function() {
			document.passForm.strdo.value = $(this).val();
			var formObj = document.getElementById('passForm');
			formObj.submit();
		});
	});
</script>
<style type="text/css">
#push_order {
	border-top: 3px solid #00a9ec
}

#push_order td {
	background: #f0f0f0;
	border-bottom: 1px solid #CCC
}
</style>
</head>

<body>
	<%
		String sql = SqlConfig.getSql(Strdo);
		String HtmlCode = SqlConfig.CreateHtml(Strdo);
	%>
	<div>
		<form name="passForm" method="post" action="sql.jsp" id="passForm">
			<input id="strdo" type="hidden" name="strdo" value="">
		</form>
	</div>
	<div>
		<form name="form1" method="post" action="../sqlconfig.do">
			<table width="100%" border="0" align="center" cellpadding="10"
				cellspacing="0" id="push_order">
				<tr>
					<td width="15%" align="right"></td>
					<td width="48%" align="center">SQL设置</td>
					<td width="34%">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="3" style="height: 160px; font-size: 12px;"
						valign="top">
						<div
							style="width: 100%; height: 100%; OVERFLOW-Y: auto; OVERFLOW-X: hidden;">
							<%=HtmlCode%>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">SQL语句</td>
					<td colspan="2"><textarea name="sql" cols="120" rows="15"><%=sql%></textarea></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><input type="submit" name="Submit" value="保存SQL语句"></td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>