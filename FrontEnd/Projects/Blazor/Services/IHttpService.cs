namespace Blazor.Services;

public interface IHttpService
{
    public Task<T> post<T>(string uri, object value);
    public Task<T> get<T>(string uri);
    public Task<T> get<T>(string uri, object value);
    public Task<T> put<T>(string uri, object value);
    public Task<T> delete<T>(string uri, object value);
}
