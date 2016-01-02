/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#laporan-core").is(function(){
        var status = "prodi";
        var tahun = "2010";
        var periode = "2010-10-01";
        var angkatan= "2007";
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
        $('#myModal').modal('show');
        $("#all, #thnWisuda, #tglWisuda, #angkt").hide();
        $.getJSON("rekapCore.htm", function(object){
            $("#tableAll tbody").html(tableLaporanCore(object.arrProdi, object.getProdiAll, ""));
            $("#tableByWisuda tbody").html(tableLaporanCore(object.arrProdi, object.getProdiTahun, tahun));
            $("#tableByTanggalWisuda tbody").html(tableLaporanCore(object.arrProdi, object.getProdiPeriode, periode));
            $("#tableByAngkatan tbody").html(tableLaporanCore(object.arrProdi, object.getProdiAngkatan, angkatan));
            $('#myModal').modal('hide');
            $("input[name=data]").change(function(){
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
                $("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
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
            $("#all, #thnWisuda, #tglWisuda, #angkt").fadeIn();
        });
        
        $("#wrap").css("margin-bottom", "10%");
        $(".container").fadeIn("slow");
        
    });
});