package dev.tdz.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "course_name")
    private String name;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "id")
    @Column(name = "instructor_id")
    private int instructorId;

    @OneToMany(mappedBy = "courseId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CourseMaterial> courseMaterials = new HashSet<>();

    public Course() {
    }

    public Course(int id, String name, String description, int instructorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.instructorId = instructorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public Set<CourseMaterial> getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(Set<CourseMaterial> courseMaterials) {
        this.courseMaterials = courseMaterials;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", instructorId=" + instructorId +
                '}';
    }
}
