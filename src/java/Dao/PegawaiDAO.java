/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Pegawai;
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
public class PegawaiDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Pegawai pegawai){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(pegawai);
    }
    
    public List<Pegawai> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Pegawai").list();
    }
    
    public Pegawai view(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Pegawai) session.get(Pegawai.class, id);
    }
    
    public void update(Pegawai pegawai){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(pegawai);
    }
    
    public void delete(String id){
        Pegawai pegawai = view(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(pegawai);
    }
    
    public void saveAll(List<Pegawai> pegawaiList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Pegawai pegawai: pegawaiList){
            session.saveOrUpdate(pegawai);
            session.flush();
        }
    }
    
    public List<Pegawai>filterPegawai(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Pegawai.class);
        criteria.add(Restrictions.eq("idPegawai", id));
        return criteria.list();
    }
}
