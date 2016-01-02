<%@include file="header.jsp"%>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <h1>Processing...</h1>
    </div>
    <div class="modal-body">
        <div class="progress progress-striped active">
            <div class="bar" style="width: 100%;"></div>
        </div>
    </div>
</div>

<div id="wrap">
    <div class="container" id="laporan-masa-tunggu">
        <div class="page-header">
            <h1>Masa Tunggu Alumni</h1>
        </div>
        <div class="pull-right" style="margin-bottom: 10px">
            <select name="data">
                <option value="1">Prodi</option>
                <option value="2">Peminatan</option>
            </select>
            <select name="sorting">
                <option value="1">Total</option>
                <option value="4">Per Tahun Angkatan</option>
                <option value="2">Per Tahun Wisuda</option>
                <option value="3">Per Periode Wisuda</option>
            </select>
        </div>
        <div id="all">
            <div class="page-header">
                <h3>Rekap Total</h3>
            </div>
            <table class="table table-striped table-bordered table-hover" id="tableAll">
                <thead>  
                    <tr>  
                        <th style="text-align: center"></th>  
                        <th style="text-align: center">Masa Tunggu</th>  
                        <th style="text-align: center">All</th>  
                    </tr>  
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div id="thnWisuda">
            <div class="page-header">
                <h3>Tahun Wisuda</h3>
            </div>
            <div class="pull-right" style="margin-bottom: 10px">
                <select id="wisuda"></select>
            </div>
            <table class="table table-striped table-bordered table-hover" id="tableByWisuda">
                <thead>  
                    <tr>  
                        <th style="text-align: center"></th>  
                        <th style="text-align: center">Masa Tunggu</th>  
                        <th style="text-align: center">All</th>  
                    </tr>  
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div id="tglWisuda">
            <div class="page-header">
                <h3>Periode Wisuda</h3>
            </div>
            <div class="pull-right" style="margin-bottom: 10px">
                <select id="tanggalWisuda"></select>
            </div>
            <table class="table table-striped table-bordered table-hover" id="tableByTanggalWisuda">
                <thead>  
                    <tr>  
                        <th style="text-align: center"></th>  
                        <th style="text-align: center">Masa Tunggu</th>  
                        <th style="text-align: center">All</th>  
                    </tr>  
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div id="angkt">
            <div class="page-header">
                <h3>Tahun Angkatan</h3>
            </div>
            <div class="pull-right" style="margin-bottom: 10px">
                <select id="angkatan"></select>
            </div>
            <table class="table table-striped table-bordered table-hover" id="tableByAngkatan">
                <thead>  
                    <tr>  
                        <th style="text-align: center"></th>  
                        <th style="text-align: center">Masa Tunggu</th>  
                        <th style="text-align: center">All</th>  
                    </tr>  
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <input type="button" id="buttonPrint" class="btn" value="Ekspor ke Excel"/>
    </div>
</div>
<%@include file="footer.jsp"%>