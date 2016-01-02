<%-- 
    Document   : header
    Created on : 27 Jun 13, 11:41:19
    Author     : FAHMI
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="title"/></title>
        <%@include file="include.jsp"%>
        <style>
            html,
            body {
                height: 100%;
            }
            #wrap {
                min-height: 100%;
                height: auto !important;
                height: 100%;
                margin: 0 auto -60px;
            }
            #footer {
                height: 60px;
            }
            #footer {
                background-color: #f5f5f5;
            }
            @media (max-width: 767px) {
                #footer {
                margin-left: -20px;
                margin-right: -20px;
                padding-left: 20px;
                padding-right: 20px;
                }
            }

            #wrap > .container {
                padding-top: 60px;
            }
            .container .credit {
                margin: 20px 0;
            }

            code {
                font-size: 80%;
            }
        </style>
    </head>
    <body>
        <%
        if(session.getAttribute("username")==null||session.getAttribute("password")==null||session.getAttribute("status")==null||session.getAttribute("level")==null){
            response.sendRedirect("login.htm");
        }else{
            if(Integer.parseInt(session.getAttribute("level").toString())==1){%>
                <%@include file="menu-admin.jsp"%>
            <%}else if(Integer.parseInt(session.getAttribute("level").toString())==2){%>
                <%@include file="menu-pegawai.jsp"%>
            <%}else{%>
                <%@include file="menu-alumni.jsp"%>
            <%}
        }
        %>