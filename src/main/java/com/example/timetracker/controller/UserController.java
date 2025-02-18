package com.example.timetracker.controller;

import com.example.timetracker.entity.User;
import com.example.timetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        User user = userService.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PostMapping("/loginuser")
    public User loginUser(@RequestBody User user) throws Exception
    {
        String currEmail = user.getEmail();

        String currPassword = user.getPassword();

        User userObj = null;
        if(currEmail != null && currPassword != null)
        {
            userObj = userService.fetchUserByEmailAndPassword(currEmail, currPassword);

        }
        if(userObj == null)
        {
            throw new Exception("User does not exists!!! Please enter valid credentials...");
        }
        return userObj;
    }

}
