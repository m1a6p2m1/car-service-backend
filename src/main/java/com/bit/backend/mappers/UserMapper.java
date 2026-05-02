package com.bit.backend.mappers;

import com.bit.backend.dtos.AuthDto;
import com.bit.backend.dtos.SignUpDto;
import com.bit.backend.dtos.UserDto;
import com.bit.backend.entities.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {


    @Mapping(source = "employee.empNumber", target = "employeeId") //customer and employee login
//    @Mapping(source = "customer.cusId", target = "customerId")
    UserDto toUserDto(User user);

    @Mapping(target = "employee", ignore = true) // Will be set manually in service(customer and employee login)
//    @Mapping(target = "customer", ignore = true) // Will be set manually in service(customer and employee login)
    @Mapping(target = "id", ignore = true)       // New user(customer and employee login)
    @Mapping(target = "password", ignore = true) // Set manually after encoding
    @Mapping(target = "email", source = "email")
    @Mapping(target = "contactNumber", source = "contactNumber")
    User signUpToUser(SignUpDto signUpDto);

    AuthDto toAuthDto(Integer authId);

//    List<AuthDto> toAuthDto(List<Integer> authIds);
}
