/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
   /*-------------------------------------------------Laporan Posisi Total-------------------------------------------------*/
   $("#laporan-posisi-total").is(function(){
        getOption();
        $('#myModal').modal('show');
        $("select[name=data], select[name=sorting]").select2();
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        $.getJSON("rekapPosisiUtama.htm", function(object){
            getLaporanPosisi(object, "total");
            //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
        });
        //$("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'W3C Example Table')");
        $("#wrap").css("margin-bottom", "10%");
        $(".container").fadeIn("slow");
    }); 
   /*-------------------------------------------------Laporan Posisi IPK < 2.75-------------------------------------------------*/ 
   $("#laporan-posisi-ipk-le").is(function(){
        getOption();
        $('#myModal').modal('show');
        $("select[name=data], select[name=sorting]").select2();
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        $.getJSON("rekapPosisiLess.htm", function(object){
            getLaporanPosisi(object, "less");
            //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
        });
        //$("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'W3C Example Table')");
        $("#wrap").css("margin-bottom", "10%");
        $(".container").fadeIn("slow");
    });
   /*-------------------------------------------------Laporan Posisi IPK >=2.75--------------------------------------------------*/
   $("#laporan-posisi-ipk-gr").is(function(){
        getOption();
        $('#myModal').modal('show');
        $("select[name=data], select[name=sorting]").select2();
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        $.getJSON("rekapPosisiGreater.htm", function(object){
            getLaporanPosisi(object, "greater");
            //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
        });
        //$("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'W3C Example Table')");
        $("#wrap").css("margin-bottom", "10%");
        $(".container").fadeIn("slow");
    }); 
   /*-------------------------------------------------Laporan Masa Tunggu--------------------------------------------------*/
   $("#laporan-masa-tunggu").is(function(){
        getOption();
        $('#myModal').modal('show');
        $("select[name=data], select[name=sorting]").select2();
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        $.getJSON("laporanMasaTunggu.htm", function(object){
            getLaporanMasaTunggu(object);
//            //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
        });
        //$("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'W3C Example Table')");
        $("#wrap").css("margin-bottom", "10%");
        $(".container").fadeIn("slow");
    }); 
   /*-------------------------------------------------Laporan Kontak--------------------------------------------------*/
   $("#laporan-kontak").is(function(){
        getOption();
        $('#myModal').modal('show');
        $("select[name=data], select[name=sorting]").select2();
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        $.getJSON("laporanKontak.htm", function(object){
            getLaporanKontak(object);
//            //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
        });
        //$("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'W3C Example Table')");
        $("#wrap").css("margin-bottom", "10%");
        $(".container").fadeIn("slow");
    }); 
   /*-------------------------------------------------Laporan EmploymentRate--------------------------------------------------*/
   $("#laporan-employment-rate").is(function(){
        getOption();
        $('#myModal').modal('show');
        $("select[name=data], select[name=sorting]").select2();
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        $.getJSON("rekapPosisiUtama.htm", function(object){
            getLaporanEmploymentRate(object);
//            //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
        });
        //$("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'W3C Example Table')");
        $("#wrap").css("margin-bottom", "10%");
        $(".container").fadeIn("slow");
    }); 
   /*-------------------------------------------------Laporan Core--------------------------------------------------*/
   $("#laporan-core").is(function(){
        getOption();
        $('#myModal').modal('show');
        $("select[name=data], select[name=sorting]").select2();
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        $.getJSON("rekapCore.htm", function(object){
            getLaporanCore(object);
//            //$("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
        });
        //$("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'W3C Example Table')");
        $("#wrap").css("margin-bottom", "10%");
        $(".container").fadeIn("slow");
    }); 
    
    $("#rekapitulasi-grafik").is(function(){
        
        $.getJSON("rekapPosisiUtama.htm", function(object){
            mainGrafik(object);
        });
        $("select[name=grafik]").change(function(){
            if($(this).val()==1){
                $.getJSON("rekapPosisiUtama.htm", function(object){
                    mainGrafik(object);
                });
            }else if($(this).val()==2){
                $.getJSON("rekapPosisiLess.htm", function(object){
                    mainGrafik(object);
                });
            }else if($(this).val()==3){
                $.getJSON("rekapPosisiGreater.htm", function(object){
                    mainGrafik(object);
                });
            }
        });
        $("select[name=grafik], select[name=data], select[name=sorting]").select2();
        $("#wrap").css("margin-bottom", "10%");
        $(".container:eq(1)").fadeIn();
        $("#printButton").click(function(){
            $("#navGrafik").hide();
            window.print();
            $("#navGrafik").show();
        });
        
    });
});

function chartReport2(render, title, kerja, wirausaha, studi, menunggu){
    var total = kerja+wirausaha+studi+menunggu;
    var countKerja = kerja/total*100;
    var countWirausaha = wirausaha/total*100;
    var countStudi = studi/total*100;
    var countMenunggu = menunggu/total*100;
    var chart = new Highcharts.Chart({
        chart: {
            renderTo: render,
            type: 'pie',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: title
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2) +' %';
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    formatter: function() {
                        //return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2) +' %';
                        return this.percentage.toFixed(2) +' %';
                    }
                },
                showInLegend: true
            }
        },
        series: [{
            type: 'pie',
            point: {
                events: {
                    click: function(e) {
                        location.href = e.point.url;
                        e.preventDefault();
                    }
                }
            },
            data: [
                {name: 'Studi', y: countStudi, url: 'laporan-detail.htm?alumni='+studi},
                {name: 'Kerja', y: countKerja, url: 'laporan-detail.htm?alumni='+kerja},
                {name: 'Wirausaha', y: countWirausaha, url: 'laporan-detail.htm?alumni='+wirausaha},
                {name: 'Menunggu', y: countMenunggu, url: 'laporan-detail.htm?alumni='+menunggu}
            ]
        }]
    });
}
