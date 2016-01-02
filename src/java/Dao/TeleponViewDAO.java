/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Teleponview;
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
public class TeleponViewDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public List<Teleponview> viewAll(String query){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery(query).list();
    }
    
    public List<Teleponview> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Teleponview").list();
    }
}
