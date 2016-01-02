package Model;
// Generated 19 Agu 13 5:20:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Jeniskerja generated by hbm2java
 */
public class Jeniskerja  implements java.io.Serializable {


     private Integer idJenisKerja;
     private String jenisKerja;
     private String keterangan;
     private Set kerjas = new HashSet(0);

    public Jeniskerja() {
    }

	
    public Jeniskerja(String jenisKerja, String keterangan) {
        this.jenisKerja = jenisKerja;
        this.keterangan = keterangan;
    }
    public Jeniskerja(String jenisKerja, String keterangan, Set kerjas) {
       this.jenisKerja = jenisKerja;
       this.keterangan = keterangan;
       this.kerjas = kerjas;
    }
   
    public Integer getIdJenisKerja() {
        return this.idJenisKerja;
    }
    
    public void setIdJenisKerja(Integer idJenisKerja) {
        this.idJenisKerja = idJenisKerja;
    }
    public String getJenisKerja() {
        return this.jenisKerja;
    }
    
    public void setJenisKerja(String jenisKerja) {
        this.jenisKerja = jenisKerja;
    }
    public String getKeterangan() {
        return this.keterangan;
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    public Set getKerjas() {
        return this.kerjas;
    }
    
    public void setKerjas(Set kerjas) {
        this.kerjas = kerjas;
    }




}


