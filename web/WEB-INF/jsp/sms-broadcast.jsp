<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="sms-broadcast">
        <div class="page-header">
            <h1>Broadcast Pesan</h1>
        </div>
        <form:form method="post">
            <textarea id="pesan" rows="5" name="pesan" style="width: 99%; resize: none; margin-bottom: 20px; font-size: 14px; padding-right: 5px;" placeholder="Tulis Pesan Disini"></textarea>
            <span id="pesanLength" class="help-block" style="margin-top: -15px;">0/160</span>
            <div class="pull-left">
                <select id="view-rows" style="width: 100px;">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="50">50</option>
                    <option value="100">100</option>
                    <option value="Semua">Semua</option>
                </select>
                <input type="button" class="btn" id="pilihKontak" value="Pilih Semua Kontak">
                <input type="button" class="btn" id="putusKontak" value="Hapus Semua Pilihan">
                <input type="submit" id="submit" value="Kirim" class="btn primary" />
            </div>
            <div class="pull-right"><input type="text" id="searchBox"/></div>
            <table class="table table-striped table-bordered table-hover" id="tableKontakAlumni">
                <thead>
                    <tr>
                        <th style="text-align: center">Nim</th>
                        <th style="text-align: center">Angkatan</th>
                        <th style="text-align: center">Nama</th>
                        <th style="text-align: center">Program Studi</th>
                        <th style="text-align: center">Peminatan</th>
                        <th style="text-align: center">Kontak</th>
                        <th style="text-align: center">Status</th>
                        <th style="text-align: center">Posisi</th>
                        <th style="text-align: center">IPK</th>
                        <th style="text-align: center"></th>
                    </tr>   
                </thead>
                <tbody></tbody>
            </table>
            <div class="pull-left">
                <div id="pagination" class="pagination"></div>
                <span id="currentPage" style="display: none"></span>
            </div>
        </form:form>
        <div class="alert" style="margin-top: 100px;">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Warning!</strong>
            <span></span>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>