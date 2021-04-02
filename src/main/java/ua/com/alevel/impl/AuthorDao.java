package ua.com.alevel.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.com.alevel.model.User;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;

public class AuthorDao implements ua.com.alevel.dao.AuthorDao {
    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    @Override
    public List<User> findAllAuthors() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("  FROM User WHERE  isAuthor = TRUE");
            List<User> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    @Override
    public List<User> findTopAuthors(int limit) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createNativeQuery(" SELECT * FROM users u JOIN post p on u.id = p.author_id ORDER BY rating LIMIT ? " );
            query.setParameter(1, limit);
            List<User> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }
}
