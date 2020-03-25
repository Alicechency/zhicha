<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String bp = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>

<link type="text/css" rel="stylesheet" href="<%=bp%>/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="<%=bp%>/css/sticky-footer-navbar.css" type="text/css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="<%=bp%>/css/AdminLTE.min.css">
<link type="text/css" rel="stylesheet" href="<%=bp%>/css/morris.css">
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">

<script src="<%=bp%>/dep/jquery-3.2.1.min.js"></script>
<script src="<%=bp%>/js/JWUtils.js"></script>