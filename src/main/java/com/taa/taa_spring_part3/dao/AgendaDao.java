package com.taa.taa_spring_part3.dao;

import com.taa.taa_spring_part3.entities.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Auto generated Spring DAO for Agenda
 */
@Transactional
public interface AgendaDao extends JpaRepository<Agenda, Long>{

    public Agenda findByUrl(String url);
}
