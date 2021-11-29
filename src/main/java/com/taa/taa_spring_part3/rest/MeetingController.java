package com.taa.taa_spring_part3.rest;

import com.taa.taa_spring_part3.dao.IndividualDao;
import com.taa.taa_spring_part3.dao.MeetingDao;
import com.taa.taa_spring_part3.dao.UserDao;
import com.taa.taa_spring_part3.entities.Agenda;
import com.taa.taa_spring_part3.entities.Individual;
import com.taa.taa_spring_part3.entities.Meeting;
import com.taa.taa_spring_part3.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("meeting")
public class MeetingController {

    @Autowired
    private MeetingDao meetingDao;

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
     * GET /state  --> Test server response.
     */
    @RequestMapping("/state")
    @ResponseBody
    public String test(){
        return "State : OK";
    }

}
