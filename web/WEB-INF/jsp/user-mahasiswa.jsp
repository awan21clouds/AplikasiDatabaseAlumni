<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="user-mahasiswa">
        <div class="page-header">
            <h1>Data User Mahasiswa</h1>
        </div>
        <form:form method="post" name="tableUser">
            <input type="hidden" name="username"/>
            <input type="hidden" name="level"/>
            <div class="pull-right"><input type="text" id="searchBox"/></div>
            <div class="pull-left">
                <select id="view-rows" style="width: 60px;">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="50">50</option>
                    <option value="100">100</option>
                </select>
            </div>
            <table class="table table-striped table-bordered table-hover" id="tableUser">
                <thead>
                    <tr>
                        <th style="text-align: center">Username</th>
                        <th style="text-align: center">Nama</th>
                        <th style="text-align: center">Tanggal Aktif</th>
                        <th style="text-align: center">Login Terakhir</th>
                        <th style="text-align: center">Status</th>
                        <th style="text-align: center">Level</th>
                    </tr>   
                </thead>
                <tbody></tbody>
            </table>
            <div class="pull-left">
                <div id="pagination" class="pagination"></div>
            </div>
            <div class="pull-right"></div>
            
        </form:form>
    </div>
</div>
<%@include file="footer.jsp"%>