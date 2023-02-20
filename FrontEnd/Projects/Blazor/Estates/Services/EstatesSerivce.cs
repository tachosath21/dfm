using Blazor.Estates.Entities;
using Blazor.Estates.Entities.Dto;
using Blazor.Services;
using Blazor.Users.Services;

namespace Blazor.Estates.Services;

public class EstatesService : IEstatesService
{
    private readonly IHttpService http_service;
    private readonly IUsersService users_service;

    public EstatesService(IHttpService http_service, IUsersService users_service)
    {
        this.http_service = http_service;
        this.users_service = users_service;
    }

    public async Task<EstateResponseDto> create(EstateEntity estate_entity)
    {
        estate_entity.seller = this.users_service.user_entity;

        var estate_request_dto = new EstateRequestDto()
        {
            estate = estate_entity
        };
        
        return await this.http_service.post<EstateResponseDto>("api/Estates", estate_request_dto);
    }

    public async Task<int> getIdAsync(EstateEntity estate_entity) =>
        await this.http_service.get<int>("api/Estates/getId?description=" + estate_entity.description);

    public async Task<EstateResponseDto> readAsync(int id) =>
        await this.http_service.get<EstateResponseDto>("api/Estates/" + id);

    public async Task<EstatesResponseDto> readAllAsync() =>
        await this.http_service.get<EstatesResponseDto>("api/Estates");
    
    public async Task<EstateResponseDto> update(int id, EstateEntity estate_entity)
    {
        var estate_request_dto = new EstateRequestDto()
        {
            estate = estate_entity
        };
        
        return await this.http_service.put<EstateResponseDto>("api/Estates/" + id, estate_request_dto);
    }

    public async Task<EstateResponseDto> deleteAsync(EstateEntity estate_entity)
    {
        var id = await this.getIdAsync(estate_entity);

        var estate_request_dto = new EstateRequestDto()
        {
            estate = estate_entity
        };

        return await this.http_service.delete<EstateResponseDto>("api/Estates/" + id, estate_request_dto);
    }
}