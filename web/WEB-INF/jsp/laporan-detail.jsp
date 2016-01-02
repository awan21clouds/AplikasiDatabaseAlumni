<%@include file="header.jsp"%>
<div id="wrap" style="margin-bottom: 10%">
    <div class="container" id="detail-laporan">
        <table>
            <thead>  
                <tr>  
                    <th style="text-align: center">No</th>  
                    <th style="text-align: center">NIM</th>  
                    <th style="text-align: center">Nama</th>  
                    <th style="text-align: center">Angkatan</th>  
                    <th style="text-align: center">Program Studi</th>  
                    <th style="text-align: center">Peminatan</th>  
                    <th style="text-align: center">Periode Wisuda</th>  
                    <th style="text-align: center">Tanggal Yudisium</th>  
                    <th style="text-align: center">IPK</th>  
                </tr>  
            </thead>
            <tbody>
                <%int i=1;%>
                <c:forEach items="${detail}" var="detail">
                    <tr>
                        <td style="text-align: center"><%=i%></td>
                        <td style="text-align: center">${detail.id.nim}</td>
                        <td>${detail.id.nama}</td>
                        <td style="text-align: center">${detail.id.angkatan}</td>
                        <td>${detail.id.prodi}</td>
                        <td>${detail.id.peminatan}</td>
                        <td style="text-align: center">${detail.id.periodewisuda}</td>
                        <td style="text-align: center">${detail.id.tanggalYudisium}</td>
                        <td style="text-align: center">${detail.id.ipk}</td>
                    </tr>
                    <%i++;%>
                </c:forEach>
            </tbody>
        </table>
        <button class="btn" id="printButton">Cetak</button>
    </div>
</div>
<%@include file="footer.jsp"%>