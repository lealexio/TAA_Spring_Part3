package com.taa.taa_spring_part3.rest;

import com.taa.taa_spring_part3.dao.UserDao;
import com.taa.taa_spring_part3.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User professional = new User(id);
            userDao.delete(professional);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * GET /update  --> Update the firstname and the lastname for the user in the database having the passed id.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateUserName(long id, String firstName, String lastName) {
        try {
            User user = userDao.findById(id).get();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    /**
     * GET /update  --> Update the firstname and the lastname for the user in the database having the passed id.
     */
    @GetMapping("/all")
    @ResponseBody
    public String findAllUsers() {
        try {
            return userDao.findAll().toString();
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public String home(){
        return "Status : OK";
    }

}
