package com.smile.petpat.user.controller;

import com.smile.petpat.user.domain.UserCommand;
import com.smile.petpat.user.dto.UserDto;
import com.smile.petpat.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    // 회원가입
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public void userRegister(@RequestBody UserDto.RegisterUserRequest request){
        UserCommand command = request.toCommand();
        userService.registerUser(command);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void usernameValidChk(@RequestParam("username") String username){
        userService.userIdValidChk(username);
    }

    // 로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> userLogin(@RequestBody UserDto.LoginUserRequest request){
        UserCommand command = request.toCommand();
        return userService.loginUser(command);
    }
}
