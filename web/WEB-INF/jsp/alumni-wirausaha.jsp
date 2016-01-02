<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="wirausaha-alumni">
        <div class="page-header">
            <h1>Wirausaha</h1>
        </div>
        <input type="hidden" id="nim" value="<%=session.getAttribute("username")%>"/>
        <form:form method="POST" cssClass="form-horizontal">
            <fieldset>
                <div class="clearfix">
                    <label>Perusahaan</label>
                    <div class="input">
                        <input type="text" name="namaPerusahaan" value="${wirausaha.namaPerusahaan}" disabled/>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Jenis Usaha</label>
                    <div class="input">
                        <input type="text" name="jenisUsaha" value="${wirausaha.jenisUsaha}" disabled/>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Tanggal Mulai</label>
                    <div class="input">
                        <div class="input-append date" id="tanggalMulaiWirausaha" data-date-format="yyyy-mm-dd">
                            <input type="text" name="tanggalMulai" value="${wirausaha.tanggalMulai}" class="span2" readonly/>
                            <span class="add-on" style="height: 20px;"><i class="icon-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="actions">
                    <input type="submit" value="Simpan" id="saveButtonWirausaha" class="btn primary"/>
                    <input type="button" value="Update" id="updateButtonWirausaha" class="btn danger"/>
                    <input type="button" value="History" id="historyButtonWirausaha" class="btn"/>
                </div>    
            </fieldset>
        </form:form>
        <div id="historyWirausaha">
            <table class="table table-striped table-bordered table-hover" id="tableHistoryWirausaha">
                <thead>  
                    <tr>  
                        <th style="text-align: center">Perusahaan</th>  
                        <th style="text-align: center">Jenis Usaha</th>  
                        <th style="text-align: center">Tanggal Mulai</th>  
                    </tr>  
                </thead>
                <tbody></tbody>
            </table>
            <div style="margin-bottom: 80px">
                <div class="pull-left" style="margin-top: -15px">
                    <div id="paginationWirausaha" class="pagination"></div>
                </div>
                <div class="pull-right">
                    <input type="button" value="Kembali" id="currentButtonWirausaha" class="btn"/>
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