<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cs.listener.showonline.ApplicationConstants" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="com.cs.listener.showonline.PersonInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	body, td {font-size: 12px; }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ShowOnline</title>
</head>
<body>
	服务器启动时间:<%=DateFormat.getTimeInstance().format(ApplicationConstants.START_DATE)%>
	累计接待:<%=ApplicationConstants.TOTAL_HISTORY_COUNT%>访客<br>
	同时在线人数最多为<%=ApplicationConstants.MAX_ONLINE_COUNT %>
	发生在<%=DateFormat.getTimeInstance().formatToCharacterIterator(ApplicationConstants.MAX_ONLINE_COUNT_DATE) %>
	目前在线人数:<%=ApplicationConstants.SESSION_MAP.size() %>,登陆用户：<%=ApplicationConstants.CURRENT_LOGIN_COUNT %>
	<table border=1>
		<tr>
			<th>jsessionid</th>
			<th>account</th>
			<th>creationTime</th>
			<th>lastAccessedTime</th>
			<th>new</th>
			<th>activeTimes</th>
			<th>ip</th>
		</tr>
		<%
			for(String id:ApplicationConstants.SESSION_MAP.keySet()){
				HttpSession sess=ApplicationConstants.SESSION_MAP.get(id);
				PersonInfo personInfo=(PersonInfo)sess.getAttribute("personInfo");
			
		%>
		<tr <%= session == sess ? "bgcolor=#DDDDDD" : "" %>>
		<td><%=id%></td>
		<td><%=personInfo==null ? "&nbsp;" : personInfo.getName()%></td>
		<td><%=DateFormat.getDateTimeInstance().format(sess.getCreationTime())%></td>
		<td><%=DateFormat.getDateTimeInstance().format(new Date(sess.getLastAccessedTime()))%></td>
		<td><%=sess.isNew()%></td>
		<td><%=sess.getAttribute("activeTimes")%></td>
		<td><%=sess.getAttribute("ip") %></td>
	</tr>
		<% }%>
	</table>
</body>
</html>