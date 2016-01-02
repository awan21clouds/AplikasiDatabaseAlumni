<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="profil-pegawai">
        <div class="page-header">
            <h1>Profil</h1>
        </div>
        <!--ul class="thumbnails">
            <li class="span4">
                <a href="#" class="thumbnail">
                    <img src="<!c:url value="/resources/bootstrap/js/holder/holder.js/200x120"/>">
                </a>
            </li>
        </ul-->
        <form:form method="POST" commandName="pegawai" cssClass="form-horizontal">
            <fieldset>
                <div class="clearfix">
                    <label>Kode Pegawai</label>
                    <div class="input">
                        <input type="text" value="${pegawai.idPegawai}" disabled/>
                        <input type="hidden" name="idPegawai" value="${pegawai.idPegawai}"/>
                        <input type="hidden" name="status" value="${pegawai.status}"/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Nama Pegawai</label>
                    <div class="input">
                        <input type="text" name="nama" value="${pegawai.nama}" disabled/><br/>
                        <span class="help-block"></span>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Telepon</label>
                    <div class="input">
                        <input type="text" name="telepon" value="${pegawai.telepon}" disabled/>
                        <span class="help-block"></span>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Email</label>
                    <div class="input">
                        <input type="text" name="email" value="${pegawai.email}" disabled/>
                        <span class="help-block"></span>
                    </div>
                </div><!-- /clearfix -->
                <div class="actions">
                    <input type="submit" value="Simpan" id="saveButton" class="btn primary"/>
                    <input type="button" value="Update" id="updateButton" class="btn danger"/>
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