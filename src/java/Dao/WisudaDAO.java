/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Wisuda;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class WisudaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(Wisuda wisuda){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(wisuda);
    }
    
    public List<Wisuda> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Wisuda").list();
    }
    
    public Wisuda view(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Wisuda) session.get(Wisuda.class, id);
    }
    
    public void update(Wisuda wisuda){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(wisuda);
    }
    
    public void delete(String id){
        Wisuda wisuda = view(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(wisuda);
    }
    public void saveAll(List<Wisuda> wisudaList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Wisuda wisuda: wisudaList){
            session.saveOrUpdate(wisuda);
            session.flush();
        }
    }
    public void saveAll(Map<String, Wisuda> wisudaMap){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Iterator iter = wisudaMap.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            session.saveOrUpdate(entry.getValue());
            session.flush();
        }
    }
    
    public List<Integer>groupBy(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("select year(tanggal) from Wisuda group by year(tanggal)").list();
    }
    
    public Wisuda viewByTanggal(Date tanggal){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Wisuda.class);
        criteria.add(Restrictions.eq("tanggal", tanggal));
        return (Wisuda) criteria.uniqueResult();
    }
    
    public Wisuda viewByKeterangan(String keterangan){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Wisuda.class);
        criteria.add(Restrictions.eq("keterangan", keterangan));
        return (Wisuda) criteria.uniqueResult();
    }
}
