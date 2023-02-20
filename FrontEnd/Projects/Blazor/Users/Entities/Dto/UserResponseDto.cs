using Blazor.Entities.Dto;

namespace Blazor.Users.Entities.Dto;

public class UserResponseDto : BaseResponseDto
{
   public UserEntity? user { get; set; }
   public string? token { get; set; }
}
