using Blazor.Entities.Dto;

namespace Blazor.Transfers.Entities.Dto;

public class TransfersResponseDto : BaseResponseDto
{
    public IEnumerable<TransferEntity> transfers { get; set; }
}
