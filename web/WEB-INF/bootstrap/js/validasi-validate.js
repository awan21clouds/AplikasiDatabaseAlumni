$(document).ready(function(){
    $('.alert').hide();
    jQuery.validator.setDefaults({
         showErrors: function(errorMap, errorList) {
            if(errorList.length==0){
                $('.alert').hide();
            }else{
                var error = "<ul>";
                $.each(errorMap, function(){
                    error += "<li>"+this+"</li>";
                });
                error +="</ul>";
                $(".alert span").html(error);
                $('.alert').show();
            }
        }
    });
    jQuery.validator.addMethod("alpha", function(value, element) {
        return this.optional(element) || value == value.match(/^[a-zA-Z ]+$/);
    },"Only Characters Allowed.");
    
    jQuery.validator.addMethod("date", function(value, element, param) {
        return this.optional(element) || value != "0000-00-00";
    }, "This has to be different...");
    
    
    $("#main-pegawai, #sms-broadcast").is(function(){
        $(this).find("form").validate({
            rules:{
                kontak:"required",
                pesan : {
                    required: true,
                    maxlength: 160
		}
            },
            messages:{
                kontak : "Pilih Tujuan Pengiriman Pesan",
                pesan : {
                    required: "Pesan Tidak Boleh Kosong",
                    maxlength: "Panjang Pesan hanya 160 digit"
		}
            }
        });
    });
    
    $("#profil-pegawai").is(function(){
        $(this).find("form").validate({
            rules:{
                nama:{
                    required : true,
                    alpha : true
                },
                telepon:{
                    required : true,
                    number: true 
                },
                email:{
                    required : true,
                    email : true
                }
            },
            messages:{
                nama:{
                    required : "Nama Tidak Boleh Dikosongkan",
                    alpha : "Nama Diisi Dengan Huruf" 
                },
                telepon:{
                    required : "Telepon Tidak Boleh Dikosongkan",
                    number: "Telepon Diisi Dengan Angka" 
                },
                email:{
                    required : "Email Tidak Boleh Dikosongkan",
                    email: "Email Tidak Valid" 
                }
            }
        });
    });
    
    $("#input-pegawai").is(function(){
        $(this).find("form").validate({
            rules:{
                idPegawai:{
                    required : true,
                    lettersonly : true,
                    minlength:3,
                    maxlength:3
                },
                nama:{
                    required : true,
                    alpha : true
                },
                telepon:{
                    required : true,
                    number: true 
                },
                email:{
                    required : true,
                    email : true
                }
            },
            messages:{
                idPegawai:{
                    required : "Kode Pegawai Tidak Boleh Dikosongkan",
                    lettersonly : "Kode Pegawai Diisi Dengan Huruf Tanpa Spasi",
                    minlength:"Kode Pegawai Diisi Dengan 3 Huruf Tanpa Spasi",
                    maxlength:"Kode Pegawai Diisi Dengan 3 Huruf Tanpa Spasi"
                },
                nama:{
                    required : "Nama Tidak Boleh Dikosongkan",
                    alpha : "Nama Diisi Dengan Huruf" 
                },
                telepon:{
                    required : "Telepon Tidak Boleh Dikosongkan",
                    number: "Telepon Diisi Dengan Angka" 
                },
                email:{
                    required : "Email Tidak Boleh Dikosongkan",
                    email: "Email Tidak Valid" 
                }
            }
        });
    });
    
    
    $("#input-alumni").is(function(){
        $(this).find("form").validate({
            rules:{
                nim:{
                    required : true,
                    number : true,
                    minlength:8,
                    maxlength:8
                },
                nama:{
                    required : true,
                    alpha : true
                },
                tanggalLahir:{
                    required : true
                }
            },
            messages:{
                nim:{
                    required : "NIM Tidak Boleh Dikosongkan",
                    number : "NIM Diisi Dengan Huruf Tanpa Spasi",
                    lettersonly : "NIM Diisi Dengan Huruf Tanpa Spasi",
                    minlength:"NIM Diisi Dengan 8 Angka Tanpa Spasi",
                    maxlength:"NIM Diisi Dengan 8 Angka Tanpa Spasi"
                },
                nama:{
                    required : "Nama Tidak Boleh Dikosongkan",
                    alpha : "Nama Diisi Dengan Huruf" 
                },
                tanggalLahir:{
                    required : "Tanggal Lahir Tidak Boleh Dikosongkan"
                }
            }
        });
    });
    
    
    $("#studi-alumni").is(function(){
        $(this).find("form").validate({
            rules:{
                universitas:{
                    required : true,
                    alpha : true
                },
                fakultas:{
                    required : true,
                    alpha : true
                },
                prodi:{
                    required : true,
                    alpha : true
                },
                tanggalMasuk:{
                    required : true,
                    date : true
                },
                tanggalKeluar:{
                    required : true
                }
            },
            messages:{
                universitas:{
                    required : "Universitas Tidak Boleh Dikosongkan",
                    alpha : "Universitas Diisi Dengan Huruf" 
                },
                fakultas:{
                    required : "Fakultas Tidak Boleh Dikosongkan",
                    alpha : "Fakultas Diisi Dengan Huruf" 
                },
                prodi:{
                    required : "Prodi Tidak Boleh Dikosongkan",
                    alpha : "Prodi Diisi Dengan Huruf" 
                },
                tanggalMasuk:{
                    required : "Tanggal Masuk Tidak Boleh Dikosongkan",
                    date : "Tanggal Masuk Tidak Boleh 0000-00-00"
                },
                tanggalKeluar:{
                    required : "Tanggal Keluar Tidak Boleh Dikosongkan"
                }
            }
        });
    });
    
    $("#kerja-alumni").is(function(){
        $(this).find("form").validate({
            rules:{
                perusahaan:{
                    required : true,
                    alpha : true
                },
                tanggalMasuk:{
                    required : true,
                    date : true
                },
                tanggalKeluar:{
                    required : true
                }, 
                gaji:{
                    required : true,
                    number : true
                } 
            },
            messages:{
                perusahaan:{
                    required : "Perusahaan Tidak Boleh Dikosongkan",
                    alpha : "Perusahaan Diisi Dengan Huruf" 
                },
                tanggalMasuk:{
                    required : "Tanggal Masuk Tidak Boleh Dikosongkan",
                    date : "Tanggal Masuk Tidak Boleh 0000-00-00"
                },
                tanggalKeluar:{
                    required : "Tanggal Keluar Tidak Boleh Dikosongkan"
                },
                gaji:{
                    required : "Gaji Tidak Boleh Dikosongkan",
                    number : "Gaji Diisi Dengan Angka"
                }
            }
        });
    });
    
    $("#wirausaha-alumni").is(function(){
        $(this).find("form").validate({
            rules:{
                namaPerusahaan:{
                    required : true,
                    alpha : true
                },
                jenisUsaha:{
                    required : true
                },
                tanggalMulai:{
                    required : true,
                    date : true
                } 
            },
            messages:{
                namaPerusahaan:{
                    required : "Perusahaan Tidak Boleh Dikosongkan",
                    alpha : "Perusahaan Diisi Dengan Huruf" 
                },
                jenisUsaha:{
                    required : "Jenis Usaha Tidak Boleh Dikosongkan"
                },
                tanggalMulai:{
                    required : "Tanggal Mulai Tidak Boleh Dikosongkan",
                    date : "Tanggal Mulai Tidak Boleh 0000-00-00"
                }
            }
        });
    });
    
    $("#kontak-alumni").is(function(){
      $(this).find("form").validate({
            rules:{
                kontak:{
                    required : true
                } 
            },
            messages:{
                kontak:{
                    required : "Jika Semua Field Kosong, Maka Form Tidak Bisa Disubmit"
                }
            }
        });
    });
     
    $("#ganti-password").is(function(){
        $(this).find("form").validate({
            rules:{
                passwordLama:{
                   required: true
                },
                passwordBaru:{
                   required: true
                },
                confPasswordBaru:{
                   required: true,
                   equalTo: "#passwordBaru"
                }
            },
            messages:{
                passwordLama:{
                   required: "Password Lama Tidak Boleh Dikosongkan"
                },
                passwordBaru:{
                   required: "Password Baru Tidak Boleh Dikosongkan"
                },
                confPasswordBaru:{
                   required: "Konfirmasi Password Baru Tidak Boleh Dikosongkan",
                   equalTo: "Konfirmasi Password Tidak Sama Dengan Password Baru"
                }
            }
        });
    });
    
    
    $("#upload-alumni").is(function(){
        $(this).find("form").validate({
            rules:{
                filePath:{
                   required: true
                }
            },
            messages:{
                filePath:{
                   required: "Pilih File Terlebih Dahulu"
                }
            }
        });
    });
    
    $("#input-prodi").is(function(){
        $(this).find("form").validate({
            rules:{
                kodeProdi:{
                   required: true,
                   number:true
                },
                nama:{
                   required: true,
                   alpha : true
                },
                inisial:{
                   required: true,
                   lettersonly:true
                }
            },
            messages:{
                kodeProdi:{
                   required: "Kode Program Studi Tidak Boleh Dikosongkan",
                   number: "Kode Program Studi Diisi Dengan Angka"
                },
                nama:{
                   required: "Nama Program Studi Tidak Boleh Dikosongkan",
                   alpha: "Nama Program Studi Diisi Dengan Huruf"
                },
                inisial:{
                   required: "Inisial Program Studi Tidak Boleh Dikosongkan",
                   lettersonly: "Nama Program Studi Diisi Dengan Huruf"
                }
            }
        });
    });
    
    $("#input-peminatan").is(function(){
        $(this).find("form").validate({
            rules:{
                nama:{
                   required: true,
                   alpha : true
                },
                inisial:{
                   required: true,
                   lettersonly:true
                }
            },
            messages:{
                nama:{
                   required: "Nama Peminatan Tidak Boleh Dikosongkan",
                   alpha: "Nama Peminatan Diisi Dengan Huruf"
                },
                inisial:{
                   required: "Inisial Peminatan Tidak Boleh Dikosongkan",
                   lettersonly: "Nama Peminatan Diisi Dengan Huruf"
                }
            }
        });
    });
    
    $("#input-jeniskontak").is(function(){
        $(this).find("form").validate({
            rules:{
                jenisKontak:{
                   required: true,
                   alpha : true
                }
            },
            messages:{
                jenisKontak:{
                   required: "Jenis Kontak Tidak Boleh Dikosongkan",
                   alpha: "Jenis Kontak Diisi Dengan Huruf"
                }
            }
        });
    });
    
    $("#detail-alumni2").is(function(){
        $(this).find("form:eq(0)").validate({
            rules:{
                kontak:{
                    required : true
                } 
            },
            messages:{
                kontak:{
                    required : "Jika Semua Field Kosong, Maka Form Tidak Bisa Disubmit"
                }
            }
        });
        
        $(this).find("form:eq(1)").validate({
            rules:{
                universitas:{
                    required : true,
                    alpha : true
                },
                fakultas:{
                    required : true,
                    alpha : true
                },
                prodi:{
                    required : true,
                    alpha : true
                },
                tanggalMasuk:{
                    required : true,
                    date : true
                },
                tanggalKeluar:{
                    required : true
                }
            },
            messages:{
                universitas:{
                    required : "Studi : Universitas Tidak Boleh Dikosongkan",
                    alpha : "Studi : Universitas Diisi Dengan Huruf" 
                },
                fakultas:{
                    required : "Studi : Fakultas Tidak Boleh Dikosongkan",
                    alpha : "Studi : Fakultas Diisi Dengan Huruf" 
                },
                prodi:{
                    required : "Studi : Prodi Tidak Boleh Dikosongkan",
                    alpha : "Studi : Prodi Diisi Dengan Huruf" 
                },
                tanggalMasuk:{
                    required : "Studi : Tanggal Masuk Tidak Boleh Dikosongkan",
                    date : "Studi : Tanggal Masuk Tidak Boleh 0000-00-00"
                },
                tanggalKeluar:{
                    required : "Studi : Tanggal Keluar Tidak Boleh Dikosongkan"
                }
            }
        });
        
        $(this).find("form:eq(2)").validate({
            rules:{
                perusahaan:{
                    required : true,
                    alpha : true
                },
                tanggalMasuk:{
                    required : true,
                    date : true
                },
                tanggalKeluar:{
                    required : true
                }, 
                gaji:{
                    required : true,
                    number : true
                } 
            },
            messages:{
                perusahaan:{
                    required : "Kerja : Perusahaan Tidak Boleh Dikosongkan",
                    alpha : "Kerja : Perusahaan Diisi Dengan Huruf" 
                },
                tanggalMasuk:{
                    required : "Kerja : Tanggal Masuk Tidak Boleh Dikosongkan",
                    date : "Kerja : Tanggal Masuk Tidak Boleh 0000-00-00"
                },
                tanggalKeluar:{
                    required : "Kerja : Tanggal Keluar Tidak Boleh Dikosongkan"
                },
                gaji:{
                    required : "Kerja : Gaji Tidak Boleh Dikosongkan",
                    number : "Kerja : Gaji Diisi Dengan Angka"
                }
            }
        });
        
        $(this).find("form:eq(3)").validate({
            rules:{
                namaPerusahaan:{
                    required : true,
                    alpha : true
                },
                jenisUsaha:{
                    required : true
                },
                tanggalMulai:{
                    required : true,
                    date : true
                } 
            },
            messages:{
                namaPerusahaan:{
                    required : "Wirausaha : Perusahaan Tidak Boleh Dikosongkan",
                    alpha : "Wirausaha : Perusahaan Diisi Dengan Huruf" 
                },
                jenisUsaha:{
                    required : "Wirausaha : Jenis Usaha Tidak Boleh Dikosongkan"
                },
                tanggalMulai:{
                    required : "Wirausaha : Tanggal Mulai Tidak Boleh Dikosongkan",
                    date : "Wirausaha : Tanggal Mulai Tidak Boleh 0000-00-00"
                }
            }
        });
    });
        
});


