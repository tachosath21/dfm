using Blazor.Transfers.Entities;
using Blazor.Transfers.Entities.Dto;

namespace Blazor.Transfers.Services;

public interface ITransfersService
{
    public Task<TransferResponseDto> create(TransferEntity transfer_entity);
    public Task<TransferResponseDto> readAsync(int id);
    public Task<TransfersResponseDto> readAllAsync();
    public Task<int> getIdAsync(TransferEntity transfer_entity);
    public Task<TransferResponseDto> update(int id, TransferEntity transfer_entity);
    public Task<TransferResponseDto> deleteAsync(TransferEntity transfer_entity);
}