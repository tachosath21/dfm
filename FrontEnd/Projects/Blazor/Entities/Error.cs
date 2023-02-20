namespace Blazor.Entities;

public abstract class ErrorEntity
{
    public int severity { get; set; }
    public string? message { get; set; }
}
