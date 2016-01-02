<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="input-jeniskontak">
        <div class="page-header">
            <h1>Masukkan Data Jenis Kontak</h1>
        </div>
        <form:form cssClass="form-horizontal" method="post">
            <fieldset>
                <div class="clearfix">
                    <label>ID Jenis Kontak</label>
                    <div class="input">
                        <input type="text" value="auto" disabled/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Jenis Kontak</label>
                    <div class="input">
                        <input type="text" name="jenisKontak" value=""/>
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