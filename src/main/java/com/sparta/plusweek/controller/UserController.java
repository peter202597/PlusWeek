package com.sparta.plusweek.controller;

import com.sparta.plusweek.dto.LoginRequestDto;
import com.sparta.plusweek.dto.MessageDto;
import com.sparta.plusweek.dto.UserSignUpRequestDto;
import com.sparta.plusweek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?>signup(@RequestBody UserSignUpRequestDto userSignUpRequestDto){
            userService.signup(userSignUpRequestDto);
            String signUpMsg = userService.signup(userSignUpRequestDto);
            return ResponseEntity.ok().body(new MessageDto(signUpMsg));
        }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRequestDto loginRequestDto){
        userService.login(loginRequestDto);
        String loginMsg = userService.login(loginRequestDto);
        return ResponseEntity.ok().body(new MessageDto(loginMsg));
    }
}

