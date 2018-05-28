<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>移动护理（移动端）api接口管理</title>
    <style type="text/css">
        <!--
        body {
            background-color: #006290;
            margin: 0px;
        }
        body,td,th {
            font-family: Arial, Helvetica, microsoft yahei,sans-serif;
            font-size: 14px;
            color: #FFFFFF;
        }
        #logintitle { width:600px; margin:auto;margin-top:200px; font-family:Arial, Helvetica, microsoft yahei,sans-serif; color:#FFF; font-size:28px; height:80px; line-height:80px}
        #login { width:100%; margin:auto; background:#003366; padding:20px; padding-right:0px; padding-left:0px;}

        -->
    </style>
</head>
<body>

<div id="logintitle">移动护理（移动PDA端）API接口设置管理</div>
<div id="login">
    <form id="form1" name="form1" method="post" action="login.do">
        <table style="width: 600; border: 0;" align="center">
            <tr>
                <td align="right" bgcolor="#003366">管理员：</td>
                <td bgcolor="#003366"><input type="text" name="name" value="" /></td>
                <td bgcolor="#003366">&nbsp;</td>
            </tr>
            <tr>
                <td align="right" bgcolor="#003366">密　码：</td>
                <td bgcolor="#003366"><input type="password" name="password"  value="" /></td>
                <td bgcolor="#003366">&nbsp;</td>
            </tr>
            <tr>
                <td align="right" bgcolor="#003366">&nbsp;</td>
                <td bgcolor="#003366"><input type="submit" name="Submit" value="登录管理" /></td>
                <td bgcolor="#003366">&nbsp;</td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>