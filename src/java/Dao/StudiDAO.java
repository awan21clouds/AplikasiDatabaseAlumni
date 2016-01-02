/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Studi;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
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
public class StudiDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Studi studi){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(studi);
    }
    
    public List<Studi> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Studi").list();
    }
    
    public Studi view(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Studi) session.get(Studi.class, id);
    }
    
    public void update(Studi studi){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(studi);
    }
    
    public void delete(String id){
        Studi studi = view(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(studi);
    }
    public void saveAll(List<Studi> studiList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Studi studi: studiList){
            session.saveOrUpdate(studi);
            session.flush();
        }
    }
    
    public List<Studi>filterStudi(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Studi.class);
        criteria.add(Restrictions.eq("mahasiswa.nim", id));
        return criteria.list();
    }
    
    public List<Studi> groupBy(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Studi group by nim").list();
    }
}