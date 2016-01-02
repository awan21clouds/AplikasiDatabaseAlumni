/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.User;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
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
public class UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void insert(User user){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
    }
    
    public List<User> viewAll(){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session.createQuery("from User").list();
    }
    
    public User view(String id){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return (User) session.get(User.class, id);
    }
    
    public void update(User user){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(user);
    }
    
    public void delete(String id){
        User user = view(id);
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(user);
    }
    public void saveAll(List<User> userList){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        for(User user: userList){
            session.saveOrUpdate(user);
            session.flush();
        }
    }
    
    public void saveAllByMap(Map<String, User> userMap){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Iterator iter = userMap.keySet().iterator();
        while(iter.hasNext()){
            String nim = (String) iter.next();
            User user = userMap.get(nim);
            session.saveOrUpdate(user);
            session.flush();
        }
    
    }
    public User login(String username, String password){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        Criterion criterion1 = Restrictions.eq("username", username);
        Criterion criterion2 = Restrictions.eq("password", password);
        Criterion criterion3 = Restrictions.and(criterion1, criterion2);
        Criterion criterion4 = Restrictions.isNotNull("activeStatus");
        criteria.add(Restrictions.and(criterion3, criterion4));
        return (User) criteria.uniqueResult();
    }
    
}
