package com.bit.backend.controllers;

import com.bit.backend.dtos.SystemPrivilegeListDto;
import com.bit.backend.services.PrivilegeServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrivilegeController {

    private final PrivilegeServiceI privilegeServiceI;

    public PrivilegeController(PrivilegeServiceI privilegeServiceI) {
        this.privilegeServiceI = privilegeServiceI;
    }

    @PutMapping("/system-privileges")
    public ResponseEntity<List<Integer>> saveSystemPrivileges(@RequestBody SystemPrivilegeListDto systemPrivilegeListDto) {
        return ResponseEntity.ok(privilegeServiceI.setSystemPrivileges(systemPrivilegeListDto));
    }
}
