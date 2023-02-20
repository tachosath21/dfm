using Blazor.Entities.Dto;

namespace Blazor.Transfers.Entities.Dto;

public class TransferResponseDto : BaseResponseDto
{
    public TransferEntity transfer { get; set; }
}
