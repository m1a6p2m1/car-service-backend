package com.bit.backend.mappers;

import com.bit.backend.dtos.PasswordResetDto;
import com.bit.backend.entities.PasswordResetEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PasswordResetMapper {

    PasswordResetDto toPasswordResetDto(PasswordResetEntity passwordResetEntity);
    PasswordResetEntity toPasswordResetEntity(PasswordResetDto passwordResetDto);
}
