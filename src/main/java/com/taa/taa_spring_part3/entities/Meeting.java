package com.taa.taa_spring_part3.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Meeting implements Serializable {
    private long id;
    @Temporal(value = TemporalType.DATE)
    public Date date;
    public String entitled;
    public Individual patient;

    public Meeting() {
    }

    /**
     * Representation of a Meeting consultation
     * @param date of Meeting
     * @param entitled of Meeting
     * @param patient of Meeting
     */
    public Meeting(Date date, String entitled, Individual patient) {
        this.date = date;
        this.entitled = entitled;
        this.patient = patient;
    }

    /**
     * Getter for Date
     * @return date of Meeting
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for Date
     * @param date of Meeting
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter for Entitled of Meeting
     * @return entitled of Meeting
     */
    public String getEntitled() {
        return entitled;
    }

    /**
     * Setter for Entitled of Meeting
     * @param entitled of Meeting
     */
    public void setEntitled(String entitled) {
        this.entitled = entitled;
    }

    /**
     *Setter for id of Meeting
     * @param id of Meeting
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get id of meeting
     * @return id of Meeting
     */
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    /**
     * Get patient of meeting
     * @return patient of meeting
     */
    @ManyToOne(targetEntity = Individual.class, fetch = FetchType.EAGER)
    public Individual getPatient() {
        return patient;
    }

    /**
     * Set patient of Meeting
     * @param patient of Meeting
     */
    public void setPatient(Individual patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", date=" + date +
                ", entitled='" + entitled + '\'' +
                ", patient=" + patient.toString() +
                '}';
    }
}
