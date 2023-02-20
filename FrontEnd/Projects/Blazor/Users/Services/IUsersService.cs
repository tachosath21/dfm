using Blazor.Users.Entities;
using Blazor.Users.Entities.Dto;

namespace Blazor.Users.Services;

public interface IUsersService
{
    public UserEntity user_entity { get; }
    public string token { get; }

    public Task initialize();
    public Task authenticate(UserEntity user_entity);
    public Task<UserRoleResponseDto> readByUserRoleAsync(UserRoleEntity user_role_entity);
    public Task register(UserEntity user_entity);
    public Task logout();
}
