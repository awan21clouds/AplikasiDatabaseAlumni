/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#user-alumni").is(function(){
        $(".container:eq(1)").hide();
        $.getJSON("user-alumni-json.htm", function(object){
            var show_per_page = 5;
            var rows = tableUser(object.userAlumni, "");
            go_to_page("#tableUser", "#pagination", 5, 1, rows);
            $("#view-rows").select2();
            $("#view-rows").change(function(){
                if(this.value=="Semua"){
                    show_per_page = rows.length;
                    go_to_page("#tableUser", "#pagination", rows.length, 1, rows);
                }else{
                    show_per_page = this.value;
                    go_to_page("#tableUser", "#pagination", this.value, 1, rows);
                }
                $("#tableUser").hide();
                $("#tableUser").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $(".container:eq(1)").fadeIn();
            $("#searchBox").keyup(function(){
                var rows = tableUser(object.userAlumni, this.value);
                go_to_page("#tableUser", "#pagination", show_per_page, 1, rows);
            });
            $("#tableUser tbody tr td a").click(function(){
                var username = $(this).parents("tr").children("td:first").text();
                if($(this).text()!="View"){
                    var r=confirm($(this).text()+", Anda Yakin?");
                    if (r==true)location.href = "user-mahasiswa-alumni-kontak.htm?username="+username;
                }
            });
            $("#wrap").css("margin-bottom", "10%");
        });
    });
    $("#user-mahasiswa").is(function(){
        $.getJSON("user-mahasiswa-json.htm", function(object){
            var show_per_page = 5;
            var rows = tableUser(object.userMahasiswa, "");
            go_to_page("#tableUser", "#pagination", 5, 1, rows);
            $("#view-rows").select2();
            $("#view-rows").change(function(){
                show_per_page = this.value;
                go_to_page("#tableUser", "#pagination", show_per_page, 1, rows);
                $("#tableUser").hide();
                $("#tableUser").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $(".container:eq(1)").fadeIn();
            $("#searchBox").keyup(function(){
                var rows = tableUser(object.userMahasiswa, this.value);
                go_to_page("#tableUser", "#pagination", show_per_page, 1, rows);
            });
            $("#wrap").css("margin-bottom", "10%");
        });
    });
    $("#user-pegawai").is(function(){
        $.getJSON("user-pegawai-json.htm", function(object){
            var show_per_page = 5;
            var rows = tableUser(object.userPegawai, "");
            go_to_page("#tableUser", "#pagination", 5, 1, rows);
            $("#view-rows").select2();
            $("#view-rows").change(function(){
//                show_per_page = this.value;
//                go_to_page("#tableUser", "#pagination", show_per_page, 1, rows);
                if(this.value=="Semua"){
                    show_per_page = rows.length;
                    go_to_page("#tableUser", "#pagination", rows.length, 1, rows);
                }else{
                    show_per_page = this.value;
                    go_to_page("#tableUser", "#pagination", this.value, 1, rows);
                }
                $("#tableUser").hide();
                $("#tableUser").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $(".container:eq(1)").fadeIn();
            $("#searchBox").keyup(function(){
                var rows = tableUser(object.userPegawai, this.value);
                go_to_page("#tableUser", "#pagination", show_per_page, 1, rows);
            });
            $("#tableUser tbody tr td a").click(function(){
                var username = $(this).parents("tr").children("td:first").text();
                if($(this).text()!="View"){
                    var r=confirm($(this).text()+" User, Anda Yakin?");
                    if (r==true)location.href = "user-pegawai-aktivasi.htm?username="+username;
                }
            });
            $("#wrap").css("margin-bottom", "10%");
        });
    });
   $("#view-alumni").is(function(){
        $.getJSON("alumniJSON.htm", function(object){
            var show_per_page = 5;
            var rows = tableAlumni(object.alumni, "");
            go_to_page("#tableAlumni", "#pagination",show_per_page, 1, rows);//dimunculkan mulai dari page 1
            $("#view-rows").select2();
            $("#view-rows").change(function(){
                if(this.value=="Semua"){
                    show_per_page = rows.length;
                    go_to_page("#tableAlumni", "#pagination", rows.length, 1, rows);
                }else{
                    show_per_page = this.value;
                    go_to_page("#tableAlumni", "#pagination", this.value, 1, rows);
                }
                $("#tableAlumni").hide();
                $("#tableAlumni").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $(".container").fadeIn("slow");
            $("#wrap").css("margin-bottom", "10%");
            $("#searchBox").keyup(function(){
                var rows = tableAlumni(object.alumni, $(this).val());
                go_to_page("#tableAlumni", "#pagination", show_per_page, 1, rows);
            });
            $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
        });
    });
    
    $("#studi-display").is(function(){
        $.getJSON("studiDisplayJSON.htm", function(object){
            var show_per_page = 5;
            var rows = tableStudiDisplay(object.alumni, object.studi, "");
            go_to_page("#tableStudiDisplay", "#pagination",show_per_page, 1, rows);//dimunculkan mulai dari page 1
            $("#view-rows").select2();
            $("#view-rows").change(function(){
                if(this.value=="Semua"){
                    show_per_page = rows.length;
                    go_to_page("#tableStudiDisplay", "#pagination", rows.length, 1, rows);
                }else{
                    show_per_page = this.value;
                    go_to_page("#tableStudiDisplay", "#pagination", this.value, 1, rows);
                }
                $("#tableStudiDisplay").hide();
                $("#tableStudiDisplay").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $("#searchBox").keyup(function(){
                var rows = tableStudiDisplay(object.alumni, object.studi, $(this).val());
                go_to_page("#tableStudiDisplay", "#pagination", show_per_page, 1, rows);
            });
            $("#wrap").css("margin-bottom", "10%");
            $(".container").fadeIn("slow");
            $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
            
        });
    });
    
    $("#kerja-display").is(function(){
        $.getJSON("kerjaDisplayJSON.htm", function(object){
            var show_per_page = 5;
            var rows = tableKerjaDisplay(object.alumni, object.kerja, "");
            go_to_page("#tableKerjaDisplay", "#pagination",show_per_page, 1, rows);//dimunculkan mulai dari page 1
            $("#view-rows").select2();
            $("#view-rows").change(function(){
                if(this.value=="Semua"){
                    show_per_page = rows.length;
                    go_to_page("#tableKerjaDisplay", "#pagination", rows.length, 1, rows);
                }else{
                    show_per_page = this.value;
                    go_to_page("#tableKerjaDisplay", "#pagination", this.value, 1, rows);
                }
                $("#tableKerjaDisplay").hide();
                $("#tableKerjaDisplay").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $("#searchBox").keyup(function(){
                var rows = tableKerjaDisplay(object.alumni, object.kerja, $(this).val());
                go_to_page("#tableKerjaDisplay", "#pagination", show_per_page, 1, rows);
            });
            $("#wrap").css("margin-bottom", "10%");
            $(".container").fadeIn("slow");
            $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
            
        });
    });
    
    $("#wirausaha-display").is(function(){
        $.getJSON("wirausahaDisplayJSON.htm", function(object){
            var show_per_page = 5;
            
            var rows = tableWirausahaDisplay(object.alumni, object.wirausaha, "");
            go_to_page("#tableWirausahaDisplay", "#pagination",show_per_page, 1, rows);//dimunculkan mulai dari page 1
            $("#view-rows").select2();
            $("#view-rows").change(function(){
                if(this.value=="Semua"){
                    show_per_page = rows.length;
                    go_to_page("#tableWirausahaDisplay", "#pagination", rows.length, 1, rows);
                }else{
                    show_per_page = this.value;
                    go_to_page("#tableWirausahaDisplay", "#pagination", this.value, 1, rows);
                }
                $("#tableWirausahaDisplay").hide();
                $("#tableWirausahaDisplay").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $("#searchBox").keyup(function(){
                var rows = tableWirausahaDisplay(object.alumni, object.wirausaha, $(this).val());
                go_to_page("#tableWirausahaDisplay", "#pagination", show_per_page, 1, rows);
            });
            $("#wrap").css("margin-bottom", "10%");
            $(".container").fadeIn("slow");
            $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
            
        });
    });
    
    $("#sms-broadcast").is(function(){
        $.getJSON("kontakAlumniJSON.htm", function(object){
            var show_per_page = 5;
            var rows = tableKontakAlumni(object.kontakAlumni, "", "");
            go_to_page("#tableKontakAlumni", "#pagination", show_per_page, 1, rows);
            
            var checked = new Array();
            
            $("#pilihKontak").click(function(){
                $("#tableKontakAlumni input[name=kontak]").each(function(){
                    $("#tableKontakAlumni input[name=kontak]").prop("checked",  true);
                    if(checked.length==0){
                        checked.push($(this).val());
                    }else{
                        var duplicate = false;
                        for(var i=0;i<checked.length;i++){
                            if($(this).val()==checked[i]){
                                duplicate = true;
                                break;
                            }
                        }
                        if(duplicate==false)checked.push($(this).val());
                    }
                    var rows = tableKontakAlumni(object.kontakAlumni, "", checked);
                    go_to_page("#tableKontakAlumni", "#pagination", show_per_page, $("#currentPage").text(), rows);
                });
            });
            $("#putusKontak").click(function(){
                $("#tableKontakAlumni input[name=kontak]").each(function(){
                    $("#tableKontakAlumni input[name=kontak]").prop("checked",  false);
                    var duplicate = false;
                    for(var i=0;i<checked.length;i++){
                        if($(this).val()==checked[i]){
                            delete checked[i];
                            break;
                        }
                    }
                    var rows = tableKontakAlumni(object.kontakAlumni, "", checked);
                    go_to_page("#tableKontakAlumni", "#pagination", show_per_page, $("#currentPage").text(), rows);
                });
            });
            
            $("#view-rows").select2();
            $("#view-rows").change(function(){
                if(this.value=="Semua"){
                    show_per_page = rows.length;
                    go_to_page("#tableKontakAlumni", "#pagination", rows.length, 1, rows);
                }else{
                    show_per_page = this.value;
                    go_to_page("#tableKontakAlumni", "#pagination",show_per_page, 1, rows);//dimunculkan mulai dari page 1
                }
                $("#tableAlumni").hide();
                $("#tableAlumni").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $(".container").fadeIn("slow");
            $("#wrap").css("margin-bottom", "10%");
            $("#searchBox").keyup(function(){
                var rows = tableKontakAlumni(object.kontakAlumni, $(this).val(), "");
                go_to_page("#tableKontakAlumni", "#pagination", show_per_page, 1, rows);
            });
            $("textarea").keyup(function(){
                $("#pesanLength").text(this.value.length+"/"+160);
            });
            
            $("#buttonPrint").attr("onclick", "tableToExcel('"+$("table:visible").attr("id")+"', 'Laporan')");
        });
    });
});


