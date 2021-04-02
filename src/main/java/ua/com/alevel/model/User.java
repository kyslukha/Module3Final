package ua.com.alevel.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "login",nullable = false,unique = true)
    private String login;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "age")
    private int age;

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "writer_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")}
    )
    private List<User> followers = new ArrayList<>();

    @Column(name = "is_author")
    private Boolean isAuthor;

    @Column(name = "is_moderator")
    private Boolean isModerator;

    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "follower_id")},
            inverseJoinColumns = {@JoinColumn(name = "writer_id")}
    )
    private List<User> subscriptions = new ArrayList<>();

    public User(String fullName, String login, String email, int age , Boolean isAuthor, Boolean isModerator) {
        this.fullName = fullName;
        this.login = login;
        this.email = email;
        this.age = age;
        this.isAuthor = isAuthor;
        this.isModerator = isModerator;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", followers=" + followers +
                ", isAuthor=" + isAuthor +
                ", isModerator=" + isModerator +
                ", subscriptions=" + subscriptions +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public Boolean getAuthor() {
        return isAuthor;
    }

    public void setAuthor(Boolean author) {
        isAuthor = author;
    }

    public Boolean getModerator() {
        return isModerator;
    }

    public void setModerator(Boolean moderator) {
        isModerator = moderator;
    }

    public List<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public User() {
    }
}
