using Blazor.Estates.Entities;
using Blazor.Estates.Entities.Dto;

namespace Blazor.Estates.Services;

public interface IEstatesService
{
    public Task<EstateResponseDto> create(EstateEntity estate_entity);
    public Task<EstateResponseDto> readAsync(int id);
    public Task<EstatesResponseDto> readAllAsync();
    public Task<int> getIdAsync(EstateEntity estate_entity);
    public Task<EstateResponseDto> update(int id, EstateEntity estate_entity);
    public Task<EstateResponseDto> deleteAsync(EstateEntity estate_entity);
}