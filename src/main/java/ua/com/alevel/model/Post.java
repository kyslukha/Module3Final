package ua.com.alevel.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "post")

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    //максимальная длина 100 символов
    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "content", length = 1000)
    //максимальная длина 1000 символов
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinColumns(@JoinColumn(name = "author_id", referencedColumnName = "id"))
    //автор сатьи
    private User author_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinColumns(@JoinColumn(name = "moderator_id", referencedColumnName = "id"))
    //модератор (тот кто апрувит статью)
    private User moderator_id;

    @Column(name = "rating")
    //рейтинг статьи, может быть как отрицательный так и положительный
    private int rating;

    @Enumerated(EnumType.STRING)
    private PostStatus status = PostStatus.DRAFT;

    public Post() {
    }

    public Post(String title, String content, User author_id, User moderator_id, int rating, PostStatus status) {
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.moderator_id = moderator_id;
        this.rating = rating;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor_id() {
        return author_id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author_id=" + author_id +
                ", moderator_id=" + moderator_id +
                ", rating=" + rating +
                ", status=" + status +
                '}';
    }

    public void setAuthor_id(User author_id) {
        this.author_id = author_id;
    }

    public User getModerator_id() {
        return moderator_id;
    }

    public void setModerator_id(User moderator_id) {
        this.moderator_id = moderator_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }
}
