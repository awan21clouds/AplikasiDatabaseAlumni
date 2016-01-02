/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.*;
import Model.Mahasiswa;
import Model.Pegawai;
import Model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author FAHMI
 */
@Controller
public class UserController {
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
    
    
/*WEB-PAGE*/
    @RequestMapping(value="user-alumni", method=RequestMethod.GET)
    public String userAlumni(){
        return "user-alumni";
    }
    
    @RequestMapping(value="user-mahasiswa", method=RequestMethod.GET)
    public String userMahasiswa(){
        return "user-mahasiswa";
    }
    
    @RequestMapping(value="user-mahasiswa-alumni-view", method=RequestMethod.GET)
    public String userMahasiswaAlumni(@RequestParam("username")String nim, Model model){
        Mahasiswa mahasiswa = mahasiswaDAO.view(nim);
        model.addAttribute("alumni", mahasiswa);
        return "alumni-profil";
    }
    
    @RequestMapping(value="user-mahasiswa-alumni-kontak", method=RequestMethod.GET)
    public String userMahasiswaAlumni(@RequestParam("username")String username){
        Mahasiswa m = mahasiswaDAO.view(username);
        if(m.getKontak()==false){
            m.setKontak(true);
        }else{
            m.setKontak(false);
        }
        mahasiswaDAO.insert(m);
        
        User u = userDAO.view(username);
        if(u.getActiveStatus()==false){
            u.setActiveStatus(true);
        }else{
            u.setActiveStatus(false);
        }
        userDAO.insert(u);
        
        String page = "";
        if(m.getStatusmahasiswa().getIdStatusMahasiswa()==3){
            page = "redirect:user-alumni";// user alumni
        }else{
            page = "redirect:user-mahasiswa";// user non-alumni
        }
        return page;
    }
    
    @RequestMapping(value="user-pegawai", method=RequestMethod.GET)
    public String userPegawai(){
        return "user-pegawai";
    }
    
    @RequestMapping(value="user-pegawai", method=RequestMethod.POST)
    public String userPegawai(@RequestParam("username")String username, @RequestParam("level")int level, Model model){
        User user = userDAO.view(username);
        user.setLevel(levelDAO.view(level));
        userDAO.insert(user);
        return "user-pegawai";
    }
    
    @RequestMapping(value="user-pegawai-aktivasi", method=RequestMethod.GET)
    public String userPegawai(@RequestParam("username")String username){
        User user = userDAO.view(username);
        if(user.getActiveStatus()==null){
            user.setActiveStatus(false);
        }else{
            user.setActiveStatus(null);
            user.setActiveSince(null);
            user.setLastLogin(null);
        }
        userDAO.insert(user);
        return "redirect:user-pegawai";
    }
    
    

/*JSON*/   
    /*ALUMNI*/
    @RequestMapping(value="user-alumni-json", method=RequestMethod.GET)
    public @ResponseBody String getUserAlumniJSON(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Mahasiswa m : mahasiswaDAO.viewAlumni()){
            String tanggalAktif = "-";
            String loginTerakhir = "-";
            if(m.getUser().getActiveSince()!=null)tanggalAktif = m.getUser().getActiveSince().toString();
            if(m.getUser().getLastLogin()!=null)loginTerakhir = m.getUser().getLastLogin().toString();
            
            JSONObject obj = new JSONObject();
            obj.put("username", m.getUser().getUsername());
            obj.put("nama", m.getNama());
            obj.put("tanggalAktif", tanggalAktif);
            obj.put("loginTerakhir", loginTerakhir);
            obj.put("status", m.getUser().getActiveStatus());
            obj.put("kontak", m.getKontak());
            obj.put("level", "Alumni");
            array.add(obj);
        }
        object.put("userAlumni", array);
        return object.toJSONString();
    }
    /*MAHASISWA*/
    @RequestMapping(value="user-mahasiswa-json", method=RequestMethod.GET)
    public @ResponseBody String getUserMahasiswaJSON(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Mahasiswa m : mahasiswaDAO.viewMahasiswa()){
            String tanggalAktif = "-";
            String loginTerakhir = "-";
            if(m.getUser().getActiveSince()!=null)tanggalAktif = m.getUser().getActiveSince().toString();
            if(m.getUser().getLastLogin()!=null)loginTerakhir = m.getUser().getLastLogin().toString();
            
            JSONObject obj = new JSONObject();
            obj.put("username", m.getUser().getUsername());
            obj.put("nama", m.getNama());
            obj.put("tanggalAktif", tanggalAktif);
            obj.put("loginTerakhir", loginTerakhir);
            obj.put("status", m.getUser().getActiveStatus());
            obj.put("kontak", m.getKontak());
            obj.put("level", "Mahasiswa");
            array.add(obj);
        }
        object.put("userMahasiswa", array);
        return object.toJSONString();
    }
    /*PEGAWAI*/
    @RequestMapping(value="user-pegawai-json", method=RequestMethod.GET)
    public @ResponseBody String getUserPegawaiJSON(){
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for(Pegawai p : pegawaiDAO.viewAll()){
            String tanggalAktif = "-";
            String loginTerakhir = "-";
            String status = "-";
            if(p.getUser().getActiveSince()!=null)tanggalAktif = p.getUser().getActiveSince().toString();
            if(p.getUser().getLastLogin()!=null)loginTerakhir = p.getUser().getLastLogin().toString();
            if(p.getUser().getActiveStatus()!=null)status = p.getUser().getActiveStatus().toString();
            
            JSONObject obj = new JSONObject();
            obj.put("username", p.getUser().getUsername());
            obj.put("nama", p.getNama());
            obj.put("tanggalAktif", tanggalAktif);
            obj.put("loginTerakhir", loginTerakhir);
            obj.put("status",  status);
            obj.put("level", p.getUser().getLevel().getNama());
            array.add(obj);
        }
        object.put("userPegawai", array);
        return object.toJSONString();
    }
}
