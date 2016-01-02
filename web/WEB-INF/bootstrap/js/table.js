/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function tableUser(object, search){
    var rows = new Array();
    $.each(object, function(){
        var row = "";
        row += "<tr>";
        row += "<td style='text-align: center'>"+this.username+"</td>";
        row += "<td width='250'>"+this.nama+"</td>";
        row += "<td width='70' style='text-align: center'>"+this.tanggalAktif+"</td>";
        row += "<td width='70' style='text-align: center'>"+this.loginTerakhir+"</td>";
        row += "<td style='text-align: center'>"+this.status+"</td>";
        if(this.level.toString().toLowerCase()=="alumni"){
            row += "<td style='text-align: center'>"+this.level+"</td>";
            row += "<td width='50' style='text-align: center'><a href='alumni-detail2.htm?nim="+this.username+"' class='btn'>View</a></td>";
            row += "<td width='140' style='text-align: center'>";
            if(this.kontak==false){
                //row += "<a href='user-mahasiswa-alumni-kontak.htm?username="+this.username+"' class='btn primary'>Hubungkan Kontak</a>";
                row += "<a class='btn primary'>Hubungkan Kontak</a>";
            }else{
                row += "<a class='btn danger'>Putuskan Kontak</a>";
            }
            row +="</td>";
        }else if(this.level.toString().toLowerCase()=="mahasiswa"){
            row += "<td style='text-align: center'>"+this.level+"</td>";
        }else if(this.level.toString().toLowerCase()=="admin" || this.level.toString().toLowerCase()=="pegawai"){
            row += "<td width='100' style='text-align: center'>";
            row += "<select style='width:100px;'>";
            if(this.level.toString().toLowerCase()=="admin"){
                row += "<option value='1' selected>Admin</option>"
            }else{
                row += "<option value='1'>Admin</option>"
            }
            if(this.level.toString().toLowerCase()=="pegawai"){
                row += "<option value='2' selected>Pegawai</option>"
            }else{
                row += "<option value='2'>Pegawai</option>"
            }
            row += "</select>";
            row += "</td>";
            row += "<td width='50'style='text-align: center'><a href='pegawai-detail.htm?idPegawai="+this.username+"' class='btn'>View</a></td>";
            row += "<td width='105' style='text-align: center'>";
            if(this.status.toString()=="-"){
                row += "<a class='btn primary'>Aktifkan</a>";
            }else{
                row += "<a class='btn danger'>Non-Aktifkan</a>";
            }
            row +="</td>";
        }
        row += "</tr>";
        var data = this.username+this.nama+this.tanggalAktif+this.loginTerakhir+this.status+this.level;
        if(data.toUpperCase().indexOf(search.toUpperCase())!=-1){
            rows.push(row);
        }
    });
    return rows;
}
function tableKontak(object, search){
    var rows = new Array();
    $.each(object, function(){
        var row = "";
        row += "<tr>";
        row += "<td>"+this.jenisKontak+"</td>";
        row += "<td>"+this.kontak+"</td>";
        row += "</tr>";
        row += this.nim;
        if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
            rows.push(row);
        }
    });
    return rows;
}
function tableStudi(object, search){
    var rows = new Array();
    $.each(object, function(){
        var row = "";
        row += "<tr>";
        row += "<td style='text-align: center'>"+this.universitas+"</td>";
        row += "<td style='text-align: center'>"+this.fakultas+"</td>";
        row += "<td style='text-align: center'>"+this.prodi+"</td>";
        row += "<td style='text-align: center'>"+this.tanggalMasuk+"</td>";
        row += "<td style='text-align: center'>"+this.tanggalKeluar+"</td>";
        row += "</tr>";
        row += this.nim;
        if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
            rows.push(row);
        }
    });
    return rows;
}
function tableKerja(object, search){
    var rows = new Array();
    $.each(object, function(){
        var row = "";
        row += "<tr>";
        row += "<td style='text-align: center'>"+this.perusahaan+"</td>";
        row += "<td style='text-align: center'>"+this.posisiKerja+"</td>";
        row += "<td style='text-align: center'>"+this.jenisKerja+"</td>";
        row += "<td style='text-align: center'>"+this.tanggalMasuk+"</td>";
        row += "<td style='text-align: center'>"+this.tanggalKeluar+"</td>";
        row += "</tr>";
        row += this.nim;
        if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
            rows.push(row);
        }
    });
    return rows;
}
function tableWirausaha(object, search){
    var rows = new Array();
    $.each(object, function(){
        var row = "";
        row += "<tr>";
        row += "<td style='text-align: center'>"+this.perusahaan+"</td>";
        row += "<td style='text-align: center'>"+this.jenisUsaha+"</td>";
        row += "<td style='text-align: center'>"+this.tanggalMulai+"</td>";
        row += "</tr>";
        row += this.nim;
        if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
            rows.push(row);
        }
    });
    return rows;
}
function tableAlumni(object, search){
    var rows = new Array();
    var i = 1;
    $.each(object, function(){
        var row = "";
        row += "<tr>";
        row += "<td style='text-align: center'>"+i+"</td>";
        row += "<td style='text-align: center'>"+this.angkatan+"</td>";
        row += "<td style='text-align: center'>"+this.nim+"</td>";
        row += "<td width='300'>"+this.nama+"</td>";
        row += "<td style='text-align: center'>"+this.ipk+"</td>";
        row += "<td>"+this.predikat+"</td>";
        row += "<td style='text-align: center'>"+this.tanggalYudisium+"</td>";
        row += "<td style='text-align: center'>"+this.periodeWisuda+"</td>";
        row += "<td>"+this.sk+"</td>";
        row += "</tr>";
        row += this.nim;
        if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
            rows.push(row);
        }
        i++;
        
    });
    return rows;
}
function tableJenisKontak(object, search){
    var rows = new Array();
    $.each(object, function(){
        var row = "";
        row += "<tr>";
        row +="<td>"+this.idJenisKontak+"</td>";
        row +="<td>"+this.namaJenisKontak+"</td>";
        row += "</tr>";
        row += this.nim;
        if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
            rows.push(row);
        }
    });
    return rows;
}

function tableProdi(object, search){
    var rows = new Array();
    $.each(object, function(){
        var row = "";
        row += "<tr>";
        row +="<td style='text-align: center'>"+this.kodeProdi+"</td>";
        row +="<td>"+this.nama+"</td>";
        row +="<td style='text-align: center'>"+this.inisial+"</td>";
        row += "</tr>";
        row += this.nim;
        if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
            rows.push(row);
        }
    });
    return rows;
}
function tablePeminatan(object, search){
    var rows = new Array();
    $.each(object, function(){
        var row = "";
        row +="<tr>";
        row +="<td style='text-align: center'>"+this.idPeminatan+"</td>";
        row +="<td>"+this.namaPeminatan+"</td>";
        row +="<td style='text-align: center'>"+this.inisial+"</td>";
        row +="<td style='text-align: center'>"+this.namaProdi+"</td>";
        row +="</tr>";
        row += this.nim;
        if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
            rows.push(row);
        }
    });
    return rows;
}

function tableLaporanEmploymentRate(objectA, objectB, search){
    var rows = new Array();
    var totalKerja = 0;
    var totalWirausaha = 0;
    var totalStudi = 0;
    var totalMenunggu = 0;
    var totalTotal = 0;
    var length = objectA.length;
    $.each(objectA, function(i){
        var emp = 0;
        var unemp = 0;
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
                kerja += this.jumlah;
                totalKerja += this.jumlah;
            }else if(status==true && this.posisiPertama.toString().toLowerCase()=="wirausaha"){
                wirausaha += this.jumlah;
                totalWirausaha += this.jumlah;
            }else if(status==true && this.posisiPertama.toString().toLowerCase()=="studi"){
                studi += this.jumlah;
                totalStudi += this.jumlah;
            }else if(status==true && this.posisiPertama.toString().toLowerCase()=="menunggu"){
                menunggu += this.jumlah;
                totalMenunggu += this.jumlah;
            }
            
            if(status==true){
                emp = kerja+wirausaha+studi;
                emp = emp/(kerja+wirausaha+studi+menunggu)*100;
                emp = emp.toFixed(2);

                unemp = menunggu/(kerja+wirausaha+studi+menunggu)*100;
                unemp = unemp.toFixed(2);
            }
                
            
        });
        var row = "<tr>";
        row += "<td>"+typeObjectA+"</td>";
        row += "<td style='text-align: center'>"+emp+"%</td>";
        row += "<td style='text-align: center'>"+unemp+"%</td>";
        //if(i==(objectA.length-1))row += "<td style='text-align: center'>"+i+"%</td>";
        row += "</tr>";
        rows.push(row);
    });
    return rows;
}
function tableLaporanKontak2(objectA, objectB, search){
    var rows = new Array();
    var row = "";
    $.each(objectA, function(){
        var kontak = 0;
        var unKontak = 0;
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
            
            if(status==true && this.kontak==true){
                kontak = this.jumlah;
            }else if(status==true && this.kontak==false){
                unKontak = this.jumlah;
            }
            
        })
        row += "<tr>";
        row += "<td>"+typeObjectA+"</td>";
        row += "<td style='text-align: center'>"+kontak+"</td>";
        row += "<td style='text-align: center'>"+unKontak+"</td>";
        row += "</tr>";
        //rows.push(row);
    })
    return row;
}
function tableLaporanMasaTunggu(objectA, objectB, search){
    var rows = new Array();
    var row = "";
    $.each(objectA, function(){
        var masaTunggu = 0;
        var masaTungguAll = 0;
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
            
            if(status==true && this.masaTunggu!=undefined){
                masaTunggu = this.masaTunggu;
            }else if(status==true && this.masaTungguAll!=undefined){
                masaTungguAll = this.masaTungguAll;
            }
            
        })
        row += "<tr>";
        row += "<td>"+typeObjectA+"</td>";
        row += "<td style='text-align: center'>"+masaTunggu+"</td>";
        row += "<td style='text-align: center'>"+masaTungguAll+"</td>";
        row += "</tr>";
        //rows.push(row);
    })
    return row;
}
function tableLaporanPosisi(objectA, objectB, search, jenisLaporan){
    var rows = new Array();
    var totalKerja = 0;
    var totalWirausaha = 0;
    var totalStudi = 0;
    var totalMenunggu = 0;
    var totalTotal = 0;
    var queryTotal = new Array();
    $.each(objectA, function(){
        var query = new Array();
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
                kerja += this.jumlah;
                totalKerja += this.jumlah;
                query[0] = "";
                query[0] += getQuery(this.prodi, this.peminatan, this.tahun, this.periode, this.angkatan, this.posisiPertama, jenisLaporan);
            }else if(status==true && this.posisiPertama.toString().toLowerCase()=="wirausaha"){
                wirausaha += this.jumlah;
                totalWirausaha += this.jumlah;
                query[1] = "";
                query[1] += getQuery(this.prodi, this.peminatan, this.tahun, this.periode, this.angkatan, this.posisiPertama, jenisLaporan);
            }else if(status==true && this.posisiPertama.toString().toLowerCase()=="studi"){
                studi = this.jumlah;
                totalStudi += this.jumlah;
                query[2] = "";
                query[2] += getQuery(this.prodi, this.peminatan, this.tahun, this.periode, this.angkatan, this.posisiPertama, jenisLaporan);
            }else if(status==true && this.posisiPertama.toString().toLowerCase()=="menunggu"){
                menunggu += this.jumlah;
                totalMenunggu += this.jumlah;
                query[3] = "";
                query[3] += getQuery(this.prodi, this.peminatan, this.tahun, this.periode, this.angkatan, this.posisiPertama, jenisLaporan);
            }
              
            if(status==true){
                query[4] = "";
                query[4] += getQuery(this.prodi, this.peminatan, this.tahun, this.periode, this.angkatan, null, jenisLaporan);  
            }
        });
//        var row = "<tr>";
//        row += "<td>"+typeObjectA+"</td>";
//        row += "<td style='text-align: center'><span style='display:none'>"+query[0]+"</span><a>"+kerja+"</a></td>";
//        row += "<td style='text-align: center'><span style='display:none'>"+query[1]+"</span><a>"+wirausaha+"</a></td>";
//        row += "<td style='text-align: center'><span style='display:none'>"+query[2]+"</span><a>"+studi+"</a></td>";
//        row += "<td style='text-align: center'><span style='display:none'>"+query[3]+"</span><a>"+menunggu+"</a></td>";
//        row += "<td style='text-align: center'><span style='display:none'>"+query[4]+"</span><a>"+(kerja+wirausaha+studi+menunggu)+"</a></td>";
//        row += "</tr>";
        var row = "<tr>";
        row += "<td>"+typeObjectA+"</td>";
        row += "<td style='text-align: center'>"+kerja+"</td>";
        row += "<td style='text-align: center'>"+wirausaha+"</td>";
        row += "<td style='text-align: center'>"+studi+"</td>";
        row += "<td style='text-align: center'>"+menunggu+"</td>";
        row += "<td style='text-align: center'>"+(kerja+wirausaha+studi+menunggu)+"</td>";
        row += "</tr>";
        rows.push(row);
    });
    
    queryTotal[0] = "";
    queryTotal[1] = "";
    queryTotal[2] = "";
    queryTotal[3] = "";
    queryTotal[4] = "";
    if(objectB[0].tahun){
        queryTotal[0] = getQueryTotal(search, null, null, "kerja", jenisLaporan);
        queryTotal[1] = getQueryTotal(search, null, null, "wirausaha", jenisLaporan);
        queryTotal[2] = getQueryTotal(search, null, null, "studi", jenisLaporan);
        queryTotal[3] = getQueryTotal(search, null, null, "menunggu", jenisLaporan);
        queryTotal[4] = getQueryTotal(search, null, null, null, jenisLaporan);
    
    }else if(objectB[0].periode){
        queryTotal[0] = getQueryTotal(null, search, null, "kerja", jenisLaporan);
        queryTotal[1] = getQueryTotal(null, search, null, "wirausaha", jenisLaporan);
        queryTotal[2] = getQueryTotal(null, search, null, "studi", jenisLaporan);
        queryTotal[3] = getQueryTotal(null, search, null, "menunggu", jenisLaporan);
        queryTotal[4] = getQueryTotal(null, search, null, null, jenisLaporan);
    
    }else if(objectB[0].angkatan){
        queryTotal[0] = getQueryTotal(null, null, search, "kerja", jenisLaporan);
        queryTotal[1] = getQueryTotal(null, null, search, "wirausaha", jenisLaporan);
        queryTotal[2] = getQueryTotal(null, null, search, "studi", jenisLaporan);
        queryTotal[3] = getQueryTotal(null, null, search, "menunggu", jenisLaporan);
        queryTotal[4] = getQueryTotal(search, null, null, search, null, jenisLaporan);
    
    }else{
        queryTotal[0] = getQueryTotal(null, null, null, "kerja", jenisLaporan);
        queryTotal[1] = getQueryTotal(null, null, null, "wirausaha", jenisLaporan);
        queryTotal[2] = getQueryTotal(null, null, null, "studi", jenisLaporan);
        queryTotal[3] = getQueryTotal(null, null, null, "menunggu", jenisLaporan);
        queryTotal[4] = getQueryTotal(null, null, null, null, jenisLaporan);
    
    }
    
//    var row = "<tr>";
//    row += "<td>Total</td>";
//    row += "<td style='text-align: center'><span style='display:none'>"+queryTotal[0]+"</span><a>"+totalKerja+"</a></td>";
//    row += "<td style='text-align: center'><span style='display:none'>"+queryTotal[1]+"</span><a>"+totalWirausaha+"</a></td>";
//    row += "<td style='text-align: center'><span style='display:none'>"+queryTotal[2]+"</span><a>"+totalStudi+"</a></td>";
//    row += "<td style='text-align: center'><span style='display:none'>"+queryTotal[3]+"</span><a>"+totalMenunggu+"</a></td>";
//    row += "<td style='text-align: center'><span style='display:none'>"+queryTotal[4]+"</span><a>"+(totalKerja+totalWirausaha+totalStudi+totalMenunggu)+"</a></td>";
//    row += "</tr>";
    var row = "<tr>";
    row += "<td>Total</td>";
    row += "<td style='text-align: center'>"+totalKerja+"</td>";
    row += "<td style='text-align: center'>"+totalWirausaha+"</td>";
    row += "<td style='text-align: center'>"+totalStudi+"</td>";
    row += "<td style='text-align: center'>"+totalMenunggu+"</td>";
    row += "<td style='text-align: center'>"+(totalKerja+totalWirausaha+totalStudi+totalMenunggu)+"</td>";
    row += "</tr>";
    rows.push(row);
    return rows;
}
function tableLaporanCore(objectA, objectB, search){
    var rows = new Array();
    var row = "";
    $.each(objectA, function(){
        var core = 0;
        var nonCore = 0;
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
            
            if(status==true && this.core=="1"){
                core = this.jumlah;
            }else if(status==true && this.core=="0"){
                nonCore = this.jumlah;
            }
            
        })
        row += "<tr>";
        row += "<td>"+typeObjectA+"</td>";
        row += "<td style='text-align: center'>"+core+"</td>";
        row += "<td style='text-align: center'>"+nonCore+"</td>";
        row += "</tr>";
        //rows.push(row);
    })
    return row;
}
function tableKontakAlumni(object, search, checked){
    var rows = new Array();
    $.each(object, function(){
        var row = "";
        row += "<tr>";
        row += "<td>"+this.nim+"</td>";
        row += "<td>"+this.angkatan+"</td>";
        row += "<td>"+this.nama+"</td>";
        row += "<td>"+this.prodi+"</td>";
        row += "<td>"+this.peminatan+"</td>";
        row += "<td>"+this.kontak+"</td>";
        row += "<td>"+this.statusMahasiswa+"</td>";
        row += "<td>"+this.posisi+"</td>";
        row += "<td>"+this.ipk+"</td>";
        if(checked.length==0){
            row += "<td><input type='checkbox' name='kontak' value='"+this.kontak+"'/></td>";
        }else{
            var duplicate = false;
            for(var i=0;i<checked.length;i++){
                if(this.kontak==checked[i]){
                    duplicate = true;
                    break;
                }
            }
            if(duplicate==false)row += "<td><input type='checkbox' name='kontak' value='"+this.kontak+"'/></td>";
            else row += "<td><input type='checkbox' name='kontak' value='"+this.kontak+"' checked/></td>";
        }
        row += "</tr>";
        row += this.nim;
        if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
            rows.push(row);
        }
    });
    return rows;
}

function getQuery(prodi, peminatan, tahun, periode, angkatan, posisiPertama, jenisLaporan){
    var query = "from Rekapposisialumni where ";
    if(prodi!=undefined && prodi!=null){
        query += "prodi='"+prodi+"'";
    }else if(peminatan!=undefined && peminatan!=null){
        query += "peminatan='"+peminatan+"'";
    }
    
    if(tahun!=undefined && tahun!=null){
        query += " AND tahunwisuda='"+tahun+"' "; 
    }else if(periode!=undefined && periode!=null){
        query += " AND wisuda='"+periode+"' "; 
    }else if(angkatan!=undefined && angkatan!=null){
        query += " AND angkatan='"+angkatan+"' "; 
    }
    
    if(posisiPertama!=undefined && posisiPertama!=null)query += " AND posisipertama='"+posisiPertama+"' "; 
    
    if(jenisLaporan=="greater"){
        query += " AND ipk>='2.75' "; 
    }else if(jenisLaporan=="less"){
        query += " AND ipk<'2.75' "; 
    }
    
    return query;
}
function getQueryTotal(tahun, periode, angkatan, posisiPertama, jenisLaporan){
    var query = "from Rekapposisialumni ";
    if(posisiPertama!=undefined && posisiPertama!=null)query += " where posisipertama='"+posisiPertama+"' "; 
    
    if(tahun!=undefined && tahun!=null){
        query += " AND tahunwisuda='"+tahun+"' "; 
    }else if(periode!=undefined && periode!=null){
        query += " AND wisuda='"+periode+"' "; 
    }else if(angkatan!=undefined && angkatan!=null){
        query += " AND angkatan='"+angkatan+"' "; 
    }
    
    if(jenisLaporan=="greater"){
        query += " AND ipk>='2.75' "; 
    }else if(jenisLaporan=="less"){
        query += " AND ipk<'2.75' "; 
    }
    
    return query;
}
function submitForm(){
    $("table tbody").find("a").click(function(){
        var temp = $(this).parent().find("span").text();
        if(temp!="undefined" && temp!=undefined && temp!=null && temp!=""){
            $("form input[name=query]").val(temp);
            $("form").submit();
        }
    });
}

function tableStudiDisplay(objectA, objectB, search){
    var rows = new Array();
    var i = 1;
    $.each(objectA, function(){
        if(this.posisiPertama=="studi"){
            var nim = this.nim;
            var nama = this.nama;
            var prodi = this.prodi;
            var tanggalMulai = this.tanggalMulai;
            var tanggalYudisium = this.tanggalYudisium;
            var periodeWisuda = this.periodeWisuda;
            $.each(objectB, function(){
                if(nim==this.nim && tanggalMulai==this.tanggalMasuk){
                    var row = "<tr>";
                    row += "<td>"+i+"</td>";
                    row += "<td style='text-align: center'>"+this.nim+"</td>";
                    row += "<td>"+prodi+"</td>";
                    row += "<td>"+nama+"</td>";
                    row += "<td>"+this.universitas+"</td>";
                    row += "<td>"+this.fakultas+"</td>";
                    row += "<td>"+this.jurusan+"</td>";
                    row += "<td>"+this.tanggalMasuk+"</td>";
                    row += "<td>"+this.tanggalKeluar+"</td>";
                    row += "<td>"+this.core+"</td>";
                    row += "<td>"+tanggalYudisium+"</td>";
                    row += "<td>"+periodeWisuda+"</td>";
                    row += "</tr>";
                    if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
                        rows.push(row);
                    }
                    i++;
                }
                
            });
        }
    });
    return rows;
}

function tableWirausahaDisplay(objectA, objectB, search){
    var rows = new Array();
    var i = 1;
    $.each(objectA, function(){
        if(this.posisiPertama=="wirausaha"){
            var nim = this.nim;
            var nama = this.nama;
            var prodi = this.prodi;
            var tanggalMulai = this.tanggalMulai;
            var tanggalYudisium = this.tanggalYudisium;
            var periodeWisuda = this.periodeWisuda;
            $.each(objectB, function(){
                if(nim==this.nim && tanggalMulai==this.tanggalMulai){
                    var row = "<tr>";
                    row += "<td>"+i+"</td>";
                    row += "<td style='text-align: center'>"+this.nim+"</td>";
                    row += "<td>"+prodi+"</td>";
                    row += "<td>"+nama+"</td>";
                    row += "<td>"+this.perusahaan+"</td>";
                    row += "<td>"+this.jenisUsaha+"</td>";
                    row += "<td>"+this.tanggalMulai+"</td>";
                    row += "<td>"+this.core+"</td>";
                    row += "<td>"+tanggalYudisium+"</td>";
                    row += "<td>"+periodeWisuda+"</td>";
                    row += "</tr>";
                    if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
                        rows.push(row);
                    }
                    i++;
                }
                
            });
        }
    });
    return rows;
}

function tableKerjaDisplay(objectA, objectB, search){
    var rows = new Array();
    var i = 1;
    $.each(objectA, function(){
        if(this.posisiPertama=="kerja"){
            var nim = this.nim;
            var nama = this.nama;
            var prodi = this.prodi;
            var tanggalMulai = this.tanggalMulai;
            var tanggalYudisium = this.tanggalYudisium;
            var periodeWisuda = this.periodeWisuda;
            var masaTunggu = this.masaTunggu;
            $.each(objectB, function(){
                if(nim==this.nim && tanggalMulai==this.tanggalMasuk){
                    var row = "<tr>";
                    row += "<td>"+i+"</td>";
                    row += "<td style='text-align: center'>"+nim+"</td>";
                    row += "<td>"+prodi+"</td>";
                    row += "<td>"+nama+"</td>";
                    row += "<td>"+this.perusahaan+"</td>";
                    row += "<td>"+this.tanggalMasuk+"</td>";
                    row += "<td>"+this.tanggalKeluar+"</td>";
                    row += "<td>"+tanggalYudisium+"</td>";
                    row += "<td>"+periodeWisuda+"</td>";
                    row += "<td>"+masaTunggu+"</td>";
                    row += "<td>"+this.posisiKerja+"</td>";
                    row += "<td>"+this.jenisKerja+"</td>";
                    row += "<td>"+this.gaji+"</td>";
                    row += "<td>"+this.core+"</td>";
                    row += "</tr>";
                    if(row.toUpperCase().indexOf(search.toUpperCase())!=-1){
                        rows.push(row);
                    }
                    i++;
                }
                
            });
        }
    });
    return rows;
}