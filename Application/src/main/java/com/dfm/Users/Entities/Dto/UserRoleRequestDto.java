package com.dfm.Users.Entities.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.dfm.Users.Entities.UserRoleEntity;

import jakarta.annotation.Nonnull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequestDto
{
    @Nonnull
    public UserRoleEntity user_role;
}
