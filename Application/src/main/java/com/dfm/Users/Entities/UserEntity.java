package com.dfm.Users.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.annotation.Nonnull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity
{
    @Nonnull
    private String email;
    @Nonnull
    private String password;
    private String firstname;
    private String lastname;
    @Nonnull
    private UserRoleEntity user_role;
}
