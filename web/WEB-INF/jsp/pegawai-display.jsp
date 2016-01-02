<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="view-pegawai">
        <div class="page-header">
            <h1>Data Pegawai</h1>
        </div>
        <div class="pull-right"><input type="text" id="searchBox"/></div>
        <table class="table table-striped table-bordered table-hover" id="tablePegawai">
            <thead>  
                <tr>  
                    <th style="text-align: center">Kode Pegawai</th>
                    <th style="text-align: center">Nama Pegawai</th>
                    <th style="text-align: center">Telepon Pegawai</th>
                    <th style="text-align: center">Email Pegawai</th>
                    <th style="text-align: center">Status Pegawai</th>
                    <th></th>
                </tr>  
            </thead>
            <tbody></tbody>
        </table>
        <div id="pagination" class="pagination"></div>
    </div>
</div>
<%@include file="footer.jsp"%>