package com.bit.backend.controllers;

import com.bit.backend.dtos.PrivilegeGroupDto;
import com.bit.backend.dtos.SignUpDto;
import com.bit.backend.dtos.SystemPrivilegeListDto;
import com.bit.backend.dtos.UserDto;
import com.bit.backend.entities.PrivilegeGroup;
import com.bit.backend.services.PrivilegeGroupServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class PrivilegeGroupController {

    private final PrivilegeGroupServiceI privilegeGroupServiceI;

    public PrivilegeGroupController(PrivilegeGroupServiceI privilegeGroupServiceI) {
        this.privilegeGroupServiceI = privilegeGroupServiceI;
    }

    @GetMapping("/privilege-groups")
    public ResponseEntity<List<PrivilegeGroupDto>> getPrivilegeGroups() {
        List<PrivilegeGroupDto> privilegeGroupDtos = privilegeGroupServiceI.getPrivilegeGroups();
        return ResponseEntity.ok(privilegeGroupDtos);
    }

    @PostMapping("/privilege-groups")
    public ResponseEntity<PrivilegeGroupDto> addPrivilegeGroup(@RequestBody PrivilegeGroupDto privilegeGroupDto) {
        PrivilegeGroupDto privilegeGroupDtoResponse = privilegeGroupServiceI.addPrivilegeGroup(privilegeGroupDto);
        return ResponseEntity.created(URI.create("/privilege-groups/" + privilegeGroupDtoResponse.getId())).body(privilegeGroupDtoResponse);
    }

    @PutMapping("/privilege-groups/{id}")
    public ResponseEntity<PrivilegeGroupDto> updatePrivilegeGroup(@PathVariable long id, @RequestBody PrivilegeGroupDto privilegeGroupDto) {
        return ResponseEntity.ok(privilegeGroupServiceI.updatePrivilegeGroup(id, privilegeGroupDto));
    }

    @PutMapping("/privilege-groups/delete/{id}")
    public ResponseEntity<PrivilegeGroupDto> deletePrivilegeGroup(@PathVariable long id, @RequestBody PrivilegeGroupDto privilegeGroupDto) {
        return ResponseEntity.ok(privilegeGroupServiceI.deletePrivilegeGroup(id, privilegeGroupDto));
    }
}
