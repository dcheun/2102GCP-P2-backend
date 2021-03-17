package dev.tdz.entities;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRoleId='" + userRoleId + '\'' +
                '}';
    }
}
