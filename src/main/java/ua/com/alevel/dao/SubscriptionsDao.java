package ua.com.alevel.dao;

import ua.com.alevel.model.User;

import java.util.List;

public interface SubscriptionsDao{
    List<User> findAllSubscriptions(User subscriber);
}
