package Model;
// Generated 19 Agu 13 5:20:12 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Statusmahasiswa generated by hbm2java
 */
public class Statusmahasiswa  implements java.io.Serializable {


     private Integer idStatusMahasiswa;
     private String statusMahasiswa;
     private Set mahasiswas = new HashSet(0);

    public Statusmahasiswa() {
    }

	
    public Statusmahasiswa(String statusMahasiswa) {
        this.statusMahasiswa = statusMahasiswa;
    }
    public Statusmahasiswa(String statusMahasiswa, Set mahasiswas) {
       this.statusMahasiswa = statusMahasiswa;
       this.mahasiswas = mahasiswas;
    }
   
    public Integer getIdStatusMahasiswa() {
        return this.idStatusMahasiswa;
    }
    
    public void setIdStatusMahasiswa(Integer idStatusMahasiswa) {
        this.idStatusMahasiswa = idStatusMahasiswa;
    }
    public String getStatusMahasiswa() {
        return this.statusMahasiswa;
    }
    
    public void setStatusMahasiswa(String statusMahasiswa) {
        this.statusMahasiswa = statusMahasiswa;
    }
    public Set getMahasiswas() {
        return this.mahasiswas;
    }
    
    public void setMahasiswas(Set mahasiswas) {
        this.mahasiswas = mahasiswas;
    }




}


