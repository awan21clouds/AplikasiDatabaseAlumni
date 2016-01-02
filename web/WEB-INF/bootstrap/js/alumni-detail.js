/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#detail-alumni2").is(function(){
        var currentDate = new Date();
        currentDate = currentDate.getFullYear()+"-"+(currentDate.getMonth()+1)+"-"+currentDate.getDate();
        $("#detail-alumni2 select").select2();
        var nim = location.search.toString().substr(location.search.toString().indexOf("=", 0)+1);
        $("input[name=nim]").val(nim);
        $("#saveButtonKontak, #saveButtonStudi, #saveButtonKerja, #saveButtonWirausaha").hide();
        $("#historyKontak, #historyStudi, #historyKerja, #historyWirausaha").hide();
        
        /*KONTAK*/
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
        
        $("#historyButtonKontak").click(function(){
            $("form:eq(0)").hide();
            $("#historyKontak").fadeIn();
        });
        $("#currentButtonKontak").click(function(){
            $("#historyKontak").hide();
            $("form:eq(0)").fadeIn();
        });
        
        /*STUDI*/
        $("#tanggalMasukStudi").find("input[name=tanggalMasuk]").is(function(){
            if($(this).val()=="") $(this).val("0000-00-00");
        });
        $("#tanggalKeluarStudi").find("input[name=tanggalKeluar]").is(function(){
            if($(this).val()=="") $(this).val("0000-00-00");
        });
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
        
        $("#historyButtonStudi").click(function(){
            $("form:eq(1)").hide();
            $("#historyStudi").fadeIn();
        });
        $("#currentButtonStudi").click(function(){
            $("#historyStudi").hide();
            $("form:eq(1)").fadeIn();
        });
        
        /*KERJA*/
        $("#tanggalMasukKerja").find("input[name=tanggalMasuk]").is(function(){
            if($(this).val()=="") $(this).val("0000-00-00");
        });
        $("#tanggalKeluarKerja").find("input[name=tanggalKeluar]").is(function(){
            if($(this).val()=="") $(this).val("0000-00-00");
        });
        $("#posisiKerjaClearFix").hide();
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
                if($("#posisiKerja").val()==""){
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
        
        $("#historyButtonKerja").click(function(){
            $("form:eq(2)").hide();
            $("#historyKerja").fadeIn();
        });
        $("#currentButtonKerja").click(function(){
            $("#historyKerja").hide();
            $("form:eq(2)").fadeIn();
        });
        /*WIRAUSAHA*/
        $("#tanggalMulaiWirausaha").find("input[name=tanggalMulai]").is(function(){
            if($(this).val()=="") $(this).val("0000-00-00");
        });
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
        
        $("#historyButtonWirausaha").click(function(){
            $("form:eq(3)").hide();
            $("#historyWirausaha").fadeIn();
        });
        $("#currentButtonWirausaha").click(function(){
            $("#historyWirausaha").hide();
            $("form:eq(3)").fadeIn();
        });
        
        $("#wrap").css("margin-bottom", "10%");
        $(".container").fadeIn("slow");
    });
});

