package com.dfm.Users.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dfm.Config.JwtService;
import com.dfm.Entities.ErrorEntity;
import com.dfm.Users.IUsersRepository;
import com.dfm.Users.Entities.UserEntity;
import com.dfm.Users.Entities.UserModel;
import com.dfm.Users.Entities.UserRoleEntity;
import com.dfm.Users.Entities.Dto.UserResponseDto;
import com.dfm.Users.Entities.Dto.UserRoleResponseDto;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;

import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService
{
    private final ModelMapper model_mapper;
    private final IUsersRepository users_repository;
    private final PasswordEncoder password_encoder;
    private final JwtService jwt_service;
    private final AuthenticationManager authentication_manager;
   

    public UserResponseDto authenticate(UserEntity user_entity)
    {
        try
        {
            var authentication = this.authentication_manager.authenticate(new UsernamePasswordAuthenticationToken(user_entity.getEmail(), user_entity.getPassword()));
            
            var user_model = (UserModel) authentication.getPrincipal();

            var claims = new HashMap<String, Object>();
            claims.put("user_role", user_model.getUser_role().name());

            var token = this.jwt_service.generateToken(claims, user_model);
            
            var user_response_dto = UserResponseDto
                .builder()
                .processed(true)
                .user(this.model_mapper.map(user_model, UserEntity.class))
                .token(token)
                .build();

            return user_response_dto;
        }
        catch (Exception e)
        {
            var user_response_dto = UserResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem Logging In")
                    .build())
                .build();

            return user_response_dto;
        }
    }

    public UserRoleResponseDto readByUserRole(UserRoleEntity user_role_entity)
    {
        try
        {
            var user_models_optional = this.users_repository.findByUserRole(user_role_entity);
            
            if (!user_models_optional.isPresent())
                throw new Exception("Roles not found");

            var user_models = user_models_optional.get();
            
            var user_entities = new ArrayList<UserEntity>();
            
            user_models.forEach(user_model ->
                user_entities.add(this.model_mapper.map(user_model, UserEntity.class)));
            
            var estates_response_dto = UserRoleResponseDto
                .builder()
                .processed(true)
                .users(user_entities)
                .build();

            return estates_response_dto;
        }
        catch (Exception e)
        {
            var user_role_response_dto = UserRoleResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem Logging In")
                    .build())
                .build();

            return user_role_response_dto;
        }
    }
    
    public UserResponseDto register(UserEntity user_entity)
    {
        try
        {
            var new_user_model = UserModel
                .builder()
                .firstname(user_entity.getFirstname())
                .lastname(user_entity.getLastname())
                .email(user_entity.getEmail())
                .password(this.password_encoder.encode(user_entity.getPassword()))
                .user_role(user_entity.getUser_role())
                .build();
            
            var saved_user_model = this.users_repository.save(new_user_model);
            
            var user_response_dto = UserResponseDto
                .builder()
                .processed(true)
                .user(this.model_mapper.map(saved_user_model, UserEntity.class))
                .build();

            return user_response_dto;
        }
        catch (Exception e)
        {
            var user_response_dto = UserResponseDto
                .builder()
                .processed(false)
                .error(ErrorEntity
                    .builder()
                    .severity(1)
                    .message("Problem saving new entry")
                    .build())
                .build();

            return user_response_dto;
        }
    }
}
