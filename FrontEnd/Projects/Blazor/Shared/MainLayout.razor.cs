using Blazor.Users.Services;
using Microsoft.AspNetCore.Components;

namespace Blazor.Shared;

public partial class MainLayout
{
    [Inject]
    public IUsersService users_service { get; set; }

    [Inject]
    public NavigationManager navigation_manager { get; set; }

    public bool is_sidebar_expanded;

    protected override async void OnInitialized()
    {
        await this.users_service.initialize();
    }
}