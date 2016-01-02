/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#submenu-posisi-alumni, #submenu-user").hide();
    $("#sub-posisi-alumni, #sub-user").hover(function(){
        $("#submenu-posisi-alumni, #submenu-user").show();
        $("#submenu-posisi-alumni, #submenu-user").hover(function(){
            $("#submenu-posisi-alumni, #submenu-user").show();
        });
    }, function(){
        $("#submenu-posisi-alumni, #submenu-user").hide();
    });
    
    
    /*LOGIN ERROR*/
    $("#login-error").is(function(){
        $(".container:eq(1)").fadeIn("slow");
        
    });
     /*JENIS KONTAK*/
     $("#view-jeniskontak").is(function(){
        $.getJSON("jenisKontakJSON.htm", function(object){
            var show_per_page = 5;
            var rows = tableJenisKontak(object.jenisKontak, "");
            go_to_page("#tableJenisKontak", "#pagination", show_per_page, 1, rows);
            $("#view-rows").select2();
            $("#view-rows").change(function(){
                show_per_page = this.value;
                go_to_page("#tableJenisKontak", "#pagination", show_per_page, 1, rows);
                $("#tableJenisKontak").hide();
                $("#tableJenisKontak").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $(".container:eq(1)").fadeIn("slow");
            $("#searchBox").keyup(function(){
                var rows = tableJenisKontak(object.jenisKontak, $(this).val());
                go_to_page("#tableJenisKontak", "#pagination", show_per_page, 1, rows);
            });
        });
        
    });
    $("#input-jeniskontak").is(function(){
        $(".container:eq(1)").fadeIn("slow");
    });
    /*PRODI*/
    $("#view-prodi").is(function(){
        $.getJSON("prodiJSON.htm", function(object){
            var show_per_page = 5;
            var rows = tableProdi(object.prodi, "");
            go_to_page("#tableProdi", "#pagination", show_per_page, 1, rows);
            $("#view-rows").select2();
            $("#view-rows").change(function(){
                show_per_page = this.value;
                go_to_page("#tableProdi", "#pagination", show_per_page, 1, rows);
                $("#tableProdi").hide();
                $("#tableProdi").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $(".container:eq(1)").fadeIn("slow");
            $("#searchBox").keyup(function(){
                var rows = tableProdi(object.prodi, $(this).val());
                go_to_page("#tableProdi", "#pagination", show_per_page, 1, rows);
            });
        });
    });
    $("#input-prodi").is(function(){
        $(".container:eq(1)").fadeIn("slow");
    });
    /*PEMINATAN*/
    $("#view-peminatan").is(function(){
        $.getJSON("peminatanJSON.htm", function(object){
            var show_per_page = 5;
            var rows = tablePeminatan(object.peminatan, "");
            go_to_page("#tablePeminatan", "#pagination", show_per_page, 1, rows);
            $("#view-rows").select2();
            $("#view-rows").change(function(){
                show_per_page = this.value;
                go_to_page("#tablePeminatan", "#pagination", show_per_page, 1, rows);
                $("#tablePeminatan").hide();
                $("#tablePeminatan").fadeIn();
                $("#wrap").css("margin-bottom", "10%");
                $("#searchBox").val("");
            });
            $(".container:eq(1)").fadeIn("slow");
            $("#searchBox").keyup(function(){
                var rows = tablePeminatan(object.peminatan, $(this).val());
                go_to_page("#tablePeminatan", "#pagination", show_per_page, 1, rows);
            });
        });
    });
    $("#input-peminatan").is(function(){
        $.getJSON("prodiJSON.htm", function(object){
            var option = "";
            $.each(object.prodi, function(){
                option += "<option value='"+this.idProdi+"'>"+this.nama+"</option>";
            });
            $("#input-peminatan select").html(option);
            $("#input-peminatan select").select2();
            $(".container:eq(1)").fadeIn("slow");
        });
    });
});