package com.taa.taa_spring_part3.dao;

import com.taa.taa_spring_part3.entities.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Auto generated Spring DAO for Individual
 */
@Transactional
public interface IndividualDao extends JpaRepository<Individual, Long>{

    public Individual findByFirstName(String firstName);

    public Individual findByLastName(String lastName);

    public Individual findByFirstNameAndLastName(String firstName, String lastName);

    public Individual findByLogin(String login);

    public Individual findByTel(String tel);
}
