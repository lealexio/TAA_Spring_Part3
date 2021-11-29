package com.taa.taa_spring_part3.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

    public String firstName;
    public String lastName;
    public String login;
    public String password;
    private long id;

    /**
     * Representation of User, must be extended
     * @param firstName of User
     * @param lastName of User
     * @param login of User
     * @param password of User
     */
    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public User(long id) {
        this.id = id;
    }

    public User() {

    }

    /**
     * Getter for name
     * @return name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName
     * @param firstName of User
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName
     * @param lastName of User
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for login
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter for login
     * @param login of USer
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password of USer
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for id
     * @param id of USer
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for id
     * @return id
     */
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }


}
