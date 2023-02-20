package com.dfm.Users.Services;

import com.dfm.Users.Entities.UserEntity;
import com.dfm.Users.Entities.UserRoleEntity;
import com.dfm.Users.Entities.Dto.UserResponseDto;
import com.dfm.Users.Entities.Dto.UserRoleResponseDto;

public interface IUsersService
{
    public UserResponseDto authenticate(UserEntity user_entity);
    public UserRoleResponseDto readByUserRole(UserRoleEntity user_role_entity);
    public UserResponseDto register(UserEntity user_entity);
}
