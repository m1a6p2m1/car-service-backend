package com.bit.backend.controllers;

import com.bit.backend.config.UserAuthProvider;
import com.bit.backend.dtos.*;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.User;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.UserServiceI;
import com.bit.backend.services.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.security.Principal;

@RestController
public class UserController {
    private final UserService userService;
    private final UserServiceI userServiceI;
    private final UserAuthProvider userAuthProvider;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserServiceI userServiceI, UserAuthProvider userAuthProvider, UserRepository userRepository) {
        this.userService = userService;
        this.userServiceI = userServiceI;
        this.userAuthProvider = userAuthProvider;
        this.userRepository = userRepository;
    }

    //employee login credentials save in the user_app table
    @PostMapping("/employee-register")
    public ResponseEntity<UserDto> empRegister(@RequestBody SignUpDto signUpDto) {
        SignUpDto employeeSignUp = new SignUpDto(
                signUpDto.id(),
                signUpDto.firstName(),
                signUpDto.lastName(),
                signUpDto.login(),
                signUpDto.password(),
                "EMPLOYEE",                     // set role here
                signUpDto.employeeId(),
                null                            // no customerId for employee
        );
        UserDto user = userServiceI.register(employeeSignUp);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }

    //get saved user(employee) data to user profile form
    @GetMapping("/user-profile")
    public ResponseEntity<UserDto> getUserProfile(Principal principal) {
        try {
            System.out.println(" user controller==============");
//            UserDto userDto = userServiceI.getUserById(id);
//            UserDto dto = new UserDto();
//            String login = principal.getName();
            UserDto loggedInUser = (UserDto) ((Authentication) principal).getPrincipal();
            String login = loggedInUser.getLogin();
            System.out.println("Logged in" +
                    " user: " + principal.getName());
//            User user = userRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("User not found"));

            UserDto userDto = userServiceI.getUserProfile(login);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update user profile form
    @PutMapping("/user-profile/{id}")
    public ResponseEntity<UserDto> updateUserData(@PathVariable long id, @RequestPart("userProfileForm") UserDto userDto, @RequestPart("image") MultipartFile file
    ){
        System.out.println(" user controller edit==============");
        try {
            if (file != null && !file.isEmpty()) {
                userDto.setImage(file.getBytes());
                userDto.setImageName(file.getOriginalFilename());
                userDto.setImageType(file.getContentType());
            }

            UserDto updatedUser = userServiceI.updateUserProfile(id, userDto);
            return ResponseEntity.ok(updatedUser);

        } catch (Exception e) {
            throw new AppException("Request Failed with Error:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
