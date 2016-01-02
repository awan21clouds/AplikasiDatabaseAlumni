/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#detail-alumni").is(function(){
        alert(1);
        $("#saveButtonKontak, #saveButtonStudi, #saveButtonKerja, #saveButtonWirausaha").hide();
        $("#detil-alumni select").select2();
        $("#updateButtonKontak").click(function(){
            alert(1);
        });
        $(".container").fadeIn("slow");
        
    });
});