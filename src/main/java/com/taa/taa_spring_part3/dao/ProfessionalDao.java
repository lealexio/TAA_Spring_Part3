package com.taa.taa_spring_part3.dao;

import com.taa.taa_spring_part3.entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Auto generated Spring DAO for Professional
 */
@Transactional
public interface ProfessionalDao extends JpaRepository<Professional, Long>{

    public Professional findByFirstName(String firstName);

    public Professional findByLastName(String lastName);

    public Professional findByFirstNameAndLastName(String firstName, String lastName);

    public Professional findByLogin(String login);

}
