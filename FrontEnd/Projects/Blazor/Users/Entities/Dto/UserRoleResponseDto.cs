using Blazor.Entities.Dto;

namespace Blazor.Users.Entities.Dto;

public class UserRoleResponseDto : BaseResponseDto
{
   public IEnumerable<UserEntity> users { get; set; }
}
