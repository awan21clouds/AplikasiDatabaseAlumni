/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Studiview;
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
public class StudiViewDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void insert(Studiview studiview){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(studiview);
    }
    
    public List<Studiview> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Studiview").list();
    }
    
    public Studiview viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Studiview) session.get(Studiview.class, id);
    }
    
    public void delete(int id){
        Studiview studiview = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(studiview);
    }
    
    public void saveAll(List<Studiview> studiviewList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Studiview studiview: studiviewList){
            session.saveOrUpdate(studiview);
            session.flush();
        }
    }
}
