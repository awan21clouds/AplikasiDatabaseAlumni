/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Posisikerja;
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
public class PosisiKerjaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Posisikerja posisiKerja){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(posisiKerja);
    }
    
    public List<Posisikerja> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Posisikerja").list();
    }
    
    public Posisikerja viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Posisikerja) session.get(Posisikerja.class, id);
    }
    
    public void delete(int id){
        Posisikerja posisiKerja = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(posisiKerja);
    }
    
    public void saveAll(List<Posisikerja> posisiKerjaList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Posisikerja posisiKerja: posisiKerjaList){
            session.saveOrUpdate(posisiKerja);
            session.flush();
        }
    }
    
    public Posisikerja viewByName(String name){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Posisikerja.class);
        criteria.add(Restrictions.eq("namaPosisi", name));
        return (Posisikerja) criteria.uniqueResult();
    }

}
