package com.taa.taa_spring_part3.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Agenda implements Serializable {
    private long id;
    public String login;
    public String password;
    public String url;
    public List<Meeting> meetingList;

    /**
     * Representation of a Professional Agenda
     * @param url      of online agenda
     * @param login    of online agenda
     * @param password of online agenda
     */
    public Agenda(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
        this.meetingList = new ArrayList<>();
    }

    /**
     * Representation of a Professional Agenda
     */
    public Agenda() {
    }

    /**
     * Getter for Url
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter for url
     */
    public void setUrl(String url) {
        this.url = url;
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
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for id
     */
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    /**
     * Get list of meeting
     *
     * @return list of meeting
     */
    @OneToMany
    @JoinColumn(name="agendaId")
    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    /**
     * Set list of meeting
     *
     * @param meetingList list of meeting
     */
    public void setMeetingList(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }

    /**
     * Add meeting to list of meeting
     *
     * @param m Meeting object
     */
    public void addMeeting(Meeting m) {
        this.meetingList.add(m);
    }

    /**
     * Remove meeting from list of meeting
     *
     * @param m Meeting object
     */
    public void removeMeeting(Meeting m) {
        this.meetingList.remove(m);
    }

    /**
     * Get string representation of class
     *
     * @return string representation of class
     */
    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", meetingList=" + meetingList +
                '}';
    }
}
