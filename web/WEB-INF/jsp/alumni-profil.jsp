<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="profil-alumni">
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
        <form:form method="POST" commandName="mahasiswa" cssClass="form-horizontal">
            <fieldset>
                <div class="clearfix">
                    <label>NIM</label>
                    <div class="input">
                        <input type="text" value="${alumni.nim}" disabled/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Nama</label>
                    <div class="input">
                        <input type="text" id="nama" value="${alumni.nama}" disabled/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Program Studi</label>
                    <div class="input">
                        <input type="text" value="${alumni.peminatan.prodi.nama}" disabled/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Peminatan</label>
                    <div class="input">
                        <input type="text" value="${alumni.peminatan.nama}" disabled/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Tanggal Yudisium</label>
                    <div class="input">
                        <input type="text" value="<fmt:formatDate value="${alumni.tanggalYudisium}" pattern="dd-MM-yyyy"/>" disabled/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Periode Wisuda</label>
                    <div class="input">
                        <input type="text" value="<fmt:formatDate value="${alumni.wisuda.tanggal}" pattern="dd-MM-yyyy"/>" disabled/>
                    </div>
                </div><!-- /clearfix -->
            </fieldset>
        </form:form>
    </div>
</div>
<%@include file="footer.jsp"%>