/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.*;
import Model.Peminatan;
import Model.Prodi;
import java.lang.String;
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
public class LaporanMasaTunggu {
    @Autowired
    private LaporanDAO laporanDAO;
    
    @Autowired
    private ProdiDAO prodiDAO;
    
    @Autowired
    private PeminatanDAO peminatanDAO;
    
    @RequestMapping(value="/laporanMasaTunggu", method=RequestMethod.GET)
    public @ResponseBody String getLaporanProdiTahunWisuda(){
        JSONObject object = new JSONObject();
        JSONArray arrProdi = new JSONArray();
        for(Prodi p : prodiDAO.viewAll()){
            JSONObject ob = new JSONObject();
            ob.put("prodi", p.getNama());
            arrProdi.add(ob);
        }
        object.put("arrProdi", arrProdi);
        JSONArray arrPeminatan = new JSONArray();
        for(Peminatan p : peminatanDAO.viewAll()){
            JSONObject ob = new JSONObject();
            ob.put("peminatan", p.getNama());
            arrPeminatan.add(ob);
        }
        object.put("arrPeminatan", arrPeminatan);
        
        object.put("getProdiAll", getProdiAll());
        object.put("getProdiTahun", getProdiTahun());
        object.put("getProdiPeriode", getProdiPeriode());
        object.put("getProdiAngkatan", getProdiAngkatan());
       
        object.put("getPeminatanAll", getPeminatanAll());
        object.put("getPeminatanTahun", getPeminatanTahun());
        object.put("getPeminatanPeriode", getPeminatanPeriode());
        object.put("getPeminatanAngkatan", getPeminatanAngkatan());
        return object.toJSONString();
    }
    
    public JSONArray getProdiAll(){
        DecimalFormat df = new DecimalFormat("#.##");
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        String[] group = {"id.prodi"}; 
        Iterator iter = laporanDAO.getMasaTunggu(group).iterator();
        while(iter.hasNext()){
            Object[] temp = (Object[]) iter.next();
            JSONObject o = new JSONObject();
            o.put("masaTunggu", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("prodi", temp[2]);
            array.add(o);
        }
        Iterator iterAll = laporanDAO.getMasaTungguAll(group).iterator();
        while(iterAll.hasNext()){
            Object[] temp = (Object[]) iterAll.next();
            JSONObject o = new JSONObject();
            o.put("masaTungguAll", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("prodi", temp[2]);
            array.add(o);
        }
        return array;
    }
    
    public JSONArray getProdiTahun(){
        DecimalFormat df = new DecimalFormat("#.##");
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        String[] group = {"id.prodi", "id.tahunwisuda"}; 
        Iterator iter = laporanDAO.getMasaTunggu(group).iterator();
        while(iter.hasNext()){
            Object[] temp = (Object[]) iter.next();
            JSONObject o = new JSONObject();
            o.put("masaTunggu", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("prodi", temp[2]);
            o.put("tahun", temp[3]);
            array.add(o);
        }
        Iterator iterAll = laporanDAO.getMasaTungguAll(group).iterator();
        while(iterAll.hasNext()){
            Object[] temp = (Object[]) iterAll.next();
            JSONObject o = new JSONObject();
            o.put("masaTungguAll", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("prodi", temp[2]);
            o.put("tahun", temp[3]);
            array.add(o);
        }
        return array;
    }
    
    public JSONArray getProdiPeriode(){
        DecimalFormat df = new DecimalFormat("#.##");
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        String[] group = {"id.prodi", "id.wisuda"}; 
        Iterator iter = laporanDAO.getMasaTunggu(group).iterator();
        while(iter.hasNext()){
            Object[] temp = (Object[]) iter.next();
            JSONObject o = new JSONObject();
            o.put("masaTunggu", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("prodi", temp[2]);
            o.put("periode", temp[3].toString());
            array.add(o);
        }
        Iterator iterAll = laporanDAO.getMasaTungguAll(group).iterator();
        while(iterAll.hasNext()){
            Object[] temp = (Object[]) iterAll.next();
            JSONObject o = new JSONObject();
            o.put("masaTungguAll", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("prodi", temp[2]);
            o.put("periode", temp[3].toString());
            array.add(o);
        }
        return array;
    }
    
    public JSONArray getProdiAngkatan(){
        DecimalFormat df = new DecimalFormat("#.##");
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        String[] group = {"id.prodi", "id.angkatan"}; 
        Iterator iter = laporanDAO.getMasaTunggu(group).iterator();
        while(iter.hasNext()){
            Object[] temp = (Object[]) iter.next();
            JSONObject o = new JSONObject();
            o.put("masaTunggu", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("prodi", temp[2]);
            o.put("angkatan", temp[3]);
            array.add(o);
        }
        Iterator iterAll = laporanDAO.getMasaTungguAll(group).iterator();
        while(iterAll.hasNext()){
            Object[] temp = (Object[]) iterAll.next();
            JSONObject o = new JSONObject();
            o.put("masaTungguAll", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("prodi", temp[2]);
            o.put("periode", temp[3].toString());
            o.put("angkatan", temp[3]);
            array.add(o);
        }
        return array;
    }  
   
    
    public JSONArray getPeminatanAll(){
        DecimalFormat df = new DecimalFormat("#.##");
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        String[] group = {"id.peminatan"}; 
        Iterator iter = laporanDAO.getMasaTunggu(group).iterator();
        while(iter.hasNext()){
            Object[] temp = (Object[]) iter.next();
            JSONObject o = new JSONObject();
            o.put("masaTunggu", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("peminatan", temp[2]);
            array.add(o);
        }
        Iterator iterAll = laporanDAO.getMasaTungguAll(group).iterator();
        while(iterAll.hasNext()){
            Object[] temp = (Object[]) iterAll.next();
            JSONObject o = new JSONObject();
            o.put("masaTungguAll", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("peminatan", temp[2]);
            array.add(o);
        }
        return array;
    }
    
    public JSONArray getPeminatanTahun(){
        DecimalFormat df = new DecimalFormat("#.##");
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        String[] group = {"id.peminatan", "id.tahunwisuda"}; 
        Iterator iter = laporanDAO.getMasaTunggu(group).iterator();
        while(iter.hasNext()){
            Object[] temp = (Object[]) iter.next();
            JSONObject o = new JSONObject();
            o.put("masaTunggu", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("peminatan", temp[2]);
            o.put("tahun", temp[3]);
            array.add(o);
        }
        Iterator iterAll = laporanDAO.getMasaTungguAll(group).iterator();
        while(iterAll.hasNext()){
            Object[] temp = (Object[]) iterAll.next();
            JSONObject o = new JSONObject();
            o.put("masaTungguAll", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("peminatan", temp[2]);
            o.put("tahun", temp[3]);
            array.add(o);
        }
        return array;
    }
    
    public JSONArray getPeminatanPeriode(){
        DecimalFormat df = new DecimalFormat("#.##");
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        String[] group = {"id.peminatan", "id.wisuda"}; 
        Iterator iter = laporanDAO.getMasaTunggu(group).iterator();
        while(iter.hasNext()){
            Object[] temp = (Object[]) iter.next();
            JSONObject o = new JSONObject();
            o.put("masaTunggu", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("peminatan", temp[2]);
            o.put("periode", temp[3].toString());
            array.add(o);
        }
        Iterator iterAll = laporanDAO.getMasaTungguAll(group).iterator();
        while(iterAll.hasNext()){
            Object[] temp = (Object[]) iterAll.next();
            JSONObject o = new JSONObject();
            o.put("masaTungguAll", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("peminatan", temp[2]);
            o.put("periode", temp[3].toString());
            array.add(o);
        }
        return array;
    }
    
    public JSONArray getPeminatanAngkatan(){
        DecimalFormat df = new DecimalFormat("#.##");
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        String[] group = {"id.peminatan", "id.angkatan"}; 
        Iterator iter = laporanDAO.getMasaTunggu(group).iterator();
        while(iter.hasNext()){
            Object[] temp = (Object[]) iter.next();
            JSONObject o = new JSONObject();
            o.put("masaTunggu", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("peminatan", temp[2]);
            o.put("angkatan", temp[3]);
            array.add(o);
        }
        Iterator iterAll = laporanDAO.getMasaTungguAll(group).iterator();
        while(iterAll.hasNext()){
            Object[] temp = (Object[]) iterAll.next();
            JSONObject o = new JSONObject();
            o.put("masaTungguAll", df.format(temp[0]));
            o.put("jumlahMahasiswa", temp[1]);
            o.put("peminatan", temp[2]);
            o.put("periode", temp[3].toString());
            o.put("angkatan", temp[3]);
            array.add(o);
        }
        return array;
    }  
}
