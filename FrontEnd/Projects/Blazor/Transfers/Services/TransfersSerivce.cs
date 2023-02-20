using Blazor.Transfers.Entities;
using Blazor.Transfers.Entities.Dto;
using Blazor.Services;
using Blazor.Users.Services;

namespace Blazor.Transfers.Services;

public class TransfersService : ITransfersService
{
    private readonly IHttpService http_service;
    private readonly IUsersService users_service;

    public TransfersService(IHttpService http_service, IUsersService users_service)
    {
        this.http_service = http_service;
        this.users_service = users_service;
    }

    public async Task<TransferResponseDto> create(TransferEntity transfer_entity)
    {
        var transfer_request_dto = new TransferRequestDto()
        {
            transfer = transfer_entity
        };
        
        return await this.http_service.post<TransferResponseDto>("api/Transfers", transfer_request_dto);
    }

    public async Task<int> getIdAsync(TransferEntity transfer_entity) =>
        await this.http_service.get<int>($"api/Transfers/getId?buyer_email={transfer_entity.buyer.email}&estate_description={transfer_entity.estate.description}");

    public async Task<TransferResponseDto> readAsync(int id) =>
        await this.http_service.get<TransferResponseDto>("api/Transfers/" + id);

    public async Task<TransfersResponseDto> readAllAsync() =>
        await this.http_service.get<TransfersResponseDto>("api/Transfers");
    
    public async Task<TransferResponseDto> update(int id, TransferEntity transfer_entity)
    {
        var transfer_request_dto = new TransferRequestDto()
        {
            transfer = transfer_entity
        };
        
        return await this.http_service.put<TransferResponseDto>("api/Transfers/" + id, transfer_request_dto);
    }

    public async Task<TransferResponseDto> deleteAsync(TransferEntity transfer_entity)
    {
        var id = await this.getIdAsync(transfer_entity);

        var transfer_request_dto = new TransferRequestDto()
        {
            transfer = transfer_entity
        };

        return await this.http_service.delete<TransferResponseDto>("api/Transfers/" + id, transfer_request_dto);
    }
}