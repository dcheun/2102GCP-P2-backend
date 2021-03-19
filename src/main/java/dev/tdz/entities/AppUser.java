package dev.tdz.entities;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String fName;

    @Column(name = "last_name")
    private String lName;

    @Column(name = "email")
    private String email;

    @ColumnTransformer(
           write = "crypt(?, gen_salt('bf'))"
    )
    @Column(name = "pw")
    private String password;

    @JoinColumn(name = "id")
    @Column(name = "user_role_id")
    private int userRoleId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_course",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="course_id")}
    )
    private Set<Course> studentCourses = new HashSet<>();

    @OneToMany(mappedBy = "instructorId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Course> instructorCourses = new HashSet<>();

    public AppUser() {
    }

    public AppUser(int id, String fName, String lName, String email, String password, int userRoleId) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.userRoleId = userRoleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Set<Course> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<Course> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public Set<Course> getInstructorCourses() {
        return instructorCourses;
    }

    public void setInstructorCourses(Set<Course> instructorCourses) {
        this.instructorCourses = instructorCourses;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", userRoleId='" + userRoleId + '\'' +
                '}';
    }
}
