package com.bit.backend.controllers;

import com.bit.backend.dtos.CommonDataDto;
import com.bit.backend.dtos.CommonDataListDto;
import com.bit.backend.entities.CommonDataEntity;
import com.bit.backend.services.CommonDataServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common-data-service")
public class CommonServiceController {

    private final CommonDataServiceI commonDataServiceI;

    public CommonServiceController(CommonDataServiceI commonDataServiceI) {
        this.commonDataServiceI = commonDataServiceI;
    }

    @GetMapping("/available-privileges/{id}")
    public List<CommonDataDto> getAvailablePrivilegesByGroupID(@PathVariable int id) {
        List<CommonDataDto> availablePrivileges = commonDataServiceI.getAvailablePrivilegesByGroupID(id);
        return availablePrivileges;
    }

    @GetMapping("/assigned-privileges/{id}")
    public List<CommonDataDto> getAssignedPrivilegesByGroupID(@PathVariable int id) {
        List<CommonDataDto> availablePrivileges = commonDataServiceI.getAssignedPrivilegesByGroupID(id);
        return availablePrivileges;
    }

    @PostMapping("/group-privileges/{id}")
    public CommonDataListDto saveData(@RequestBody CommonDataListDto commonDataListDto, @PathVariable int id) {
        CommonDataListDto savedData = commonDataServiceI.saveData(id, commonDataListDto);
        return savedData;
    }

    @GetMapping("/group-available-users/{id}")
    public List<CommonDataDto> getAvailableUsersByGroupID(@PathVariable int id) {
        List<CommonDataDto> availablePrivileges = commonDataServiceI.getAvailableUsersByGroupID(id);
        return availablePrivileges;
    }

    @GetMapping("/group-assigned-users/{id}")
    public List<CommonDataDto> getAssignedUsersByGroupID(@PathVariable int id) {
        List<CommonDataDto> availablePrivileges = commonDataServiceI.getAssignedUsersByGroupID(id);
        return availablePrivileges;
    }

    @PostMapping("/privilege-group-users/{id}")
    public CommonDataListDto saveGroupUserData(@RequestBody CommonDataListDto commonDataListDto, @PathVariable int id) {
        CommonDataListDto savedData = commonDataServiceI.saveGroupUserData(id, commonDataListDto);
        return savedData;
    }
}
