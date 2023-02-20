package com.dfm.Users.Entities.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;

import com.dfm.Entities.Dto.BaseResponseDto;
import com.dfm.Users.Entities.UserEntity;

import jakarta.annotation.Nonnull;

import java.util.List;

@Data
@SuperBuilder
@Nonnull
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponseDto extends BaseResponseDto
{
    private List<UserEntity> users;
}
