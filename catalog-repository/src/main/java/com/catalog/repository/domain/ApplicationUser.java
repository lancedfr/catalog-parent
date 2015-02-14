package com.catalog.repository.domain;

import javax.persistence.*;

/**
 * Created by Lance on 11/02/2015.
 */
@Entity
@Table(name = "application_user")
public class ApplicationUser {
    /**
     * The id.
     */
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String emailAddress;

    private String password;

    private String firstName;

    private String lastName;

    private Integer age;

    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
