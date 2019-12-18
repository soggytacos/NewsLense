package org.launchcode.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int userId;

    @NotNull
    @Size(min=5, max=20)
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min=6, max=20)
    private String password;

    @DateTimeFormat
    private Date dateCreated;

    public User() {}

    public User(String username, String email, String password) {
        Date date = new Date();
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateCreated = date;
    }

    public int getUserId() {
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

    public Date getDateCreated() {
        return dateCreated;
    }
}
