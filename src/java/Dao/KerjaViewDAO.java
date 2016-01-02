/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Kerjaview;
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
public class KerjaViewDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void insert(Kerjaview kerjaview){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(kerjaview);
    }
    
    public List<Kerjaview> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Kerjaview").list();
    }
    
    public Kerjaview viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Kerjaview) session.get(Kerjaview.class, id);
    }
    
    public void delete(int id){
        Kerjaview kerjaview = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(kerjaview);
    }
    
    public void saveAll(List<Kerjaview> kerjaviewList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Kerjaview kerjaview: kerjaviewList){
            session.saveOrUpdate(kerjaview);
            session.flush();
        }
    }
    
    public List<Kerjaview> filterKontak(String id, int jenisKontak){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Kerjaview.class);
        criteria.add(Restrictions.eq("mahasiswa.nim", id));
        criteria.add(Restrictions.eq("jeniskontak.idJenisKontak", jenisKontak));
        return criteria.list();
    }
}
