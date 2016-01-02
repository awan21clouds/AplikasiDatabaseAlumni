/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import au.com.bytecode.opencsv.CSVReader;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author FAHMI
 */
public class ImportFile {
    private ArrayList<String>msg = new ArrayList<String>();
    public List<User> readXLS(InputStream inputStream, Level level, int sheetIndex, List<User>userList){
        ArrayList<User>arrUser = new ArrayList<User>();
        if(!userList.isEmpty()){
            arrUser = (ArrayList<User>) userList;
        }
        try {
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(sheetIndex);
            Cell cell[] = new Cell[6];
            for(int i=1;i<sheet.getRows();i++){
                cell[0] = sheet.getCell(2, i);//nim
                User user = new User();
                user.setUsername(cell[0].getContents());
                user.setPassword(cell[0].getContents());
                user.setLevel(level);
                if(arrUser.isEmpty()){
                    arrUser.add(user);
                }else{
                    for(int l=0;l<arrUser.size();l++){
                        if(arrUser.get(l).getUsername().trim().equals(user.getUsername().trim())){
                            user.setActiveSince(arrUser.get(l).getActiveSince());
                            user.setLastLogin(arrUser.get(l).getLastLogin());
                            user.setActiveStatus(arrUser.get(l).getActiveStatus());
                            arrUser.set(l, user);
                            break;
                        }
                    }
                }
                
            }
        } catch (Exception ex) {
            
        } 
        return arrUser;
    }
    public List<Mahasiswa> readXLSMahasiswa(InputStream inputStream, int sheetIndex,  boolean statusMhs, List<Wisuda> wisudaList){
        ArrayList<Mahasiswa>arrMhs = new ArrayList<Mahasiswa>();
        try {
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(sheetIndex);
            Cell cell[] = new Cell[6];
            for(int i=1;i<sheet.getRows();i++){
                cell[0] = sheet.getCell(2, i);//nim
                cell[1] = sheet.getCell(3, i);//nama
                cell[2] = sheet.getCell(4, i);//ipk
                cell[3] = sheet.getCell(6, i);//tanggal yudisium
                cell[4] = sheet.getCell(7, i);//periode
                String yudisium[] = cell[3].getContents().toUpperCase().split(" ");
                if(yudisium[1].equals("JANUARI")){
                    yudisium[1] = "01";
                }else if(yudisium[1].equals("FEBRUARI")){
                    yudisium[1] = "02";
                }else if(yudisium[1].equals("MARET")){
                    yudisium[1] = "03";
                }else if(yudisium[1].equals("APRIL")){
                    yudisium[1] = "04";
                }else if(yudisium[1].equals("MEI")){
                    yudisium[1] = "05";
                }else if(yudisium[1].equals("JUNI")){
                    yudisium[1] = "06";
                }else if(yudisium[1].equals("JULI")){
                    yudisium[1] = "07";
                }else if(yudisium[1].equals("AGUSTUS")){
                    yudisium[1] = "08";
                }else if(yudisium[1].equals("SEPTEMBER")){
                    yudisium[1] = "09";
                }else if(yudisium[1].equals("OKTOBER")){
                    yudisium[1] = "10";
                }else if(yudisium[1].equals("NOVEMBER")){
                    yudisium[1] = "11";
                }else if(yudisium[1].equals("DESEMBER")){
                    yudisium[1] = "12";
                }
                
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(yudisium[2]+"-"+yudisium[1]+"-"+yudisium[0]);
                String periode[] = cell[4].getContents().split("/");
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNim(cell[0].getContents());
                mhs.setNama(WordUtils.capitalizeFully(cell[1].getContents()));
                mhs.setIpk(Double.parseDouble(cell[2].getContents()));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for(int j=0;j<wisudaList.size();j++){
                    /*msg.add("|"+dateFormat.format(wisudaList.get(j).getTanggal())+"+"+periode[2]+"-"+periode[1]+"-"+periode[0]+"="
                            +String.valueOf(dateFormat.format(wisudaList.get(j).getTanggal()).equals(periode[2]+"-"+periode[1]+"-"+periode[0]))+"|");*/
                    if(dateFormat.format(wisudaList.get(j).getTanggal()).equals(periode[2]+"-"+periode[1]+"-"+periode[0])){
                        mhs.setWisuda(wisudaList.get(j));
                    }
                }
                mhs.setTanggalYudisium(date);
//                mhs.setStatusMahasiswa(statusMhs);
                arrMhs.add(mhs);
            }
            
            
        } catch (Exception ex) {
            
        } 
        return arrMhs;
    }
    public List<Kerja>readXLSKerja(InputStream inputStream, List<Mahasiswa>mahasiswa, List<Posisikerja> posisiKerja, List<Jeniskerja>jenisKerja, List<Kerja>kerjaList){
        ArrayList<Kerja>arrKerja = new ArrayList<Kerja>();
        if(!kerjaList.isEmpty()){
            arrKerja = (ArrayList<Kerja>) kerjaList;
        }
        try {
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                Kerja kerja = new Kerja();
                String nim = sheet.getCell(1, i).getContents().trim();
                for(Mahasiswa mhs : mahasiswa){
                    //msg.add(mhs.getNim());
                    if(mhs.getNim().contains(nim)){
                        kerja.setMahasiswa(mhs);
                        break;
                    }
                }
                //msg.add(kerja.getMahasiswa().getNim());
                kerja.setPerusahaan(sheet.getCell(2, i).getContents());//perusahaan
                kerja.setStatusKerja(true);
                String tempPk = sheet.getCell(3, i).getContents().trim();
                for(Posisikerja pk : posisiKerja){
                    if(pk.getNamaPosisi().trim().toUpperCase().contains(tempPk.toUpperCase())){
                        kerja.setPosisikerja(pk);
                        break;
                    }
                }
                
                if(!sheet.getCell(4, i).getContents().trim().equals("")){
                    kerja.setTanggalMasuk(new Date(sheet.getCell(4, i).getContents()));//tanggal masuk
                }
                if (!sheet.getCell(5, i).getContents().trim().equals("")) {
                    kerja.setTanggalKeluar(new Date(sheet.getCell(5, i).getContents()));//tanggal Keluar
                }
                
                for (int j = 0; j < jenisKerja.size(); j++) {
                    if (jenisKerja.get(j).getJenisKerja().trim().toUpperCase().equals(sheet.getCell(3, i).getContents().trim())) {
                        kerja.setJeniskerja(jenisKerja.get(j));
                    }
                }
                if(!sheet.getCell(7, i).getContents().trim().equals("")){
                    kerja.setGaji(Integer.parseInt(sheet.getCell(7, i).getContents().trim()));
                }
                if(kerja.getMahasiswa()!=null && kerja.getTanggalMasuk()!=null){
                    arrKerja.add(kerja);
//                    if(arrKerja.isEmpty()){ 
//                            arrKerja.add(kerja);
//                    }else{
//                        boolean syarat = false;
//                        int j=0;
//                        while(j<arrKerja.size()){
//                            if(arrKerja.get(j).getMahasiswa().getNim().trim().equals(kerja.getMahasiswa().getNim().trim())&&arrKerja.get(j).getStatusKerja()==true){
//                                syarat = false;
//                                arrKerja.get(j).setStatusKerja(syarat);
//                                arrKerja.set(j, arrKerja.get(j));
//                                break;
//                            }else{
//                                syarat = true;
//                            }
//                            j++;
//                        }
//                        
//                        arrKerja.add(kerja);
//                    }
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         return arrKerja;
    }
    public List<Studi>readXlSStudi(InputStream inputStream, List<Mahasiswa>mahasiswa){
        ArrayList<Studi>arrStudi = new ArrayList<Studi>();
        try {
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                Studi studi = new Studi();
                for (int j = 0; j < mahasiswa.size(); j++) {
                    if(mahasiswa.get(j).getNim().trim().equals(sheet.getCell(1, i).getContents().trim())){
                        studi.setMahasiswa(mahasiswa.get(j));
                        break;
                    }
                }
                studi.setUniversitas(sheet.getCell(2, i).getContents());
                studi.setFakultas(sheet.getCell(3, i).getContents());
                studi.setProdi(sheet.getCell(4, i).getContents());
                if(!sheet.getCell(5, i).getContents().equals("")){
                    studi.setTanggalMasuk(new Date(sheet.getCell(5, i).getContents()));
                }
                if(!sheet.getCell(6, i).getContents().equals("")){
                    studi.setTanggalMasuk(new Date(sheet.getCell(6, i).getContents()));
                }
//                if(!sheet.getCell(7, i).getContents().equals("")){
//                    if(sheet.getCell(7, i).getContents().equals("")){
//                    
//                    }
//                }
                if(studi.getMahasiswa()!=null){
                    arrStudi.add(studi);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return arrStudi;
    }
    public List<Wirausaha>readXLSWirausaha(InputStream inputStream, List<Mahasiswa>mahasiswa){
        ArrayList<Wirausaha>arrWirausaha = new ArrayList<Wirausaha>();
        try {
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                Wirausaha wirausaha = new Wirausaha();
                for (int j = 0; j < mahasiswa.size(); j++) {
                    if(mahasiswa.get(j).getNim().trim().equals(sheet.getCell(1, i).getContents().trim())){
                        wirausaha.setMahasiswa(mahasiswa.get(j));
                        break;
                    }
                }
                wirausaha.setNamaPerusahaan(sheet.getCell(2, i).getContents());
                wirausaha.setJenisUsaha(sheet.getCell(3, i).getContents());
                if (!sheet.getCell(3, i).getContents().trim().equals("")) {
                    wirausaha.setTanggalMulai(new Date(sheet.getCell(4, i).getContents()));
                }
                if (wirausaha.getMahasiswa()!=null) {
                    arrWirausaha.add(wirausaha);
                }
//                System.out.println(wirausaha.getNamaPerusahaan());
//                System.out.println(wirausaha.getJenisUsaha());
//                System.out.println(wirausaha.getTanggalMulai());
            }
        } catch (IOException ex) {
            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return arrWirausaha;
    }
    public List<Wisuda> readXLSWisuda(InputStream inputStream, List<Wisuda> wisudaList){
        ArrayList<Wisuda>arrWisuda = new ArrayList<Wisuda>();
        if(wisudaList!=null){
            for(int i=0;i<wisudaList.size();i++){
                arrWisuda.add(wisudaList.get(i));
            }
        }
        try {
            Workbook workbook = Workbook.getWorkbook(inputStream);
            for(int k=0;k<3;k++){
                System.out.println("-------------------"+k+"-----------------");
                Sheet sheet = workbook.getSheet(k);
                for(int i=1;i<sheet.getRows();i++){
                    String temp[] = sheet.getCell(7, i).getContents().split("/");//periode wisuda
                    String bulan = "";
                    String periode = "";
                    if(temp[1].equals("04")){
                        bulan = "APRIL";
                        periode = "1";
                    }else if(temp[1].equals("10")){
                        bulan = "OKTOBER";
                        periode = "2";
                    }

                    Wisuda wisuda = new Wisuda();
                    wisuda.setPeriode(temp[2]+" "+periode);
                    wisuda.setKeterangan(temp[2]+" "+bulan);
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(temp[2]+"-"+temp[1]+"-"+temp[0]);
                    wisuda.setTanggal(date);
                    boolean syarat = false;
                    if(arrWisuda.isEmpty()){
                        arrWisuda.add(wisuda);
                    }else{
                        int j=0;
                        while(j<arrWisuda.size()){
                            if(arrWisuda.get(j).getPeriode().equals(wisuda.getPeriode())){
                                syarat = false;
                                break;//periode wisuda yg sama tidak akan disimpan
                            }else{
                                syarat = true;//periode berbeda akan disimpan
                            }
                            j++;
                        }
                        if(syarat==true){
                            arrWisuda.add(wisuda);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            
        } 
        return arrWisuda;
    } 
    public List<Posisikerja>readXLSPosisiKerja(InputStream inputStream, List<Posisikerja> posisikerjaList){
        ArrayList<Posisikerja>arrPosisiKerja = new ArrayList<Posisikerja>();
        if(posisikerjaList!=null){
            arrPosisiKerja = (ArrayList<Posisikerja>) posisikerjaList;
        }
        try {
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                if(!sheet.getCell(3, i).getContents().trim().equals("")){
                    if(arrPosisiKerja.isEmpty()){
                        arrPosisiKerja.add(new Posisikerja(WordUtils.capitalizeFully(sheet.getCell(3, i).getContents().trim())));
                    }else{
                        int j=0;
                        boolean syarat = false;
                        while(j<arrPosisiKerja.size()){
                            if(arrPosisiKerja.get(j).getNamaPosisi().trim().toUpperCase().equals(sheet.getCell(3, i).getContents().trim().toUpperCase())){
                                syarat = false;
                                break;
                            }else{
                                syarat = true;
                            }
                            j++;
                        }
                        if(syarat==true){
                            arrPosisiKerja.add(new Posisikerja(WordUtils.capitalizeFully(sheet.getCell(3, i).getContents().trim())));
                        }
                    }
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return arrPosisiKerja;
    }  
    public List<User> readCSVUser(InputStream inputStream, Level level){
        ArrayList<User>arrUser = new ArrayList<User>();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            List content = reader.readAll();
            String row[] = null;
            for(Object object : content){
                row = (String[]) object;
                String cell[] = row[0].replaceAll("\"", " ").split(";");
                User user = new User();
                user.setUsername(cell[0]);
                user.setPassword(cell[0]);
                user.setLevel(level);
                user.setActiveStatus(false);
                arrUser.add(user);
            }
        } catch (IOException ex) {
            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return arrUser;
    }
    public List<Mahasiswa> readCSVMahasiswa(InputStream inputStream){
        ArrayList<Mahasiswa>arrMahasiswa = new ArrayList<Mahasiswa>();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            List content = reader.readAll();
            String row[] = null;
            for(Object object : content){
                row = (String[]) object;
                String cell[] = row[0].replaceAll("\"", " ").split(";");
                /*int i = 0;
                while(i<cell.length){
                    System.out.print(cell[i].trim()+" ");
                    i++;
                }*/
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(cell[0]);
                mahasiswa.setNama(WordUtils.capitalizeFully(cell[1]));
//                mahasiswa.setStatusMahasiswa(true);
                arrMahasiswa.add(mahasiswa);
            }
        } catch (IOException ex) {
            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return arrMahasiswa;
    }
    public ArrayList<String> getMsg() {
        return msg;
    }
//    public static void main(String args[]){
//        InputStream inputStream = null;
//        try {
//            ImportFile file = new ImportFile();
//            String path = "D:/My Documents/KULIAH/PA/FILE PENDUKUNG/Data Wirausaha.xls";
//            inputStream = new FileInputStream(path);
//            file.readXLSWirausaha(inputStream, null);
//        } catch (Exception ex) {
//            Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } finally {
//            try {
//                inputStream.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ImportFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            }
//        }
//    }
}
