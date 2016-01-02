/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.*;
import Model.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author FAHMI
 */
@Controller
@SessionAttributes({"username", "password", "level", "status", "nama"})
public class AlumniController {
    @Autowired
    private MahasiswaDAO mahasiswaDAO;
    
    @Autowired
    private StudiDAO studiDAO;
    
    @Autowired
    private KerjaDAO kerjaDAO;
    
    @Autowired
    private WirausahaDAO wirausahaDAO;
    
    @Autowired
    private JenisKerjaDAO jenisKerjaDAO;
    
    @Autowired
    private PosisiKerjaDAO posisiKerjaDAO;
    
    @Autowired
    private JenisKontakDAO jenisKontakDAO;
    
    @Autowired
    private KontakDAO kontakDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private LevelDAO levelDAO;
    
    @Autowired
    private WisudaDAO wisudaDAO;
    
    @Autowired
    private PeminatanDAO peminatanDAO;
    
    @Autowired
    private StatusMahasiswaDAO statusMahasiswaDAO;
    
    @InitBinder 
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
    
    @RequestMapping(value="/alumni-main", method=RequestMethod.GET)
    public String alumniMain(){
        return "alumni-main";
    }
    
    @RequestMapping(value="/alumni-profil", method=RequestMethod.GET)
    public String alumniProfil(Model model, HttpSession session){
        Mahasiswa mahasiswa = mahasiswaDAO.view(session.getAttribute("username").toString());
        model.addAttribute("alumni", mahasiswa);
        return "alumni-profil";
    }
    
    @RequestMapping(value="/alumni-studi", method=RequestMethod.GET)
    public String alumniStudi(Model model, HttpSession session){
        Studi studi = new Studi();
        if(studiDAO.filterStudi(session.getAttribute("username").toString()).size()>0){
            studi = studiDAO.filterStudi(session.getAttribute("username").toString()).get(studiDAO.filterStudi(session.getAttribute("username").toString()).size()-1);
        }
        model.addAttribute("studi", studi);
        return "alumni-studi";
    }
    
    @RequestMapping(value="/alumni-studi", method= RequestMethod.POST)
    public String alumniStudi(@ModelAttribute("studi")Studi studi, HttpSession session, Model model){
        studi.setMahasiswa(mahasiswaDAO.view(session.getAttribute("username").toString()));
        studi.setStatus(true);
        if(new SimpleDateFormat("yyyy-MM-dd").format(studi.getTanggalMasuk()).trim().equals("0002-11-30"))studi.setTanggalMasuk(null);
        if(new SimpleDateFormat("yyyy-MM-dd").format(studi.getTanggalKeluar()).trim().equals("0002-11-30"))studi.setTanggalKeluar(null);
        if(studiDAO.filterStudi(session.getAttribute("username").toString()).size()>0){//jika data studi sebelumnya ada
            Studi studiTemp = studiDAO.filterStudi(session.getAttribute("username").toString()).get(studiDAO.filterStudi(session.getAttribute("username").toString()).size()-1);
            if(studiTemp.getUniversitas().trim().toLowerCase().equals(studi.getUniversitas().trim().toLowerCase())){
                studiTemp.setProdi(studi.getProdi());
                studiTemp.setFakultas(studi.getFakultas());
                studiTemp.setTanggalMasuk(studi.getTanggalMasuk());
                studiTemp.setTanggalKeluar(studi.getTanggalKeluar());
                studiTemp.setStatus(true);
                studiDAO.insert(studiTemp);
            }else{
                studiTemp.setStatus(false);//status lama diubah menjadi false
                studiDAO.insert(studiTemp);//simpan perubahan
                studiDAO.insert(studi);//simpan data studi baru
            }
        }else{//jika belum ada data studi sebelumnya
            studiDAO.insert(studi);
        }
        return alumniStudi(model, session);
    }
    
    @RequestMapping(value="/alumni-kerja", method=RequestMethod.GET)
    public String alumniKerja(Model model, HttpSession session){
        Kerja kerja = new Kerja();
        if(kerjaDAO.filterKerja(session.getAttribute("username").toString()).size()>0){//jika data kerja dari suatu usernama tidak kosong, maka objek kerja akan diisi dengan data yang ada di database
            kerja = kerjaDAO.filterKerja(session.getAttribute("username").toString()).get(kerjaDAO.filterKerja(session.getAttribute("username").toString()).size()-1);
        }
        model.addAttribute("posisiKerja", posisiKerjaDAO.viewAll());
        model.addAttribute("jenisKerja", jenisKerjaDAO.viewAll());
        model.addAttribute("kerja", kerja);
        return "alumni-kerja";
    }
    
    @RequestMapping(value="/alumni-kerja", method=RequestMethod.POST)
    public String kerjaView(@ModelAttribute("kerja")Kerja kerja, @RequestParam("posisiKerja")String posisiKrj,  Model model, HttpSession session){
        kerja.setMahasiswa(mahasiswaDAO.view(session.getAttribute("username").toString()));
        kerja.setStatusKerja(true);
        boolean status;
        try{
            Integer.parseInt(posisiKrj.trim());
            status = false;
        }catch(NumberFormatException e){
            status = true;
        }
        
        Posisikerja posisikerja;
        if(status==true){
            posisiKerjaDAO.insert(new Posisikerja(posisiKrj));//jika ada data posisi kerja baru maka akan disimpan terlebih dahulu
            posisikerja = posisiKerjaDAO.viewAll().get(posisiKerjaDAO.viewAll().size()-1);
        }else{
            posisikerja = posisiKerjaDAO.viewById(Integer.parseInt(posisiKrj.trim())); 
        }
        
        if(new SimpleDateFormat("yyyy-MM-dd").format(kerja.getTanggalMasuk()).trim().equals("0002-11-30"))kerja.setTanggalMasuk(null);
        if(new SimpleDateFormat("yyyy-MM-dd").format(kerja.getTanggalKeluar()).trim().equals("0002-11-30"))kerja.setTanggalKeluar(null);
        if(kerjaDAO.filterKerja(session.getAttribute("username").toString()).size()>0){//jika data kerja sebelumnya ada
            Kerja kerjaTemp = kerjaDAO.filterKerja(session.getAttribute("username").toString()).get(kerjaDAO.filterKerja(session.getAttribute("username").toString()).size()-1);
            if(kerjaTemp.getPerusahaan().toLowerCase().equals(kerja.getPerusahaan().trim().toLowerCase())){//jika nama perusahaan sama, maka data akan diupdate
                kerjaTemp.setPosisikerja(posisikerja);
                kerjaTemp.setJeniskerja(kerja.getJeniskerja());
                kerjaTemp.setTanggalMasuk(kerja.getTanggalMasuk());
                kerjaTemp.setTanggalKeluar(kerja.getTanggalKeluar());
                kerjaTemp.setGaji(kerja.getGaji());
                kerjaTemp.setStatusKerja(true);
                kerjaDAO.insert(kerjaTemp);
            }else{
                kerjaTemp.setStatusKerja(false);
                kerjaDAO.insert(kerjaTemp);
                kerja.setPosisikerja(posisikerja);
                kerjaDAO.insert(kerja);
            }
        }else{//jika belum ada data kerja sebelumnya
            kerja.setPosisikerja(posisikerja);
            kerjaDAO.insert(kerja);
        }
        return alumniKerja(model, session);
    }
    @RequestMapping(value="/alumni-wirausaha", method=RequestMethod.GET)
    public String alumniWirausaha(HttpSession session, Model model){
        Wirausaha wirausaha = new Wirausaha();
        if(wirausahaDAO.filterWirausaha(session.getAttribute("username").toString()).size()>0){
            wirausaha = wirausahaDAO.filterWirausaha(session.getAttribute("username").toString()).get(wirausahaDAO.filterWirausaha(session.getAttribute("username").toString()).size()-1);
        }
        model.addAttribute("wirausaha", wirausaha);
        return "alumni-wirausaha";
    }
    @RequestMapping(value="/alumni-wirausaha", method=RequestMethod.POST)
    public String alumniWirausaha(@ModelAttribute("wirausaha")Wirausaha wirausaha, HttpSession session, Model model){
        wirausaha.setMahasiswa(mahasiswaDAO.view(session.getAttribute("username").toString()));
        wirausaha.setStatus(true);
        if(new SimpleDateFormat("yyyy-MM-dd").format(wirausaha.getTanggalMulai()).trim().equals("0002-11-30"))wirausaha.setTanggalMulai(null);
        if(wirausahaDAO.filterWirausaha(session.getAttribute("username").toString()).size()>0){
            Wirausaha wirausahaTemp = wirausahaDAO.filterWirausaha(session.getAttribute("username").toString()).get(wirausahaDAO.filterWirausaha(session.getAttribute("username").toString()).size()-1);
            if(wirausahaTemp.getNamaPerusahaan().trim().toLowerCase().equals(wirausaha.getNamaPerusahaan().trim().toLowerCase())){
                wirausahaTemp.setJenisUsaha(wirausaha.getJenisUsaha());
                wirausahaTemp.setTanggalMulai(wirausaha.getTanggalMulai());
                wirausahaTemp.setStatus(true);
                wirausahaDAO.insert(wirausahaTemp);
            }else{
                wirausahaTemp.setStatus(false);
                wirausahaDAO.insert(wirausahaTemp);
                wirausahaDAO.insert(wirausaha);
            }
        }else{
            wirausahaDAO.insert(wirausaha);
        }
        return alumniWirausaha(session, model);
    }
    @RequestMapping(value="/alumni-kontak", method=RequestMethod.GET)
    public String alumniKontak(Model model, HttpSession session){
        ArrayList<Kontak> arrKontak = new ArrayList<Kontak>();
        for(int i=0;i<jenisKontakDAO.viewAll().size();i++){//looping sejumlah data yang ada pada table jenis kontak
            if(kontakDAO.filterKontak(session.getAttribute("username").toString(), (i+1)).size()>0){
                arrKontak.add(kontakDAO.filterKontak(session.getAttribute("username").toString(), (i+1)).get(kontakDAO.filterKontak(session.getAttribute("username").toString(), (i+1)).size()-1));
            }else{
                arrKontak.add(new Kontak(jenisKontakDAO.viewAll().get(i), null, null, false));
            }
        }
        model.addAttribute("kontak", arrKontak);
        return "alumni-kontak";
    }
    @RequestMapping(value="/alumni-kontak", method=RequestMethod.POST)
    public String alumniKontak(@RequestParam("kontak")String kontak[], @RequestParam("jenisKontak")int jenisKontak[], Model model, HttpSession session){
        Mahasiswa mahasiswa = mahasiswaDAO.view(session.getAttribute("username").toString());
        for(int i=0;i<kontak.length;i++){
            if(kontakDAO.filterKontak(session.getAttribute("username").toString(), jenisKontak[i]).size()>0){
                Kontak k = kontakDAO.filterKontak(session.getAttribute("username").toString(), jenisKontak[i]).get(kontakDAO.filterKontak(session.getAttribute("username").toString(), jenisKontak[i]).size()-1);
                k.setStatus(false);//status kontak lama di set false
                if(!k.getKontak().toLowerCase().equals(kontak[i].trim().toLowerCase()) && !kontak[i].trim().equals("")){//jika ada kontak baru maka perubahan akan disimpan
                    kontakDAO.insert(k);//perubahan status kontak lama disimpan
                    //kontakDAO.insert(new Kontak(jenisKontakDAO.viewById(jenisKontak[i]), mahasiswa, kontak[i], true));//penyimpanan kontak baru
                    if(jenisKontak[i]==1 && kontak[i].substring(0, 1).equals("0")){
                        kontakDAO.insert(new Kontak(jenisKontakDAO.viewById(jenisKontak[i]), mahasiswa, kontak[i].replaceFirst(kontak[i].substring(0, 1), "+62"), true));//penyimpanan kontak baru
                    }else{
                        kontakDAO.insert(new Kontak(jenisKontakDAO.viewById(jenisKontak[i]), mahasiswa, kontak[i], true));
                    }
                    
                }
            }else{
                if(!kontak[i].trim().equals("")){//jika field kontak tidak kosong maka akan disimpan
                    if(jenisKontak[i]==1 && kontak[i].substring(0, 1).equals("0")){
                        kontakDAO.insert(new Kontak(jenisKontakDAO.viewById(jenisKontak[i]), mahasiswa, kontak[i].replaceFirst(kontak[i].substring(0, 1), "+62"), true));//penyimpanan kontak baru
                    }else{
                        kontakDAO.insert(new Kontak(jenisKontakDAO.viewById(jenisKontak[i]), mahasiswa, kontak[i], true));
                    }
                }
            }
            
        }
        return alumniKontak(model, session);
    }
    @RequestMapping(value="/alumni-display", method=RequestMethod.GET)
    public String alumniDisplay(){
        return "alumni-display";
    }
    @RequestMapping(value="/alumni-simpan", method=RequestMethod.GET)
    public String alumniSimpan(Model model){
        model.addAttribute("wisuda", wisudaDAO.viewAll());
        model.addAttribute("status", statusMahasiswaDAO.viewAll());
        return "alumni-simpan";
    }
    @RequestMapping(value="/alumni-simpan", method=RequestMethod.POST)
    public String alumniSimpan(@ModelAttribute("mahasiswa")Mahasiswa mahasiswa, Model model){
        User user = new User();
        user.setUsername(mahasiswa.getNim());
        user.setPassword(mahasiswa.getNim());
        user.setLevel(levelDAO.view(3));
        user.setActiveStatus(false);
        userDAO.insert(user);
        mahasiswa.setNama(WordUtils.capitalizeFully(mahasiswa.getNama().trim()));
        mahasiswaDAO.insert(mahasiswa);
        model.addAttribute("peminatan", peminatanDAO.viewAll());
        model.addAttribute("wisuda", wisudaDAO.viewAll());
        return "user-display";
    }
    @RequestMapping(value="/alumni-upload", method= RequestMethod.GET)
    public String alumniUpload(){
        return "alumni-upload";
    }
//    @RequestMapping(value="/alumni-upload", method=RequestMethod.POST)
//    public String alumniUpload(@RequestParam("file")MultipartFile file, @RequestParam("jenisData")String jenisData, Model model){
//            ImportFile importFile = new ImportFile();
//        try {
//            if(jenisData.trim().equals("0")){//Data Mahasiswa
//                Level level = levelDAO.view(3);
//                userDAO.saveAll(importFile.readCSVUser(file.getInputStream(), level));//simpan ke tabel user
//                mahasiswaDAO.saveAll(importFile.readCSVMahasiswa(file.getInputStream()));//simpan ke tabel mahasiswa
//            }else if(jenisData.trim().equals("1")){//Data Yudisium
//                Level level = levelDAO.view(4);
//                wisudaDAO.saveAll(importFile.readXLSWisuda(file.getInputStream(), wisudaDAO.viewAll()));//simpan ke table wisuda
//                for(int i=0;i<3;i++){
//                    userDAO.saveAll(importFile.readXLS(file.getInputStream(), level, i, userDAO.viewAll()));//simpan ke tabel user
//                    mahasiswaDAO.saveAll(importFile.readXLSMahasiswa(file.getInputStream(), i, false, wisudaDAO.viewAll()));//simpan ke tabel mahasiswa
//                }
//            }else if(jenisData.trim().equals("2")){//Data Studi Lanjut
//                studiDAO.saveAll(importFile.readXlSStudi(file.getInputStream(), mahasiswaDAO.viewAll()));
//            }else if(jenisData.trim().equals("3")){//Data Kerja
//                posisiKerjaDAO.saveAll(importFile.readXLSPosisiKerja(file.getInputStream(), posisiKerjaDAO.viewAll()));
//                kerjaDAO.saveAll(importFile.readXLSKerja(file.getInputStream(),mahasiswaDAO.viewAll() ,posisiKerjaDAO.viewAll(), jenisKerjaDAO.viewAll(), kerjaDAO.viewAll()));
//            }else if(jenisData.trim().equals("4")){//Data Wirausaha
//                wirausahaDAO.saveAll(importFile.readXLSWirausaha(file.getInputStream(), mahasiswaDAO.viewAll()));
//            }
//        } catch (IOException ex) {
//            //model.addAttribute("file", ex);
//        }
//        return "alumni-upload";
//    }
    @RequestMapping(value="/alumni-upload", method=RequestMethod.POST)
    public String alumniUpload(@RequestParam("file")MultipartFile file, @RequestParam("jenisData")int jenisData, Model model){
        String exe = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        if(exe.toLowerCase().equals("xlsx")){
            try {
                UploadXLSX xlsx = new UploadXLSX();
                switch(jenisData){
                    case 0 : Map<String, User> userMap = xlsx.getUser(file.getInputStream(), levelDAO.view(3));
                             Map<String, Mahasiswa>mhsMap = xlsx.getMahasiswa(file.getInputStream(), statusMahasiswaDAO, peminatanDAO);
                             List<Kontak>teleponList = xlsx.getTelepon(file.getInputStream(), mhsMap, jenisKontakDAO);
                             List<Kontak>emailList = xlsx.getEmail(file.getInputStream(), mhsMap, jenisKontakDAO);
                             if(xlsx.isStatus()==true){
                                 userDAO.saveAllByMap(userMap);
                                 mahasiswaDAO.saveAllByMap(mhsMap);
                                 kontakDAO.saveAll(teleponList);
                                 kontakDAO.saveAll(emailList);
                                 model.addAttribute("msg", "Upload Data Berhasil");
                             }else{
                                 model.addAttribute("msg", "Upload Data Gagal : Coba Periksa File Excel Anda Kembali");
                             }
                             break;
                    case 1 : Map<String, Wisuda> wsdMap= xlsx.getWisuda(file.getInputStream(), wisudaDAO);
                             List<Mahasiswa> arrMhs = xlsx.getAlumni(file.getInputStream(), wsdMap, statusMahasiswaDAO, mahasiswaDAO);
                             if(xlsx.isStatus()==true){
                                 wisudaDAO.saveAll(wsdMap);
                                 mahasiswaDAO.saveAll(arrMhs);
                                 model.addAttribute("msg", "Upload Data Berhasil");
                             }else{
                                 model.addAttribute("msg", "Upload Data Gagal : Coba Periksa File Excel Anda Kembali");
                             }
                             break;
                    case 2 : List<Studi>arrStudi = xlsx.getStudi(file.getInputStream(), mahasiswaDAO);
                             if(xlsx.isStatus()==true){
                                 studiDAO.saveAll(arrStudi);
                                 model.addAttribute("msg", "Upload Data Berhasil");
                             }else{
                                 model.addAttribute("msg", "Upload Data Gagal : Coba Periksa File Excel Anda Kembali");
                             }
                             break;
                    case 3 : List<Posisikerja> arrPosKer = xlsx.getPosisiKerja(file.getInputStream(), posisiKerjaDAO);
                             List<Kerja> arrKerja = xlsx.getKerja(file.getInputStream(), mahasiswaDAO, posisiKerjaDAO, jenisKerjaDAO);
                             if(xlsx.isStatus()==true){
                                 posisiKerjaDAO.saveAll(arrPosKer);
                                 kerjaDAO.saveAll(arrKerja);
                                 model.addAttribute("msg", "Upload Data Berhasil");
                             }else{
                                 model.addAttribute("msg", "Upload Data Gagal : Coba Periksa File Excel Anda Kembali");
                             }
                             break;
                    case 4 : List<Wirausaha> arrWrs = xlsx.getWirausaha(file.getInputStream(), mahasiswaDAO);
                             if(xlsx.isStatus()==true){
                                 wirausahaDAO.saveAll(arrWrs);
                                 model.addAttribute("msg", "Upload Data Berhasil");
                             }else{
                                 model.addAttribute("msg", "Upload Data Gagal : Coba Periksa File Excel Anda Kembali");
                             }
                             break;
                    default : break;    
                }
            } catch (Exception ex) {
                model.addAttribute("msg", "Upload Data Gagal : Coba Periksa File Excel Anda Kembali");
                return "alumni-upload";
            }
        }
        return "alumni-upload";
    }
    
    @RequestMapping(value="/alumni-detail2", method=RequestMethod.GET)
    public String alumniDetail2(@RequestParam("nim")String nim, Model model, HttpSession session){
        //kontak
        ArrayList<Kontak> arrKontak = new ArrayList<Kontak>();
        for(int i=0;i<jenisKontakDAO.viewAll().size();i++){//looping sejumlah data yang ada pada table jenis kontak
            if(kontakDAO.filterKontak(nim, (i+1)).size()>0){
                arrKontak.add(kontakDAO.filterKontak(nim, (i+1)).get(kontakDAO.filterKontak(nim, (i+1)).size()-1));
            }else{
                arrKontak.add(new Kontak(jenisKontakDAO.viewAll().get(i), null, null, false));
            }
        }
        model.addAttribute("kontak", arrKontak);
        //kerja
        Kerja kerja = new Kerja();
        if(kerjaDAO.filterKerja(nim).size()>0){//jika data kerja dari suatu usernama tidak kosong, maka objek kerja akan diisi dengan data yang ada di database
            kerja = kerjaDAO.filterKerja(nim).get(kerjaDAO.filterKerja(nim).size()-1);
        }
        model.addAttribute("posisiKerja", posisiKerjaDAO.viewAll());
        model.addAttribute("jenisKerja", jenisKerjaDAO.viewAll());
        model.addAttribute("kerja", kerja);
        //studi
        Studi studi = new Studi();
        if(studiDAO.filterStudi(nim).size()>0){
            studi = studiDAO.filterStudi(nim).get(studiDAO.filterStudi(nim).size()-1);
        }
        model.addAttribute("studi", studi);
        
        //wirausaha
        Wirausaha wirausaha = new Wirausaha();
        if(wirausahaDAO.filterWirausaha(nim).size()>0){
            wirausaha = wirausahaDAO.filterWirausaha(nim).get(wirausahaDAO.filterWirausaha(nim).size()-1);
        }
        model.addAttribute("wirausaha", wirausaha);
        return "alumni-detail2";
    }
    
    @RequestMapping(value="/alumni-detail2-kontak", method=RequestMethod.POST)
    public String alumniKontak(@RequestParam("nim")String nim, @RequestParam("kontak")String kontak[], @RequestParam("jenisKontak")int jenisKontak[], Model model){
        Mahasiswa mahasiswa = mahasiswaDAO.view(nim);
        for(int i=0;i<kontak.length;i++){
            if(kontakDAO.filterKontak(nim, jenisKontak[i]).size()>0){
                Kontak k = kontakDAO.filterKontak(nim, jenisKontak[i]).get(kontakDAO.filterKontak(nim, jenisKontak[i]).size()-1);
                k.setStatus(false);//status kontak lama di set false
                if(!k.getKontak().toLowerCase().equals(kontak[i].trim().toLowerCase()) && !kontak[i].trim().equals("")){//jika ada kontak baru maka perubahan akan disimpan
                    kontakDAO.insert(k);//perubahan status kontak lama disimpan
                    if(jenisKontak[i]==1 && kontak[i].substring(0, 1).equals("0")){
                        kontakDAO.insert(new Kontak(jenisKontakDAO.viewById(jenisKontak[i]), mahasiswa, kontak[i].replaceFirst(kontak[i].substring(0, 1), "+62"), true));//penyimpanan kontak baru
                    }else{
                        kontakDAO.insert(new Kontak(jenisKontakDAO.viewById(jenisKontak[i]), mahasiswa, kontak[i], true));
                    }
                }
            }else{
                if(!kontak[i].trim().equals("")){//jika field kontak tidak kosong maka akan disimpan
                    if(jenisKontak[i]==1 && kontak[i].substring(0, 1).equals("0")){
                        kontakDAO.insert(new Kontak(jenisKontakDAO.viewById(jenisKontak[i]), mahasiswa, kontak[i].replaceFirst(kontak[i].substring(0, 1), "+62"), true));//penyimpanan kontak baru
                    }else{
                        kontakDAO.insert(new Kontak(jenisKontakDAO.viewById(jenisKontak[i]), mahasiswa, kontak[i], true));
                    }
                }
            }
            
        }
        return "redirect:alumni-detail2.htm?nim="+nim;
    }
   
    @RequestMapping(value="/alumni-detail2-studi", method= RequestMethod.POST)
    public String alumniStudi(@ModelAttribute("studi")Studi studi, @RequestParam("nim")String nim){
        studi.setMahasiswa(mahasiswaDAO.view(nim));
        studi.setStatus(true);
        if(new SimpleDateFormat("yyyy-MM-dd").format(studi.getTanggalMasuk()).trim().equals("0002-11-30"))studi.setTanggalMasuk(null);
        if(new SimpleDateFormat("yyyy-MM-dd").format(studi.getTanggalKeluar()).trim().equals("0002-11-30"))studi.setTanggalKeluar(null);
        if(studiDAO.filterStudi(nim).size()>0){//jika data studi sebelumnya ada
            Studi studiTemp = studiDAO.filterStudi(nim).get(studiDAO.filterStudi(nim).size()-1);
            if(studiTemp.getUniversitas().trim().toLowerCase().equals(studi.getUniversitas().trim().toLowerCase())){
                studiTemp.setProdi(studi.getProdi());
                studiTemp.setFakultas(studi.getFakultas());
                studiTemp.setTanggalMasuk(studi.getTanggalMasuk());
                studiTemp.setTanggalKeluar(studi.getTanggalKeluar());
                studiTemp.setStatus(true);
                studiDAO.insert(studiTemp);
            }else{
                studiTemp.setStatus(false);//status lama diubah menjadi false
                studiDAO.insert(studiTemp);//simpan perubahan
                studiDAO.insert(studi);//simpan data studi baru
            }
        }else{//jika belum ada data studi sebelumnya
            
            studiDAO.insert(studi);
        }
        return "redirect:alumni-detail2.htm?nim="+nim;
    }
    
    @RequestMapping(value="/alumni-detail2-kerja", method=RequestMethod.POST)
    public String kerjaView(@ModelAttribute("kerja")Kerja kerja, @RequestParam("posisiKerja")String posisiKrj, @RequestParam("nim")String nim){
        kerja.setMahasiswa(mahasiswaDAO.view(nim));
        kerja.setStatusKerja(true);
        if(new SimpleDateFormat("yyyy-MM-dd").format(kerja.getTanggalMasuk()).trim().equals("0002-11-30"))kerja.setTanggalMasuk(null);
        if(new SimpleDateFormat("yyyy-MM-dd").format(kerja.getTanggalKeluar()).trim().equals("0002-11-30"))kerja.setTanggalKeluar(null);
        boolean status;
        try{
            Integer.parseInt(posisiKrj.trim());
            status = false;
        }catch(NumberFormatException e){
            status = true;
        }
        
        Posisikerja posisikerja;
        if(status==true){
            posisiKerjaDAO.insert(new Posisikerja(posisiKrj.toUpperCase()));//jika ada data posisi kerja baru maka akan disimpan terlebih dahulu
            posisikerja = posisiKerjaDAO.viewAll().get(posisiKerjaDAO.viewAll().size()-1);
        }else{
            posisikerja = posisiKerjaDAO.viewById(Integer.parseInt(posisiKrj.trim())); 
        }
     
        if(kerjaDAO.filterKerja(nim).size()>0){//jika data kerja sebelumnya ada
            Kerja kerjaTemp = kerjaDAO.filterKerja(nim).get(kerjaDAO.filterKerja(nim).size()-1);
            if(kerjaTemp.getPerusahaan().toLowerCase().equals(kerja.getPerusahaan().trim().toLowerCase())){//jika nama perusahaan sama, maka data akan diupdate
                kerjaTemp.setPosisikerja(posisikerja);
                kerjaTemp.setJeniskerja(kerja.getJeniskerja());
                kerjaTemp.setTanggalMasuk(kerja.getTanggalMasuk());
                kerjaTemp.setTanggalKeluar(kerja.getTanggalKeluar());
                kerjaTemp.setGaji(kerja.getGaji());
                kerjaTemp.setStatusKerja(true);
                kerjaDAO.insert(kerjaTemp);
            }else{
                kerjaTemp.setStatusKerja(false);
                kerjaDAO.insert(kerjaTemp);
                kerja.setPosisikerja(posisikerja);
                kerjaDAO.insert(kerja);
            }
        }else{//jika belum ada data kerja sebelumnya
            kerja.setPosisikerja(posisikerja);
            kerjaDAO.insert(kerja);
        }
        return "redirect:alumni-detail2.htm?nim="+nim;
    }
    
    @RequestMapping(value="/alumni-detail2-wirausaha", method=RequestMethod.POST)
    public String alumniWirausaha(@ModelAttribute("wirausaha")Wirausaha wirausaha, @RequestParam("nim")String nim){
        wirausaha.setMahasiswa(mahasiswaDAO.view(nim));
        wirausaha.setStatus(true);
        if(new SimpleDateFormat("yyyy-MM-dd").format(wirausaha.getTanggalMulai()).trim().equals("0002-11-30"))wirausaha.setTanggalMulai(null);
        if(wirausahaDAO.filterWirausaha(nim).size()>0){
            Wirausaha wirausahaTemp = wirausahaDAO.filterWirausaha(nim).get(wirausahaDAO.filterWirausaha(nim).size()-1);
            if(wirausahaTemp.getNamaPerusahaan().trim().toLowerCase().equals(wirausaha.getNamaPerusahaan().trim().toLowerCase())){
                wirausahaTemp.setJenisUsaha(wirausaha.getJenisUsaha());
                wirausahaTemp.setTanggalMulai(wirausaha.getTanggalMulai());
                wirausahaTemp.setStatus(true);
                wirausahaDAO.insert(wirausahaTemp);
            }else{
                wirausahaTemp.setStatus(false);
                wirausahaDAO.insert(wirausahaTemp);
                wirausahaDAO.insert(wirausaha);
            }
        }else{
            wirausahaDAO.insert(wirausaha);
        }
        return "redirect:alumni-detail2.htm?nim="+nim;
    }
    
    @RequestMapping(value="studi-display", method= RequestMethod.GET)
    public String studiDisplay(){
        return "alumni-studi-display";
    }
    
    @RequestMapping(value="kerja-display", method= RequestMethod.GET)
    public String kerjaDisplay(){
        return "alumni-kerja-display";
    }
    
    @RequestMapping(value="wirausaha-display", method= RequestMethod.GET)
    public String wirausahaDisplay(){
        return "alumni-wirausaha-display";
    }
}
