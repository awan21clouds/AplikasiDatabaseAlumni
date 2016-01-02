<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="input-pegawai">
        <div class="page-header">
            <h1>Masukkan Data Pegawai</h1>
        </div>
        <form:form>
            <fieldset>
                <div class="clearfix">
                    <label>Kode Pegawai</label>
                    <div class="input">
                        <input type="text" name="idPegawai" value=""/>
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Nama Pegawai</label>
                    <div class="input">
                        <input type="text" name="nama" value=""/>
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="clearfix">
                    <label>No Telepon</label>
                    <div class="input">
                        <input type="text" name="telepon" value=""/>
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Email</label>
                    <div class="input">
                        <input type="text" name="email" value=""/>
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Level</label>
                    <div class="input">
                        <select name="level">
                            <option value="1">Admin</option>
                            <option value="2">Pegawai</option>
                        </select>
                    </div>
                </div>
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