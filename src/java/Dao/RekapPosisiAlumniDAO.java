/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Rekapposisialumni;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
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
public class RekapPosisiAlumniDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void insert(Rekapposisialumni rekapposisialumni){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(rekapposisialumni);
    }
    
    public List<Rekapposisialumni> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from Rekapposisialumni").list();
    }
    
    public Rekapposisialumni viewById(int id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (Rekapposisialumni) session.get(Rekapposisialumni.class, id);
    }
    
    public void delete(int id){
        Rekapposisialumni rekapposisialumni = viewById(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(rekapposisialumni);
    }
    
    public void saveAll(List<Rekapposisialumni> rekapposisialumniList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(Rekapposisialumni rekapposisialumni: rekapposisialumniList){
            session.saveOrUpdate(rekapposisialumni);
            session.flush();
        }
    }
    
    public List<Rekapposisialumni> selectAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        return criteria.list();
    }
    
/*-------------------------------------------------------------Prodi------------------------------------------------------------------------*/    
 
/*-------------------------------------------------------------Peminatan------------------------------------------------------------------------*/    
 
    public List<Rekapposisialumni> selectWhereAnd(String idProdi, String posisiPertama){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criterion criterion1 = Restrictions.like("id.nim", idProdi+"%");
        Criterion criterion2 = Restrictions.eq("id.posisiPertama", posisiPertama);
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        criteria.add(Restrictions.and(criterion1, criterion2));
        return criteria.list();
    }
    
    public List<Rekapposisialumni> selectWhere(String posisiPertama){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criterion criterion2 = Restrictions.eq("id.posisiPertama", posisiPertama);
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        criteria.add(criterion2);
        return criteria.list();
    }
    
    public List<Rekapposisialumni> selectByPeminatan(int idPeminatan, String posisiPertama){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criterion criterion1 = Restrictions.eq("id.idPeminatan", idPeminatan);
        Criterion criterion2 = Restrictions.eq("id.posisiPertama", posisiPertama);
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        criteria.add(Restrictions.and(criterion1, criterion2));
        return criteria.list();
    }
    
    public List<Object>groupByAngkatan(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        ProjectionList list = Projections.projectionList();
        list.add(Projections.groupProperty("id.angkatan"));
        criteria.setProjection(list);
        return criteria.list();
    }
}
