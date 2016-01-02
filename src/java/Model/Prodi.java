package Model;
// Generated 19 Agu 13 5:20:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Prodi generated by hbm2java
 */
public class Prodi  implements java.io.Serializable {


     private Integer idProdi;
     private String kodeProdi;
     private String nama;
     private String inisial;
     private boolean status;
     private Set peminatans = new HashSet(0);

    public Prodi() {
    }

	
    public Prodi(String kodeProdi, String nama, String inisial, boolean status) {
        this.kodeProdi = kodeProdi;
        this.nama = nama;
        this.inisial = inisial;
        this.status = status;
    }
    public Prodi(String kodeProdi, String nama, String inisial, boolean status, Set peminatans) {
       this.kodeProdi = kodeProdi;
       this.nama = nama;
       this.inisial = inisial;
       this.status = status;
       this.peminatans = peminatans;
    }
   
    public Integer getIdProdi() {
        return this.idProdi;
    }
    
    public void setIdProdi(Integer idProdi) {
        this.idProdi = idProdi;
    }
    public String getKodeProdi() {
        return this.kodeProdi;
    }
    
    public void setKodeProdi(String kodeProdi) {
        this.kodeProdi = kodeProdi;
    }
    public String getNama() {
        return this.nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getInisial() {
        return this.inisial;
    }
    
    public void setInisial(String inisial) {
        this.inisial = inisial;
    }
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    public Set getPeminatans() {
        return this.peminatans;
    }
    
    public void setPeminatans(Set peminatans) {
        this.peminatans = peminatans;
    }




}

