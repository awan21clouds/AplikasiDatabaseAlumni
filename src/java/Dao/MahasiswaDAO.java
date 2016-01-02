/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Mahasiswa;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class MahasiswaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Mahasiswa mahasiswa){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(mahasiswa);
    }
    
    public List<Mahasiswa> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Mahasiswa").list();
    }
    
    public Mahasiswa view(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Mahasiswa) session.get(Mahasiswa.class, id);
    }
    
    public void update(Mahasiswa mahasiswa){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(mahasiswa);
    }
    
    public void delete(String id){
        Mahasiswa mahasiswa = view(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(mahasiswa);
    }
    public void saveAll(List<Mahasiswa> mahasiswaList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Mahasiswa mahasiswa: mahasiswaList){
            session.saveOrUpdate(mahasiswa);
            session.flush();
        }
    }
    
    public void saveAllByMap(Map<String, Mahasiswa> mahasiswaMap){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Iterator iter = mahasiswaMap.keySet().iterator();
        while(iter.hasNext()){
            String nim = (String) iter.next();
            Mahasiswa mahasiswa = mahasiswaMap.get(nim);
            session.saveOrUpdate(mahasiswa);
            session.flush();
        }
    }
    
    public List<Mahasiswa> viewAlumni(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Mahasiswa.class);
        criteria.add(Restrictions.eq("statusmahasiswa.idStatusMahasiswa", 3));
        return criteria.list();
    }
    
    public List<Mahasiswa> viewMahasiswa(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Mahasiswa.class);
        criteria.add(Restrictions.not(Restrictions.eq("statusmahasiswa.idStatusMahasiswa", 3)));
        return criteria.list();
    }
}
