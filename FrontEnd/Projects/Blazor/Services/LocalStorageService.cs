using System.Text.Json;
using Microsoft.JSInterop;

namespace Blazor.Services;

public class LocalStorageService : ILocalStorageService
{
    private readonly IJSRuntime js_runtime;

    public LocalStorageService(IJSRuntime js_runtime) =>
        this.js_runtime = js_runtime;
    
    public async Task<T> getItem<T>(string key)
    {
        var json = await this.js_runtime.InvokeAsync<string>("localStorage.getItem", key);

        if (json == null)
            return default;

        return JsonSerializer.Deserialize<T>(json);
    }

    public async Task setItem<T>(string key, T value) =>
        await this.js_runtime.InvokeVoidAsync("localStorage.setItem", key, JsonSerializer.Serialize(value));
    
    public async Task removeItem(string key) =>
        await this.js_runtime.InvokeVoidAsync("localStorage.removeItem", key);
}
