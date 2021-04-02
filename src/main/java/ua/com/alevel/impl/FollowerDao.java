package ua.com.alevel.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.com.alevel.dao.FollowersDao;
import ua.com.alevel.model.User;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;

public class FollowerDao implements FollowersDao {
    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    @Override
    public List<User> findAllFollowers(User author) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createNativeQuery("SELECT u.login FROM users u JOIN subscriptions us ON u.id = s.writer_id " +
                    "WHERE us.writer_id = ? AND u.id = us.follower_id");
            query.setParameter(1, author);
            List<User> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }

    }
}
