using Blazor.Services;
using Blazor.Users.Entities;
using Blazor.Users.Entities.Dto;
using Microsoft.AspNetCore.Components;

namespace Blazor.Users.Services;

public class UsersService : IUsersService
{
    private readonly IHttpService http_service;
    private readonly NavigationManager navigation_manager;
    private readonly ILocalStorageService local_storage_service;

    public UserEntity user_entity { get; private set; }
    public string token { get; private set; }

    public UsersService(IHttpService http_service, NavigationManager navigation_manager, ILocalStorageService local_storage_service)
    {
        this.http_service = http_service;
        this.navigation_manager = navigation_manager;
        this.local_storage_service = local_storage_service;
    }

    public async Task initialize()
    {
        this.user_entity = await this.local_storage_service.getItem<UserEntity>("user");
        this.token = await this.local_storage_service.getItem<string>("token");
    }

    public async Task authenticate(UserEntity user_entity)
    {
        var user_request_dto = new UserRequestDto()
        {
            user = user_entity
        };

        var user_response_dto = await this.http_service.post<UserResponseDto>("api/Users/authenticate", user_request_dto);

        await this.local_storage_service.setItem("user", user_response_dto.user);
        await this.local_storage_service.setItem("token", user_response_dto.token);

        this.navigation_manager.NavigateTo("", true);
    }

    public async Task<UserRoleResponseDto> readByUserRoleAsync(UserRoleEntity user_role_entity)
    {
        var user_role_response_dto = await this.http_service.get<UserRoleResponseDto>($"api/Users?user_role={user_role_entity}");
        
        return user_role_response_dto;
    }

    public async Task register(UserEntity user_entity)
    {
        var user_request_dto = new UserRequestDto()
        {
            user = user_entity
        };

        await this.http_service.post<UserResponseDto>("api/Users/register", user_request_dto);
    }

    public async Task logout()
    {
        this.user_entity = null;
        this.token = null;
        
        await this.local_storage_service.removeItem("user");
        await this.local_storage_service.removeItem("token");

        this.navigation_manager.NavigateTo("Users/authenticate");
    }
}
