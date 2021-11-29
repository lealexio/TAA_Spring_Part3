package com.taa.taa_spring_part3.rest;

import com.taa.taa_spring_part3.dao.ProfessionalDao;
import com.taa.taa_spring_part3.entities.Agenda;
import com.taa.taa_spring_part3.entities.Professional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfessionalController {

    @Autowired
    private ProfessionalDao professionalDao;

    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String firstName, String lastName, String login, String password, Agenda agenda) {
        String userId = "";
        try {
            Professional professional = new Professional(firstName, lastName, login, password, agenda);
            professionalDao.save(professional);
            userId = String.valueOf(professional.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "Professional succesfully created with id = " + userId;
    }
}
