/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $(".container:eq(1)").hide();
    $("#searchBox").attr("placeholder", "Cari Data");
/*-------------------------------------------------Nav Bar-------------------------------------------------*/
    
/*-------------------------------------------------End of Nav Bar-------------------------------------------------*/

/*-------------------------------------------------User-------------------------------------------------*/
    $("#detail-laporan").is(function(){
        $(this).fadeIn();
    });
    $("#ganti-password").is(function(){
        $(".container:eq(1)").fadeIn();
    });
/*-------------------------------------------------End of User-------------------------------------------------*/
    
/*-------------------------------------------------Pegawai-------------------------------------------------*/
    $("#main-pegawai").is(function(){
        $(".statusMahasiswa").change(function(){
            if($(this).val()==1){
                $("input[name=mahasiswa]").prop("checked", this.checked);
            }else{
                $("input[name=alumni]").prop("checked", this.checked);
            }
        });
        $("input[name=mahasiswa]").change(function(){
            $(".statusMahasiswa:eq(0)").prop("checked", this.checked);
        });
        $("input[name=alumni]").change(function(){
            $(".statusMahasiswa:eq(1)").prop("checked", this.checked);
            
        });
        $(".container:eq(1)").fadeIn("slow");
    });
    $("#input-pegawai").is(function(){
        $("#input-pegawai select").select2();
        $(".container:eq(1)").fadeIn("slow");
        $("#wrap").css("margin-bottom", "10%");
    });
    $("#detail-pegawai").is(function(){
        $("#detail-pegawai input[type=text]").each(function(){
            $(this).attr("disabled", true);
        });
        $(".container:eq(1)").fadeIn("slow");
    });
    $("#profil-pegawai").is(function(){
        $("#saveButton").hide();
        $("#updateButton").click(function(){
            $("#profil-pegawai input[type=text]").each(function(i){
                if(i>0){
                    $(this).removeAttr("disabled");
                }  
            });
            $(this).hide()
            $("#tanggalMasuk, #tanggalKeluar").attr("data-date", currentDate);
            $("#tanggalMasuk, #tanggalKeluar").datepicker();
            $("#saveButton").show();
        });
        $(".container:eq(1)").fadeIn("slow");
    });
    
    

/*-------------------------------------------------End of Pegawai-------------------------------------------------*/

/*-------------------------------------------------Alumni-------------------------------------------------*/
    /* go_to_page()
        * show_per_page = jumlah row yang akan ditampilkan perhalaman
        * 1 = menampilkan row dimulai dari halaman 1
        * rows = array yang menampung row
        **/
    var show_per_page = 5;
    var currentDate = new Date();
    currentDate = currentDate.getFullYear()+"-"+(currentDate.getMonth()+1)+"-"+currentDate.getDate();
    $("#login").is(function(){
        $(".container").show();
    });
    $("#input-alumni").is(function(){
        $("#periodeWisuda, #yudisium, #ipk").hide();
        $.getJSON("prodiPeminatanJSON.htm", function(object){
            var option = "";
            $(object.prodi).each(function(key, val){
                option +="<option value='"+val.idProdi+"'>"+val.namaProdi+"</option>";
            });
            $("#prodi").html(option);
            $("#prodi").change(function(){
                var option2 = ""; 
                $(object.peminatan).each(function(key, val){
                    if(val.kodeProdiPeminatan == $("#prodi").find("option:selected").val()){
                        option2 +="<option value='"+val.idPeminatan+"'>"+val.namaPeminatan+"</option>";
                    }
                });
                $("#peminatan").html(option2);
            });
            var option2 = ""; 
            $(object.peminatan).each(function(key, val){
                if(val.kodeProdiPeminatan == "301"){
                    option2 +="<option value='"+val.idPeminatan+"'>"+val.namaPeminatan+"</option>";
                }
            });
            $("#peminatan").html(option2);
            $("#statusMahasiswa, #prodi, #peminatan, #periodeWisuda select, #ipk select").select2();
            $("#tanggalLahir, #tanggalYudisium").attr("data-date", currentDate);
            $("#tanggalLahir, #tanggalYudisium").datepicker();
        });
        var option3 = "";
        for(var i=parseFloat("0.00");i<=4.00;i=i+0.01){
            option3 +="<option value='"+i.toFixed(2).replace(".", ",")+"'>"+i.toFixed(2).replace(".", ",")+"</option>";
        }
        $("select[name=ipk]").html(option3);
        $("select[name=ipk]").find("option:selected").change(function(){
            var val = $(this).val();
            $(this).val(val);
        });
        $(".container").fadeIn(1500);
        $("#statusMahasiswa").change(function(){
            if($(this).find("option:selected").val()==3){
                $("#periodeWisuda").slideDown(function(){
                    $(this).find("select").attr("disabled", false);
                });
                $("#yudisium").slideDown(function(){
                    $(this).find("#tanggalYudisium input[type=text]").attr("disabled", false);
                    $("input[name=tanggalYudisium]").rules("add", {
                        required: true,
                        messages: {
                            required: "Tidak Boleh Dikosongkan"
                        }
                    });
                });
                $("#ipk").slideDown(function(){
                    $(this).find("select").attr("disabled", false);
                });
            }else{
                $("#periodeWisuda").slideUp(function(){
                    $(this).find("select").attr("disabled", true);
                });
                $("#yudisium").slideUp(function(){
                    $(this).find("#tanggalYudisium input[type=text]").attr("disabled", true);
                    $("input[name=tanggalYudisium]").rules("remove");
                });
                $("#ipk").slideUp(function(){
                    $(this).find("select").attr("disabled", true);
                });
            }
        });
        $("#wrap").css("margin-bottom", "10%");
    });
    $("#upload-alumni").is(function(){
        $("input[type=file]").hide();
        $("input[name=jenisData]").each(function(i){
            $(this).change(function(){
                $("input[type=file]").attr("accept", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            });
        });
        $("#uploadButton").click(function(){
            $("input[type=file]").click();
            $("input[type=file]").change(function(){
                $("#filePath").val($(this).val().substr(12));
            });
        });
        $(".container:eq(1)").fadeIn("slow");
    });
    $("#main-alumni").is(function(){
        $(".container:eq(1)").fadeIn("slow");
    });
    $("#profil-alumni").is(function(){
        $(".container:eq(1)").fadeIn("slow");
    });
    $("#kontak-alumni").is(function(){
       var nim = $("#nim").val();
       $("#saveButtonKontak").hide();
       $("#updateButtonKontak").click(function(){
            $(this.form).find("input[type=text]").each(function(){
                $(this).attr("disabled", false);
            });
            $("#updateButtonKontak").hide();
            $("#saveButtonKontak").show();
        });
        $.getJSON("kontakJSON.htm", function(object){
            var rows = tableKontak(object.kontak, nim);
            go_to_page("#tableHistoryKontak", "#paginationKontak", 5, 1, rows);
        });
        $("#historyKontak").hide();
            
        $("#historyButtonKontak").click(function(){
            $("form").hide();
            $("#historyKontak").fadeIn();
        });
        $("#currentButtonKontak").click(function(){
            $("#historyKontak").hide();
            $("form").fadeIn();
        });
        $(".container:eq(1)").fadeIn("slow");
    });
    
    
    $("#studi-alumni").is(function(){
        var nim = $("#nim").val();
        $("#tanggalMasukStudi").find("input[name=tanggalMasuk]").is(function(){
            if($(this).val()=="") $(this).val("0000-00-00");
        });
        $("#tanggalKeluarStudi").find("input[name=tanggalKeluar]").is(function(){
            if($(this).val()=="") $(this).val("0000-00-00");
        });
        $("#saveButtonStudi").hide();
        $("#updateButtonStudi").click(function(){
            $(this.form).find("input[type=text]").each(function(){
                $(this).attr("disabled", false);
            });
            $("#tanggalMasukStudi, #tanggalKeluarStudi").attr("data-date", currentDate);
            $("#tanggalMasukStudi, #tanggalKeluarStudi").datepicker();
            $("#updateButtonStudi").hide();
            $("#saveButtonStudi").show();
        });
        $.getJSON("studiJSON.htm", function(object){
            var rows = tableStudi(object.studi, nim);
            go_to_page("#tableHistoryStudi", "#paginationStudi", 5, 1, rows);
        });
        $("#historyStudi").hide();
        $("#historyButtonStudi").click(function(){
            $("form").hide();
            $("#historyStudi").fadeIn();
        });
        $("#currentButtonStudi").click(function(){
            $("#historyStudi").hide();
            $("form").fadeIn();
        });
        $(".container:eq(1)").fadeIn("slow");
    });
    $("#kerja-alumni").is(function(){
        var nim = $("#nim").val();
        $("#tanggalMasukKerja").find("input[name=tanggalMasuk]").is(function(){
            if($(this).val()=="") $(this).val("0000-00-00");
        });
        $("#tanggalKeluarKerja").find("input[name=tanggalKeluar]").is(function(){
            if($(this).val()=="") $(this).val("0000-00-00");
        });
        $("#posisiKerjaClearFix").hide();
        $("#saveButtonKerja").hide();
        $("#wrap").css("margin-bottom", "10%");
        $("#kerja-alumni select").select2();
        $("#updateButtonKerja").click(function(){
            $(this.form).find("input[type=text]").each(function(i){
                if(i!=3){
                    $(this).attr("disabled", false);
                }
            });
            $(this.form).find("select").each(function(){
                $(this).attr("disabled", false);
            });
            var posisiKerja = $(this.form).find("select:eq(0)").find(":selected").val();
            $("#posisiKerja").val(posisiKerja);
            $(this.form).find("select:eq(0)").change(function(){
                posisiKerja = $(this).val();
                $("#posisiKerja").val(posisiKerja);
                //alert(posisiKerja);
                if($(this).val()==0){
                    $(this).attr("disabled", true);
                    $("#posisiKerjaText").attr("disabled", false);
                    $("#posisiKerjaClearFix").slideDown();
                }
            });
            $("#tanggalMasukKerja, #tanggalKeluarKerja").attr("data-date", currentDate);
            $("#tanggalMasukKerja, #tanggalKeluarKerja").datepicker();
            $("#updateButtonKerja").hide();
            $("#saveButtonKerja").show();
            $(this.form).submit(function(){
                if(posisiKerja==0){
                    posisiKerja = $("#posisiKerjaText").val();
                    $("#posisiKerja").val(posisiKerja);
                }
                if(trim($("#posisiKerja").val())==""){
                    return false
                }else{
                    return true;
                }
                
            });
        });
        $.getJSON("kerjaJSON.htm", function(object){
            var rows = tableKerja(object.kerja, nim);
            go_to_page("#tableHistoryKerja", "#paginationKerja", 5, 1, rows);
        });
        
        $("#historyKerja").hide();
        $("#historyButtonKerja").click(function(){
            $("form").hide();
            $("#historyKerja").fadeIn();
        });
        $("#currentButtonKerja").click(function(){
            $("#historyKerja").hide();
            $("form").fadeIn();
        });
        $(".container:eq(1)").fadeIn("slow");
        
    });
    
    $("#wirausaha-alumni").is(function(){
        var nim = $("#nim").val();
        $("#tanggalMulaiWirausaha").find("input[name=tanggalMulai]").is(function(){
            if($(this).val()=="") $(this).val("0000-00-00");
        });
        $("#saveButtonWirausaha").hide();
        $("#updateButtonWirausaha").click(function(){
            $(this.form).find("input[type=text]").each(function(i){
                $(this).attr("disabled", false);
            });
            $("#tanggalMulaiWirausaha").attr("data-date", currentDate);
            $("#tanggalMulaiWirausaha").datepicker();
            $("#updateButtonWirausaha").hide();
            $("#saveButtonWirausaha").show();
        });
        $.getJSON("wirausahaJSON.htm", function(object){
            var rows = tableWirausaha(object.wirausaha, nim);
            go_to_page("#tableHistoryWirausaha", "#paginationWirausaha", 5, 1, rows);
        });
        $("#historyWirausaha").hide();
        $("#historyButtonWirausaha").click(function(){
            $("form").hide();
            $("#historyWirausaha").fadeIn();
        });
        $("#currentButtonWirausaha").click(function(){
            $("#historyWirausaha").hide();
            $("form").fadeIn();
        });
        
        $("#wrap").css("margin-bottom", "10%");
        $(".container").fadeIn("slow");
    });
/*-------------------------------------------------End of Alumni-------------------------------------------------*/

/*-------------------------------------------------Manajemen Data-------------------------------------------------*/
//    $.getJSON("usersJSON.htm", function(object){
//        $("#manajemen-level").is(function(){
//            var rows = getLevelJSON(object, "");
//            go_to_page("#tableLevel", show_per_page, 1, rows);
//            $(".container:eq(1)").fadeIn("slow");
//            $("#searchBox").keyup(function(){
//                var rows = getLevelJSON(object, $(this).val());
//                go_to_page("#tableLevel", show_per_page, 1, rows);
//            });
//        });
//    });
    
   
/*-------------------------------------------------End of Manajemen Data-------------------------------------------------*/


});
