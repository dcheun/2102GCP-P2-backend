package dev.tdz.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "stars")
    private int stars;

    @Column(name = "rating_comment")
    private String comment;

    @Column(name = "course_id")
    @JoinColumn(name = "id")
    private int courseId;

    public Rating() {
    }

    public Rating(int id, int stars, String comment, int courseId) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", stars=" + stars +
                ", comment='" + comment + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
