/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Kontak;
import Model.Kontakview;
import java.util.List;
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
public class KontakViewDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
   
    public List<Kontakview>getKontak(String prodi, int status){
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Kontakview.class);
        Criterion criterion1 = Restrictions.like("id.nim", prodi+"%");
        Criterion criterion2 = Restrictions.eq("id.idJenisKontak", 1);
        Criterion criterion3 = Restrictions.and(criterion1, criterion2);
        Criterion criterion4 = Restrictions.eq("id.idStatusMahasiswa", status);
        criteria.add(Restrictions.and(criterion3, criterion4));
        return criteria.list();
    }
}
