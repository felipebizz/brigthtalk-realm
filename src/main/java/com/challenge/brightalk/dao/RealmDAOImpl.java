package com.challenge.brightalk.dao;

import com.challenge.brightalk.model.Realm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RealmDAOImpl implements RealmDAO {

    private static Logger log = Logger.getLogger(RealmDAOImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int insert(Realm realm) {
        sessionFactory.getCurrentSession().save(realm);
        return realm.getId();
    }

    @Override
    public List<Realm> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Realm> cq = cb.createQuery(Realm.class);
        Root<Realm> root = cq.from(Realm.class);
        cq.select(root);
        Query<Realm> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Realm getRealmById(final int id) {
        return sessionFactory.getCurrentSession().get(Realm.class, id);
    }

    @Override
    public Realm getRealmByName(final String name) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Realm> cq = cb.createQuery(Realm.class);
        Root<Realm> c = cq.from(Realm.class);
        cq.select(c).where(cb.equal(c.get("name"), name));
        Query<Realm> query = session.createQuery(cq);
        return query.uniqueResult();
    }
}




