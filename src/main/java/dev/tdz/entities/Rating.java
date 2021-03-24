package dev.tdz.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    @JoinColumn(name = "id")
    private int userId;

    @Column(name = "course_id")
    @JoinColumn(name = "id")
    private int courseId;

    @Column(name = "stars")
    private int stars;

    @Column(name = "rating_comment")
    private String comment;

    public Rating() {
    }

    public Rating(int id, int userId, int courseId, int stars, String comment) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.stars = stars;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", stars=" + stars +
                ", comment='" + comment + '\'' +
                '}';
    }
}
