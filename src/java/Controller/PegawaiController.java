/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.*;
import Model.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.catalina.tribes.transport.RxTaskPool;
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

/**
 *
 * @author FAHMI
 */
@Controller
@SessionAttributes({"username", "password", "level", "status", "nama"})
public class PegawaiController {
    @Autowired
    private PegawaiDAO pegawaiDAO;
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private KontakDAO kontakDAO;
    
    @Autowired
    private LevelDAO levelDAO;
    
    @Autowired
    private KontakViewDAO kontakViewDAO;
    
    @Autowired
    private ProdiDAO prodiDAO;
    
    @Autowired
    private PeminatanDAO peminatanDAO;
    
    @Autowired
    private TeleponViewDAO teleponViewDAO;
    
    
    @InitBinder 
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
    
    @RequestMapping(value="/pegawai-main", method=RequestMethod.GET)
    public String pegawaiMain(Model model){
        model.addAttribute("prodi", prodiDAO.viewAll());
        model.addAttribute("peminatan", peminatanDAO.viewAll());
        
        return "pegawai-main";
    }
    
    @RequestMapping(value="/pegawai-main", method=RequestMethod.POST)
    public String pegawaiMain(@RequestParam("pesan")String pesan, @RequestParam("statusMahasiswa")String statusMahasiswa[], /*@RequestParam("alumni")String alumni[],*/ Model model){
        String []number = new String[1];
        number[0] = "+6285742926207";
        
        String query = "";
        if(statusMahasiswa.length==1){
            query = "from Teleponview where id.idStatusMahasiswa="+statusMahasiswa[0];
        }else if(statusMahasiswa.length==2){
            query = "from Teleponview where id.idStatusMahasiswa="+statusMahasiswa[0]+" or id.idStatusMahasiswa="+statusMahasiswa[1];
        }
        
       
        //model.addAttribute("kontak", teleponViewDAO.viewAll(query));
        //model.addAttribute("pesan", teleponViewDAO.viewAll(query).size());
        model.addAttribute("prodi", prodiDAO.viewAll());
        model.addAttribute("peminatan", peminatanDAO.viewAll());
        return "pegawai-main";
    }
    
    @RequestMapping(value="/pegawai-profil", method=RequestMethod.GET)
    public String pegawaiProfil(Model model, HttpSession session){
        Pegawai pegawai = pegawaiDAO.view(session.getAttribute("username").toString());
//        if(pegawaiDAO.filterPegawai(session.getAttribute("username").toString()).size()>0){
//            pegawai = pegawaiDAO.filterPegawai(session.getAttribute("username").toString()).get(pegawaiDAO.filterPegawai(session.getAttribute("username").toString()).size()-1);
//        }
        model.addAttribute("pegawai", pegawai);
        return "pegawai-profil";
    }
    
    @RequestMapping(value="/pegawai-profil", method=RequestMethod.POST)
    public String pegawaiProfil(@ModelAttribute("pegawai")Pegawai pegawai, Model model){
        pegawaiDAO.insert(pegawai);
        model.addAttribute("msgType", "info");
        model.addAttribute("msg", "Data Berhasil Diperbarui");
        return "pegawai-profil";
    }
    @RequestMapping(value="/pegawai-display", method=RequestMethod.GET)
    public String pegawaiDisplay(){
        return "pegawai-display";
    }
    @RequestMapping(value="/pegawai-detail", method=RequestMethod.GET)
    public String pegawaiEdit(@RequestParam("idPegawai")String id, Model model){
        model.addAttribute("pegawai", pegawaiDAO.view(id));
        return "pegawai-detail";
    }
    @RequestMapping(value="/pegawai-simpan", method=RequestMethod.GET)
    public String pegawaiSimpan(){
        return "pegawai-simpan";
    }
    @RequestMapping(value="/pegawai-simpan", method=RequestMethod.POST)
    public String pegawaiSimpan(@RequestParam("idPegawai")String idPegawai, @RequestParam("nama")String nama, @RequestParam("telepon")String telepon, @RequestParam("email")String email, @RequestParam("level")int level){
        User user = new User();
        user.setUsername(idPegawai);
        user.setPassword(idPegawai);
        user.setActiveStatus(false);
        user.setLevel(levelDAO.view(level));
        userDAO.insert(user);
        
        Pegawai pegawai = new Pegawai();
        pegawai.setIdPegawai(idPegawai);
        pegawai.setNama(WordUtils.capitalizeFully(nama));
        pegawai.setTelepon(telepon);
        pegawai.setEmail(email);
        pegawai.setStatus(true);
        pegawaiDAO.insert(pegawai);
        return "user-pegawai";
    }
}
