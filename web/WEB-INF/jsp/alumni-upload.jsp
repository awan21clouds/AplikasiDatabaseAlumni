<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="upload-alumni">
        <div class="page-header">
            <h1>Import Data</h1>
        </div>
        <form:form method="POST" enctype="multipart/form-data" cssClass="form-horizontal">
            <fieldset>
                <div class="clearfix">
                    <label>Jenis Data</label>
                    <div class="input" style="margin-top: 6px;">
                        <div class="inline-inputs">
                            <input type="radio" id="input1" name="jenisData" value="0" checked>
                            <span>Data Mahasiswa</span>
                            <input type="radio" name="jenisData" value="1">
                            <span>Data Yudisium</span>
                            <input type="radio" name="jenisData" value="2">
                            <span>Data Studi Lanjut</span>
                            <input type="radio" name="jenisData" value="3">
                            <span>Data Kerja</span>
                            <input type="radio" name="jenisData" value="4">
                            <span>Data Wirausaha</span>
                        </div>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>File</label>
                    <div class="input">
                        <input type="text" name="filePath" id="filePath" readonly/>
                        <a href="#" class="btn danger" id="uploadButton">Pilih File</a>
                        <input type="file" onchange="document.getElementById('filePath').value = this.value;" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                        <span class="help-block"></span>
                    </div>
                </div><!-- /clearfix -->
                <div class="actions">
                    <input type="submit" value="Import" class="btn primary" onclick="validation(this.form, 0)"/>
                </div>
            </fieldset>
        </form:form>
        <div class="alert">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Warning!</strong><br/>
            <span></span>
        </div>
        ${msg}
    </div>
</div>
<%@include file="footer.jsp"%>