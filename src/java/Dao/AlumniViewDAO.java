/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Alumniview;
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
public class AlumniViewDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void insert(Alumniview alumniview){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(alumniview);
    }
    
    public List<Alumniview> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Alumniview").list();
    }
    
    public Alumniview viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Alumniview) session.get(Alumniview.class, id);
    }
    
    public void delete(int id){
        Alumniview alumniview = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(alumniview);
    }
    
    public void saveAll(List<Alumniview> alumniviewList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Alumniview alumniview: alumniviewList){
            session.saveOrUpdate(alumniview);
            session.flush();
        }
    }
}
