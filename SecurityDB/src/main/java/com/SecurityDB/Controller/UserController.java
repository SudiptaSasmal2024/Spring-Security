package com.SecurityDB.Controller;

import com.SecurityDB.DTO.UserDTO;
import com.SecurityDB.Entity.Users;
import com.SecurityDB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody UserDTO userDTO){
        return userService.register(userDTO);
    }

    @PutMapping("/update/password")
    public String updatePassword(@RequestBody Users user){
        return userService.updatePassword(user);
    }
    @GetMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
        return userService.verify(userDTO);
    }


}
