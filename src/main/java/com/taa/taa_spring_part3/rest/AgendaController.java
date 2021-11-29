package com.taa.taa_spring_part3.rest;

import com.taa.taa_spring_part3.dao.AgendaDao;
import com.taa.taa_spring_part3.entities.Agenda;
import com.taa.taa_spring_part3.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("agenda")
public class AgendaController {

    @Autowired
    private AgendaDao agendaDao;

    /**
     * GET /all  --> Return all Meetings.
     */
    @RequestMapping("/all")
    @ResponseBody
    public ResponseEntity getAllAgenda(){
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(agendaDao.findAll());
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @RequestMapping(value = "/update/{id}/", params = {"firstName", "lastName", "login", "password"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity updateAgenda(@PathVariable long id, @RequestParam(name="url") String url, @RequestParam(name="login") String login, @RequestParam(name="password") String password) {
        try {
            Optional<Agenda> optionalAgenda = agendaDao.findById(id);
            if (optionalAgenda.isPresent()){
                Agenda agenda = optionalAgenda.get();

                if (!Objects.equals(url, "")){
                    agenda.setUrl(url);
                }
                if (!Objects.equals(login, "")){
                    agenda.setLogin(login);
                }
                if (!Objects.equals(password, "")){
                    agenda.setPassword(password);
                }
                agendaDao.save(agenda);
                return ResponseEntity.status(HttpStatus.CREATED).body(agenda);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, Agenda with id=" + id + " not found");
            }
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }


    /**
     * GET /state  --> Remove Agenda
     */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public ResponseEntity getAllAgenda(@PathVariable long id){
        Optional<Agenda> optionalAgenda = agendaDao.findById(id);
        if (optionalAgenda.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body(optionalAgenda.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error, Agenda with id=" + id + " not found");
        }
    }

    /**
     * GET /state  --> Test server response.
     */
    @RequestMapping("/state")
    @ResponseBody
    public String test(){
        return "State : OK";
    }

}
