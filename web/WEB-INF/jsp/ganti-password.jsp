<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="ganti-password">
        <div class="page-header">
            <h1>Ganti Password</h1>
        </div>
        <form:form method="POST" cssClass="form-horizontal">
            <fieldset>
                <div class="clearfix">
                    <label>Password Lama</label>
                    <div class="input">
                        <input type="password" name="passwordLama">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Password Baru</label>
                    <div class="input">
                        <input type="password" id="passwordBaru" name="passwordBaru">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="clearfix">
                    <label>Konfirmasi Password Baru</label>
                    <div class="input">
                        <input type="password" name="confPasswordBaru">
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="actions">
                    <input type="submit" value="Simpan" class="btn primary"/>
                </div>
            </fieldset>
        </form:form>
    </div>
</div>
<%@include file="footer.jsp"%>