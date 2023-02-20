using Blazor.Entities.Dto;

namespace Blazor.Estates.Entities.Dto;

public class EstatesResponseDto : BaseResponseDto
{
    public IEnumerable<EstateEntity> estates { get; set; }
}
