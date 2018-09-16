<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>医院信息化·移动护理通用接口API设置</title>
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
-->
</style>
</head>

<body>
	<div>
		<div id="mainName">系统设置</div>
	</div>

	<%--
<script>alert("系统设置成功！");window.location.href='setup.jsp';</script>
--%>


	<div>
		<form name="form1" method="post" action="?act=save">
			<table width="100%" border="0" align="center" cellpadding="10"
				cellspacing="0" id="push_order">
				<tr>
					<td width="18%" align="right">接口串码</td>
					<td width="48%"><input type="text" name="apicode"
						value="apicode"></td>
					<td width="34%">&nbsp;</td>
				</tr>
				<tr>
					<td width="18%" align="right">上传最新版APK</td>
					<td width="48%"><input type="file" name="apkfile"></td>
					<td width="34%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right">版本号</td>
					<td><input name="newver" type="text" size="10" value="1"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">APP超时自动退出时长</td>
					<td><input name="apptimeout" type="text" size="10"
						value="35000"> 秒</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">版权信息</td>
					<td><textarea name="verinfos" cols="60" rows="10"></textarea></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td align="right">&nbsp;</td>
					<td><input type="submit" name="Submit" value="保存系统设置"></td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
