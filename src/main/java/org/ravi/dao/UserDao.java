package org.ravi.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.ravi.models.User;
import org.ravi.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

@Repository
public class UserDao {

    @Autowired
    @Qualifier("hbSessionFactory")
    private SessionFactory sessionFactory;

    public Integer saveUserProfile(UserProfile userProfile){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Integer  id = (Integer)session.save(userProfile);
            tx.commit();
            return id;
        } catch (Exception e){
            tx.rollback();
            return 0;
        }

    }

    public Integer saveUser(User user){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Integer  id = (Integer)session.save(user);
            tx.commit();
            return id;
        } catch (Exception e){
            tx.rollback();
            return 0;
        }

    }

}
