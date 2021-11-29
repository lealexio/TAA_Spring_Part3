package com.taa.taa_spring_part3.rest;

import com.taa.taa_spring_part3.dao.MeetingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
