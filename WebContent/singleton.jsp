<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="com.cs.listener.singleton.PersonInfo"%>
<%
	String strUrl = request.getRequestURI();
	String action=request.getParameter("action");
	String account=request.getParameter("account");
	PersonInfo personInfo = null;
	if("login".equals(action)&& account.trim().length()>0){
		personInfo=new PersonInfo();
		personInfo.setAccount(account.trim().toLowerCase());
		personInfo.setIp(request.getRemoteAddr());
		personInfo.setLoginDate(new Date());
		
		session.setAttribute("personInfo", personInfo);
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
		return;
	}else if("logout".equals(action)){
		session.removeAttribute("personInfo");
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Singleton Login</title>
<style type="text/css">
body {
	font-size: 12px;
}
</style>
<script type="text/javaScript">
var logInfoDiv = document.getElementById("LogInfo");
var loginInfo = <%=personInfo%>;
if(loginInfo!=null){
	
	
}else{
	"<form action='<%=%>'>"
}
</script>
</head>
<body>
	<div id="logInfo"></div>
				欢迎您，${ personInfo.account }。<br /> 
				您的登录IP为${ personInfo.ip }，<br />
				<a href="${ pageContext.request.requestURI }?action=logout">退出</a>
			<script>setTimeout("location=location; ", 3000); </script>

				${ msg } 
			<form action="${ pageContext.request.requestURI }?action=login"
				method="post">
				帐号： <input name="account" /> <input type="submit" value="登录">
			</form>


</body>
</html>