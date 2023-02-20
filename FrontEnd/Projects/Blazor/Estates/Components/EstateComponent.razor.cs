using Blazor.Estates.Entities;
using Blazor.Estates.Services;
using Blazor.Users.Services;
using Microsoft.AspNetCore.Components;

namespace Blazor.Estates.Components;

public partial class EstateComponent : ComponentBase
{
    [Inject]
    private IEstatesService estates_service { get; set; }
    [Inject]
    private IUsersService users_service { get; set; }
    [Inject]
    private NavigationManager navigation_manager { get; set; }

    [Parameter]
    public int Id { get; set; }
    
    private bool loading;
    private string error;

    public EstateEntity estate_entity { get; set; }

    protected override async Task OnInitializedAsync()
    {
        this.loading = true;

        this.estate_entity = new EstateEntity();
        
        if (this.Id != 0)
        {
            var estate_response_dto = await this.estates_service.readAsync(this.Id);

            if (estate_response_dto.processed)
                this.estate_entity = estate_response_dto.estate;
            else
                this.error = estate_response_dto.error.message;
        }

        this.loading = false;
    }

    private async Task save()
    {
        this.loading = true;

        var estate_response_dto = this.Id == 0 ? await this.estates_service.create(this.estate_entity) : await this.estates_service.update(this.Id, this.estate_entity);

        this.loading = false;

        if (estate_response_dto.processed)
            this.navigation_manager.NavigateTo("Estates");
    }

    private void cancel()
    {
        this.navigation_manager.NavigateTo("Estates");
    }
}