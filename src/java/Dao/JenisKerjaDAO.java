/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Jeniskerja;
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
public class JenisKerjaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Jeniskerja jenisKerja){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(jenisKerja);
    }
    
    public List<Jeniskerja> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Jeniskerja").list();
    }
    
    public Jeniskerja viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Jeniskerja) session.get(Jeniskerja.class, id);
    }
    
    public void delete(int id){
        Jeniskerja jenisKerja = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(jenisKerja);
    }
    
    public void saveAll(List<Jeniskerja> jenisKerjaList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Jeniskerja jenisKerja: jenisKerjaList){
            session.saveOrUpdate(jenisKerja);
            session.flush();
        }
    }
    
    public Jeniskerja viewByName(String name){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Jeniskerja.class);
        criteria.add(Restrictions.eq("jenisKerja", name));
        return (Jeniskerja) criteria.uniqueResult();
    }
}
