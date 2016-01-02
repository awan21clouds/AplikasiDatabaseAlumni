<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="input-prodi">
        <div class="page-header">
            <h1>Masukkan Data Program Studi</h1>
        </div>
        <form:form cssClass="form-horizontal" method="post">
            <fieldset>
                <div class="clearfix">
                    <label>Kode</label>
                    <div class="input">
                        <input type="text" name="kodeProdi" value=""/>
                    </div>
                </div><!-- /clearfix -->
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