<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="detail-alumni2">
        <div class="page-header">
            <h1>Detail Alumni</h1>
        </div>
        <div class="page-header">
            <h3>Kontak</h3>
        </div>
        <form:form method="POST" cssClass="form-horizontal" action="alumni-detail2-kontak.htm">
            <fieldset>
                <input type="hidden" name="nim"/>
                <c:forEach items="${kontak}" var="kontak">
                    <div class="clearfix">
                        <label>${kontak.jeniskontak.jenisKontak}</label>
                        <div class="input">
                            <input type="text" name="kontak" value="${kontak.kontak}" disabled/>
                            <c:if test="${kontak.jeniskontak.jenisKontak=='Telepon'}"><i>(+62xxxxxxx)</i></c:if>
                            <input type="hidden" name="jenisKontak" value="${kontak.jeniskontak.idJenisKontak}"/>
                        </div>
                    </div>
                </c:forEach>
                <div class="actions">
                    <input type="submit" value="Simpan" id="saveButtonKontak" class="btn primary"/>
                    <input type="button" value="Update" id="updateButtonKontak" class="btn danger"/>
                    <input type="button" value="History" id="historyButtonKontak" class="btn"/>
                </div>
            </fieldset>
        </form:form>
        <div id="historyKontak">
            <table class="table table-striped table-bordered table-hover" id="tableHistoryKontak">
                <thead>  
                    <tr>  
                        <th style="text-align: center">Jenis Kontak</th>  
                        <th style="text-align: center">Kontak</th>  
                    </tr>  
                </thead>
                <tbody></tbody>
            </table>
            <div style="margin-bottom: 80px">
                <div class="pull-left" style="margin-top: -15px">
                    <div id="paginationKontak" class="pagination"></div>
                </div>
                <div class="pull-right">
                    <input type="button" value="Kembali" id="currentButtonKontak" class="btn"/>
                </div>
            </div>
        </div>
        <div class="page-header">
            <h3>Studi</h3>
        </div>
        <form:form method="POST" cssClass="form-horizontal" action="alumni-detail2-studi.htm">
            <fieldset>
                <input type="hidden" name="nim"/>
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
        <div class="page-header">
            <h3>Kerja</h3>
        </div>
        <form:form method="POST" cssClass="form-horizontal" action="alumni-detail2-kerja.htm">
            <fieldset>
                <input type="hidden" name="nim"/>
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
        <div class="page-header">
            <h3>Wirausaha</h3>
        </div>
        <form:form method="POST" cssClass="form-horizontal" action="alumni-detail2-wirausaha.htm">
            <fieldset>
                <input type="hidden" name="nim"/>
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