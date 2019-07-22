package com.app.api.controller;

import com.app.api.entity.Book;
import com.app.api.entity.User;
import com.app.api.entity.request.RequestUser;
import com.app.api.entity.response.ResponseEntity;
import com.app.api.service.Service;
import com.app.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addUser(@RequestBody RequestUser user) {
        return userService.addUser(user);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity editUser(@RequestBody RequestUser user) {
        return userService.editUser(user);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteUser(@RequestBody RequestUser user) {
        return userService.deleteUser(user);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getUser(@RequestBody RequestUser user) {
        return userService.getUser(user);
    }

//    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
//    public String getAllUsers() {
//        return service.getAllUsers();
//    }

}
