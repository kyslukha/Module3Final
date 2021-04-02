package ua.com.alevel.dao;

import ua.com.alevel.model.User;

import java.util.List;

public interface AuthorDao {
    List<User> findAllAuthors();

    List<User> findTopAuthors(int limit);
}
