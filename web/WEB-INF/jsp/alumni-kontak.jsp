<%@include file="header.jsp"%>
<div id="wrap">
    <div class="container" id="kontak-alumni">
        <div class="page-header">
            <h1>Kontak</h1>
        </div>
        <input type="hidden" id="nim" value="<%=session.getAttribute("username")%>"/>
        <form:form method="POST" cssClass="form-horizontal" action="">
            <fieldset>
                <c:forEach items="${kontak}" var="kontak">
                    <div class="clearfix">
                        <label>${kontak.jeniskontak.jenisKontak}</label>
                        <div class="input">
                            <input type="text" name="kontak" value="${kontak.kontak}" disabled/>
                            <c:if test="${kontak.jeniskontak.jenisKontak=='Telepon'}"><i>(+62xxxxxxx)</i></c:if>
                            <input type="hidden" name="jenisKontak" value="${kontak.jeniskontak.idJenisKontak}"/>
                        </div>
                    </div>
                </c:forEach>
                <div class="actions">
                    <input type="submit" value="Simpan" id="saveButtonKontak" class="btn primary"/>
                    <input type="button" value="Update" id="updateButtonKontak" class="btn danger"/>
                    <input type="button" value="History" id="historyButtonKontak" class="btn"/>
                </div>
            </fieldset>
        </form:form>
        <div id="historyKontak">
            <table class="table table-striped table-bordered table-hover" id="tableHistoryKontak">
                <thead>  
                    <tr>  
                        <th style="text-align: center">Jenis Kontak</th>  
                        <th style="text-align: center">Kontak</th>  
                    </tr>  
                </thead>
                <tbody></tbody>
            </table>
            <div style="margin-bottom: 80px">
                <div class="pull-left" style="margin-top: -15px">
                    <div id="paginationKontak" class="pagination"></div>
                </div>
                <div class="pull-right">
                    <input type="button" value="Kembali" id="currentButtonKontak" class="btn"/>
                </div>
            </div>
        </div>
        <div class="alert">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Warning!</strong>
            <span></span>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>