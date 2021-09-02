package com.lookie.toy1_back.tome.controller;

import com.lookie.toy1_back.tome.domain.User;
import com.lookie.toy1_back.tome.request.UserCreationRequest;
import com.lookie.toy1_back.tome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> createUser (@RequestBody UserCreationRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }
}
