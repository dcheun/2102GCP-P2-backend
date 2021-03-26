package dev.tdz.entities;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@Table(
        name = "rating",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"})
)
@DynamicUpdate
@SelectBeforeUpdate
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JoinColumn(name = "id")
    @Column(name = "user_id", nullable = false)
    private int userId;

    @JoinColumn(name = "id")
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "stars")
    private Integer stars;

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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
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
