package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.getUsers();

    }
    @RequestMapping(path = "/userId", method = RequestMethod.GET)
    public User getUserById(@PathVariable int userId) {
        return userDao.getUserById(userId);
    }
    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public User createNewUser(@Valid @RequestBody User user) {
        return userDao.createUser(user);


    }





}
