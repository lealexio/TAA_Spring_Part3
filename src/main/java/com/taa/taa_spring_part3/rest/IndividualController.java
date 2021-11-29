package com.taa.taa_spring_part3.rest;

import com.taa.taa_spring_part3.dao.IndividualDao;
import com.taa.taa_spring_part3.dao.UserDao;
import com.taa.taa_spring_part3.entities.Individual;
import com.taa.taa_spring_part3.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("individual")
public class IndividualController {

    @Autowired
    private IndividualDao individualDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserController userController;

    /**
     * GET /update/{id}/  --> Update user from id, empty params will be skipped
     * Example : http://localhost:8080/user/update/2/?firstName=myNewFirstName&lastName=&password=myNewPassword&login=&phone=0765541799
     */
    @RequestMapping(value = "/update/{id}/", params = {"firstName", "lastName", "login", "password", "phone"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity updateIndividual(@PathVariable long id, @RequestParam(name="firstName") String firstName, @RequestParam(name="lastName") String lastName, @RequestParam(name="login") String login, @RequestParam(name="password") String password, @RequestParam(name="phone") String phone) {
        try {
            Optional<Individual> optionalUser = individualDao.findById(id);
            if (optionalUser.isPresent()){
                userController.updateUser(id, firstName, lastName, login, password);
                User user = optionalUser.get();

                userDao.save(user);
                return ResponseEntity.status(HttpStatus.CREATED).body(user);
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, user with id=" + id + " not found");
            }
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    /**
     * GET /remove/{id}  --> Remove Individual.
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity removeProfessional(@PathVariable long id) {
        Optional<Individual> pro = individualDao.findById(id);
        if (pro.isPresent()){
            return userController.removeUser(id);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Individual with Id=" + id + " not found.");
        }
    }


    /**
     * GET /all  --> Get all Individuals.
     */
    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity findAllIndividuals() {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(individualDao.findAll());
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
