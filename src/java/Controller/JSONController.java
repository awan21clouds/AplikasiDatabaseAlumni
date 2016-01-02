/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.*;
import Model.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author FAHMI
 */
@Controller
public class JSONController {
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private LevelDAO levelDAO;
    
    @Autowired
    private MahasiswaDAO mahasiswaDAO;
    
    @Autowired
    private WisudaDAO wisudaDAO;
    
    @Autowired
    private PegawaiDAO pegawaiDAO;
    
    @Autowired
    private ProdiDAO prodiDAO;
    
    @Autowired
    private PeminatanDAO peminatanDAO;
    
    @Autowired
    private JenisKontakDAO jenisKontakDAO;
    
    @Autowired
    private JenisKerjaDAO jenisKerjaDAO;
    
    @Autowired
    private PosisiKerjaDAO posisiKerjaDAO;
    
    @Autowired
    private KerjaDAO kerjaDAO;
    
    @Autowired
    private StudiDAO studiDAO;
    
    @Autowired
    private WirausahaDAO wirausahaDAO;
    
    @Autowired
    private KontakDAO kontakDAO;
    
    @Autowired
    private KerjaViewDAO kerjaViewDAO; 
    
    @Autowired
    private WirausahaViewDAO wirausahaViewDAO; 
    
    @Autowired
    private StudiViewDAO studiViewDAO; 
    
    @Autowired
    private AlumniViewDAO alumniViewDAO; 
    
    @Autowired
    private RekapPosisiAlumniDAO rekapPosisiAlumniDAO; 
    
    @Autowired
    private TeleponViewDAO teleponViewDAO; 
    
    @RequestMapping(value="/userJSON", method=RequestMethod.GET)
    public @ResponseBody String getUser(){
        JSONObject object = new JSONObject();
        JSONArray arrUser = new JSONArray();
        for(User user : userDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("username", user.getUsername());
            String temp = "-";
            if(user.getActiveSince()!=null) temp = user.getActiveSince().toString();
            jSONObject.put("tanggalAktif", temp);
            temp = "-";
            if(user.getLastLogin()!=null) temp = user.getLastLogin().toString();
            jSONObject.put("tanggalLoginTerakhir", temp);
            jSONObject.put("status", String.valueOf(user.getActiveStatus()));
            jSONObject.put("level", String.valueOf(user.getLevel().getIdLevel()));
            arrUser.add(jSONObject);
        }
        object.put("user", arrUser);
        JSONArray arrPgw = new JSONArray();
        for(Pegawai pegawai : pegawaiDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("idPegawai", pegawai.getIdPegawai().toString());
            jSONObject.put("namaPegawai", pegawai.getNama());
            jSONObject.put("teleponPegawai", pegawai.getTelepon());
            jSONObject.put("emailPegawai", pegawai.getEmail());
            jSONObject.put("statusPegawai", pegawai.getStatus().toString());
            arrPgw.add(jSONObject);
        }
        object.put("pegawai", arrPgw);
        JSONArray arrMhs = new JSONArray();
        for(Mahasiswa mahasiswa : mahasiswaDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("nim", mahasiswa.getNim());
            jSONObject.put("namaMahasiswa", mahasiswa.getNama());
            arrMhs.add(jSONObject);
        }
        object.put("mahasiswa", arrMhs);
        JSONArray arrLevel = new JSONArray();
        for(Level level : levelDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("idLevel", level.getIdLevel().toString());
            jSONObject.put("namaLevel", level.getNama());
            arrLevel.add(jSONObject);
        }
        object.put("level", arrLevel);
        return object.toJSONString();
    }
    @RequestMapping(value="/alumniJSON", method=RequestMethod.GET)
    public @ResponseBody String getAlumni(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Rekapposisialumni alumni : rekapPosisiAlumniDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("angkatan", alumni.getId().getAngkatan());
            jSONObject.put("nim", alumni.getId().getNim());
            jSONObject.put("nama", alumni.getId().getNama());
            jSONObject.put("tanggalYudisium", alumni.getId().getTanggalYudisium().toString());
            jSONObject.put("ipk", alumni.getId().getIpk());
            jSONObject.put("periodeWisuda", alumni.getId().getWisuda().toString());
            jSONObject.put("predikat", alumni.getId().getPredikat());
            jSONObject.put("sk", alumni.getId().getSk());
            array.add(jSONObject);
        }
        object.put("alumni", array);
        return object.toJSONString();
    }
    @RequestMapping(value="/prodiJSON", method=RequestMethod.GET)
    public @ResponseBody String getProdi(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Prodi p : prodiDAO.viewAll()){
            String idProdi = "-";
            String kodeProdi = "-";
            String nama = "-";
            String inisial = "-";
            
            if(p.getIdProdi()!=null)idProdi = p.getIdProdi().toString();
            if(p.getKodeProdi()!=null)kodeProdi = p.getKodeProdi();
            if(p.getNama()!=null)nama = p.getNama();
            if(p.getInisial()!=null)inisial = p.getInisial();
            
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("idProdi", idProdi);
            jSONObject.put("kodeProdi", kodeProdi);
            jSONObject.put("nama", nama);
            jSONObject.put("inisial", inisial);
            array.add(jSONObject);
        }
        object.put("prodi", array);
        return object.toJSONString();
    }
    @RequestMapping(value="/peminatanJSON", method=RequestMethod.GET)
    public @ResponseBody String getPeminatan(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Peminatan p : peminatanDAO.viewAll()){
            String idPeminatan = "-";
            String namaPeminatan = "-";
            String inisial = "-";
            String idProdi = "-";
            String namaProdi = "-";
            
            if(p.getIdPeminatan()!=null)idPeminatan = p.getIdPeminatan().toString();
            if(p.getNama()!=null)namaPeminatan = p.getNama();
            if(p.getInisial()!=null)inisial = p.getInisial();
            if(p.getProdi().getIdProdi()!=null)idProdi = p.getProdi().getIdProdi().toString();
            if(p.getProdi().getNama()!=null)namaProdi = p.getProdi().getNama();
            
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("idPeminatan", idPeminatan);
            jSONObject.put("namaPeminatan", namaPeminatan);
            jSONObject.put("inisial", inisial);
            jSONObject.put("idProdi", idProdi);
            jSONObject.put("namaProdi", namaProdi);
            array.add(jSONObject);
        }
        object.put("peminatan", array);
        return object.toJSONString();
    }
    
    @RequestMapping(value="/jenisKontakJSON", method=RequestMethod.GET)
    public @ResponseBody String getJenisKontak(){
    JSONObject object = new JSONObject();
        JSONArray arrJenisKontak = new JSONArray();
        for(Jeniskontak jeniskontak : jenisKontakDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("idJenisKontak", jeniskontak.getIdJenisKontak().toString());
            jSONObject.put("namaJenisKontak", jeniskontak.getJenisKontak());
            arrJenisKontak.add(jSONObject);
        }
        object.put("jenisKontak", arrJenisKontak);
        return object.toJSONString();
    }
    @RequestMapping(value="/wisudaJSON", method=RequestMethod.GET)
    public @ResponseBody String Wisuda(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(int w : wisudaDAO.groupBy()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tahun", w);
            array.add(jSONObject);
        }
        object.put("wisuda", array);
        return object.toString();
    }
    @RequestMapping(value="/tanggalWisudaJSON", method=RequestMethod.GET)
    public @ResponseBody String tanggalWisuda(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Wisuda w : wisudaDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("periode", w.getKeterangan());
            jSONObject.put("tanggal", w.getTanggal().toString());
            array.add(jSONObject);
        }
        object.put("wisuda", array);
        return object.toString();
    }
    @RequestMapping(value="/angkatanJSON", method=RequestMethod.GET)
    public @ResponseBody String angkatan(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        Iterator iter = rekapPosisiAlumniDAO.groupByAngkatan().iterator();
        while(iter.hasNext()){
            String temp = (String) iter.next();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tahun", temp);
            array.add(jSONObject);
        }
        object.put("angkatan", array);
        return object.toString();
    }
    @RequestMapping(value="/kontakJSON", method=RequestMethod.GET)
    public @ResponseBody String getKontak(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Kontak k : kontakDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("nim", k.getMahasiswa().getNim());
            jSONObject.put("kontak", k.getKontak());
            jSONObject.put("jenisKontak", k.getJeniskontak().getJenisKontak());
            array.add(jSONObject);
        }
        object.put("kontak", array);
        return object.toJSONString();
    }
    @RequestMapping(value="/studiJSON", method=RequestMethod.GET)
    public @ResponseBody String getStudi(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Studi s : studiDAO.viewAll()){
            String unv = "-";
            String fak = "-";
            String prodi = "-";
            String tglMasuk = "-";
            String tglKeluar = "-";
            
            if(s.getUniversitas()!=null)unv = s.getUniversitas();
            if(s.getFakultas()!=null)fak = s.getFakultas();
            if(s.getProdi()!=null)prodi = s.getProdi();
            if(s.getTanggalMasuk()!=null)tglMasuk = s.getTanggalMasuk().toString();
            if(s.getTanggalKeluar()!=null)tglKeluar = s.getTanggalKeluar().toString();
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("universitas", s.getUniversitas());
            jSONObject.put("fakultas", fak);
            jSONObject.put("prodi", prodi);
            jSONObject.put("tanggalMasuk", tglMasuk);
            jSONObject.put("tanggalKeluar", tglKeluar);
            jSONObject.put("nim", s.getMahasiswa().getNim());
            array.add(jSONObject);
        }
        object.put("studi", array);
        return object.toJSONString();
    }
    @RequestMapping(value="/studiDisplayJSON", method=RequestMethod.GET)
    public @ResponseBody String getStudiDisplay(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Studi s : studiDAO.viewAll()){
            String unv = " ";
            String fak = " ";
            String prodi = " ";
            String tglMasuk = " ";
            String tglKeluar = " ";
            String core = " ";
            if(s.getUniversitas()!=null)unv = s.getUniversitas();
            if(s.getFakultas()!=null)fak = s.getFakultas();
            if(s.getProdi()!=null)prodi = s.getProdi();
            if(s.getTanggalMasuk()!=null)tglMasuk = s.getTanggalMasuk().toString();
            if(s.getTanggalKeluar()!=null)tglKeluar = s.getTanggalKeluar().toString();
            if(s.getCore()!=null && s.getCore()==1){core = "YA";}
            else if(s.getCore()!=null && s.getCore()==0){core = "TIDAK";}
            
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("universitas", s.getUniversitas());
            jSONObject.put("fakultas", fak);
            jSONObject.put("jurusan", prodi);
            jSONObject.put("tanggalMasuk", tglMasuk);
            jSONObject.put("tanggalKeluar", tglKeluar);
            jSONObject.put("core", core);
            jSONObject.put("nim", s.getMahasiswa().getNim());
            array.add(jSONObject);
        }
        object.put("studi", array);
        JSONArray arrRekap = new JSONArray();
        for(Rekapposisialumni alumni : rekapPosisiAlumniDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("angkatan", alumni.getId().getAngkatan());
            jSONObject.put("nim", alumni.getId().getNim());
            jSONObject.put("nama", alumni.getId().getNama());
            jSONObject.put("tanggalMulai", alumni.getId().getTanggalMulai().toString());
            jSONObject.put("tanggalYudisium", alumni.getId().getTanggalYudisium().toString());
            jSONObject.put("ipk", alumni.getId().getIpk());
            jSONObject.put("periodeWisuda", alumni.getId().getPeriodewisuda());
            jSONObject.put("posisiPertama", alumni.getId().getPosisiPertama());
            jSONObject.put("prodi", alumni.getId().getProdi());
            arrRekap.add(jSONObject);
        }
        object.put("alumni", arrRekap);
        return object.toJSONString();
    }
    @RequestMapping(value="/kerjaJSON", method=RequestMethod.GET)
    public @ResponseBody String getKerja(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Kerja k : kerjaDAO.viewAll()){
            String perusahaan = "-";
            String posisiKerja = "-";
            String jenisKerja = "-";
            String gaji = "-";
            String tglMasuk = "-";
            String tglKeluar = "-";
            
            if(k.getPerusahaan()!=null)perusahaan = k.getPerusahaan();
            if(k.getPosisikerja()!=null)posisiKerja = k.getPosisikerja().getNamaPosisi();
            if(k.getJeniskerja()!=null)jenisKerja = k.getJeniskerja().getJenisKerja();
            if(k.getGaji()!=null)gaji = k.getGaji().toString();
            if(k.getTanggalMasuk()!=null)tglMasuk = k.getTanggalMasuk().toString();
            if(k.getTanggalKeluar()!=null)tglKeluar = k.getTanggalKeluar().toString();
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("perusahaan", perusahaan);
            jSONObject.put("posisiKerja", posisiKerja);
            jSONObject.put("jenisKerja", jenisKerja);
            jSONObject.put("tanggalMasuk", tglMasuk);
            jSONObject.put("tanggalKeluar", tglKeluar);
            jSONObject.put("nim", k.getMahasiswa().getNim());
            array.add(jSONObject);
        }
        object.put("kerja", array);
        return object.toJSONString();
    }
    @RequestMapping(value="/kerjaDisplayJSON", method=RequestMethod.GET)
    public @ResponseBody String getKerjaDisplay(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Kerja k : kerjaDAO.viewAll()){
            String perusahaan = " ";
            String posisiKerja = " ";
            String jenisKerja = " ";
            String gaji = " ";
            String tglMasuk = " ";
            String tglKeluar = " ";
            String core = " ";
            if(k.getPerusahaan()!=null)perusahaan = k.getPerusahaan();
            if(k.getPosisikerja()!=null)posisiKerja = k.getPosisikerja().getNamaPosisi();
            if(k.getJeniskerja()!=null)jenisKerja = k.getJeniskerja().getJenisKerja();
            if(k.getGaji()!=null)gaji = k.getGaji().toString();
            if(k.getTanggalMasuk()!=null)tglMasuk = k.getTanggalMasuk().toString();
            if(k.getTanggalKeluar()!=null)tglKeluar = k.getTanggalKeluar().toString();
            if(k.getCore()!=null && k.getCore()==1){core = "YA";}
            else if(k.getCore()!=null && k.getCore()==0){core = "TIDAK";}
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("perusahaan", perusahaan);
            jSONObject.put("posisiKerja", posisiKerja);
            jSONObject.put("jenisKerja", jenisKerja);
            jSONObject.put("tanggalMasuk", tglMasuk);
            jSONObject.put("tanggalKeluar", tglKeluar);
            jSONObject.put("gaji", gaji);
            jSONObject.put("core", core);
            jSONObject.put("nim", k.getMahasiswa().getNim());
            array.add(jSONObject);
        }
        object.put("kerja", array);
        JSONArray arrRekap = new JSONArray();
        for(Rekapposisialumni alumni : rekapPosisiAlumniDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("angkatan", alumni.getId().getAngkatan());
            jSONObject.put("nim", alumni.getId().getNim());
            jSONObject.put("nama", alumni.getId().getNama());
            jSONObject.put("tanggalMulai", alumni.getId().getTanggalMulai().toString());
            jSONObject.put("tanggalYudisium", alumni.getId().getTanggalYudisium().toString());
            jSONObject.put("ipk", alumni.getId().getIpk());
            jSONObject.put("periodeWisuda", alumni.getId().getPeriodewisuda());
            jSONObject.put("posisiPertama", alumni.getId().getPosisiPertama());
            jSONObject.put("prodi", alumni.getId().getProdi());
            jSONObject.put("masaTunggu", alumni.getId().getMasaTunggu().toString());
            arrRekap.add(jSONObject);
        }
        object.put("alumni", arrRekap);
        return object.toJSONString();
    }
    @RequestMapping(value="/wirausahaJSON", method=RequestMethod.GET)
    public @ResponseBody String getWirausaha(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Wirausaha w : wirausahaDAO.viewAll()){
            String perusahaan = "-";
            String jenisUsaha = "-";
            String tglMulai = "-";
            String core = " ";
            
            if(w.getNamaPerusahaan()!=null)perusahaan = w.getNamaPerusahaan();
            if(w.getJenisUsaha()!=null)jenisUsaha = w.getJenisUsaha();
            if(w.getTanggalMulai()!=null)tglMulai = w.getTanggalMulai().toString();
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("perusahaan", perusahaan);
            jSONObject.put("jenisUsaha", jenisUsaha);
            jSONObject.put("tanggalMulai", tglMulai);
            jSONObject.put("nim", w.getMahasiswa().getNim());
            array.add(jSONObject);
        }
        object.put("wirausaha", array);
        return object.toJSONString();
    }
    @RequestMapping(value="/wirausahaDisplayJSON", method=RequestMethod.GET)
    public @ResponseBody String getWirausahaDisplay(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Wirausaha w : wirausahaDAO.viewAll()){
            String perusahaan = " ";
            String jenisUsaha = " ";
            String tglMulai = " ";
            String core = " ";
            if(w.getNamaPerusahaan()!=null)perusahaan = w.getNamaPerusahaan();
            if(w.getJenisUsaha()!=null)jenisUsaha = w.getJenisUsaha();
            if(w.getTanggalMulai()!=null)tglMulai = w.getTanggalMulai().toString();
            if(w.getCore()!=null && w.getCore()==1){core = "YA";}
            else if(w.getCore()!=null && w.getCore()==0){core = "TIDAK";}
            
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("perusahaan", perusahaan);
            jSONObject.put("jenisUsaha", jenisUsaha);
            jSONObject.put("tanggalMulai", tglMulai);
            jSONObject.put("core", core);
            jSONObject.put("nim", w.getMahasiswa().getNim());
            array.add(jSONObject);
        }
        object.put("wirausaha", array);
        JSONArray arrRekap = new JSONArray();
        for(Rekapposisialumni alumni : rekapPosisiAlumniDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("angkatan", alumni.getId().getAngkatan());
            jSONObject.put("nim", alumni.getId().getNim());
            jSONObject.put("nama", alumni.getId().getNama());
            jSONObject.put("tanggalMulai", alumni.getId().getTanggalMulai().toString());
            jSONObject.put("tanggalYudisium", alumni.getId().getTanggalYudisium().toString());
            jSONObject.put("ipk", alumni.getId().getIpk());
            jSONObject.put("periodeWisuda", alumni.getId().getPeriodewisuda());
            jSONObject.put("posisiPertama", alumni.getId().getPosisiPertama());
            jSONObject.put("prodi", alumni.getId().getProdi());
            arrRekap.add(jSONObject);
        }
        object.put("alumni", arrRekap);
        return object.toJSONString();
    }
    @RequestMapping(value="/kontakAlumniJSON", method=RequestMethod.GET)
    public @ResponseBody String getKontakAlumni(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Teleponview t : teleponViewDAO.viewAll()){
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("nim", t.getId().getNim());
            jSONObject.put("angkatan", t.getId().getAngkatan());
            jSONObject.put("nama", t.getId().getNama());
            jSONObject.put("kontak", t.getId().getKontak());
            jSONObject.put("prodi", t.getId().getProdi());
            jSONObject.put("peminatan", t.getId().getPeminatan());
            jSONObject.put("posisi", t.getId().getPosisi());
            jSONObject.put("ipk", t.getId().getIpk());
            jSONObject.put("statusMahasiswa", t.getId().getStatusMahasiswa());
            jSONObject.put("idStatusMahasiswa", t.getId().getIdStatusMahasiswa());
            array.add(jSONObject);
        }
        object.put("kontakAlumni", array);
        return object.toJSONString();
    }
}
