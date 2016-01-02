<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="view-jeniskontak">
        <div class="page-header">
            <h1>Jenis Kontak</h1>
        </div>
        <div class="pull-right"><input type="text" id="searchBox"/></div>
        <div class="pull-left">
            <select id="view-rows" style="width: 60px;">
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
            <a href="jeniskontak-simpan.htm" class="btn"><span class="icon-plus-sign"></span> Kontak</a>
        </div>
        <table class="table table-striped table-bordered table-hover" id="tableJenisKontak">
            <thead> 
                <tr>
                    <th style="text-align: center">ID Jenis Kontak</th>
                    <th style="text-align: center">Jenis Kontak</th>
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