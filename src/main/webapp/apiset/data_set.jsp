<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="com.nursing.data.DatabaseConfig" %>
<%@ page import="java.util.Properties" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>移动护理API</title>
<style type="text/css">
<!--
body {
	margin:20px;
}
body,td,th {
	font-family: Arial, Helvetica,microsoft yahei,sans-serif;
	font-size: 12px;
	color:#505050;
}
#mainName {float:left;font-size:28px; line-height:60px; margin-left:20px}
#push_order { border-top:3px solid #00a9ec}
#push_order td {background:#f0f0f0; border-bottom:1px solid #CCC}
#th td{background:#1e7ca5; color:#FFF}

-->
</style>
</head>

<body>
<div>
<div id="mainName">API接口数据库配置</div>
</div>

<%
    Properties properties= DatabaseConfig.getProperties();
    String url=properties.getProperty("db.url");
    String user=properties.getProperty("db.user");
    String password=properties.getProperty("db.password");
%>

<div>
  <form name="form1" method="post" action="../dbconfig.do">
    <table width="100%" border="0" align="center" cellpadding="10" cellspacing="0" id="push_order">
	 <tr>
        <td width="18%" align="right">数据库类型</td>
        <td width="48%"><select name="select">
          <option>oracle</option>
          <option>MsSql</option>
          <option>MySql</option>
        </select>
        </td>
        <td width="34%">&nbsp;</td>
      </tr>
<%--      <tr>
        <td width="18%" align="right">服务器IP</td>
        <td width="48%"><input name="db_server" type="text" value="<%=DBHelper.DB_USER%>"></td>
        <td width="34%">&nbsp;</td>
      </tr>--%>
      <tr>
        <td align="right">数据库URL</td>
        <td><input name="url" type="text" value="<%=url %>" size="35"></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="right">用户名</td>
        <td><input name="user" type="text" value="<%=user %>" size="10"></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="right">密码</td>
        <td><input name="password" type="text" value="<%=password %>" size="10"></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="right">&nbsp;</td>
        <td><input type="submit" name="Submit" value="保存数据库配置"></td>
        <td>&nbsp;</td>
      </tr>
    </table>
  </form>
</div>

</body>
</html>
