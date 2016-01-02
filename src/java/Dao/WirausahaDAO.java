/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Wirausaha;
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
public class WirausahaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Wirausaha wirausaha){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(wirausaha);
    }
    
    public List<Wirausaha> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Wirausaha").list();
    }
    
    public Wirausaha view(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Wirausaha) session.get(Wirausaha.class, id);
    }
    
    public void update(Wirausaha wirausaha){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(wirausaha);
    }
    
    public void delete(String id){
        Wirausaha wirausaha = view(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(wirausaha);
    }
    public void saveAll(List<Wirausaha> wirausahaList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Wirausaha wirausaha: wirausahaList){
            session.saveOrUpdate(wirausaha);
            session.flush();
        }
    }
    
    public List<Wirausaha> filterWirausaha(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Wirausaha.class);
        criteria.add(Restrictions.eq("mahasiswa.nim", id));
        return criteria.list();
    }
    
    public List<Wirausaha> groupBy(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Wirausaha group by nim").list();
    }
}
