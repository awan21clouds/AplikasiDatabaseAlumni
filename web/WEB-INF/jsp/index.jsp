<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="title"/></title>
        <%@include file="include.jsp"%>
    </head>
    <body>
        <%
        if(session.getAttribute("username")==null||session.getAttribute("password")==null||session.getAttribute("status")==null||session.getAttribute("level")==null){
            response.sendRedirect("login.htm");
        }else{
            if(Integer.parseInt(session.getAttribute("level").toString())<3){
                response.sendRedirect("pegawai-main.htm");
            }else{
                response.sendRedirect("alumni-main.htm");
            }
        }
        %>
       
    </body>
</html>