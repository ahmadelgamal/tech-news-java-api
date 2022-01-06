package com.technews.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "post")
public class Post implements Serializable {
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", voteCount=" + voteCount +
                ", userId=" + userId +
                ", postedAt=" + postedAt +
                ", updatedAt=" + updatedAt +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return voteCount == post.voteCount && Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(postUrl, post.postUrl) && Objects.equals(userName, post.userName) && Objects.equals(userId, post.userId) && Objects.equals(postedAt, post.postedAt) && Objects.equals(updatedAt, post.updatedAt) && Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, postUrl, userName, voteCount, userId, postedAt, updatedAt, comments);
    }

    public Integer getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String postUrl;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    @Transient
    private String userName;
    @Transient
    private int voteCount;
    private Integer userId;
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "posted_at")
    private Date postedAt = new Date();

    public Post(Integer id) {
        this.id = id;
    }

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt = new Date();
    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
}
