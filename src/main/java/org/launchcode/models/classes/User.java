package org.launchcode.models.classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotNull(message = "please provide an email")
    @Email(message = "please provide a valid email")
    private String email;

    @NotNull(message = "You must create a password between 6 and 20 characters long.")
    @Size(min=6)
    private String password;

//    @DateTimeFormat
//    private Date dateCreated;

    @NotNull(message = "please provide a unique username")
    @Size(min=5, max=20)
    private String username;

    private int active;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

//    @NotNull
//    private Date birthday;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Rating> ratings = new ArrayList<>(); //a user can create many ratings. each rating will be assigned to a single user.

    public User() {
//        this.dateCreated = new Date();
    }

    public User(String firstName, String lastName, String username, String email, String password) {
//        Date date = new Date();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
//        this.dateCreated = date;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

//    public void setDateCreated(Date dateCreated) {
////        this.dateCreated = dateCreated;
//    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

//    public String getDateCreated() {
////        return dateCreated.toString();
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
