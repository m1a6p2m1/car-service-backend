package com.bit.backend.services.impl;

import com.bit.backend.config.RSADecryptor;
import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.PasswordResetMapper;
import com.bit.backend.mappers.UserMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.NotificationServiceI;
import com.bit.backend.services.UserServiceI;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceI {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final PrivilegeGroupRepository privilegeGroupRepository;
    private final PasswordResetMapper passwordResetMapper;
    private final NotificationServiceI notificationServiceI;
    private final PasswordResetRepository passwordResetRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository,
                       PasswordEncoder passwordEncoder, UserMapper userMapper, PrivilegeGroupRepository privilegeGroupRepository,
                       PasswordResetMapper passwordResetMapper, NotificationServiceI notificationServiceI,
                       PasswordResetRepository passwordResetRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.privilegeGroupRepository = privilegeGroupRepository;
        this.passwordResetMapper = passwordResetMapper;
        this.notificationServiceI = notificationServiceI;
        this.passwordResetRepository = passwordResetRepository;
    }

    @Override
    public UserDto login(CredentialsDto credentialsDto) throws Exception {
        logger.debug("Entering in login Method...");
        User user = userRepository.findByLogin(credentialsDto.login()).orElseThrow(() -> new AppException("Unknown User", HttpStatus.NOT_FOUND));
        String decryptedPassword = RSADecryptor.decrypt(new String(credentialsDto.password()));
        try {
            if (passwordEncoder.matches(CharBuffer.wrap(decryptedPassword.toCharArray()), user.getPassword())) {
                UserDto userDto = userMapper.toUserDto(user);

                // Add employee image details here:
                if (user.getEmployee() != null) {
                    userDto.setImage(user.getEmployee().getImage());
                    userDto.setImageName(user.getEmployee().getImageName());
                    userDto.setImageType(user.getEmployee().getImageType());
                    userDto.setImageType(user.getEmployee().getEmail());
                    userDto.setImageType(user.getEmployee().getPhoneNumber());
                    userDto.setJobTitle(user.getEmployee().getJobTitle());
                }
                return userDto;
            }
        } catch (Exception exception) {
            throw new AppException("Error Occurred", HttpStatus.BAD_REQUEST);
        }


        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    //user register with login credentials
    //(employee login credentials saved using employee register form)
    @Override
    public UserDto register(SignUpDto signUpDto) {
        Optional<User> oUser = userRepository.findByLogin(signUpDto.login());

        if (oUser.isPresent()) {
            throw new AppException("User Already Exists", HttpStatus.BAD_REQUEST);
        }
        User user = userMapper.signUpToUser(signUpDto);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));

        user.setRole(signUpDto.role());

        // Associate based on role
        if ("EMPLOYEE".equalsIgnoreCase(signUpDto.role()) && signUpDto.employeeId() != null) {
            EmployeeEntity employee = employeeRepository.findById(signUpDto.employeeId())
                    .orElseThrow(() -> new AppException("Employee Not Found", HttpStatus.NOT_FOUND));
            user.setEmployee(employee);
        }

        if ("CUSTOMER".equalsIgnoreCase(signUpDto.role()) && signUpDto.customerId() != null) {
            CustomerEntity customer = customerRepository.findById(signUpDto.customerId())
                    .orElseThrow(() -> new AppException("Customer Not Found", HttpStatus.NOT_FOUND));
            user.setCustomer(customer);
        }

        if ("CUSTOMER".equalsIgnoreCase(signUpDto.role())) {
            user.setUniqueCusNo(generateCustomerNumber(user));
        }

        // get image from employee form to show in the profile
        User savedUser = userRepository.save(user);
        UserDto userDto = userMapper.toUserDto(savedUser);

        // set default privilege group

        if (savedUser.getRole().equals("CUSTOMER")) {
            // SET DEFAULT PRIVILEGE GROUP
            int id = savedUser.getId().intValue();
            Optional<List<PrivilegeGroup>> privilegeGroupList = privilegeGroupRepository.findByDefaultValue(true);

            if (privilegeGroupList.isPresent()) {
                int authGroupId = privilegeGroupList.get().get(0).getId().intValue();
                privilegeGroupRepository.setAuthGroupToCustomer(authGroupId, id);
            }

        }

        if (savedUser.getEmployee() != null) {
            userDto.setImage(savedUser.getEmployee().getImage());
            userDto.setImageName(savedUser.getEmployee().getImageName());
            userDto.setImageType(savedUser.getEmployee().getImageType());
            userDto.setImageType(savedUser.getEmployee().getImageType());
            userDto.setEmail(savedUser.getEmail());
            userDto.setContactNumber(savedUser.getContactNumber());
//            userDto.setImageType(savedUser.getEmployee().getEmail());
//            userDto.setImageType(savedUser.getEmployee().getPhoneNumber());
        }

        return userDto;
    }

    @Override
    public List<Integer> getAuthIds(long userId) {
        Optional<List<Integer>> optionalAuthIdLists = userRepository.findAuthIdsByUserId(userId);
        List<Integer> authIdLists = optionalAuthIdLists.get();

        return authIdLists;
    }

    @Override
    public SystemPrivilegeListDto getSystemPrivileges() {
        List<Tuple> tupleAvailableSystemPrivilegeLists = userRepository.getAvailableSystemPrivileges();
        List<Tuple> tupleAssignedSystemPrivilegeLists = userRepository.getAssignedSystemPrivileges();
        SystemPrivilegeListDto systemPrivilegeListDto = new SystemPrivilegeListDto();

        List<SystemPrivilegeDto> availableSystemPrivilegeLists = tupleAvailableSystemPrivilegeLists.stream().map(t -> {
            SystemPrivilegeDto systemPrivilegeDto = new SystemPrivilegeDto();
            systemPrivilegeDto.setId(t.get(0, Integer.class));
            systemPrivilegeDto.setDescription(t.get(1, String.class));
            return systemPrivilegeDto;
        }).collect(Collectors.toList());

        List<SystemPrivilegeDto> assignSystemPrivilegeLists = tupleAssignedSystemPrivilegeLists.stream().map(t -> {
            SystemPrivilegeDto systemPrivilegeDto = new SystemPrivilegeDto();
            systemPrivilegeDto.setId(t.get(0, Integer.class));
            systemPrivilegeDto.setDescription(t.get(1, String.class));
            return systemPrivilegeDto;
        }).collect(Collectors.toList());

        systemPrivilegeListDto.setSourcePrivileges(availableSystemPrivilegeLists);
        systemPrivilegeListDto.setTargetPrivileges(assignSystemPrivilegeLists);

        return systemPrivilegeListDto;
    }

    @Override
    public List<Integer> setSystemPrivileges(SystemPrivilegeListDto systemPrivilegeListDto) {

        return null;
    }

    //get saved user data to the user profile form
    @Override
    public UserDto getUserProfile(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setContactNumber(user.getContactNumber());

        if (user.getEmployee() != null) {
            dto.setImage(user.getEmployee().getImage());
            dto.setImageType(user.getEmployee().getImageType());
            dto.setImageName(user.getEmployee().getImageName());

        }

        return dto;
    }
    @Override
    public UserDto updateUserProfile(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update User fields
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        // Update related Employee fields
        if (user.getEmployee() != null) {
            EmployeeEntity employee = user.getEmployee();
            employee.setEmail(userDto.getEmail());
            employee.setPhoneNumber(userDto.getContactNumber());
            employee.setImage(userDto.getImage());
            employee.setImageName(userDto.getImageName());
            employee.setImageType(userDto.getImageType());

            employeeRepository.save(employee);
        }

        User savedUser = userRepository.save(user);
        UserDto responseUserDto = userMapper.toUserDto(savedUser);
        return responseUserDto;
    }

    @Override
    public PasswordResetDto forgotPassword(PasswordResetDto passwordResetDto) {

        // check if the user from relevant mail exist (Make user fill profile with email)
        // check in Login [TODO]

        passwordResetDto.setToken(UUID.randomUUID().toString());
        passwordResetDto.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        String resetLink = "http://localhost:4200/reset-password?token=" + passwordResetDto.getToken();

        boolean isPasswordResetLinkSent = this.notificationServiceI.sendPasswordResetLink(passwordResetDto, resetLink);

        PasswordResetDto savedDto = new PasswordResetDto();
        if (isPasswordResetLinkSent == true) {
            PasswordResetEntity passwordResetEntity = this.passwordResetMapper.toPasswordResetEntity(passwordResetDto);
            PasswordResetEntity savedEntity = passwordResetRepository.save(passwordResetEntity);
            savedDto  = passwordResetMapper.toPasswordResetDto(savedEntity);
        }

        return savedDto;
    }

    public boolean isTokenValid(String token) {
        PasswordResetEntity resetToken = passwordResetRepository.findByToken(token);
        return resetToken != null && resetToken.getExpiryDate().isAfter(LocalDateTime.now());
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetEntity resetToken = passwordResetRepository.findByToken(token);
        if (resetToken != null && isTokenValid(token)) {
//            [TODO]
            // find user by email and update password (loginRepository.findByEmail())
            // update user password logic
            // userRepo.updatePassword(resetToken.getUserEmail(), newPassword);
            passwordResetRepository.delete(resetToken); // remove token after reset
        } else {
            throw new RuntimeException("Invalid or expired token");
        }
    }

    //get customer names list into the vehiclesForm customer name field
    @Override
    public List<Map<String, Object>> getVehicleRegUsers() { return userRepository.getVehicleRegUsersList();}

    public String generateCustomerNumber(User user) {
        String unique;
        do {
            String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String uniquePart = String.format("%03d", new Random().nextInt(1000)); // 000 - 999

            unique = "CUS" + datePart + uniquePart;
        } while (userRepository.existsByUniqueCusNo(unique));
          return unique;

    }

    //get customer details when enter phone number in appointment form
    @Override
    public UserDto getCustomerByPhone(String contactNumber) {
        User customerDetails = userRepository.findByContactNumber(contactNumber)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        return userMapper.toUserDto(customerDetails);

    }
}
