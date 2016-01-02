<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="studi-alumni">
        <div class="page-header">
            <h1>Studi</h1>
        </div>
        <input type="hidden" id="nim" value="<%=session.getAttribute("username")%>"/>
        <form:form method="POST" cssClass="form-horizontal">
            <fieldset>
                <div class="clearfix">
                    <label>Universitas</label>
                    <div class="input">
                        <input type="text" name="universitas" value="${studi.universitas}" disabled/>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Fakultas</label>
                    <div class="input">
                        <input type="text" name="fakultas" value="${studi.fakultas}" disabled/>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Program Studi</label>
                    <div class="input">
                        <input type="text" name="prodi" value="${studi.prodi}" disabled/>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Tanggal Masuk</label>
                    <div class="input">
                        <div class="input-append date" id="tanggalMasukStudi" data-date-format="yyyy-mm-dd">
                            <input type="text" name="tanggalMasuk" value="${studi.tanggalMasuk}" class="span2" readonly/>
                            <span class="add-on" style="height: 20px;"><i class="icon-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Tanggal Keluar</label>
                    <div class="input">
                        <div class="input-append date" id="tanggalKeluarStudi" data-date-format="yyyy-mm-dd">
                            <input type="text" name="tanggalKeluar" value="${studi.tanggalKeluar}" class="span2" readonly/>
                            <span class="add-on" style="height: 20px;"><i class="icon-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="actions">
                    <input type="submit" value="Simpan" id="saveButtonStudi" class="btn primary"/>
                    <input type="button" value="Update" id="updateButtonStudi" class="btn danger"/>
                    <input type="button" value="History" id="historyButtonStudi" class="btn"/>
                </div>
            </fieldset>
        </form:form>
        <div id="historyStudi">
            <table class="table table-striped table-bordered table-hover" id="tableHistoryStudi">
                <thead>  
                    <tr>  
                        <th style="text-align: center">Universitas</th>  
                        <th style="text-align: center">Fakultas</th>  
                        <th style="text-align: center">Program Studi</th>  
                        <th style="text-align: center">Tanggal Masuk</th>  
                        <th style="text-align: center">Tanggal Keluar</th>  
                    </tr>  
                </thead>
                <tbody></tbody>
            </table>
            <div style="margin-bottom: 80px">
                <div class="pull-left" style="margin-top: -15px">
                    <div id="paginationStudi" class="pagination"></div>
                </div>
                <div class="pull-right">
                    <input type="button" value="Kembali" id="currentButtonStudi" class="btn"/>
                </div>
            </div>
        </div>
        <div class="alert">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Warning!</strong>
            <span></span>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>