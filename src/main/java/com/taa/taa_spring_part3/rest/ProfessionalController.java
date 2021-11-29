package com.taa.taa_spring_part3.rest;

import com.taa.taa_spring_part3.dao.AgendaDao;
import com.taa.taa_spring_part3.dao.ProfessionalDao;
import com.taa.taa_spring_part3.entities.Agenda;
import com.taa.taa_spring_part3.entities.Professional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("professional")
public class ProfessionalController {

    @Autowired
    private ProfessionalDao professionalDao;

    @Autowired
    private AgendaDao agendaDao;

    /**
     * GET /create  --> Create a new professional with new agenda and save it in the database.
     */
    @RequestMapping(value= "/createWithNewAgenda", params = { "firstName", "lastName", "login", "password", "agendaUrl", "agendaLogin", "agendaPassword"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity createProfessionalWithAgenda(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("agendaUrl") String agendaUrl, @RequestParam("agendaLogin") String agendaLogin, @RequestParam("agendaPassword") String agendaPassword) {
        try {
            Agenda agenda = new Agenda(agendaUrl, agendaLogin, agendaPassword);
            agendaDao.save(agenda);
            Professional professional = new Professional(firstName, lastName, login, password, agenda);
            professionalDao.save(professional);
            return ResponseEntity.status(HttpStatus.CREATED).body(professional);
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    /**
     * GET /create  --> Create a new professional with new agenda and save it in the database.
     */
    @RequestMapping(value= "/createFromAgendaId", params = { "firstName", "lastName", "login", "password", "agendaId"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity createProfessionalWithAgendaId(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("agendaId") Long agendaId) {
        try {
            Optional<Agenda> agenda = agendaDao.findById(agendaId);
            if (agenda.isPresent()){
                Professional professional = new Professional(firstName, lastName, login, password, agenda.get());
                professionalDao.save(professional);
                return ResponseEntity.status(HttpStatus.CREATED).body(professional);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, agenda with id=" + agendaId + " not found");
            }
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @RequestMapping("/all")
    @ResponseBody
    public ResponseEntity getAllProfessionals(){
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(professionalDao.findAll());
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
    @RequestMapping(value= "/get", params = { "id"})
    @ResponseBody
    public ResponseEntity getProfessionalFromId(@RequestParam("id") Long id){
        try {
            Optional<Professional> pro = professionalDao.findById(id);
            if (pro.isPresent()){
                return ResponseEntity.status(HttpStatus.FOUND).body(pro.get());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professional with Id=" + id + " not found.");
            }
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @RequestMapping("/state")
    @ResponseBody
    public ResponseEntity test(){
        return ResponseEntity.status(HttpStatus.OK).body("State : OK");
    }
}
