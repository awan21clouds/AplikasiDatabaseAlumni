/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Level;
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
public class LevelDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Level level){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(level);
    }
    
    public List<Level> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Level").list();
    }
    
    public Level view(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Level) session.get(Level.class, id);
    }
    
    public void update(Level level){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(level);
    }
    
    public void delete(int id){
        Level level = view(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(level);
    }
    
    
}
