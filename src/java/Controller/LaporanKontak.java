/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.*;
import Model.Peminatan;
import Model.Prodi;
import java.lang.String;
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
public class LaporanKontak {
    @Autowired
    private LaporanDAO laporanDAO;
    
    @Autowired
    private ProdiDAO prodiDAO;
    
    @Autowired
    private PeminatanDAO peminatanDAO;
    
    @RequestMapping(value="/laporanKontak", method=RequestMethod.GET)
    public @ResponseBody String getLaporanKontakProdiTahunWisuda(){
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
        JSONObject obj = new JSONObject();
        
        JSONArray arrKontak = new JSONArray();
        String[] group = {"id.prodi", "id.kontak"}; 
        Iterator iterKontak = laporanDAO.getLaporan(group).iterator();
        while(iterKontak.hasNext()){
            Object[] temp = (Object[]) iterKontak.next();
            JSONObject o = new JSONObject();
            o.put("jumlah", temp[0]);
            o.put("prodi", temp[1]);
            o.put("kontak", temp[2]);
            arrKontak.add(o);
        }
        return arrKontak;
    }
    
    public JSONArray getProdiTahun(){
        JSONObject obj = new JSONObject();
        
        JSONArray arrKontak = new JSONArray();
        String[] group = {"id.prodi", "id.kontak", "id.tahunwisuda"}; 
        Iterator iterKontak = laporanDAO.getLaporan(group).iterator();
        while(iterKontak.hasNext()){
            Object[] temp = (Object[]) iterKontak.next();
            JSONObject o = new JSONObject();
            o.put("jumlah", temp[0]);
            o.put("prodi", temp[1]);
            o.put("kontak", temp[2]);
            o.put("tahun", temp[3]);
            arrKontak.add(o);
        }
        return arrKontak;
    }
    
    public JSONArray getProdiPeriode(){
        JSONObject obj = new JSONObject();
        
        JSONArray arrKontak = new JSONArray();
        String[] group = {"id.prodi", "id.kontak", "id.wisuda"}; 
        Iterator iterKontak = laporanDAO.getLaporan(group).iterator();
        while(iterKontak.hasNext()){
            Object[] temp = (Object[]) iterKontak.next();
            JSONObject o = new JSONObject();
            o.put("jumlah", temp[0]);
            o.put("prodi", temp[1]);
            o.put("kontak", temp[2]);
            o.put("periode", temp[3].toString());
            arrKontak.add(o);
        }
        return arrKontak;
    }
    
    public JSONArray getProdiAngkatan(){
        JSONObject obj = new JSONObject();
        
        JSONArray arrKontak = new JSONArray();
        String[] group = {"id.prodi", "id.kontak", "id.angkatan"}; 
        Iterator iterKontak = laporanDAO.getLaporan(group).iterator();
        while(iterKontak.hasNext()){
            Object[] temp = (Object[]) iterKontak.next();
            JSONObject o = new JSONObject();
            o.put("jumlah", temp[0]);
            o.put("prodi", temp[1]);
            o.put("kontak", temp[2]);
            o.put("angkatan", temp[3]);
            arrKontak.add(o);
        }
        return arrKontak;
    }
    
    
    public JSONArray getPeminatanAll(){
        JSONObject obj = new JSONObject();
        
        JSONArray arrKontak = new JSONArray();
        String[] group = {"id.peminatan", "id.kontak"}; 
        Iterator iterKontak = laporanDAO.getLaporan(group).iterator();
        while(iterKontak.hasNext()){
            Object[] temp = (Object[]) iterKontak.next();
            JSONObject o = new JSONObject();
            o.put("jumlah", temp[0]);
            o.put("peminatan", temp[1]);
            o.put("kontak", temp[2]);
            arrKontak.add(o);
        }
        return arrKontak;
    }
    
    public JSONArray getPeminatanTahun(){
        JSONObject obj = new JSONObject();
        
        JSONArray arrKontak = new JSONArray();
        String[] group = {"id.peminatan", "id.kontak", "id.tahunwisuda"}; 
        Iterator iterKontak = laporanDAO.getLaporan(group).iterator();
        while(iterKontak.hasNext()){
            Object[] temp = (Object[]) iterKontak.next();
            JSONObject o = new JSONObject();
            o.put("jumlah", temp[0]);
            o.put("peminatan", temp[1]);
            o.put("kontak", temp[2]);
            o.put("tahun", temp[3]);
            arrKontak.add(o);
        }
        return arrKontak;
    }
    
    public JSONArray getPeminatanPeriode(){
        JSONObject obj = new JSONObject();
        
        JSONArray arrKontak = new JSONArray();
        String[] group = {"id.peminatan", "id.kontak", "id.wisuda"}; 
        Iterator iterKontak = laporanDAO.getLaporan(group).iterator();
        while(iterKontak.hasNext()){
            Object[] temp = (Object[]) iterKontak.next();
            JSONObject o = new JSONObject();
            o.put("jumlah", temp[0]);
            o.put("peminatan", temp[1]);
            o.put("kontak", temp[2]);
            o.put("periode", temp[3].toString());
            arrKontak.add(o);
        }
        return arrKontak;
    }
    
    public JSONArray getPeminatanAngkatan(){
        JSONObject obj = new JSONObject();
        
        JSONArray arrKontak = new JSONArray();
        String[] group = {"id.peminatan", "id.kontak", "id.angkatan"}; 
        Iterator iterKontak = laporanDAO.getLaporan(group).iterator();
        while(iterKontak.hasNext()){
            Object[] temp = (Object[]) iterKontak.next();
            JSONObject o = new JSONObject();
            o.put("jumlah", temp[0]);
            o.put("peminatan", temp[1]);
            o.put("kontak", temp[2]);
            o.put("angkatan", temp[3]);
            arrKontak.add(o);
        }
        return arrKontak;
    }
    
}
