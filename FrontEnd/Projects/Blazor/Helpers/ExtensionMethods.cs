using System.Collections.Specialized;
using System.Web;
using Microsoft.AspNetCore.Components;

namespace Blazor.Helpers;

public static class ExtensionMethods
{
    public static NameValueCollection QueryString(this NavigationManager navigation_manager)
    {
        return HttpUtility
            .ParseQueryString(new Uri(navigation_manager.Uri)
                .Query);
    }

    public static string QueryString(this NavigationManager navigation_manager, string key) =>
        navigation_manager.QueryString()[key];
}
