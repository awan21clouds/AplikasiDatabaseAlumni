<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="view-alumni">
        <div class="page-header">
            <h1>Data Alumni</h1>
        </div>
        <div class="pull-right"><input type="text" id="searchBox"/></div>
        <div class="pull-left">
            <select id="view-rows" style="width: 100px;">
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="50">50</option>
                <option value="100">100</option>
                <option value="Semua">Semua</option>
            </select>
        </div>
        <table class="table table-striped table-bordered table-hover" id="tableAlumni">
            <thead>  
                <tr>  
                    <th style="text-align: center">No</th>  
                    <th style="text-align: center">Angkatan</th>  
                    <th style="text-align: center">NIM</th>  
                    <th style="text-align: center">Nama</th>  
                    <th style="text-align: center">IPK</th>  
                    <th style="text-align: center">Predikat</th>  
                    <th style="text-align: center">Tanggal Yudisium</th>  
                    <th style="text-align: center">Periode Wisuda</th>  
                    <th style="text-align: center">No. SK Kelulusan</th>  
                </tr>  
            </thead>
            <tbody></tbody>
        </table>
        <div class="pull-left">
            <div id="pagination" class="pagination"></div>
        </div>
        <div class="pull-right"><input type="button" id="buttonPrint" class="btn" value="Ekspor ke Excel" style="margin-top: 20px;"/></div>
        
    </div>
</div>
<%@include file="footer.jsp"%>