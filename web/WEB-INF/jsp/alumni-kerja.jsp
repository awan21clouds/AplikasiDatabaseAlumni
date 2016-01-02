<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="kerja-alumni">
        <div class="page-header">
            <h1>Kerja</h1>
        </div>
        <input type="hidden" id="nim" value="<%=session.getAttribute("username")%>"/>
        <form:form method="POST" cssClass="form-horizontal">
            <fieldset>
                <div class="clearfix">
                    <label>Perusahaan</label>
                    <div class="input">
                        <input type="text" name="perusahaan" value="${kerja.perusahaan}" disabled/>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Posisi Kerja</label>
                    <div class="input">
                        <select style="text-transform: capitalize; width:300px;" disabled>
                            <option></option>    
                            <c:forEach items="${posisiKerja}" var="posisiKerja" >
                                <c:if test="${posisiKerja.idPosisiKerja==kerja.posisikerja.idPosisiKerja}">
                                    <option value="${posisiKerja.idPosisiKerja}" selected>${posisiKerja.namaPosisi}</option>
                                </c:if>
                                <c:if test="${posisiKerja.idPosisiKerja!=kerja.posisikerja.idPosisiKerja}">
                                    <option value="${posisiKerja.idPosisiKerja}" >${posisiKerja.namaPosisi}</option>
                                </c:if>
                            </c:forEach>
                            <option value="0">LAINNYA</option>    
                        </select>
                    </div>
                </div>
                <div class="clearfix" id="posisiKerjaClearFix">
                    <label></label>
                    <div class="input">
                        <input type="text" id="posisiKerjaText" disabled/>
                        <input type="hidden" name="posisiKerja" id="posisiKerja"/>  
                    </div>
                </div>
                <div class="clearfix">
                    <label>Tanggal Masuk</label>
                    <div class="input">
                        <div class="input-append date" id="tanggalMasukKerja" data-date-format="yyyy-mm-dd">
                            <input type="text" name="tanggalMasuk" value="${kerja.tanggalMasuk}" class="span2" readonly/>
                            <span class="add-on" style="height: 20px;"><i class="icon-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Tanggal Keluar</label>
                    <div class="input">
                        <div class="input-append date" id="tanggalKeluarKerja" data-date-format="yyyy-mm-dd">
                            <input type="text" name="tanggalKeluar" value="${kerja.tanggalKeluar}" class="span2" readonly/>
                            <span class="add-on" style="height: 20px;"><i class="icon-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Gaji</label>
                    <div class="input">
                        <input type="text" name="gaji" value="${kerja.gaji}" disabled/>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Status Pegawai</label>
                    <div class="input">
                        <select name="jeniskerja.idJenisKerja" disabled>
                            <c:forEach items="${jenisKerja}" var="jenisKerja" >
                                <c:if test="${jenisKerja.idJenisKerja==kerja.jeniskerja.idJenisKerja}">
                                    <option value="${jenisKerja.idJenisKerja}" selected>${jenisKerja.jenisKerja}</option>
                                </c:if>
                                <c:if test="${jenisKerja.idJenisKerja!=kerja.jeniskerja.idJenisKerja}">
                                    <option value="${jenisKerja.idJenisKerja}" >${jenisKerja.jenisKerja}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="actions">
                    <input type="submit" value="Simpan" id="saveButtonKerja" class="btn primary"/>
                    <input type="button" value="Update" id="updateButtonKerja" class="btn danger"/>
                    <input type="button" value="History" id="historyButtonKerja" class="btn"/>
                </div>
            </fieldset>
        </form:form> 
        <div id="historyKerja">
            <table class="table table-striped table-bordered table-hover" id="tableHistoryKerja">
                <thead>  
                    <tr>  
                        <th style="text-align: center">Perusahaan</th>  
                        <th style="text-align: center">Posisi Kerja</th>  
                        <th style="text-align: center">Status Kerja</th>  
                        <th style="text-align: center">Tanggal Masuk</th>  
                        <th style="text-align: center">Tanggal Keluar</th>  
                    </tr>  
                </thead>
                <tbody></tbody>
            </table>
            <div style="margin-bottom: 80px">
                <div class="pull-left" style="margin-top: -15px">
                    <div id="paginationKerja" class="pagination"></div>
                </div>
                <div class="pull-right">
                    <input type="button" value="Kembali" id="currentButtonKerja" class="btn"/>
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