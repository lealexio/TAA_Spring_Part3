package com.taa.taa_spring_part3.dao;

import com.taa.taa_spring_part3.entities.Individual;
import com.taa.taa_spring_part3.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

/**
 * Auto generated Spring DAO for Meeting
 */
@Transactional
public interface MeetingDao extends JpaRepository<Meeting, Long>{

    public Meeting findByDate(Date date);

    public Meeting findByEntitled(String entitled);

    public Meeting findByPatient(Individual patient);

    public Meeting findByPatientAndDate(Individual patient,  Date date);
}
