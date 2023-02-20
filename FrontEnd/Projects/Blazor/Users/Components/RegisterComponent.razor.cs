using Blazor.Helpers;
using Blazor.Users.Entities;
using Blazor.Users.Services;
using Microsoft.AspNetCore.Components;

namespace Blazor.Users.Components;

public partial class RegisterComponent : ComponentBase
{
    [Inject]
    IUsersService users_service { get; set; }

    [Inject]
    NavigationManager navigation_manager { get; set; }

    private readonly UserEntity user_entity = new ();
    private readonly IEnumerable<UserRoleEntity> user_role_entities = new List<UserRoleEntity>()
    {
        UserRoleEntity.NOTARY,
        UserRoleEntity.SELLER,
        UserRoleEntity.BUYER
    };
    private bool loading;
    private string error;

    protected override void OnInitialized()
    {
        if (this.users_service.user_entity != null)
            this.navigation_manager.NavigateTo("");
    }

    private async void register()
    {
        this.loading = true;

        try
        {
            await this.users_service.register(user_entity);

            var returnUrl = this.navigation_manager.QueryString("returnUrl") ?? "/";
            
            this.navigation_manager.NavigateTo(returnUrl);
        }
        catch (Exception ex)
        {
            this.error = ex.Message;
            this.loading = false;

            this.StateHasChanged();
        }
    }
}
