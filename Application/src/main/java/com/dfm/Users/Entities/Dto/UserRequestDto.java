package com.dfm.Users.Entities.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.dfm.Users.Entities.UserEntity;

import jakarta.annotation.Nonnull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto
{
    @Nonnull
    private UserEntity user;
}
