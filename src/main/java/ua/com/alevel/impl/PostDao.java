package ua.com.alevel.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.com.alevel.dao.Dao;
import ua.com.alevel.model.Post;
import ua.com.alevel.model.User;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;

public class PostDao implements ua.com.alevel.dao.PostDao, Dao<Post> {
    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    @Override
    public List<Post> findAllPostsByAuthor(User author) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Post> query = session.createQuery("FROM Post where author_id =: author");
            query.setParameter("author", author);
            List<Post> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    @Override
    public List<Post> findTopPosts(int limit) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Post> query = session.createNativeQuery("SELECT * FROM Post order by rating LIMIT ?");
            query.setParameter(1, limit);
            List<Post> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    @Override
    public List<Post> findTopAuthorsPosts(User author, int limit) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Post> query = session.createNativeQuery("SELECT * FROM Post WHERE author_id = ? order by rating LIMIT ?");
            query.setParameter(1, author);
            query.setParameter(2, limit);
            List<Post> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    @Override
    public void create(Post model) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(model);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Post post, int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Post " +
                    "SET " +
                    "title =: title, " +
                    "content =: content, " +
                    "author_id =: author_id, " +
                    "moderator_id =: moderator_id, " +
                    "rating =: rating, " +
                    "status =: status " +
                    "WHERE id =: id");
            query.setParameter("title", post);
            query.setParameter("content", post.getContent());
            query.setParameter("author_id", post.getAuthor_id());
            query.setParameter("moderator_id", post.getModerator_id());
            query.setParameter("rating", post.getRating());
            query.setParameter("status", post.getStatus());
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public Post findById(int postId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("  FROM  User where id =: post_id");
            query.setParameter("post_id", postId);
            Post singleResult = (Post) query.getSingleResult();
            session.getTransaction().commit();
            return singleResult;
        }
    }

    @Override
    public List<Post> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Post> query = session.createQuery("FROM Post");
            List<Post> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    @Override
    public boolean deleteById(int postId) {
        int rows;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Post WHERE id =: post_id");
            query.setParameter("post_id", postId);
            rows = query.executeUpdate();
            session.getTransaction().commit();
        }
        if (rows > 0){
            return true;
        }else{
            return false;
        }
    }
}
