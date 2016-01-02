/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Wirausahaview;
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
public class WirausahaViewDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void insert(Wirausahaview wirausahaview){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(wirausahaview);
    }
    
    public List<Wirausahaview> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Wirausahaview").list();
    }
    
    public Wirausahaview viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Wirausahaview) session.get(Wirausahaview.class, id);
    }
    
    public void delete(int id){
        Wirausahaview wirausahaview = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(wirausahaview);
    }
    
    public void saveAll(List<Wirausahaview> wirausahaviewList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Wirausahaview wirausahaview: wirausahaviewList){
            session.saveOrUpdate(wirausahaview);
            session.flush();
        }
    }
}
