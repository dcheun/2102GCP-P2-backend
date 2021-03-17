package dev.tdz.entities;


import javax.persistence.*;

@Entity
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "material_type")
    private String materialType;

    @Column(name = "description")
    private String description;


    @Column(name = "course_id")
    @JoinColumn(name = "id")
    private int courseId;

    public CourseMaterial() {
    }

    public CourseMaterial(int id, String materialType, String description, int courseId) {
        this.id = id;
        this.materialType = materialType;
        this.description = description;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CourseMaterial{" +
                "id=" + id +
                ", materialType='" + materialType + '\'' +
                ", description='" + description + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
