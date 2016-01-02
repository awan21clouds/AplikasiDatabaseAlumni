/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Prodi;
import java.util.List;
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
public class ProdiDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Prodi prodi){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(prodi);
    }
    
    public List<Prodi> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Prodi").list();
    }
    
    public Prodi viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Prodi) session.get(Prodi.class, id);
    }
    
    public Prodi viewByKodeProdi(String kodeProdi){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Prodi.class);
        criteria.add(Restrictions.eq("kodeProdi", kodeProdi));
        return (Prodi) criteria;
    }
    
    public void update(Prodi prodi){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(prodi);
    }
    
    public void delete(int id){
        Prodi prodi = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(prodi);
    }
    
    public void saveAll(List<Prodi> prodiList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Prodi prodi: prodiList){
            session.saveOrUpdate(prodi);
            session.flush();
        }
    }
    
    public void close(){
        getSessionFactory().close();
    }
}
