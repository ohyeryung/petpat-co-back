package com.smile.petpat.user.controller;

import com.smile.petpat.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/v1")
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public void userRegister(@RequestBody UserDto.RegisterUserRequest request){

    }
}
