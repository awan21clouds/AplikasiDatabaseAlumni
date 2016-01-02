<%-- 
    Document   : rekapitulasi-grafik
    Created on : 17 Agu 13, 5:54:50
    Author     : FAHMI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="rekapitulasi-grafik">
        <div class="page-header">
            <h1>Grafik</h1>
        </div>
        <div id="navGrafik">
            <select name="grafik" style="margin-right: 50%">
                <option value="1">Total</option>
                <option value="2">IPK<2.75</option>
                <option value="3">IPK>=2.75</option>
            </select>
            <select name="data" style="margin-right: 50%">
                <option value="1">Prodi</option>
                <option value="2">Peminatan</option>
            </select>
            <select name="sorting">
                <option value="1">Total</option>
                <option value="4">Per Tahun Angkatan</option>
                <option value="2">Per Tahun Wisuda</option>
                <option value="3">Per Periode Wisuda</option>
            </select>
            <input type="button" class="btn primary" id="printButton" value="Ekspor ke Pdf">
        </div>
        <!--script src="<@c:url value="/bootstrap/js/exporting.js"/>"></script-->
        <div id="all">
            <div class="grafikProdi">
                <c:forEach items="${prodi}" var="prodi">
                    <div id="${prodi.nama}" style="width: 600px; height: 400px; text-align: center; margin-left: auto; margin-right: auto"></div>
                </c:forEach>
            </div>
            <div class="grafikPeminatan">
                <c:forEach items="${peminatan}" var="peminatan">
                    <div id="${peminatan.nama}" style="width: 600px; height: 400px; text-align: center; margin-left: auto; margin-right: auto"></div>
                </c:forEach>
            </div>
        </div>
        <div id="thnWisuda">
            <div class="grafikProdi">
                <c:forEach items="${prodi}" var="prodi">
                    <div id="${prodi.nama} Tahun" style="width: 600px; height: 400px; text-align: center; margin-left: auto; margin-right: auto"></div>
                </c:forEach>
            </div>
            <div class="grafikPeminatan">
                <c:forEach items="${peminatan}" var="peminatan">
                    <div id="${peminatan.nama} Tahun" style="width: 600px; height: 400px; text-align: center; margin-left: auto; margin-right: auto"></div>
                </c:forEach>
            </div>
        </div>
        <div id="tglWisuda">
            <div class="grafikProdi">
                <c:forEach items="${prodi}" var="prodi">
                    <div id="${prodi.nama} Periode" style="width: 600px; height: 400px; text-align: center; margin-left: auto; margin-right: auto"></div>
                </c:forEach>
            </div>
            <div class="grafikPeminatan">
                <c:forEach items="${peminatan}" var="peminatan">
                    <div id="${peminatan.nama} Periode" style="width: 600px; height: 400px; text-align: center; margin-left: auto; margin-right: auto"></div>
                </c:forEach>
            </div>
        </div>
        <div id="angkt">
            <div class="grafikProdi">
                <c:forEach items="${prodi}" var="prodi">
                    <div id="${prodi.nama} Angkatan" style="width: 600px; height: 400px; text-align: center; margin-left: auto; margin-right: auto"></div>
                </c:forEach>
            </div>
            <div class="grafikPeminatan">
                <c:forEach items="${peminatan}" var="peminatan">
                    <div id="${peminatan.nama} Angkatan" style="width: 600px; height: 400px; text-align: center; margin-left: auto; margin-right: auto"></div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
