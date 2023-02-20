using Blazor.Entities.Dto;

namespace Blazor.Estates.Entities.Dto;

public class EstateResponseDto : BaseResponseDto
{
    public EstateEntity estate { get; set; }
}
