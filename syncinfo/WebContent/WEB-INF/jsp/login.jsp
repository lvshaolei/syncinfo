<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<title>学生信息管理系统</title>
		
		<link rel="stylesheet" type="text/css" href="src/css/lodding.css"/>
		<script type="text/javascript" src="src/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="src/js/Particleground.js"></script>
		<script type="text/javascript" src="src/js/hash.js"></script>
		
		<style type="text/css">
		input::-webkit-input-placeholder {
			color: #CCCCCC;
		}
		html,body{
		-moz-user-select: none;
		-khtml-user-select: none;
		user-select: none;
		}
		.code{
			font-family: Arial;
			font-style: italic;
			letter-spacing: 3px;
			font-weight: bolder;
			border: 0;
		}
		* {
			margin: 0;
			padding: 0;
			line-height: 20px;
			border: 0;
			font-family: "times new roman";
			font-size: 18px;
			font-weight: 300;
		}
		#back {
			position: fixed;
			width: 100%;
			height: 100%;
			background-color: #000000;
			opacity: 0.9;
		}
		#login_container {
			position: fixed;
			width: 100%;
			height: 100%;
			display: flex;
			align-items: center;
			justify-content: center;
		}
		#login {
			position: fixed;
			width: 300px;
			height: 460px;
			background-color: #666666;
			border-radius: 5px;
			box-shadow: 10px;
			display: flex;
			flex-flow: column;
			align-items: flex-start;
		}
		#login_top {
			color: #CCCCCC;
			width: 100%;
			height: 50px;
			display: flex;
			flex-flow: column;
			align-items: center;
			justify-content: center;
		}
		.login_info {
			margin-top: 10px;
			width: 100%;
			height: 50px;
			display: flex;
			align-items: center;
			justify-content: center;
			background-color: #333333;
		}
		#lodding {
			margin-top: 10px;
			width: 100%;
			height: 40px;
			display: none;
			align-items: center;
			justify-content: center;
		}
		#submit {
			cursor: pointer;
			color: #CCCCCC;
		}
		#submit:hover {
			background-color: #666666;
		}
		input {
			width: 100%;
			height: 40px;
			background-color: #333333;
			padding: 0 59px;
		}
		select {
			padding: 0 55px;
			color: #CCCCCC;
			width: 100%;
			height: 40px;
			background-color: #333333;
		}
		option {
			color: #CCCCCC;
		}
		</style>
		<script type="text/javascript">
			var code;
			function createCode(){
				code="";
				var codeLength=4;
				var checkCode=document.getElementById("checkNode");
				var str="23QWERTYUIOPASDFGHJKLZXCVBNM1456789zxcvbnmasdfghjklqwertyuiop";
				for(var i=0;i<codeLength;i++){
					var charIndex=Math.floor(Math.random()*str.length);
					code += str[charIndex];
				}
				if (code.length != codeLength) {
					createCode();
				}
				checkCode.value=code;
			}
			function check(){
				if(document.login.role.value=="-1")
				{
					alert("请选择登陆身份");
					document.login.role.focus();
					return false;
				}
				 if(document.login.username.value=="")
				 {
					alert("请输入用户名");
					document.login.username.focus();
					return false;
				 }
				 if(document.login.password.value=="")
				 {
					alert("请输入密码");
					document.login.password.focus();
					return false;
				 }
				 if(document.login.checkcode.value=="")
				 {
				 	alert("请输入验证码");
				 	document.login.checkcode.focus();
				 	return false;
				 }else{
					if(document.login.checkcode.value.toLowerCase() == document.login.checkNode.value.toLowerCase()){
						$("#lodding").css({"display":"flex"});
						var pwd = document.login.password.value;
						alert(pwd);
						document.login.password.value = hash(pwd);
						alert(document.login.password.value);
						setTimeout(function(){
							document.login.submit();
						},3000);
					}else{
						alert("验证码输入错误！");
						createCode();
						document.login.checkcode.focus();
						return false;
					}
				 }
			}
			
		</script>
		<script>
			$(document).ready(function() {
			//粒子背景特效
				$("#login_container").particleground({
					dotColor: '#A9E4E9',
					lineColor: '#CCCCCC'
				});
			});
		</script>
	</head>
	<body onload="createCode()">
		<form name="login" action="${ctx }/login" method="post">
		<input type="hidden" name="action" value="login">
		<div id="back"></div>
		<div id="login_container">
			<div id="login">
				<div id="login_top">
					<label>学生信息管理系统登录</label>
					<label>Information Management System</label>
				</div>
				<div class="login_info">
					<select name="role">
						<option value="-1">请选择登录身份</option>
						<option value="0">学生</option>
						<option value="1">教师</option>
						<option value="2">教务员</option>
					</select>
				</div>
				<div class="login_info">
					<input name="username" type="text" placeholder="请输入用户名" autocomplete="off" />
				</div>
				<div class="login_info">
					<input name="password" type="password" placeholder="请输入密码" />
				</div>
				<div class="login_info" id="chk">
					<input readonly="readonly" id="checkNode" name="checkNode" class="code" onclick="createCode()" style="cursor: pointer; color: #CCCCCC;"/>
				</div>
				<div class="login_info">
					<input name="checkcode" type="text" autocomplete="off" placeholder="请输入验证码"/>
				</div>
				<div id="submit" class="login_info" onclick="check()">登录</div>
				<div id="lodding">
					<div class="spinner">
						<div class="spinner-container container1">
						<div class="circle1"></div>
						<div class="circle2"></div>
						<div class="circle3"></div>
						<div class="circle4"></div>
					</div>
					<div class="spinner-container container2">
						<div class="circle1"></div>
						<div class="circle2"></div>
						<div class="circle3"></div>
						<div class="circle4"></div>
					  </div>
					  <div class="spinner-container container3">
						<div class="circle1"></div>
						<div class="circle2"></div>
						<div class="circle3"></div>
						<div class="circle4"></div>
					  </div>
					</div>
				</div>
			</div>
		</div>
		</form>
	</body>
</html>