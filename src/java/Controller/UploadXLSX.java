/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.*;
import Model.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import org.apache.commons.lang.WordUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author FAHMI
 */
public class UploadXLSX {
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    public Map<String, User> getUser(InputStream inputStream, Level level){
        Map<String, User> userMap = new HashMap<String, User>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            Iterator rows = sheet.rowIterator();
            while(rows.hasNext()){
                row = (XSSFRow) rows.next();
                if(row.getRowNum()==0){
                    if(row.getCell(0)!=null && row.getCell(0).toString().toLowerCase().equals("nim")){
                        setStatus(true);
                    }else{
                        setStatus(false);
                        break;
                    }
                }else{
                    if(isStatus() == true && row.getCell(0)!=null && row.getCell(1)!=null){
                        String nim = BigDecimal.valueOf(Double.valueOf(row.getCell(0).toString())).toPlainString();
                        String nama = row.getCell(1).toString();
                        User user = new User();
                        user.setUsername(nim);
                        user.setPassword(nim);
                        user.setLevel(level);
                        user.setActiveStatus(false);
                        userMap.put(nim, user);
                    }
                }
            }
        } catch (Exception ex) {
            setStatus(false);
        }
        return userMap;
    }
    public Map<String, Mahasiswa> getMahasiswa(InputStream inputStream, StatusMahasiswaDAO statusMahasiswaDAO, PeminatanDAO peminatanDAO){
        Map<String, Mahasiswa>mhsMap = new HashMap<String, Mahasiswa>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            Iterator rows = sheet.rowIterator();
            while(rows.hasNext()){
                row = (XSSFRow) rows.next();
                if(row.getRowNum()==0){
                    if(row.getCell(0)!=null && row.getCell(0).toString().toLowerCase().equals("nim") && row.getCell(1)!=null && row.getCell(1).toString().toLowerCase().equals("nama") && row.getCell(7)!=null && row.getCell(7).toString().toLowerCase().equals("kelas") && row.getCell(13)!=null && row.getCell(13).toString().toLowerCase().equals("status")){
                        setStatus(true);
                    }else{
                        setStatus(false);
                        break;
                    }
                }else{
                    if(isStatus() == true && row.getCell(0)!=null && row.getCell(1)!=null && row.getCell(7)!=null && row.getCell(13)!=null){
                        String nim = BigDecimal.valueOf(Double.valueOf(row.getCell(0).toString())).toPlainString();
                        String nama = row.getCell(1).toString();
                        String peminatan = row.getCell(7).toString().substring(0, row.getCell(7).toString().indexOf("-"));
                        String statusMhs = row.getCell(13).toString();
                        String kodeProdi = nim.substring(0, 3);
                        String angkatan = nim.substring(3, 5);
                        Peminatan pm = null;
                        if(Integer.parseInt(angkatan)<10){
                            pm = peminatanUnder2010(Integer.parseInt(kodeProdi), peminatanDAO);
                        }else{
                            if(peminatanDAO.viewByInisial(peminatan)!=null){
                                pm = peminatanDAO.viewByInisial(peminatan);
                            }else{
                                pm = peminatanDAO.viewByInisialNull();
                            }
                        }

                        Mahasiswa mhs = new Mahasiswa();
                        mhs.setNim(nim);
                        mhs.setNama(WordUtils.capitalizeFully(nama));
                        mhs.setPeminatan(pm);
                        mhs.setStatusmahasiswa(statusMahasiswaDAO.viewByName(statusMhs));
                        mhs.setKontak(true);
                        mhsMap.put(nim, mhs);
                    }
                }
                
            }
        } catch (Exception ex) {
            setStatus(false);
        }
        return mhsMap;
    }
    public List<Mahasiswa> getAlumni(InputStream inputStream, Map<String, Wisuda> wsdMap, StatusMahasiswaDAO statusMahasiswaDAO, MahasiswaDAO mahasiswaDAO){
        Map<String, Mahasiswa> mapMhs = new HashMap<String, Mahasiswa>();
        List<Mahasiswa>arrAlm = new ArrayList<Mahasiswa>();
        try {
            if(mahasiswaDAO!=null && !mahasiswaDAO.viewAll().isEmpty()){
                for(Mahasiswa mhs : mahasiswaDAO.viewAll()){
                    mapMhs.put(mhs.getNim(), mhs);
                }
            }
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            
            Iterator rows = sheet.rowIterator();
            while(rows.hasNext()){
                row = (XSSFRow) rows.next();
                if(row.getRowNum()==0){
                    if((row.getCell(2)!=null && row.getCell(2).toString().toLowerCase().equals("nim")) && (row.getCell(3)!=null && row.getCell(3).toString().toLowerCase().equals("nama")) && (row.getCell(4)!=null && row.getCell(4).toString().toLowerCase().equals("ipk")) && (row.getCell(5)!=null && row.getCell(5).toString().toLowerCase().equals("predikat")) && (row.getCell(6)!=null && row.getCell(6).toString().toLowerCase().equals("tanggal yudisium")) && (row.getCell(7)!=null && row.getCell(7).toString().toLowerCase().equals("periode wisuda")) && (row.getCell(8)!=null && row.getCell(8).toString().toLowerCase().equals("no. sk kelulusan"))){
                        setStatus(true);
                    }else{
                        setStatus(false);
                        break;
                    }
                }else if(row.getRowNum()>0 && isStatus()==true && row.getCell(2)!=null && row.getCell(3)!=null && row.getCell(4)!=null && row.getCell(6)!=null && row.getCell(7)!=null){
                    String nim = BigDecimal.valueOf(Double.valueOf(row.getCell(2).toString())).toPlainString();
                    String nama = row.getCell(3).toString();
                    String ipk = row.getCell(4).toString();
                    Date tanggalYudisium = row.getCell(6).getDateCellValue();
                    Date periodeWisuda = row.getCell(7).getDateCellValue();
                    String periode = new SimpleDateFormat("yyyy").format(periodeWisuda);
                    String keterangan = new SimpleDateFormat("MMMM yyyy").format(periodeWisuda);
                    if(keterangan.toUpperCase().contains("APRIL")){
                        periode = periode+" 1";
                    }else{
                        periode = periode+" 2";
                    }
                    String predikat = " ";
                    if(row.getCell(5)!=null) predikat = row.getCell(5).toString();
                    String sk = " ";
                    if(row.getCell(8)!=null) sk = row.getCell(8).toString();
                    if(mapMhs.containsKey(nim)==true && wsdMap.containsKey(periode)==true){
                        Mahasiswa mhs = mapMhs.get(nim);
                        mhs.setTanggalYudisium(tanggalYudisium);
                        mhs.setIpk(Double.parseDouble(ipk.substring(ipk.indexOf(".")-1)));
                        mhs.setWisuda(wsdMap.get(periode));
                        mhs.setStatusmahasiswa(statusMahasiswaDAO.viewByName("LULUS"));
                        mhs.setPredikat(predikat);
                        mhs.setSk(sk);
                        arrAlm.add(mhs);
                    }
                }
            }
        } catch (Exception ex) {
            setStatus(false);
        }
        return arrAlm;
    }
    public Map<String, Wisuda> getWisuda(InputStream inputStream, WisudaDAO wisudaDAO){
        Map<String, Wisuda> mapWsd = new TreeMap<String, Wisuda>();
        try {
            if(wisudaDAO!=null && !wisudaDAO.viewAll().isEmpty()){
                for(Wisuda w : wisudaDAO.viewAll()){
                    mapWsd.put(w.getPeriode(), w);
                }
            }
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            Iterator rows = sheet.rowIterator();
            while(rows.hasNext()){
                row = (XSSFRow) rows.next();
                if(row.getRowNum()==0){
                    if(row.getCell(7)!=null && row.getCell(7).toString().toLowerCase().equals("periode wisuda")){
                        setStatus(true);
                    }else{
                        setStatus(false);
                        break;
                    }
                }else if(row.getRowNum()>0 && isStatus()==true && row.getCell(7)!=null){
                    Date tanggal = row.getCell(7).getDateCellValue();
                    String periode = new SimpleDateFormat("yyyy").format(tanggal);
                    String keterangan = new SimpleDateFormat("MMMM yyyy").format(tanggal);
                    if(keterangan.toUpperCase().contains("APRIL")){
                        periode = periode+" 1";
                    }else{
                        periode = periode+" 2";
                    }
                    
                    if(mapWsd.containsKey(periode)==false){
                        Wisuda wisuda = new Wisuda();
                        wisuda.setPeriode(periode);
                        wisuda.setTanggal(tanggal);
                        wisuda.setKeterangan(keterangan);
                        mapWsd.put(periode, wisuda);
                    }
                }
            }
        } catch (Exception ex) {
            setStatus(false);
        }
        return mapWsd;
    }
    public List<Kontak> getTelepon(InputStream inputStream, Map<String, Mahasiswa> mhsMap, JenisKontakDAO jenisKontakDAO){
        List<Kontak>arrKontak = new ArrayList<Kontak>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            Iterator rows = sheet.rowIterator();
            while(rows.hasNext()){
                row = (XSSFRow) rows.next();
                if(row.getRowNum()==0){
                    if(row.getCell(10)!=null && row.getCell(10).toString().trim().toLowerCase().equals("telepon")){
                        setStatus(true);
                    }else{
                        setStatus(false);
                        break;
                    }
                }else if(row.getRowNum()>0 && isStatus() == true && (row.getCell(10)!=null && !row.getCell(10).toString().trim().equals(""))){
                    String nim = BigDecimal.valueOf(Double.valueOf(row.getCell(0).toString())).toPlainString();
                    String telepon = BigDecimal.valueOf(Double.valueOf(row.getCell(10).toString())).toPlainString();
                    //System.out.println(nim+" "+telepon);
                    if(mhsMap.containsKey(nim)==true)arrKontak.add(new Kontak(jenisKontakDAO.viewById(1), mhsMap.get(nim), "+62"+telepon, true));
                }
            }
        } catch (Exception ex) {
            setStatus(false);
        }
        return arrKontak;
    }
    public List<Kontak> getEmail(InputStream inputStream, Map<String, Mahasiswa> mhsMap, JenisKontakDAO jenisKontakDAO){
        List<Kontak>arrKontak = new ArrayList<Kontak>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            Iterator rows = sheet.rowIterator();
            while(rows.hasNext()){
                row = (XSSFRow) rows.next();
                if(row.getRowNum()==0){
                    if(row.getCell(11)!=null && row.getCell(11).toString().trim().toLowerCase().equals("email")){
                        setStatus(true);
                    }else{
                        setStatus(false);
                        break;
                    }
                }else if(row.getRowNum()>0 && isStatus() == true && (row.getCell(11)!=null && !row.getCell(11).toString().trim().equals(""))){
                    String nim = BigDecimal.valueOf(Double.valueOf(row.getCell(0).toString())).toPlainString();
                    String email = row.getCell(11).toString();
                    //System.out.println(nim+" "+email);
                    if(mhsMap.containsKey(nim)==true)arrKontak.add(new Kontak(jenisKontakDAO.viewById(2), mhsMap.get(nim), email, true));
                }
            }
        } catch (Exception ex) {
            setStatus(false);
        }
        return arrKontak;
    }
    public List<Studi> getStudi(InputStream inputStream, MahasiswaDAO mahasiswaDAO){
        List<Studi>arrStudi = new ArrayList<Studi>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            Iterator rows = sheet.rowIterator();
            while(rows.hasNext()){
                row = (XSSFRow) rows.next();
                if(row.getRowNum()==0){
                    if((row.getCell(1)!=null && row.getCell(1).toString().trim().toLowerCase().equals("nim")) && 
                            (row.getCell(4)!=null && row.getCell(4).toString().trim().toLowerCase().equals("universitas")) && 
                            (row.getCell(5)!=null && row.getCell(5).toString().trim().toLowerCase().equals("fakultas")) && 
                            (row.getCell(6)!=null && row.getCell(6).toString().trim().toLowerCase().equals("jurusan")) && 
                            (row.getCell(7)!=null && row.getCell(7).toString().trim().toLowerCase().equals("mulai studi")) && 
                            (row.getCell(8)!=null && row.getCell(8).toString().trim().toLowerCase().equals("keluar studi")) && 
                            (row.getCell(9)!=null && row.getCell(9).toString().trim().toLowerCase().equals("core"))){
                        setStatus(true);
                    }else{
                        setStatus(false);
                        break;
                    }
                }else if(row.getRowNum()>0 && isStatus()==true && row.getCell(1)!=null && row.getCell(4)!=null){
                    String nim = BigDecimal.valueOf(Double.valueOf(row.getCell(1).toString())).toPlainString();
                    String universitas = row.getCell(4).toString();
                    String fakultas = "";
                    String prodi = "";
                    int core = 3;
                    Date tanggalMasuk = new Date();
                    Date tanggalKeluar = new Date();
                    
                    if(row.getCell(5)!=null)fakultas = row.getCell(5).toString();
                    if(row.getCell(6)!=null)prodi = row.getCell(6).toString();
                    if(row.getCell(7)!=null)tanggalMasuk = row.getCell(7).getDateCellValue();
                    if(row.getCell(8)!=null)tanggalKeluar = row.getCell(8).getDateCellValue();
                    if(row.getCell(9)!=null && row.getCell(9).toString().toLowerCase().equals("ya")){
                        core = 1;
                    }else if(row.getCell(9)!=null && row.getCell(9).toString().toLowerCase().equals("tidak")){
                        core = 0;
                    }
                    
                    //System.out.println(nim+" "+universitas+" "+fakultas+" "+prodi+" "+tanggalMasuk+" "+tanggalKeluar);
                    if(mahasiswaDAO.view(nim)!=null){
                        Studi studi = new Studi();
                        studi.setMahasiswa(mahasiswaDAO.view(nim));
                        studi.setUniversitas(WordUtils.capitalizeFully(universitas));
                        studi.setFakultas(fakultas);
                        studi.setProdi(prodi);
                        studi.setTanggalMasuk(tanggalMasuk);
                        studi.setTanggalKeluar(tanggalKeluar);
                        studi.setStatus(true);
                        studi.setCore(core);
                        arrStudi.add(studi);
                    }
                }
            }
        } catch (Exception ex) {
            setStatus(false);
        }
        return arrStudi;
    }
    public List<Wirausaha>getWirausaha(InputStream inputStream, MahasiswaDAO mahasiswaDAO) throws IOException{
        List<Wirausaha>arrWsd = new ArrayList<Wirausaha>();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        Iterator rows = sheet.rowIterator();
        while(rows.hasNext()){
            row = (XSSFRow) rows.next();
            if(row.getRowNum()==0){
                if((row.getCell(1)!=null && row.getCell(1).toString().trim().toLowerCase().equals("nim")) && 
                        (row.getCell(4)!=null && row.getCell(4).toString().trim().toLowerCase().equals("perusahaan")) && 
                        (row.getCell(5)!=null && row.getCell(5).toString().trim().toLowerCase().equals("jenis usaha")) && 
                        (row.getCell(6)!=null && row.getCell(6).toString().trim().toLowerCase().equals("mulai wirausaha")) && 
                        (row.getCell(7)!=null && row.getCell(7).toString().trim().toLowerCase().equals("core"))){
                        setStatus(true);
                }else{
                    setStatus(false);
                    break;
                }
            }else if(row.getRowNum()>0 && isStatus()==true && row.getCell(1)!=null && row.getCell(4)!=null && row.getCell(5)!=null && row.getCell(6)!=null){
                String nim = BigDecimal.valueOf(Double.valueOf(row.getCell(1).toString())).toPlainString();
                String perusahaan = row.getCell(4).toString();
                String jenisUsaha = row.getCell(5).toString();
                Date tanggalMulai = row.getCell(6).getDateCellValue();
                int core = 3;
                if(row.getCell(7)!=null && row.getCell(7).toString().toLowerCase().equals("ya")){
                    core = 1;
                }else if(row.getCell(7)!=null && row.getCell(7).toString().toLowerCase().equals("tidak")){
                    core = 0;
                }
                //System.out.println(nim+" "+perusahaan+" "+jenisUsaha+" "+tanggalMulai);
                if(mahasiswaDAO.view(nim)!=null){
                    Wirausaha wsd = new Wirausaha();
                    wsd.setMahasiswa(mahasiswaDAO.view(nim));
                    wsd.setNamaPerusahaan(WordUtils.capitalizeFully(perusahaan));
                    wsd.setJenisUsaha(WordUtils.capitalizeFully(jenisUsaha));
                    wsd.setTanggalMulai(tanggalMulai);
                    wsd.setStatus(true);
                    wsd.setCore(core);
                    arrWsd.add(wsd);
                }
            }
        }
        return arrWsd;
    }
    public List<Kerja>getKerja(InputStream inputStream, MahasiswaDAO mahasiswaDAO, PosisiKerjaDAO posisiKerjaDAO, JenisKerjaDAO jenisKerjaDAO){
        List<Kerja>arr = new ArrayList<Kerja>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            Iterator rows = sheet.rowIterator();
            while(rows.hasNext()){
                row = (XSSFRow) rows.next();
                if(row.getRowNum()==0){
                    if((row.getCell(1)!=null && row.getCell(1).toString().trim().toLowerCase().equals("nim")) && 
                            (row.getCell(4)!=null && row.getCell(4).toString().trim().toLowerCase().equals("perusahaan")) && 
                            (row.getCell(5)!=null && row.getCell(5).toString().trim().toLowerCase().equals("mulai kerja")) && 
                            (row.getCell(6)!=null && row.getCell(6).toString().trim().toLowerCase().equals("keluar kerja")) && 
                            (row.getCell(10)!=null && row.getCell(10).toString().trim().toLowerCase().equals("posisi")) &&
                            (row.getCell(11)!=null && row.getCell(11).toString().trim().toLowerCase().equals("jenis pegawai")) && 
                            (row.getCell(12)!=null && row.getCell(12).toString().trim().toLowerCase().equals("gaji")) && 
                            (row.getCell(13)!=null && row.getCell(13).toString().trim().toLowerCase().equals("core"))){
                        setStatus(true);
                    }else{
                        setStatus(false);
                        break;
                    }
                }else if(row.getRowNum()>0 && isStatus()==true && row.getCell(1)!=null && row.getCell(4)!=null && row.getCell(5)!=null && row.getCell(6)!=null){
                    String nim = BigDecimal.valueOf(Double.valueOf(row.getCell(1).toString())).toPlainString();
                    String perusahaan = row.getCell(4).toString();
                    String posisi = "";
                    String jenisKerja = "";
                    int gaji = 0;
                    int core = 3;
                    Date tanggalMulai = new Date();
                    Date tanggalKeluar = new Date();
                    if(row.getCell(5)!=null)tanggalMulai = row.getCell(5).getDateCellValue();
                    if(row.getCell(6)!=null)tanggalKeluar = row.getCell(6).getDateCellValue();
                    if(row.getCell(10)!=null)posisi = row.getCell(10).toString();
                    if(row.getCell(11)!=null)jenisKerja = row.getCell(11).toString();
                    if(row.getCell(12)!=null)gaji = (int) row.getCell(12).getNumericCellValue();
                    if(row.getCell(13)!=null && row.getCell(13).toString().toLowerCase().equals("ya")){
                        core = 1;
                    }else if(row.getCell(13)!=null && row.getCell(13).toString().toLowerCase().equals("tidak")){
                        core = 0;
                    }
                    //System.out.println(" "+perusahaan+" "+tanggalMulai+" "+posisi+" "+jenisKerja+" "+gaji);
                    if(mahasiswaDAO.view(nim)!=null){
                        Kerja kerja = new Kerja();
                        kerja.setMahasiswa(mahasiswaDAO.view(nim));
                        kerja.setJeniskerja(jenisKerjaDAO.viewByName(jenisKerja));
                        kerja.setPerusahaan(perusahaan);
                        kerja.setPosisikerja(posisiKerjaDAO.viewByName(posisi));
                        kerja.setStatusKerja(true);
                        kerja.setGaji(gaji);
                        kerja.setTanggalMasuk(tanggalMulai);
                        kerja.setTanggalKeluar(tanggalKeluar);
                        kerja.setCore(core);
                        arr.add(kerja);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(UploadXLSX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return arr;
    }
    public List<Posisikerja>getPosisiKerja(InputStream inputStream, PosisiKerjaDAO posisiKerjaDAO) throws IOException{
        Map<String, Posisikerja>map = new HashMap<String, Posisikerja>();
        if(posisiKerjaDAO!=null && !posisiKerjaDAO.viewAll().isEmpty()){
            for(Posisikerja p : posisiKerjaDAO.viewAll()){
                map.put(p.getNamaPosisi().trim().toUpperCase(), p);
            }
        }
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        Iterator rows = sheet.rowIterator();
        while(rows.hasNext()){
            row = (XSSFRow) rows.next();
            if(row.getRowNum()==0){
                if(row.getCell(10)!=null && row.getCell(10).toString().trim().toLowerCase().equals("posisi")){
                    setStatus(true);
                }else{
                    setStatus(false);
                    break;
                }
            }else if(row.getRowNum()>0 && isStatus()==true && (row.getCell(10)!=null && !row.getCell(10).toString().trim().equals(""))){
                String posisi = row.getCell(10).toString().trim().toUpperCase();
                if(map.containsKey(posisi.toUpperCase())==false){
                    map.put(posisi.trim().toUpperCase(), new Posisikerja(posisi.trim().toUpperCase()));
                }
            }
        }
        List<Posisikerja>arr = new ArrayList<Posisikerja>(map.values());
        return arr;
    }
    
    public Peminatan peminatanUnder2010(int kodeProdi, PeminatanDAO peminatanDAO){
        Peminatan peminatan = null;
        switch(kodeProdi){
            case 301 : peminatan = peminatanDAO.viewByInisial("DBD");
                       break;
            case 302 : peminatan = peminatanDAO.viewByInisial("NE");
                       break;
            case 303 : peminatan = peminatanDAO.viewByInisial("SIA");
                       break;
        }
        return peminatan;
    }
    
//    public static void main(String args[]) throws FileNotFoundException, IOException{
//        //String file = "D:/My Documents/KULIAH/PA/FILE PENDUKUNG/mahasiswa.xlsx";
//        String file = "D:/My Documents/KULIAH/PA/FILE PENDUKUNG/Data Yudisium.xlsx";
//        InputStream is = new FileInputStream(file);
//        UploadXLSX xlsx = new UploadXLSX();
//        xlsx.getWisuda(is, null);
//       
//    }
}
