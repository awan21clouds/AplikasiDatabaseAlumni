/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Statusmahasiswa;
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
public class StatusMahasiswaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Statusmahasiswa statusmahasiswa){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(statusmahasiswa);
    }
    
    public List<Statusmahasiswa> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Statusmahasiswa").list();
    }
    
    public Statusmahasiswa view(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Statusmahasiswa) session.get(Statusmahasiswa.class, id);
    }
    
    public void update(Statusmahasiswa statusmahasiswa){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(statusmahasiswa);
    }
    
    public void delete(int id){
        Statusmahasiswa statusmahasiswa = view(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(statusmahasiswa);
    }
    public void saveAll(List<Statusmahasiswa> statusmahasiswasList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Statusmahasiswa mahasiswa: statusmahasiswasList){
            session.saveOrUpdate(mahasiswa);
            session.flush();
        }
    }
    
    public Statusmahasiswa viewByName(String name){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Statusmahasiswa.class);
        criteria.add(Restrictions.eq("statusMahasiswa", name));
        return (Statusmahasiswa) criteria.uniqueResult();
    }
    
}
