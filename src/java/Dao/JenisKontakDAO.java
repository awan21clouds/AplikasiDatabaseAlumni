/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Jeniskontak;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FAHMI
 */
@Repository
@Transactional
public class JenisKontakDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Jeniskontak jenisKontak){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(jenisKontak);
    }
    
    public List<Jeniskontak> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Jeniskontak").list();
    }
    
    public Jeniskontak viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Jeniskontak) session.get(Jeniskontak.class, id);
    }
    
    public void delete(int id){
        Jeniskontak jenisKontak = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(jenisKontak);
    }
    
    public void saveAll(List<Jeniskontak> jenisKontakList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Jeniskontak jenisKontak: jenisKontakList){
            session.saveOrUpdate(jenisKontak);
            session.flush();
        }
    }
    
    public void close(){
        getSessionFactory().close();
    }
}
