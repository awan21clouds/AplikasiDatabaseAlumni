/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Peminatan;
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
public class PeminatanDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Peminatan peminatan){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(peminatan);
    }
    
    public List<Peminatan> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Peminatan").list();
    }
    
    public Peminatan viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Peminatan) session.get(Peminatan.class, id);
    }
    
    public void update(Peminatan peminatan){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(peminatan);
    }
    
    public void delete(int id){
        Peminatan peminatan = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(peminatan);
    }
    
    public void saveAll(List<Peminatan> peminatanList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Peminatan peminatan: peminatanList){
            session.saveOrUpdate(peminatan);
            session.flush();
        }
    }
    
    public Peminatan viewByInisial(String inisial){
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Peminatan.class);
        criteria.add(Restrictions.eq("inisial", inisial));
        return (Peminatan) criteria.uniqueResult();
    }
    
    public Peminatan viewByInisialNull(){
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Peminatan.class);
        criteria.add(Restrictions.isNull("inisial"));
        return (Peminatan) criteria.uniqueResult();
    }
    
}
