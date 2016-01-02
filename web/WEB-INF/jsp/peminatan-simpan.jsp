<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="input-peminatan">
        <div class="page-header">
            <h1>Masukkan Data Peminatan</h1>
        </div>
        <form:form cssClass="form-horizontal" method="post">
            <fieldset>
                <div class="clearfix">
                    <label>Nama</label>
                    <div class="input">
                        <input type="text" name="nama" value=""/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Inisial</label>
                    <div class="input">
                        <input type="text" name="inisial" value=""/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Prodi</label>
                    <div class="input">
                        <select name="Prodi.idProdi"></select>
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