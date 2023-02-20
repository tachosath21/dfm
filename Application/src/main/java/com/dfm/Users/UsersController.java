package com.dfm.Users;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dfm.Users.Entities.UserRoleEntity;
import com.dfm.Users.Entities.Dto.UserRequestDto;
import com.dfm.Users.Entities.Dto.UserResponseDto;
import com.dfm.Users.Entities.Dto.UserRoleResponseDto;
import com.dfm.Users.Services.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Users")
@RequiredArgsConstructor
public class UsersController
{
    private final UsersService users_service;

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserResponseDto authenticate(@RequestBody UserRequestDto user_request_dto)
    {
        var user_response_dto = this.users_service.authenticate(user_request_dto.getUser());

        return user_response_dto;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserRoleResponseDto readByUserRole(@RequestParam UserRoleEntity user_role)
    {
        var user_role_response_dto = this.users_service.readByUserRole(user_role);

        return user_role_response_dto;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserResponseDto register(@RequestBody UserRequestDto user_request_dto)
    {
        var user_response_dto = this.users_service.register(user_request_dto.getUser());
        
        return user_response_dto;
    }   
}
