namespace Blazor.Entities.Dto;

public abstract class BaseResponseDto
{
    public bool processed { get; set; }
    public ErrorEntity? error { get; set; }
}
