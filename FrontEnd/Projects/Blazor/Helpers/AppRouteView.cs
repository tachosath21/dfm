using System.Net;
using Blazor.Users.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Rendering;

namespace Blazor.Helpers;

public class AppRouteView : RouteView
{
    [Inject]
    public NavigationManager navigation_manager { get; set; }

    [Inject]
    public IUsersService users_service { get; set; }

    protected override void Render(RenderTreeBuilder builder)
    {
        var authorize = Attribute.GetCustomAttribute(RouteData.PageType, typeof(AuthorizeAttribute)) != null;

        if (authorize && this.users_service.user_entity == null && this.users_service.token == null)
        {
            var returnUrl = WebUtility.UrlEncode(new Uri(this.navigation_manager.Uri).PathAndQuery);

            this.navigation_manager.NavigateTo($"login?returnUrl={returnUrl}");
        }
        else
        {
            base.Render(builder);
        }
    }
}
