/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.*;
import Model.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.WordUtils;
import org.smslib.GatewayException;
import org.smslib.SMSLibException;
import org.smslib.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author FAHMI
 */
@Controller
@SessionAttributes({"username", "password", "level", "status", "nama"})
public class MainController {
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private LevelDAO levelDAO;
    
    @Autowired
    private ProdiDAO prodiDAO;
    
    @Autowired
    private JenisKontakDAO jenisKontakDAO;
    
    @Autowired
    private PeminatanDAO peminatanDAO;
    
    @Autowired
    private MahasiswaDAO mahasiswaDAO;
    
    @Autowired
    private PegawaiDAO pegawaiDAO;
    
    @Autowired
    private LaporanDAO laporanDAO;
    
    @InitBinder 
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index(){
        return "index";
    }
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(Model model, HttpSession session){
        String page = "login";
        Date today = Calendar.getInstance().getTime();
        model.addAttribute("today", today);
        return page;
    }
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@ModelAttribute("user")User user, Model model){
        String page = "redirect:login-error.htm";
        User userTemp = userDAO.login(user.getUsername(), user.getPassword());
        boolean status = false;
        if(userTemp!=null && (userTemp.getLevel().getIdLevel()==1 || userTemp.getLevel().getIdLevel()==2)){
            status = true;
        }else if(userTemp!=null && userTemp.getLevel().getIdLevel()==3){
            Mahasiswa m = mahasiswaDAO.view(userTemp.getUsername());
            if(m!=null && m.getStatusmahasiswa().getIdStatusMahasiswa()==3){
                status = true;
                if(m!=null && m.getKontak()==false){
                    m.setKontak(true);
                    mahasiswaDAO.insert(m);
                }
            }else{
                status = false;
            }
        }
        
        
        if(status==true){
            model.addAttribute("username", userTemp.getUsername());
            model.addAttribute("password", userTemp.getPassword());
            model.addAttribute("status", userTemp.getActiveStatus());
            model.addAttribute("level", userTemp.getLevel().getIdLevel());
            if(mahasiswaDAO.view(userTemp.getUsername())!=null){
                model.addAttribute("nama", mahasiswaDAO.view(userTemp.getUsername()).getNama());
            }else{
                model.addAttribute("nama", pegawaiDAO.view(userTemp.getUsername()).getNama());
            }
            
            if(userTemp.getActiveSince()==null)userTemp.setActiveSince(user.getLastLogin());
            userTemp.setLastLogin(user.getLastLogin());
            userTemp.setActiveStatus(true);
            userDAO.insert(userTemp);
            page = "index";
        }
        
//        if(userTemp!=null && userTemp.getActiveStatus()!=null){
//            if(userTemp.getPassword().toLowerCase().equals(user.getPassword().trim().toLowerCase())){
                
                
//                Mahasiswa m = mahasiswaDAO.view(userTemp.getUsername());
                
//                
//            }
//        }
        return page;
    }
    @RequestMapping(value="/login-error", method=RequestMethod.GET)
    public String loginError(Model model){
        Date today = Calendar.getInstance().getTime();
        model.addAttribute("today", today);
        return "login-error";
    }
    @RequestMapping(value="/login-error", method=RequestMethod.POST)
    public String loginError(@ModelAttribute("user")User user, Model model){
        String page = "redirect:login-error.htm";
        User userTemp = userDAO.view(user.getUsername());
        if(userTemp!=null && userTemp.getActiveStatus()!=null){
            if(userTemp.getPassword().toLowerCase().equals(user.getPassword().trim().toLowerCase())){
                model.addAttribute("username", userTemp.getUsername());
                model.addAttribute("password", userTemp.getPassword());
                model.addAttribute("status", userTemp.getActiveStatus());
                model.addAttribute("level", userTemp.getLevel().getIdLevel());
                if(mahasiswaDAO.view(userTemp.getUsername())!=null){
                    model.addAttribute("nama", mahasiswaDAO.view(userTemp.getUsername()).getNama());
                }else{
                    model.addAttribute("nama", pegawaiDAO.view(userTemp.getUsername()).getNama());
                }
                if(userTemp.getActiveSince()==null)userTemp.setActiveSince(user.getLastLogin());
                userTemp.setLastLogin(user.getLastLogin());
                userTemp.setActiveStatus(true);
                userDAO.insert(userTemp);
                page = "index";
            }
        }
        return page;
    }
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "index";
    }
    @RequestMapping(value="/laporan-posisi-total", method=RequestMethod.GET)
    public String laporanPosisi(){
        return "laporan-posisi-total";
    }
    @RequestMapping(value="/laporan-posisi-ipk-le", method=RequestMethod.GET)
    public String laporanPosisiIpkLe(){
        return "laporan-posisi-ipk-le";
    }
    @RequestMapping(value="/laporan-posisi-ipk-gr", method=RequestMethod.GET)
    public String laporanPosisiIpkGr(){
        return "laporan-posisi-ipk-gr";
    }
    @RequestMapping(value="/laporan-employment-rate", method=RequestMethod.GET)
    public String laporanEmploymentRate(){
        return "laporan-employment-rate";
    }
    @RequestMapping(value="/laporan-masa-tunggu", method=RequestMethod.GET)
    public String laporanMasaTunggu(){
        return "laporan-masa-tunggu";
    }
    @RequestMapping(value="/laporan-kontak", method=RequestMethod.GET)
    public String laporanKontak(){
        return "laporan-kontak";
    }
    @RequestMapping(value="/laporan-core", method=RequestMethod.GET)
    public String laporanCore(){
        return "laporan-core";
    }
    @RequestMapping(value="/laporan-detail", method=RequestMethod.POST)
    public String laporanDetail(Model model, @RequestParam("query")String query){
//        String arrNim[] = alumni.split(",");
//        ArrayList<Mahasiswa> arrAlumni = new ArrayList<Mahasiswa>();
//        for(String nim : arrNim){
//            arrAlumni.add(mahasiswaDAO.view(nim));
//        }
//        model.addAttribute("alumni", arrAlumni);
        List<Rekapposisialumni> list = laporanDAO.getLaporanDetail(query); 
        model.addAttribute("detail", list);
        return "laporan-detail";
    }
    @RequestMapping(value="/ganti-password", method=RequestMethod.GET)
    public String gantiPassword(){
        return "ganti-password";
    }
    @RequestMapping(value="/ganti-password", method=RequestMethod.POST)
    public String gantiPassword(Model model, HttpSession session, @RequestParam("passwordLama")String passwordLama, @RequestParam("passwordBaru")String passwordBaru, @RequestParam("confPasswordBaru")String confPasswordBaru){
        User user = userDAO.view(session.getAttribute("username").toString());
        if(user!=null && user.getPassword().toUpperCase().equals(passwordLama.trim().toUpperCase()) && passwordBaru.trim().toUpperCase().equals(confPasswordBaru.trim().toUpperCase())){
            user.setPassword(passwordBaru.trim().toUpperCase());
            userDAO.insert(user);
            model.addAttribute("msg", "Password Berhasil Disimpan");
            model.addAttribute("msgType", "info");
        }else{
            model.addAttribute("msg", "Password Gagal Disimpan");
            model.addAttribute("msgType", "warning");
        }
        return "ganti-password";
    }
    @RequestMapping(value="/user-display", method=RequestMethod.GET)
    public String userDisplay(){
        return "user-display";
    }
    @RequestMapping(value="/user-display", method=RequestMethod.POST)
    public String userDisplay(@RequestParam("username")String username, @RequestParam("level")int level, Model model){
        User user = userDAO.view(username);
        user.setLevel(levelDAO.view(level));
        userDAO.insert(user);
        if(user.getLevel().getIdLevel()>=3){//jika yang dirubah adalah mahasiswa atau alumni
            Mahasiswa mahasiswa = mahasiswaDAO.view(username);
            if(user.getLevel().getIdLevel()==3){
//                mahasiswa.setStatusMahasiswa(true);//mahasiswa
            }else{
//                mahasiswa.setStatusMahasiswa(false);//alumni
            }
            mahasiswaDAO.insert(mahasiswa);
        }
        return "user-display";
    }
//    @RequestMapping(value="/user-level", method=RequestMethod.GET)
//    public String userLevel(@RequestParam("username")String username){
//        User user = userDAO.view(username);
//        if(user.getActiveStatus()==null){
//            user.setActiveStatus(false);
//        }else{
//            user.setActiveStatus(null);
//        }
//        userDAO.insert(user);
//        return "user-display";
//    }
    
    
    @RequestMapping(value="/prodi-display", method=RequestMethod.GET)
    public String prodiDisplay(){
        return "prodi-display";
    }
    @RequestMapping(value="/prodi-simpan", method=RequestMethod.GET)
    public String prodiSimpan(){
        return "prodi-simpan";
    }
    @RequestMapping(value="/prodi-simpan", method=RequestMethod.POST)
    public String prodiSimpan(@ModelAttribute("prodi")Prodi prodi){
        prodi.setStatus(true);
        prodi.setInisial(prodi.getInisial().toUpperCase());
        prodi.setNama(WordUtils.capitalizeFully(prodi.getNama()));
        prodiDAO.insert(prodi);
        return "prodi-display";
    }
    @RequestMapping(value="/peminatan-display", method=RequestMethod.GET)
    public String peminatanDisplay(){
        return "peminatan-display";
    }
    @RequestMapping(value="/peminatan-simpan", method=RequestMethod.GET)
    public String peminatanSimpan(){
        return "peminatan-simpan";
    }
    @RequestMapping(value="/peminatan-simpan", method=RequestMethod.POST)
    public String peminatanSimpan(@ModelAttribute("peminatan")Peminatan peminatan){
        peminatan.setNama(WordUtils.capitalizeFully(peminatan.getNama()));
        peminatan.setInisial(peminatan.getInisial().toUpperCase());
        peminatan.setStatus(true);
        peminatanDAO.insert(peminatan);
        return "peminatan-display";
    }
    @RequestMapping(value="/jeniskontak-display", method=RequestMethod.GET)
    public String jenisKontakDisplay(){
        return "jeniskontak-display";
    }
    @RequestMapping(value="/jeniskontak-simpan", method=RequestMethod.GET)
    public String jenisKontakSimpan(){
        return "jeniskontak-simpan";
    }
    @RequestMapping(value="/jeniskontak-simpan", method=RequestMethod.POST)
    public String jenisKontakSimpan(@ModelAttribute("jeniskontak")Jeniskontak jeniskontak){
        jeniskontak.setJenisKontak(WordUtils.capitalizeFully(jeniskontak.getJenisKontak()));
        jenisKontakDAO.insert(jeniskontak);
        return "jeniskontak-display";
    }
    
    @RequestMapping(value="/sms-broadcast", method=RequestMethod.GET)
    public String smsBroadcast(){
        return "sms-broadcast";
    }
    @RequestMapping(value="/sms-broadcast", method=RequestMethod.POST)
    public String smsBroadcast(@RequestParam("pesan")String pesan, @RequestParam("kontak")String kontak[]){
        SmsLibExample sms = new SmsLibExample();
        try {
            sms.send(kontak, pesan);
        } catch (GatewayException ex) {
            Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SMSLibException ex) {
            Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return "sms-broadcast";
    }
    @RequestMapping(value="/rekapitulasi-grafik", method=RequestMethod.GET)
    public String rekapitulasiGrafik(Model model){
        model.addAttribute("prodi", prodiDAO.viewAll());
        model.addAttribute("peminatan", peminatanDAO.viewAll());
        return "rekapitulasi-grafik";
    }
}
