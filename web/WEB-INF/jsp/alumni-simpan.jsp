<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="input-alumni">
        <div class="page-header">
            <h1>Masukkan Data Mahasiswa/Alumni</h1>
        </div>
        <form:form cssClass="form-horizontal" method="post" commandName="mahasiswa">
            <fieldset>
                <div class="clearfix">
                    <label>NIM</label>
                    <div class="input">
                        <input type="text" name="nim" value=""/>
                        <span class="help-block"></span>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Nama</label>
                    <div class="input">
                        <input type="text" name="nama" value=""/>
                        <span class="help-block"></span>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Prodi</label>
                    <div class="input">
                        <select id="prodi"></select>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Peminatan</label>
                    <div class="input">
                        <select id="peminatan" name="peminatan.idPeminatan"></select>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Tanggal Lahir</label>
                    <div class="input">
                        <div class="input-append date" id="tanggalLahir" data-date-format="yyyy-mm-dd">
                            <input type="text" name="tanggalLahir" class="span2" value="" readonly/>
                            <span class="add-on" style="height: 20px;"><i class="icon-calendar"></i></span>
                            <span class="help-block"></span>
                        </div>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Status</label>
                    <div class="input">
                        <select name="statusmahasiswa.idStatusMahasiswa" id="statusMahasiswa">
                            <option value="1">AKTIF</option>
                            <option value="3">LULUS</option>
                            <!--c:forEach items="$!{status}" var="status">
                                <option value="$!{status.idStatusMahasiswa}">$!{status.statusMahasiswa}</option>
                            <!--/c:forEach-->
                        </select>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix" id="periodeWisuda">
                    <label>Periode Wisuda</label>
                    <div class="input">
                        <select name="wisuda.idWisuda" disabled>
                            <c:forEach items="${wisuda}" var="wisuda">
                                <option value="${wisuda.idWisuda}">${wisuda.keterangan}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix" id="yudisium">
                    <label>Tanggal Yudisium</label>
                    <div class="input">
                        <div class="input-append date" id="tanggalYudisium" data-date-format="yyyy-mm-dd">
                            <input type="text" name="tanggalYudisium" class="span2" value="" readonly disabled/>
                            <span class="add-on" style="height: 20px;"><i class="icon-calendar"></i></span>
                            <span class="help-block"></span>
                        </div>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix" id="ipk">
                    <label>IPK</label>
                    <div class="input">
                        <select name="ipk" disabled>
                        </select>
                    </div>
                </div><!-- /clearfix -->
                <div class="actions">
                    <input type="submit" value="Simpan" class="btn primary"/>
                </div>
            </fieldset>
        </form:form>
        <div class="alert">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Warning!</strong><br/>
            <span></span>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>