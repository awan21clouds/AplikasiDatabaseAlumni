package Model;
// Generated 19 Agu 13 5:20:12 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * RekapposisialumniId generated by hbm2java
 */
public class RekapposisialumniId  implements java.io.Serializable {


     private String nim;
     private String nama;
     private String angkatan;
     private String prodi;
     private String peminatan;
     private Date wisuda;
     private String periodewisuda;
     private Integer tahunwisuda;
     private Date tanggalYudisium;
     private Double ipk;
     private Boolean kontak;
     private String predikat;
     private String sk;
     private String tanggalKerja;
     private String tanggalWirausaha;
     private String tanggalStudiLanjut;
     private Date tanggalMulai;
     private String posisiPertama;
     private Long core;
     private Integer masaTunggu;

    public RekapposisialumniId() {
    }

	
    public RekapposisialumniId(String nim, String nama, String prodi, Date wisuda, String periodewisuda, String tanggalKerja, String tanggalWirausaha, String tanggalStudiLanjut, Date tanggalMulai, String posisiPertama) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.wisuda = wisuda;
        this.periodewisuda = periodewisuda;
        this.tanggalKerja = tanggalKerja;
        this.tanggalWirausaha = tanggalWirausaha;
        this.tanggalStudiLanjut = tanggalStudiLanjut;
        this.tanggalMulai = tanggalMulai;
        this.posisiPertama = posisiPertama;
    }
    public RekapposisialumniId(String nim, String nama, String angkatan, String prodi, String peminatan, Date wisuda, String periodewisuda, Integer tahunwisuda, Date tanggalYudisium, Double ipk, Boolean kontak, String predikat, String sk, String tanggalKerja, String tanggalWirausaha, String tanggalStudiLanjut, Date tanggalMulai, String posisiPertama, Long core, Integer masaTunggu) {
       this.nim = nim;
       this.nama = nama;
       this.angkatan = angkatan;
       this.prodi = prodi;
       this.peminatan = peminatan;
       this.wisuda = wisuda;
       this.periodewisuda = periodewisuda;
       this.tahunwisuda = tahunwisuda;
       this.tanggalYudisium = tanggalYudisium;
       this.ipk = ipk;
       this.kontak = kontak;
       this.predikat = predikat;
       this.sk = sk;
       this.tanggalKerja = tanggalKerja;
       this.tanggalWirausaha = tanggalWirausaha;
       this.tanggalStudiLanjut = tanggalStudiLanjut;
       this.tanggalMulai = tanggalMulai;
       this.posisiPertama = posisiPertama;
       this.core = core;
       this.masaTunggu = masaTunggu;
    }
   
    public String getNim() {
        return this.nim;
    }
    
    public void setNim(String nim) {
        this.nim = nim;
    }
    public String getNama() {
        return this.nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getAngkatan() {
        return this.angkatan;
    }
    
    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }
    public String getProdi() {
        return this.prodi;
    }
    
    public void setProdi(String prodi) {
        this.prodi = prodi;
    }
    public String getPeminatan() {
        return this.peminatan;
    }
    
    public void setPeminatan(String peminatan) {
        this.peminatan = peminatan;
    }
    public Date getWisuda() {
        return this.wisuda;
    }
    
    public void setWisuda(Date wisuda) {
        this.wisuda = wisuda;
    }
    public String getPeriodewisuda() {
        return this.periodewisuda;
    }
    
    public void setPeriodewisuda(String periodewisuda) {
        this.periodewisuda = periodewisuda;
    }
    public Integer getTahunwisuda() {
        return this.tahunwisuda;
    }
    
    public void setTahunwisuda(Integer tahunwisuda) {
        this.tahunwisuda = tahunwisuda;
    }
    public Date getTanggalYudisium() {
        return this.tanggalYudisium;
    }
    
    public void setTanggalYudisium(Date tanggalYudisium) {
        this.tanggalYudisium = tanggalYudisium;
    }
    public Double getIpk() {
        return this.ipk;
    }
    
    public void setIpk(Double ipk) {
        this.ipk = ipk;
    }
    public Boolean getKontak() {
        return this.kontak;
    }
    
    public void setKontak(Boolean kontak) {
        this.kontak = kontak;
    }
    public String getPredikat() {
        return this.predikat;
    }
    
    public void setPredikat(String predikat) {
        this.predikat = predikat;
    }
    public String getSk() {
        return this.sk;
    }
    
    public void setSk(String sk) {
        this.sk = sk;
    }
    public String getTanggalKerja() {
        return this.tanggalKerja;
    }
    
    public void setTanggalKerja(String tanggalKerja) {
        this.tanggalKerja = tanggalKerja;
    }
    public String getTanggalWirausaha() {
        return this.tanggalWirausaha;
    }
    
    public void setTanggalWirausaha(String tanggalWirausaha) {
        this.tanggalWirausaha = tanggalWirausaha;
    }
    public String getTanggalStudiLanjut() {
        return this.tanggalStudiLanjut;
    }
    
    public void setTanggalStudiLanjut(String tanggalStudiLanjut) {
        this.tanggalStudiLanjut = tanggalStudiLanjut;
    }
    public Date getTanggalMulai() {
        return this.tanggalMulai;
    }
    
    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }
    public String getPosisiPertama() {
        return this.posisiPertama;
    }
    
    public void setPosisiPertama(String posisiPertama) {
        this.posisiPertama = posisiPertama;
    }
    public Long getCore() {
        return this.core;
    }
    
    public void setCore(Long core) {
        this.core = core;
    }
    public Integer getMasaTunggu() {
        return this.masaTunggu;
    }
    
    public void setMasaTunggu(Integer masaTunggu) {
        this.masaTunggu = masaTunggu;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RekapposisialumniId) ) return false;
		 RekapposisialumniId castOther = ( RekapposisialumniId ) other; 
         
		 return ( (this.getNim()==castOther.getNim()) || ( this.getNim()!=null && castOther.getNim()!=null && this.getNim().equals(castOther.getNim()) ) )
 && ( (this.getNama()==castOther.getNama()) || ( this.getNama()!=null && castOther.getNama()!=null && this.getNama().equals(castOther.getNama()) ) )
 && ( (this.getAngkatan()==castOther.getAngkatan()) || ( this.getAngkatan()!=null && castOther.getAngkatan()!=null && this.getAngkatan().equals(castOther.getAngkatan()) ) )
 && ( (this.getProdi()==castOther.getProdi()) || ( this.getProdi()!=null && castOther.getProdi()!=null && this.getProdi().equals(castOther.getProdi()) ) )
 && ( (this.getPeminatan()==castOther.getPeminatan()) || ( this.getPeminatan()!=null && castOther.getPeminatan()!=null && this.getPeminatan().equals(castOther.getPeminatan()) ) )
 && ( (this.getWisuda()==castOther.getWisuda()) || ( this.getWisuda()!=null && castOther.getWisuda()!=null && this.getWisuda().equals(castOther.getWisuda()) ) )
 && ( (this.getPeriodewisuda()==castOther.getPeriodewisuda()) || ( this.getPeriodewisuda()!=null && castOther.getPeriodewisuda()!=null && this.getPeriodewisuda().equals(castOther.getPeriodewisuda()) ) )
 && ( (this.getTahunwisuda()==castOther.getTahunwisuda()) || ( this.getTahunwisuda()!=null && castOther.getTahunwisuda()!=null && this.getTahunwisuda().equals(castOther.getTahunwisuda()) ) )
 && ( (this.getTanggalYudisium()==castOther.getTanggalYudisium()) || ( this.getTanggalYudisium()!=null && castOther.getTanggalYudisium()!=null && this.getTanggalYudisium().equals(castOther.getTanggalYudisium()) ) )
 && ( (this.getIpk()==castOther.getIpk()) || ( this.getIpk()!=null && castOther.getIpk()!=null && this.getIpk().equals(castOther.getIpk()) ) )
 && ( (this.getKontak()==castOther.getKontak()) || ( this.getKontak()!=null && castOther.getKontak()!=null && this.getKontak().equals(castOther.getKontak()) ) )
 && ( (this.getPredikat()==castOther.getPredikat()) || ( this.getPredikat()!=null && castOther.getPredikat()!=null && this.getPredikat().equals(castOther.getPredikat()) ) )
 && ( (this.getSk()==castOther.getSk()) || ( this.getSk()!=null && castOther.getSk()!=null && this.getSk().equals(castOther.getSk()) ) )
 && ( (this.getTanggalKerja()==castOther.getTanggalKerja()) || ( this.getTanggalKerja()!=null && castOther.getTanggalKerja()!=null && this.getTanggalKerja().equals(castOther.getTanggalKerja()) ) )
 && ( (this.getTanggalWirausaha()==castOther.getTanggalWirausaha()) || ( this.getTanggalWirausaha()!=null && castOther.getTanggalWirausaha()!=null && this.getTanggalWirausaha().equals(castOther.getTanggalWirausaha()) ) )
 && ( (this.getTanggalStudiLanjut()==castOther.getTanggalStudiLanjut()) || ( this.getTanggalStudiLanjut()!=null && castOther.getTanggalStudiLanjut()!=null && this.getTanggalStudiLanjut().equals(castOther.getTanggalStudiLanjut()) ) )
 && ( (this.getTanggalMulai()==castOther.getTanggalMulai()) || ( this.getTanggalMulai()!=null && castOther.getTanggalMulai()!=null && this.getTanggalMulai().equals(castOther.getTanggalMulai()) ) )
 && ( (this.getPosisiPertama()==castOther.getPosisiPertama()) || ( this.getPosisiPertama()!=null && castOther.getPosisiPertama()!=null && this.getPosisiPertama().equals(castOther.getPosisiPertama()) ) )
 && ( (this.getCore()==castOther.getCore()) || ( this.getCore()!=null && castOther.getCore()!=null && this.getCore().equals(castOther.getCore()) ) )
 && ( (this.getMasaTunggu()==castOther.getMasaTunggu()) || ( this.getMasaTunggu()!=null && castOther.getMasaTunggu()!=null && this.getMasaTunggu().equals(castOther.getMasaTunggu()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getNim() == null ? 0 : this.getNim().hashCode() );
         result = 37 * result + ( getNama() == null ? 0 : this.getNama().hashCode() );
         result = 37 * result + ( getAngkatan() == null ? 0 : this.getAngkatan().hashCode() );
         result = 37 * result + ( getProdi() == null ? 0 : this.getProdi().hashCode() );
         result = 37 * result + ( getPeminatan() == null ? 0 : this.getPeminatan().hashCode() );
         result = 37 * result + ( getWisuda() == null ? 0 : this.getWisuda().hashCode() );
         result = 37 * result + ( getPeriodewisuda() == null ? 0 : this.getPeriodewisuda().hashCode() );
         result = 37 * result + ( getTahunwisuda() == null ? 0 : this.getTahunwisuda().hashCode() );
         result = 37 * result + ( getTanggalYudisium() == null ? 0 : this.getTanggalYudisium().hashCode() );
         result = 37 * result + ( getIpk() == null ? 0 : this.getIpk().hashCode() );
         result = 37 * result + ( getKontak() == null ? 0 : this.getKontak().hashCode() );
         result = 37 * result + ( getPredikat() == null ? 0 : this.getPredikat().hashCode() );
         result = 37 * result + ( getSk() == null ? 0 : this.getSk().hashCode() );
         result = 37 * result + ( getTanggalKerja() == null ? 0 : this.getTanggalKerja().hashCode() );
         result = 37 * result + ( getTanggalWirausaha() == null ? 0 : this.getTanggalWirausaha().hashCode() );
         result = 37 * result + ( getTanggalStudiLanjut() == null ? 0 : this.getTanggalStudiLanjut().hashCode() );
         result = 37 * result + ( getTanggalMulai() == null ? 0 : this.getTanggalMulai().hashCode() );
         result = 37 * result + ( getPosisiPertama() == null ? 0 : this.getPosisiPertama().hashCode() );
         result = 37 * result + ( getCore() == null ? 0 : this.getCore().hashCode() );
         result = 37 * result + ( getMasaTunggu() == null ? 0 : this.getMasaTunggu().hashCode() );
         return result;
   }   


}


