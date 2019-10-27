package org.launchcode.dndmvc.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {


    @NotNull
    @Size(min=5, max=15, message="Please enter a valid username!!!")
    private String username;


    @NotNull
    @Size(min=5, max= 15, message="Please enter a valid password")
    private String password;

    @Email
    private String email = "";

    public User(){

    }

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
