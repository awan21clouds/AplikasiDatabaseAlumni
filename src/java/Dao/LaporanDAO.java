/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Rekapposisialumni;
import java.util.List;
import org.hibernate.Criteria;
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
public class LaporanDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public List<Object> getLaporan(String[] groupBy){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        ProjectionList list = Projections.projectionList();
        list.add(Projections.count("id.nim"));
        for(String group : groupBy){
            list.add(Projections.groupProperty(group));
        }
        criteria.setProjection(list);
        return criteria.list();
    }
    
    public List<Object> getLaporanGreater(String[] groupBy){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        Criterion criteria1 = Restrictions.gt("id.ipk", 2.75);
        Criterion criteria2 = Restrictions.eq("id.ipk", 2.75);
        criteria.add(Restrictions.or(criteria1, criteria2));
        ProjectionList list = Projections.projectionList();
        list.add(Projections.count("id.nim"));
        for(String group : groupBy){
            list.add(Projections.groupProperty(group));
        }
        criteria.setProjection(list);
        return criteria.list();
    }
    
    public List<Object> getLaporanLess(String[] groupBy){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        criteria.add(Restrictions.le("id.ipk", 2.75));
        ProjectionList list = Projections.projectionList();
        list.add(Projections.count("id.nim"));
        for(String group : groupBy){
            list.add(Projections.groupProperty(group));
        }
        criteria.setProjection(list);
        return criteria.list();
    }
    
    public List<Object> getMasaTunggu(String[] groupBy){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        criteria.add(Restrictions.not(Restrictions.eq("id.posisiPertama", "menunggu")));
        ProjectionList list = Projections.projectionList();
        list.add(Projections.avg("id.masaTunggu"));
        list.add(Projections.count("id.nim"));
        for(String group : groupBy){
            list.add(Projections.groupProperty(group));
        }
        criteria.setProjection(list);
        return criteria.list();
    }
    public List<Object> getMasaTungguAll(String[] groupBy){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        ProjectionList list = Projections.projectionList();
        list.add(Projections.avg("id.masaTunggu"));
        list.add(Projections.count("id.nim"));
        for(String group : groupBy){
            list.add(Projections.groupProperty(group));
        }
        criteria.setProjection(list);
        return criteria.list();
    }
    public List<Object> getCore(String[] groupBy){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Rekapposisialumni.class);
        criteria.add(Restrictions.not(Restrictions.eq("id.core", Long.parseLong("3"))));
        ProjectionList list = Projections.projectionList();
        list.add(Projections.count("id.nim"));
        for(String group : groupBy){
            list.add(Projections.groupProperty(group));
        }
        criteria.setProjection(list);
        return criteria.list();
    }
    
    public List<Rekapposisialumni> getLaporanDetail(String query){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery(query).list();
    }
}
