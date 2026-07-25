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
import java.util.List;
import java.util.Map;

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
                signUpDto.nic(),
                signUpDto.email(),
                signUpDto.contactNumber(),
                signUpDto.address(),
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

    @PostMapping("/customer-register")
    public ResponseEntity<UserDto> cusRegister(@RequestBody SignUpDto signUpDto) {
        System.out.println("*******customer-register*****");
        SignUpDto customerSignUp = new SignUpDto(
                signUpDto.id(),
                signUpDto.firstName(),
                signUpDto.lastName(),
                signUpDto.nic(),
                signUpDto.email(),
                signUpDto.contactNumber(),
                signUpDto.address(),
                signUpDto.login(),
                signUpDto.password(),
                "CUSTOMER",                     // set role here
                null,                // no employeeId for employee
                signUpDto.customerId()
        );
        UserDto user = userServiceI.register(customerSignUp);
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

    //get customer names list into the vehiclesForm customer name field
    @GetMapping("/register/users")
    public ResponseEntity<List<Map<String, Object>>> getVehicleRegUsers(){
        try {
            List<Map<String, Object>> vehicleRegUserList = userServiceI.getVehicleRegUsers();
            return ResponseEntity.ok(vehicleRegUserList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get customer details when enter phone number in appointment form
    @GetMapping("/user/by-phone/{contactNumber}")
    public ResponseEntity<UserDto> getCustomerByPhone(@PathVariable String contactNumber){
        UserDto userDto = userServiceI.getCustomerByPhone(contactNumber);
        return ResponseEntity.ok(userDto);
    }

    //check phoneNumber is already exist
    @GetMapping("/check-contact")
    public ResponseEntity<Boolean> checkContactNumber(@RequestParam String contactNumber){
        boolean exists = userServiceI.isContactNumberIsExists(contactNumber);
        return ResponseEntity.ok(exists);
    }

    //edit employee user credentials
    @GetMapping("/employee-login/get-credentials/{employeeId}")
    public ResponseEntity<EmployeeCredentialDto> getEmployeeLogin(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                userServiceI.getEmployeeLogin(employeeId));
    }

    @PutMapping("/employee-login/edit-credentials/{employeeId}")
    public ResponseEntity<EmployeeCredentialDto> updateEmployeeLogin(@PathVariable long employeeId, @RequestBody EmployeeCredentialDto dto){
        return ResponseEntity.ok(userServiceI.updateEmployeeLogin(employeeId, dto));
    }

    //customer form get user credentials
    @GetMapping("/customer-login/get-credentials/{customerId}")
    public ResponseEntity<CustomerCredentialDto> getCustomerLogin(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                userServiceI.getCustomerLogin(customerId));
    }

    @PutMapping("/customer-login/edit-credentials/{customerId}")
    public ResponseEntity<CustomerCredentialDto> updateEmployeeLogin(@PathVariable long customerId, @RequestBody CustomerCredentialDto dto){
        return ResponseEntity.ok(userServiceI.updateCustomerLogin(customerId, dto));
    }

}
