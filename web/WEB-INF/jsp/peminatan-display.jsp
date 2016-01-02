<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="view-peminatan">
        <div class="page-header">
            <h1>Peminatan</h1>
        </div>
        <div class="pull-right"><input type="text" id="searchBox"/></div>
        <div class="pull-left">
            <select id="view-rows" style="width: 60px;">
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
            <a href="peminatan-simpan.htm" class="btn"><span class="icon-plus-sign"></span> Peminatan</a>
        </div>
        <table class="table table-striped table-bordered table-hover" id="tablePeminatan">
            <thead>  
                <tr>
                    <th style="text-align: center">ID Peminatan</th>
                    <th style="text-align: center">Nama Peminatan</th>
                    <th style="text-align: center">Inisial Peminatan</th>
                    <th style="text-align: center">Prodi Peminatan</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
        <div class="pull-left" style="margin-top: -15px">
            <div id="pagination" class="pagination"></div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>