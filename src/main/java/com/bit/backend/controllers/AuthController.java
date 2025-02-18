package com.bit.backend.controllers;

import com.bit.backend.config.UserAuthProvider;
import com.bit.backend.dtos.*;
import com.bit.backend.services.UserServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthController {

    private final UserServiceI userServiceI;
    private final UserAuthProvider userAuthProvider;

    public AuthController(UserServiceI userServiceI, UserAuthProvider userAuthProvider) {
        this.userServiceI = userServiceI;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto user = userServiceI.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto user = userServiceI.register(signUpDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }

    @GetMapping("/get-auth-ids/{id}")
    public ResponseEntity<List<Integer>> getAuthDetails(@PathVariable long id) {
        try {
            List<Integer> authIds = userServiceI.getAuthIds(id);
            return ResponseEntity.status(HttpStatus.OK).body(authIds);
        } catch (Exception e) {
            List<Integer> returnList = new ArrayList<>();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnList);
        }
    }

    @GetMapping("/system-privileges")
    public ResponseEntity<SystemPrivilegeListDto> getAuthDetails() {
        SystemPrivilegeListDto systemPrivilegeListDto = userServiceI.getSystemPrivileges();
        return ResponseEntity.ok(systemPrivilegeListDto);
    }

//    @PostMapping("/system-privileges")
//    public ResponseEntity<List<Integer>> saveSystemPrivileges(@RequestBody SystemPrivilegeListDto systemPrivilegeListDto) {
//        return ResponseEntity.ok(userServiceI.setSystemPrivileges(systemPrivilegeListDto));
//    }
}
