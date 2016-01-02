<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="view-prodi">
        <div class="page-header">
            <h1>Program Studi</h1>
        </div>
        <div class="pull-right"><input type="text" id="searchBox"/></div>
        <div class="pull-left">
            <select id="view-rows" style="width: 60px;">
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
            <a href="prodi-simpan.htm" class="btn"><span class="icon-plus-sign"></span> Program Studi</a>
        </div>
        <table class="table table-striped table-bordered table-hover" id="tableProdi">
            <thead>  
                <tr>
                    <th style="text-align: center">Kode Prodi</th>
                    <th style="text-align: center">Nama Prodi</th>
                    <th style="text-align: center">Insial Perodi</th>
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