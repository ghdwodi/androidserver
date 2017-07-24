<%@page import="com.hb.am.VO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hb.am.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>List</title>
	</head>
	<body>
		<% 
			DAO dao = new DAO();
			ArrayList<VO> voList = dao.getSelectAll();
			request.setAttribute("voList", voList);
		%>
		<table>
			<tr>
				<th>IDX</th>
				<th>ID</th>
				<th>PWD</th>
				<th>NAME</th>
				<th>AGE</th>
				<th>ADDR</th>
			</tr>
			<c:forEach items="${voList}" var="k">
				<tr>
					<td>${k.idx}</td>
					<td>${k.id}</td>
					<td>${k.pwd}</td>
					<td>${k.name}</td>
					<td>${k.age}</td>
					<td>${k.addr}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>