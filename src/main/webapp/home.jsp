<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>医院信息化·移动护理通用接口API设置</title>
<style type="text/css">
<!--
html, body {
	width: 100%;
	height: 100%;
	overflow: hidden
}

body {
	margin: 0px;
}

body, td, th {
	font-family: Arial, Helvetica, microsoft yahei, sans-serif;
	font-size: 12px;
	color: #505050
}

ul, li, ol {
	margin: 0px;
	padding: 0px;
	list-style: none
}

a {
	color: #505050;
	text-decoration: none;
}

#top {
	height: 50px;
	background: #006290;
	color: #FFF;
	font-size: 16px;
	line-height: 50px;
	padding-left: 20px;
}

#zsysmenu {
	position: relative;
	float: left;
	width: 200px;
	height: 100%;
	background: #1e7ca5;
	overflow: hidden
}

#zsysmenu ul {
	position: absolute;
	z-index: 100;
	margin-top: 50px;
}

#zsysmenu li {
	width: 120px;
	height: 50px;
	padding-left: 80px;
	padding-top: 16px;
	line-height: 18px;
	margin-top: 15px;
	cursor: pointer
}

#menua {
	color: #FFF;
	background-image: url(img/msg.png);
	background-repeat: no-repeat;
	background-position: 18px center;
}

#menub {
	color: #FFF;
	background-image: url(img/set.png);
	background-repeat: no-repeat;
	background-position: 18px center;
}

#menuc {
	color: #FFF;
	background-image: url(img/api.png);
	background-repeat: no-repeat;
	background-position: 18px center;
}

#fsysmenu {
	position: absolute;
	right: -128px;
	width: 122px;
	height: 100%;
	background: #006290;
	border-left: 6px solid #006290;
	z-index: 1000;
}

#fsysmenu li {
	width: 102px;
	height: 40px;
	line-height: 40px;
	padding: 0px;
	padding-left: 20px;
	background: #006290;
	color: #FFF
}

#fmtop {
	width: 60px;
	height: 60px;
	background: url(img/back.png);
	margin-top: 20px;
	margin-left: 30px;
	cursor: pointer
}

#sysmain {
	margin-left: 200px;
	height: 100%;
	background: #FFF;
	overflow: hidden
}
-->
</style>

<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(
			function() {

				$("#menua").click(
						function() {
							$("#fsysmenu").animate({
								right : '-128px'
							}, 200);
							$(window.parent.document).find("#mainfrm").attr(
									"src", "msg.jsp");
							$("#menua").css({
								"background-image" : "url(img/msgs.png)",
								"background-color" : "#FFF",
								"color" : "#505050"
							});
							$("#menub").css({
								"background-image" : "url(img/set.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#menuc").css({
								"background-image" : "url(img/api.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
						});

				$("#menub").click(
						function() {
							$("#fsysmenu").animate({
								right : '-128px'
							}, 200);
							$(window.parent.document).find("#mainfrm").attr(
									"src", "setup3.jsp");
							$("#menub").css({
								"background-image" : "url(img/sets.png)",
								"background-color" : "#FFF",
								"color" : "#505050"
							});
							$("#menua").css({
								"background-image" : "url(img/msg.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#menuc").css({
								"background-image" : "url(img/api.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
						});

				$("#menuc").click(
						function() {
							$(window.parent.document).find("#mainfrm").attr(
									"src", "apiset/data_set.jsp");
							$("#menuc").css({
								"background-image" : "url(img/apis.png)",
								"background-color" : "#006290",
								"color" : "#FFF"
							});
							$("#menua").css({
								"background-image" : "url(img/msg.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#menub").css({
								"background-image" : "url(img/set.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#menud").css({
								"background-color" : "#FFF",
								"color" : "#505050"
							});
							$("#menue").css({
								"background-color" : "#006290",
								"color" : "#FFF"
							});
							$("#menuf").css({
								"background-color" : "#006290",
								"color" : "#FFF"
							});
							$("#fsysmenu").animate({
								right : '0px'
							}, 200);
						});

				$("#fmtop").click(function() {
					$("#fsysmenu").animate({
						right : '-128px'
					}, 200);
				});
				
				$("#menud").click(
						function() {
							$(window.parent.document).find("#mainfrm").attr(
									"src", "apiset/data_set.jsp");
							$("#menud").css({
								"background-color" : "#FFF",
								"color" : "#505050"
							});
							$("#menue").css({
								"background-color" : "#006290",
								"color" : "#FFF"
							});
							$("#menuf").css({
								"background-color" : "#006290",
								"color" : "#FFF"
							});
							$("#menua").css({
								"background-image" : "url(img/msg.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#menub").css({
								"background-image" : "url(img/set.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#menuc").css({
								"background-image" : "url(img/apis.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#fsysmenu").animate({
								right : '0px'
							}, 200);
						});
				$("#menue").click(
						function() {
							$(window.parent.document).find("#mainfrm").attr(
									"src", "apiset/sql.jsp");
							$("#menue").css({
								"background-color" : "#FFF",
								"color" : "#505050"
							});
							$("#menud").css({
								"background-color" : "#006290",
								"color" : "#FFF"
							});
							$("#menuf").css({
								"background-color" : "#006290",
								"color" : "#FFF"
							});
							$("#menua").css({
								"background-image" : "url(img/msg.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#menub").css({
								"background-image" : "url(img/set.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#menuc").css({
								"background-image" : "url(img/apis.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#fsysmenu").animate({
								right : '0px'
							}, 200);
						});
				$("#menuf").click(
						function() {
							$(window.parent.document).find("#mainfrm").attr(
									"src", "apiset/data_set.jsp");
							$("#menuf").css({
								"background-color" : "#FFF",
								"color" : "#505050"
							});
							$("#menue").css({
								"background-color" : "#006290",
								"color" : "#FFF"
							});
							$("#menud").css({
								"background-color" : "#006290",
								"color" : "#FFF"
							});
							$("#menua").css({
								"background-image" : "url(img/msg.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#menub").css({
								"background-image" : "url(img/set.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#menuc").css({
								"background-image" : "url(img/apis.png)",
								"background-color" : "",
								"color" : "#FFF"
							});
							$("#fsysmenu").animate({
								right : '0px'
							}, 200);
						});

			});
</script>
</head>

<body>
	<div id="zsysmenu">
		<ul>
			<li id="menua">消息推送<br>Messages Push
			</li>
			<li id="menub">系统设置<br>System Setup
			</li>
			<li id="menuc">API接口配置<br>Api Setup
			</li>
		</ul>
		<div id="fsysmenu">
			<div id="fmtop"></div>
			<ul>
				<li id="menud">数据库配置</li>
				<li id="menue">接口参数配置</li>
				<li id="menuf">接口模块配置</li>
			</ul>
		</div>
	</div>

	<div id="sysmain">
		<iframe id="mainfrm" name="mainfrm"
			style="width: 100%; height: 100%; border: 0px" src="main.html"></iframe>
	</div>
</body>
</html>
