<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>移动查房版本更新</title>
<style type="text/css">
<!--
body {
	margin: 20px;
}

body, td, th {
	font-family: Arial, Helvetica, microsoft yahei, sans-serif;
	font-size: 12px;
	color: #505050;
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

#version {
	float: left;
	font-size: 28px;
	line-height: 60px;
	margin-left: 20px
}

#update {
	border-top: 3px solid #00a9ec
}

#update td {
	background: #f0f0f0;
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
		<div id="version">更新版本</div>
	</div>

	<%
    String message="";
    if (request.getAttribute("message")!=null){
        message= (String) request.getAttribute("message");
    }
%>

	<div>
		<form name="form2" method="post" action="docupload.do"
			enctype="multipart/form-data">
			<table style="width: 100%; border: 0; align-content: center;"
				id="update">
				<tr>
					<td width="18%" align="right">上传最新版APK</td>
					<td width="48%"><input type="file" name="apkfile"></td>
					<td width="34%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td><input type="submit" name="Submit" value="点击上传"></td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>

		<p><%=message %></p>

	</div>

</body>
</html>
