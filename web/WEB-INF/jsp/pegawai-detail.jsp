<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="detail-pegawai">
        <div class="page-header">
            <h1>Detail Data Pegawai</h1>
        </div>
        <form:form method="POST" commandName="pegawai">
            <fieldset>
                <div class="clearfix">
                    <label>ID Pegawai</label>
                    <div class="input">
                        <input type="text" name="idPegawai" value="${pegawai.idPegawai}"/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Nama Pegawai</label>
                    <div class="input">
                        <input type="text" name="nama" value="${pegawai.nama}"/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Telepon</label>
                    <div class="input">
                        <input type="text" name="telepon" value="${pegawai.telepon}"/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Email</label>
                    <div class="input">
                        <input type="text" name="email" value="${pegawai.email}"/>
                    </div>
                </div><!-- /clearfix -->
                <div class="clearfix">
                    <label>Status</label>
                    <div class="input">
                        <c:if test="${pegawai.status==true}" >
                            <input type="text" value="Aktif"/>
                        </c:if>
                        <c:if test="${pegawai.status==false||pegawai.status==null}">
                            <input type="text" value="Non-Aktif"/>
                        </c:if>
                    </div>
                </div><!-- /clearfix -->
            </fieldset>
        </form:form>
    </div>
</div>
<%@include file="footer.jsp"%>