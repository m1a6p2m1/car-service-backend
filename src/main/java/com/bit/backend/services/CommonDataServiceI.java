package com.bit.backend.services;

import com.bit.backend.dtos.CommonDataDto;
import com.bit.backend.dtos.CommonDataListDto;

import java.util.List;
import java.util.Map;

public interface CommonDataServiceI {
    List<CommonDataDto> getAvailablePrivilegesByGroupID(int id);
    List<CommonDataDto> getAssignedPrivilegesByGroupID(int id);
    CommonDataListDto saveData(int id, CommonDataListDto commonDataListDto);
    List<CommonDataDto> getAvailableUsersByGroupID(int id);
    List<CommonDataDto> getAssignedUsersByGroupID(int id);
    CommonDataListDto saveGroupUserData(int id, CommonDataListDto commonDataListDto);
    List<Map<String, Object>> getCommonUserServices();
}
