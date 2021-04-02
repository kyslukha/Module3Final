package ua.com.alevel.dao;

import ua.com.alevel.model.User;

import java.util.List;

public interface FollowersDao {
    List<User> findAllFollowers(User author);
}
