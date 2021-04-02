package ua.com.alevel;

import ua.com.alevel.impl.*;
import ua.com.alevel.model.Post;
import ua.com.alevel.model.User;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        List<User> userFollowersList = new ArrayList<>();
        List<User> userSubscribersList = new ArrayList<>();
        List<User> userSubscribers = new ArrayList<>();

        User user1 = new User();
        user1.setAge(20);
        user1.setEmail("mail@mail");
        user1.setFullName("Name");
        user1.setAuthor(true);
        user1.setModerator(true);
        user1.setLogin("login");
        user1.setFollowers(userFollowersList);
        user1.setSubscriptions(userSubscribersList);
userDao.create(user1);
        userSubscribers.add(user1);






    }


}
