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
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <div class="pull-left">
                        <a href="login.htm" class="brand"><span class="text-warning"><fmt:message key="title"/></span></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="container" id="login-error" style="margin-top: 100px">
            <div class="page-header">
                <h1>Gagal Masuk!</h1>
            </div>
            <form:form method="POST" cssClass="form-horizontal">
                <fieldset>
                    <div class="clearfix">
                        <label>Tanggal Mulai</label>
                        <div class="input">
                                <div class="input-prepend">
                                <span class="add-on" style="height: 20px;"><i class="icon-user"></i></span>
                                <input type="text" name="username" value="" placeholder="Username" style="width: 79%"/>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix">
                        <label>Tanggal Mulai</label>
                        <div class="input">
                                <div class="input-prepend" >
                                <span class="add-on" style="height: 20px;"><i class="icon-lock"></i></span>
                                <input type="password" name="password" value="" placeholder="Password" style="width: 79%"/>
                                <input type="hidden" name="lastLogin" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}"/>"/>
                            </div>
                        </div>
                    </div>
                    <div class="actions">
                        <input type="submit" class="btn primary" value="Masuk"/>
                    </div>    
                </fieldset>
            </form:form>
        </div>
    </body>
</html>