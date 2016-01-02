/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Kontak;
import Model.Statusmahasiswa;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FAHMI
 */
@Repository
@Transactional
public class KontakDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void insert(Kontak kontak){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(kontak);
    }
    
    public List<Kontak> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Kontak").list();
    }
    
    public Kontak viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Kontak) session.get(Kontak.class, id);
    }
    
    public void delete(int id){
        Kontak kontak = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(kontak);
    }
    
    public void saveAll(List<Kontak> kontakList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Kontak kontak: kontakList){
            session.saveOrUpdate(kontak);
            session.flush();
        }
    }
    
    public List<Kontak> filterKontak(String id, int jenisKontak){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Kontak.class);
        criteria.add(Restrictions.eq("mahasiswa.nim", id));
        criteria.add(Restrictions.eq("jeniskontak.idJenisKontak", jenisKontak));
        return criteria.list();
    }
    
    public List<Kontak> filterKontakByNim(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Kontak.class);
        criteria.add(Restrictions.eq("mahasiswa.nim", id));
        return criteria.list();
    }
    
    public List<Kontak>getKontak(String prodi){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Kontak.class);
        Criterion criterion1 = Restrictions.like("mahasiswa.nim", prodi+"%");
        Criterion criterion2 = Restrictions.eq("jeniskontak.idJenisKontak", 1);
        Criterion criterion3 = Restrictions.and(criterion1, criterion2);
        Criterion criterion4 = Restrictions.eq("status", true);
        Criterion criterion5 = Restrictions.eq("mahasiswa.idStatusMahasiswa", 1);
        criteria.add(criterion5);
        return criteria.list();
    }
}
