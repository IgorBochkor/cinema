package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user entity " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> findUserByEmail
                    = session.createQuery("FROM User u JOIN FETCH u.roles "
                    + "where u.email = :email ", User.class)
                    .setParameter("email", email);
            return findUserByEmail.uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException("Can't get user by email = " + email, e);
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> findUserByEmail
                    = session.createQuery("FROM User u JOIN FETCH u.roles "
                    + "where u.id = :id ", User.class)
                    .setParameter("id", id);
            return findUserByEmail.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving user by id " + id, e);
        }
    }
}
