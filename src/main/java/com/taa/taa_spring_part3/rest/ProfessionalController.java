package com.taa.taa_spring_part3.rest;

import com.taa.taa_spring_part3.dao.AgendaDao;
import com.taa.taa_spring_part3.dao.ProfessionalDao;
import com.taa.taa_spring_part3.entities.Agenda;
import com.taa.taa_spring_part3.entities.Professional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("professional")
public class ProfessionalController {

    @Autowired
    private ProfessionalDao professionalDao;

    @Autowired
    private AgendaDao agendaDao;

    @Autowired
    private UserController userController;

    /**
     * GET /createWithNewAgenda  --> Create a new professional with new empty agenda and save it in the database.
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
     * GET /createFromAgendaId  --> Create a new professional from existing agenda id.
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

    /**
     * GET /all  --> Return all Professionals.
     */
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

    /**
     * GET /get/{id}  --> Return a professional from id.
     */
    @RequestMapping(value= "/get/{id}", params = {"id"})
    @ResponseBody
    public ResponseEntity getProfessionalFromId(@PathVariable("id") Long id){
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

    /**
     * GET /remove/{id}  --> Remove Professional.
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity removeProfessional(@PathVariable long id) {
        Optional<Professional> pro = professionalDao.findById(id);
        if (pro.isPresent()){
            return userController.removeUser(id);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professional with Id=" + id + " not found.");
        }
    }

    /**
     * GET /state  --> Test server response.
     */
    @RequestMapping("/state")
    @ResponseBody
    public ResponseEntity test(){
        return ResponseEntity.status(HttpStatus.OK).body("State : OK");
    }
}
