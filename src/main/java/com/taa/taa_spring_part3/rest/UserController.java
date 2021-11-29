package com.taa.taa_spring_part3.rest;

import com.taa.taa_spring_part3.dao.UserDao;
import com.taa.taa_spring_part3.entities.Professional;
import com.taa.taa_spring_part3.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    /**
     * GET /update/{id}/  --> Update user from id, empty params will be skipped
     * Example : http://localhost:8080/user/update/2/?firstName=myNewFirstName&lastName=&password=myNewPassword&login
     */
    @RequestMapping(value = "/update/{id}/", params = {"firstName", "lastName", "login", "password"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity updateUser(@PathVariable long id, @RequestParam(name="firstName") String firstName, @RequestParam(name="lastName") String lastName, @RequestParam(name="login") String login, @RequestParam(name="password") String password) {
        try {
            Optional<User> optionalUser = userDao.findById(id);
            if (optionalUser.isPresent()){
                User user = optionalUser.get();

                if (!Objects.equals(firstName, "")){
                    user.setFirstName(firstName);
                }
                if (!Objects.equals(lastName, "")){
                    user.setLastName(lastName);
                }
                if (!Objects.equals(login, "")){
                    user.setLogin(login);
                }
                if (!Objects.equals(password, "")){
                    user.setPassword(password);
                }
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

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity removeUser(@PathVariable long id) {
        try {
            Optional<User> user = userDao.findById(id);
            if (user.isPresent()) {
                userDao.delete(user.get());
                return ResponseEntity.status(HttpStatus.FOUND).body("User with id=" + id + " removed");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, user with id=" + id + " not found");
            }
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }


    /**
     * GET /get/{id}  --> Return a User from id.
     */
    @RequestMapping(value= "/get/{id}", params = {"id"})
    @ResponseBody
    public ResponseEntity getUserFromId(@PathVariable("id") Long id){
        try {
            Optional<User> u = userDao.findById(id);
            if (u.isPresent()){
                return ResponseEntity.status(HttpStatus.FOUND).body(u.get());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with Id=" + id + " not found.");
            }
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    /**
     * GET /all  --> Get all users.
     */
    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity findAllUsers() {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(userDao.findAll());
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
