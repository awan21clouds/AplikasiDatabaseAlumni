<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="studi-display">
        <div class="page-header">
            <h1>Data Studi Lanjut</h1>
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
        <table class="table table-striped table-bordered table-hover" id="tableStudiDisplay">
            <thead>  
                <tr>  
                    <th style="text-align: center">No</th>  
                    <th style="text-align: center">NIM</th>  
                    <th style="text-align: center">Prodi</th>  
                    <th style="text-align: center">Nama</th>  
                    <th style="text-align: center">Universitas</th>  
                    <th style="text-align: center">Fakultas</th>  
                    <th style="text-align: center">Jurusan</th>  
                    <th style="text-align: center">Mulai Studi</th>  
                    <th style="text-align: center">Keluar Studi</th>  
                    <th style="text-align: center">Core</th>  
                    <th style="text-align: center">Yudisium</th>  
                    <th style="text-align: center">Masa Wisuda</th>  
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