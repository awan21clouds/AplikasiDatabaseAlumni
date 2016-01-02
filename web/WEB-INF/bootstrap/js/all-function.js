/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*-------------------------------------------------Pagination-------------------------------------------------*/
function go_to_page(table_id, pagination_id, show_per_page, page, rows){
    page = page - 1;//current page
    var start_row = page * show_per_page;
    var index_row = 0;
    var row = "";
    for(var i=0;i<rows.length;i++){
        if(i>=start_row && index_row<show_per_page){
           row += rows[i];
           index_row++;
        }
    }
    $(table_id).find("tbody").html(row);
    $(table_id).find("select").select2();
    $("#tableUser select").change(function(){
        $("input[name='username']").val($(this).closest('tr').find("td:first").text());
        $("input[name='level']").val($(this).find(":selected").val());
        document.forms['tableUser'].submit();
    });
    pagination(table_id, pagination_id, page, show_per_page, rows);
}
function pagination(table_id, pagination_id, page, show_per_page, rows){
    page = page + 1;
    var total_pages = parseInt(rows.length/show_per_page);
    if(parseInt(rows.length%show_per_page)!=0)total_pages++;
    var show_pages = 5;
    var start_page = 1;
    var end_page = 5;
    
    if(total_pages<=5){
        start_page = 1;
        end_page = total_pages;
    }else{
        if(page>3 && page<(total_pages-3)){
            start_page = page-2;
            end_page = page+2;
        }else if(page<(total_pages-2) && page>3){
            start_page = page-2;
            end_page = page+2;
        }else if(page<=total_pages && page>=(total_pages-2)){
            start_page = total_pages-4;
            end_page = total_pages;
        }
    }
    
    var pages = "<ul>";
    if(page==1){
        pages += "<li class='disabled'><a href='#' id='1'><<</a></li>";
    }else{
        pages += "<li><a href='#' id='1'><<</a></li>";
    }
    for(var i=start_page;i<=end_page;i++){
        if(i==page){
            pages += "<li class='active'><a href='#' id='"+i+"'>"+i+"</a></li>";
        }else{
            pages += "<li><a href='#' id='"+i+"'>"+i+"</a></li>";
        }
    }
    
    if(page==total_pages){
        pages += "<li class='disabled'><a href='#' id='"+total_pages+"'>>></a></li>";
    }else{
        pages += "<li><a href='#' id='"+total_pages+"'>>></a></li>";
    }
    pages += "</ul>";
    
    $(pagination_id).html(pages);
    $(pagination_id+" ul li").find("a").click(function(){
        page = $(this).attr("id");
        go_to_page(table_id, pagination_id, show_per_page, page, rows);
        $("#tableUser tr td select").select2();
        $("#currentPage").text(page);
    });
}
function getOption(){
    $.getJSON("wisudaJSON.htm",function(object){
        var option = "";
        $.each(object.wisuda, function(){
            option += "<option value='"+this.tahun+"'>"+this.tahun+"</option>";
        });
        $("#wisuda").html(option);
        $("#wisuda").select2();
    });
    
    var option2 = "";
    $.getJSON("tanggalWisudaJSON.htm",function(object){
        $.each(object.wisuda, function(){
            option2 += "<option value='"+this.tanggal+"'>"+this.periode+"</option>";
        });
        $("#tanggalWisuda").html(option2);
        $("#tanggalWisuda").select2();
    });
    var option3 = "";
    $.getJSON("angkatanJSON.htm",function(object){
        $.each(object.angkatan, function(){
            option3 += "<option value='"+this.tahun+"'>"+this.tahun+"</option>";
        });
        $("#angkatan").html(option3);
        $("#angkatan").select2();
    });
}

/*-------------------------------------------------Laporan-------------------------------------------------*/
function getLaporanPosisi(object, jenis){
    var status = "prodi";
    var sorting = "1";
    var tahun = "2010";
    var periode = "2010-10-01";
    var angkatan= "2007";
    $("#tableAll tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiAll, "", jenis));
    $("#tableByWisuda tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiTahun, tahun, jenis));
    $("#tableByTanggalWisuda tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiPeriode, periode, jenis));
    $("#tableByAngkatan tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiAngkatan, angkatan, jenis));
    $('#myModal').modal('hide');
    
    ////submitForm();
    $("select[name=sorting]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        sorting = $(this).val();
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
    });
    $("select[name=data]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        if($(this).val()==1){
            status = "prodi";
            $("#tableAll tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiAll, "", jenis));
            $("#tableByWisuda tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiTahun, tahun, jenis));
            $("#tableByTanggalWisuda tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiPeriode, periode, jenis));
            $("#tableByAngkatan tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiAngkatan, angkatan, jenis));
            ////submitForm();
        }else{
            status = "pemintan";
            $("#tableAll tbody").html(tableLaporanPosisi(object.arrPeminatan, object.getPeminatanAll, "", jenis));
            $("#tableByWisuda tbody").html(tableLaporanPosisi(object.arrPeminatan, object.getPeminatanTahun, tahun, jenis));
            $("#tableByTanggalWisuda tbody").html(tableLaporanPosisi(object.arrPeminatan, object.getPeminatanPeriode, periode, jenis));
            $("#tableByAngkatan tbody").html(tableLaporanPosisi(object.arrPeminatan, object.getPeminatanAngkatan, angkatan, jenis));
            ////submitForm();
        }

        //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        //$("table:visible").attr("id");
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
    });

    $("#wisuda").change(function(){
        tahun = this.value;
        if(status=="prodi"){
            $("#tableByWisuda tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiTahun, tahun, jenis));
            //submitForm();
        }else{
            $("#tableByWisuda tbody").html(tableLaporanPosisi(object.arrPeminatan, object.getPeminatanTahun, tahun, jenis));
            //submitForm();
        }
    });
    $("#tanggalWisuda").change(function(){
        periode = this.value;
        if(status=="prodi"){
            $("#tableByTanggalWisuda tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiPeriode, periode, jenis));
            //submitForm();
        }else{
            $("#tableByTanggalWisuda tbody").html(tableLaporanPosisi(object.arrPeminatan, object.getPeminatanPeriode, periode, jenis));
            //submitForm();
        }
    });

    $("#angkatan").change(function(){
        angkatan = this.value;
        if(status=="prodi"){
            $("#tableByAngkatan tbody").html(tableLaporanPosisi(object.arrProdi, object.getProdiAngkatan, angkatan, jenis));
            //submitForm();
        }else{
            $("#tableByAngkatan tbody").html(tableLaporanPosisi(object.arrPeminatan, object.getPeminatanAngkatan, angkatan, jenis));
            //submitForm();
        }
    });
    $("#all").fadeIn();
    $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
    
}
function getLaporanMasaTunggu(object){
    var status = "prodi";
    var sorting = "1";
    var tahun = "2010";
    var periode = "2010-10-01";
    var angkatan= "2007";
    $("#tableAll tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiAll, ""));
    $("#tableByWisuda tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiTahun, tahun));
    $("#tableByTanggalWisuda tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiPeriode, periode));
    $("#tableByAngkatan tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiAngkatan, angkatan));
    $('#myModal').modal('hide');
    $("select[name=sorting]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        sorting = $(this).val();
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
    });
    $("select[name=data]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        if($(this).val()==1){
            status = "prodi";
            $("#tableAll tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiAll, ""));
            $("#tableByWisuda tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiTahun, tahun));
            $("#tableByTanggalWisuda tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiPeriode, periode));
            $("#tableByAngkatan tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiAngkatan, angkatan));
        }else{
            status = "pemintan";
            $("#tableAll tbody").html(tableLaporanMasaTunggu(object.arrPeminatan, object.getPeminatanAll, ""));
            $("#tableByWisuda tbody").html(tableLaporanMasaTunggu(object.arrPeminatan, object.getPeminatanTahun, tahun));
            $("#tableByTanggalWisuda tbody").html(tableLaporanMasaTunggu(object.arrPeminatan, object.getPeminatanPeriode, periode));
            $("#tableByAngkatan tbody").html(tableLaporanMasaTunggu(object.arrPeminatan, object.getPeminatanAngkatan, angkatan));
        }
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
        //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
    });

    $("#wisuda").change(function(){
        tahun = this.value;
        if(status=="prodi"){
            $("#tableByWisuda tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiTahun, tahun));
        }else{
            $("#tableByWisuda tbody").html(tableLaporanMasaTunggu(object.arrPeminatan, object.getPeminatanTahun, tahun));
        }
    });
    $("#tanggalWisuda").change(function(){
        periode = this.value;
        if(status=="prodi"){
            $("#tableByTanggalWisuda tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiPeriode, periode));
        }else{
            $("#tableByTanggalWisuda tbody").html(tableLaporanMasaTunggu(object.arrPeminatan, object.getPeminatanPeriode, periode));
        }
    });

    $("#angkatan").change(function(){
        angkatan = this.value;
        if(status=="prodi"){
            $("#tableByAngkatan tbody").html(tableLaporanMasaTunggu(object.arrProdi, object.getProdiAngkatan, angkatan));
        }else{
            $("#tableByAngkatan tbody").html(tableLaporanMasaTunggu(object.arrPeminatan, object.getPeminatanAngkatan, angkatan));
        }
    });
    $("#all").fadeIn();
    $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
}
function getLaporanKontak(object){
    var status = "prodi";
    var sorting = "1";
    var tahun = "2010";
    var periode = "2010-10-01";
    var angkatan= "2007";
    $("#tableAll tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiAll, ""));
    $("#tableByWisuda tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiTahun, tahun));
    $("#tableByTanggalWisuda tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiPeriode, periode));
    $("#tableByAngkatan tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiAngkatan, angkatan));
    $('#myModal').modal('hide');
    $("select[name=sorting]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        sorting = $(this).val();
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
    });
    $("select[name=data]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        if($(this).val()==1){
            status = "prodi";
            $("#tableAll tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiAll, ""));
            $("#tableByWisuda tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiTahun, tahun));
            $("#tableByTanggalWisuda tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiPeriode, periode));
            $("#tableByAngkatan tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiAngkatan, angkatan));
        }else{
            status = "pemintan";
            $("#tableAll tbody").html(tableLaporanKontak2(object.arrPeminatan, object.getPeminatanAll, ""));
            $("#tableByWisuda tbody").html(tableLaporanKontak2(object.arrPeminatan, object.getPeminatanTahun, tahun));
            $("#tableByTanggalWisuda tbody").html(tableLaporanKontak2(object.arrPeminatan, object.getPeminatanPeriode, periode));
            $("#tableByAngkatan tbody").html(tableLaporanKontak2(object.arrPeminatan, object.getPeminatanAngkatan, angkatan));
        }
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
        //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
    });

    $("#wisuda").change(function(){
        tahun = this.value;
        if(status=="prodi"){
            $("#tableByWisuda tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiTahun, tahun));
        }else{
            $("#tableByWisuda tbody").html(tableLaporanKontak2(object.arrPeminatan, object.getPeminatanTahun, tahun));
        }
    });
    $("#tanggalWisuda").change(function(){
        periode = this.value;
        if(status=="prodi"){
            $("#tableByTanggalWisuda tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiPeriode, periode));
        }else{
            $("#tableByTanggalWisuda tbody").html(tableLaporanKontak2(object.arrPeminatan, object.getPeminatanPeriode, periode));
        }
    });

    $("#angkatan").change(function(){
        angkatan = this.value;
        if(status=="prodi"){
            $("#tableByAngkatan tbody").html(tableLaporanKontak2(object.arrProdi, object.getProdiAngkatan, angkatan));
        }else{
            $("#tableByAngkatan tbody").html(tableLaporanKontak2(object.arrPeminatan, object.getPeminatanAngkatan, angkatan));
        }
    });
    $("#all").fadeIn();
    $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
}
function getLaporanEmploymentRate(object){
    var status = "prodi";
    var sorting = "1";
    var tahun = "2010";
    var periode = "2010-10-01";
    var angkatan= "2007";
    $("#tableAll tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiAll, ""));
    $("#tableByWisuda tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiTahun, tahun));
    $("#tableByTanggalWisuda tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiPeriode, periode));
    $("#tableByAngkatan tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiAngkatan, angkatan));
    $('#myModal').modal('hide');
    $("select[name=sorting]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        sorting = $(this).val();
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
    });
    $("select[name=data]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        if($(this).val()==1){
            status = "prodi";
            $("#tableAll tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiAll, ""));
            $("#tableByWisuda tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiTahun, tahun));
            $("#tableByTanggalWisuda tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiPeriode, periode));
            $("#tableByAngkatan tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiAngkatan, angkatan));
        }else{
            status = "pemintan";
            $("#tableAll tbody").html(tableLaporanEmploymentRate(object.arrPeminatan, object.getPeminatanAll, ""));
            $("#tableByWisuda tbody").html(tableLaporanEmploymentRate(object.arrPeminatan, object.getPeminatanTahun, tahun));
            $("#tableByTanggalWisuda tbody").html(tableLaporanEmploymentRate(object.arrPeminatan, object.getPeminatanPeriode, periode));
            $("#tableByAngkatan tbody").html(tableLaporanEmploymentRate(object.arrPeminatan, object.getPeminatanAngkatan, angkatan));
        }
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
        //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
    });

    $("#wisuda").change(function(){
        tahun = this.value;
        if(status=="prodi"){
            $("#tableByWisuda tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiTahun, tahun));
        }else{
            $("#tableByWisuda tbody").html(tableLaporanEmploymentRate(object.arrPeminatan, object.getPeminatanTahun, tahun));
        }
    });
    $("#tanggalWisuda").change(function(){
        periode = this.value;
        if(status=="prodi"){
            $("#tableByTanggalWisuda tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiPeriode, periode));
        }else{
            $("#tableByTanggalWisuda tbody").html(tableLaporanEmploymentRate(object.arrPeminatan, object.getPeminatanPeriode, periode));
        }
    });

    $("#angkatan").change(function(){
        angkatan = this.value;
        if(status=="prodi"){
            $("#tableByAngkatan tbody").html(tableLaporanEmploymentRate(object.arrProdi, object.getProdiAngkatan, angkatan));
        }else{
            $("#tableByAngkatan tbody").html(tableLaporanEmploymentRate(object.arrPeminatan, object.getPeminatanAngkatan, angkatan));
        }
    });
    $("#all").fadeIn();
    $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
}
function getLaporanCore(object){
    var status = "prodi";
    var sorting = "1";
    var tahun = "2010";
    var periode = "2010-10-01";
    var angkatan= "2007";
    $("#tableAll tbody").html(tableLaporanCore(object.arrProdi, object.getProdiAll, ""));
    $("#tableByWisuda tbody").html(tableLaporanCore(object.arrProdi, object.getProdiTahun, tahun));
    $("#tableByTanggalWisuda tbody").html(tableLaporanCore(object.arrProdi, object.getProdiPeriode, periode));
    $("#tableByAngkatan tbody").html(tableLaporanCore(object.arrProdi, object.getProdiAngkatan, angkatan));
    $('#myModal').modal('hide');
    $("select[name=sorting]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        sorting = $(this).val();
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
    });
    $("select[name=data]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        if($(this).val()==1){
            status = "prodi";
            $("#tableAll tbody").html(tableLaporanCore(object.arrProdi, object.getProdiAll, ""));
            $("#tableByWisuda tbody").html(tableLaporanCore(object.arrProdi, object.getProdiTahun, tahun));
            $("#tableByTanggalWisuda tbody").html(tableLaporanCore(object.arrProdi, object.getProdiPeriode, periode));
            $("#tableByAngkatan tbody").html(tableLaporanCore(object.arrProdi, object.getProdiAngkatan, angkatan));
        }else{
            status = "pemintan";
            $("#tableAll tbody").html(tableLaporanCore(object.arrPeminatan, object.getPeminatanAll, ""));
            $("#tableByWisuda tbody").html(tableLaporanCore(object.arrPeminatan, object.getPeminatanTahun, tahun));
            $("#tableByTanggalWisuda tbody").html(tableLaporanCore(object.arrPeminatan, object.getPeminatanPeriode, periode));
            $("#tableByAngkatan tbody").html(tableLaporanCore(object.arrPeminatan, object.getPeminatanAngkatan, angkatan));
        }
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
        //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
    });

    $("#wisuda").change(function(){
        tahun = this.value;
        if(status=="prodi"){
            $("#tableByWisuda tbody").html(tableLaporanCore(object.arrProdi, object.getProdiTahun, tahun));
        }else{
            $("#tableByWisuda tbody").html(tableLaporanCore(object.arrPeminatan, object.getPeminatanTahun, tahun));
        }
    });
    $("#tanggalWisuda").change(function(){
        periode = this.value;
        if(status=="prodi"){
            $("#tableByTanggalWisuda tbody").html(tableLaporanCore(object.arrProdi, object.getProdiPeriode, periode));
        }else{
            $("#tableByTanggalWisuda tbody").html(tableLaporanCore(object.arrPeminatan, object.getPeminatanPeriode, periode));
        }
    });

    $("#angkatan").change(function(){
        angkatan = this.value;
        if(status=="prodi"){
            $("#tableByAngkatan tbody").html(tableLaporanCore(object.arrProdi, object.getProdiAngkatan, angkatan));
        }else{
            $("#tableByAngkatan tbody").html(tableLaporanCore(object.arrPeminatan, object.getPeminatanAngkatan, angkatan));
        }
    });
    $("#all").fadeIn();
    $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
}
//GRAFIK
function mainGrafik(object){
    var status = "prodi";
    var sorting = "1";
    var tahun = "2010";
    var periode = "2010-10-01";
    var angkatan= "2007";
    grafik(object.arrProdi, object.getProdiAll, "", "");
    grafik(object.arrProdi, object.getProdiTahun, tahun, " Tahun");
    grafik(object.arrProdi, object.getProdiPeriode, periode, " Periode");
    grafik(object.arrProdi, object.getProdiAngkatan, angkatan, " Angkatan");

    $("#all, #thnWisuda, #tglWisuda, #angkt, .grafikPeminatan, .grafikProdi").hide();
    $("#all, .grafikProdi").fadeIn();
    $("select[name=sorting]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        sorting = $(this).val();
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
    });
    $("select[name=data]").change(function(){
        $("#all, #thnWisuda, #tglWisuda, #angkt, .grafikPeminatan, .grafikProdi").hide();
        if($(this).val()==1){
            status = "prodi";
            grafik(object.arrProdi, object.getProdiAll, "", "");
            grafik(object.arrProdi, object.getProdiTahun, tahun, " Tahun");
            grafik(object.arrProdi, object.getProdiPeriode, periode, " Periode");
            grafik(object.arrProdi, object.getProdiAngkatan, angkatan, " Angkatan");
            $(".grafikProdi").fadeIn();
        }else{
            status = "pemintan";
            grafik(object.arrPeminatan, object.getPeminatanAll, "", "");
            grafik(object.arrPeminatan, object.getPeminatanTahun, tahun, " Tahun");
            grafik(object.arrPeminatan, object.getPeminatanPeriode, periode, " Periode");
            grafik(object.arrPeminatan, object.getPeminatanAngkatan, angkatan, " Angkatan");
            $(".grafikPeminatan").fadeIn();
        }
        if(sorting==1){
            $("#all").fadeIn();
        }else if(sorting==2){
            $("#thnWisuda").fadeIn();
        }else if(sorting==3){
            $("#tglWisuda").fadeIn();
        }else if(sorting==4){
            $("#angkt").fadeIn();
        }
        $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
        //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
    });
}
function grafik(objectA, objectB, search, tipe){
    $.each(objectA, function(){
        var kerja = 0;
        var wirausaha = 0;
        var studi = 0;
        var menunggu = 0;
        
        var typeObjectA = "";
        if(this.prodi!=undefined){
            typeObjectA = this.prodi;
        }else if(this.peminatan!=undefined){
            typeObjectA = this.peminatan;
        }
        
        $.each(objectB, function(){
            var typeObjectB ="";
            if(this.prodi!=undefined){
                typeObjectB = this.prodi;
            }else if(this.peminatan!=undefined){
                typeObjectB = this.peminatan;
            }
            var status = false;
            if(this.tahun!=undefined){
                typeObjectB += this.tahun;
                status = (typeObjectB.toUpperCase().indexOf(typeObjectA.toUpperCase())!=-1) && (typeObjectB.toUpperCase().indexOf(search.toUpperCase())!=-1);
            }else if(this.periode!=undefined){
                typeObjectB += this.periode;
                status = (typeObjectB.toUpperCase().indexOf(typeObjectA.toUpperCase())!=-1) && (typeObjectB.toUpperCase().indexOf(search.toUpperCase())!=-1);
            }else if(this.angkatan!=undefined){
                typeObjectB += this.angkatan;
                status = (typeObjectB.toUpperCase().indexOf(typeObjectA.toUpperCase())!=-1) && (typeObjectB.toUpperCase().indexOf(search.toUpperCase())!=-1);
            }else{
                status = (typeObjectB.toUpperCase().indexOf(typeObjectA.toUpperCase())!=-1);
            }
            
            if(status==true && this.posisiPertama.toString().toLowerCase()=="kerja"){
                kerja = this.jumlah;
            }else if(status==true && this.posisiPertama.toString().toLowerCase()=="wirausaha"){
                wirausaha = this.jumlah; 
            }else if(status==true && this.posisiPertama.toString().toLowerCase()=="studi"){
                studi = this.jumlah; 
            }else if(status==true && this.posisiPertama.toString().toLowerCase()=="menunggu"){
                menunggu = this.jumlah;   
            }
        });
        chartReport2(typeObjectA+tipe, typeObjectA, kerja, wirausaha, studi, menunggu);
    });
}