/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Kerja;
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
public class KerjaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Kerja kerja){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(kerja);
    }
    
    public List<Kerja> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Kerja").list();
    }
    
    public Kerja view(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Kerja) session.get(Kerja.class, id);
    }
    
    public void update(Kerja kerja){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(kerja);
    }
    
    public void delete(String id){
        Kerja kerja = view(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(kerja);
    }
    public void saveAll(List<Kerja> kerjaList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Kerja kerja: kerjaList){
            session.saveOrUpdate(kerja);
            session.flush();
        }
    }
    
    public List<Kerja>filterKerja(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Kerja.class);
        criteria.add(Restrictions.eq("mahasiswa.nim", id));
        return criteria.list();
    } 
    
    public List<Kerja> groupBy(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Kerja group by nim").list();
    }
}
