package com.taa.taa_spring_part3.dao;

import com.taa.taa_spring_part3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Auto generated Spring DAO for user
 */
@Transactional
public interface UserDao extends JpaRepository<User, Long>{

    public User findByFirstName(String firstName);

    public User findByLastName(String lastName);

    public User findByFirstNameAndLastName(String firstName, String lastName);

    public User findByLogin(String login);
}
