using System.Net;
using System.Net.Http.Headers;
using System.Net.Http.Json;
using System.Text;
using System.Text.Json;
using Blazor.Users.Entities;
using Microsoft.AspNetCore.Components;

namespace Blazor.Services;

public class HttpService : IHttpService
{
    private readonly HttpClient http_client;
    private readonly NavigationManager navigation_manager;
    private readonly ILocalStorageService local_storage_service;

    public HttpService(HttpClient http_client, NavigationManager navigation_manager, ILocalStorageService local_storage_service)
    {
        this.http_client = http_client;
        this.navigation_manager = navigation_manager;
        this.local_storage_service = local_storage_service;
    }

    public async Task<T> post<T>(string uri, object value)
    {
        var request = new HttpRequestMessage(HttpMethod.Post, uri)
        {
            Content = new StringContent(JsonSerializer.Serialize(value), Encoding.UTF8, "application/json")
        };

        return await sendRequest<T>(request);
    }

    public async Task<T> get<T>(string uri)
    {
        var request = new HttpRequestMessage(HttpMethod.Get, uri);
        
        return await sendRequest<T>(request);
    }

    public async Task<T> get<T>(string uri, object value)
    {
        var request = new HttpRequestMessage(HttpMethod.Get, uri)
        {
            Content = new StringContent(JsonSerializer.Serialize(value), Encoding.UTF8, "application/json")
        };
        
        return await sendRequest<T>(request);
    }

    public async Task<T> put<T>(string uri, object value)
    {
        var request = new HttpRequestMessage(HttpMethod.Put, uri)
        {
            Content = new StringContent(JsonSerializer.Serialize(value), Encoding.UTF8, "application/json")
        };

        return await sendRequest<T>(request);
    }

    public async Task<T> delete<T>(string uri, object value)
    {
        var request = new HttpRequestMessage(HttpMethod.Delete, uri)
        {
            Content = new StringContent(JsonSerializer.Serialize(value), Encoding.UTF8, "application/json")
        };

        return await sendRequest<T>(request);
    }

    private async Task<T> sendRequest<T>(HttpRequestMessage request)
    {
        var user_entity = await this.local_storage_service.getItem<UserEntity>("user");
        var token = await this.local_storage_service.getItem<string>("token");
        
        var is_api_url = !request.RequestUri.IsAbsoluteUri;

        if (user_entity is not null && token is not null && is_api_url)
            request.Headers.Authorization = new AuthenticationHeaderValue("Bearer", token);

        using var response = await this.http_client.SendAsync(request);

        if (response.StatusCode == HttpStatusCode.Unauthorized)
        {
            this.navigation_manager.NavigateTo("logout");
            return default;
        }

        if (!response.IsSuccessStatusCode)
        {
            var error = await response.Content.ReadFromJsonAsync<Dictionary<string, string>>();
            throw new Exception(error["message"]);
        }

        return await response.Content.ReadFromJsonAsync<T>();
    }
}
