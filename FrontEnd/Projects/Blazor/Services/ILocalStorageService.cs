namespace Blazor.Services;

public interface ILocalStorageService
{
    public Task<T> getItem<T>(string key);
    public Task setItem<T>(string key, T value);
    public Task removeItem(string key);
}
