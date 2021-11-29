package com.taa.taa_spring_part3.rest;

import com.taa.taa_spring_part3.dao.AgendaDao;
import com.taa.taa_spring_part3.dao.IndividualDao;
import com.taa.taa_spring_part3.dao.MeetingDao;
import com.taa.taa_spring_part3.entities.Agenda;
import com.taa.taa_spring_part3.entities.Individual;
import com.taa.taa_spring_part3.entities.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("meeting")
public class MeetingController {

    @Autowired
    private MeetingDao meetingDao;

    @Autowired
    private IndividualDao individualDao;

    @Autowired
    private AgendaDao agendaDao;

    /**
     * GET /all  --> Return all Meetings.
     */
    @RequestMapping("/all")
    @ResponseBody
    public ResponseEntity getAllMeetings(){
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(meetingDao.findAll());
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    /**
     * GET /all  --> Add Meeting between Professional and Individual.
     */
    @RequestMapping(value= "/create", params = { "professionalId", "IndividualId", "date", "entitled"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity createMeeting(@RequestParam("professionalId") Long agendaId, @RequestParam("IndividualId") Long IndividualId, @RequestParam("date") String date, @RequestParam("entitled") String entitled){
        try {

            Date date_tmp=new SimpleDateFormat("dd/MM/yyyy").parse(date);

            Optional<Individual> individual_tmp = individualDao.findById(IndividualId);
            Optional<Agenda> agenda_tmp = agendaDao.findById(agendaId);

            if (!individual_tmp.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, individual with id=" + IndividualId + " not found");
            }
            if (!agenda_tmp.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, agenda with id=" + agenda_tmp + " not found");
            }

            Individual individual = individual_tmp.get();
            Agenda agenda = agenda_tmp.get();

            Meeting meeting = new Meeting(date_tmp, entitled, individual);
            meetingDao.save(meeting);

            agenda.addMeeting(meeting);
            agendaDao.save(agenda);

            individual.meetingList.add(meeting);
            individualDao.save(individual);


            return ResponseEntity.status(HttpStatus.FOUND).body("");
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
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
