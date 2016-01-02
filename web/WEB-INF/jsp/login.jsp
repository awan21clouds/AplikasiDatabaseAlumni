<%-- 
    Document   : login
    Created on : 27 Jun 13, 11:04:31
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
            html,body{height:100%;}
            .carousel,.item,.active{height:100%;}
            .carousel-inner{height:100%;}
            .fill{width:100%;height:100%;background-position:center;background-size:cover;}
        </style>
        <script type="text/javascript">
            $("document").ready(function() {
                $('#carousel').carousel();
            });
         </script>
    </head>
    <body>
        <%
            if(session.getAttribute("username")!=null||session.getAttribute("password")!=null||session.getAttribute("status")!=null||session.getAttribute("level")!=null){
                response.sendRedirect("index.htm");
            }
        %>
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <div class="pull-left">
                        <a href="index.htm" class="brand"><span class="text-warning"><fmt:message key="title"/></span></a>
                    </div>
                    <ul class="nav pull-left">
                        <li class="divider-vertical"></li>
                    </ul>
                    <div class="nav-collapse">
                        <ul class="nav pull-right">
                            <li class="divider-vertical"></li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" href="#" data-toggle="dropdown">Masuk</a>
                                <div class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
                                    <form:form>
                                        <div class="input-prepend">
                                            <span class="add-on" style="height: 20px;"><i class="icon-user"></i></span>
                                            <input type="text" name="username" value="" placeholder="Username" style="width: 79%"/>
                                        </div>
                                        <div class="input-prepend" >
                                            <span class="add-on" style="height: 20px;"><i class="icon-lock"></i></span>
                                            <input type="password" name="password" value="" placeholder="Password" style="width: 79%"/>
                                        </div>
                                        <input type="hidden" name="lastLogin" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}"/>"/>
                                        <input type="submit" class="btn primary" style="clear: left; width: 100%; height: 32px; font-size: 13px;" value="Masuk" style="margin-top: -10.5px;"/>
                                    </form:form>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="container fill" id="login">
            <div id="carousel" class="carousel slide">  
                <div class="carousel-inner">
                    <div class="item active">
                       <img src="<c:url value="/bootstrap/img/slide-01.jpg"/>"/>
                       <div class="container">
                            <div class="carousel-caption">
                                <h1></h1>
                                <p class="lead"></p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <img src="<c:url value="/bootstrap/img/slide-02.jpg"/>"/>
                        <div class="container">
                            <div class="carousel-caption">
                                <h1></h1>
                                <p class="lead"></p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <img src="<c:url value="/bootstrap/img/slide-03.jpg"/>"/>
                        <div class="container">
                            <div class="carousel-caption">
                                <h1></h1>
                                <p class="lead"></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
