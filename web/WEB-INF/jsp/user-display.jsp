<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="view-user">
        <div class="page-header">
            <h1>Data User</h1>
        </div>
        <form:form method="post" name="tableUser">
            <input type="hidden" name="username"/>
            <input type="hidden" name="level"/>
            <div class="pull-left">
                <a href="pegawai-simpan.htm" class="btn"><span class="icon-plus-sign"></span> Pegawai</a>
                <a href="alumni-simpan.htm" class="btn"><span class="icon-plus-sign"></span> Alumni</a>
            </div>
            <div class="pull-right"><input type="text" id="searchBox"/></div>
            <table class="table table-striped table-bordered table-hover" id="tableUser">
                <thead>
                    <tr>
                        <th style="text-align: center">Username</th>
                        <th>Nama</th>
                        <th style="text-align: center">Tanggal Aktif</th>
                        <th style="text-align: center">Tanggal Login Terakhir</th>
                        <th style="text-align: center">Status</th>
                        <th style="text-align: center">Level</th>
                        <th style="text-align: center"></th>
                    </tr>   
                </thead>
                <tbody></tbody>
            </table>
            <div id="pagination" class="pagination"></div>
        </form:form>
    </div>
</div>
<%@include file="footer.jsp"%>