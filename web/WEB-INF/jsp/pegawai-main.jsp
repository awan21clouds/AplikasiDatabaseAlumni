<%-- 
    Document   : pegawai-main
    Created on : 27 Jun 13, 11:54:45
    Author     : FAHMI
--%>
<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="main-pegawai">
        <div class="page-header">
            <h1>Beranda</h1>
        </div>
        Selamat Datang <%=session.getAttribute("nama")%>
    </div>
</div>
<%@include file="footer.jsp"%>