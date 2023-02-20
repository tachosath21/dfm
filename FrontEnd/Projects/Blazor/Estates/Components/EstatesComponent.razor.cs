using Blazor.Estates.Entities;
using Blazor.Estates.Services;
using Blazor.Users.Services;
using Microsoft.AspNetCore.Components;

namespace Blazor.Estates.Components;

public partial class EstatesComponent : ComponentBase
{
    [Inject]
    private IEstatesService estates_service { get; set; }
    [Inject]
    private IUsersService users_service { get; set; }
    [Inject]
    private NavigationManager navigation_manager { get; set; }

    private IEnumerable<EstateEntity> estate_entities { get; set; }    
    private IList<EstateEntity> selected_estate_entities { get; set; }

    private bool loading;
    private string error;
    
    protected override async Task OnInitializedAsync()
    {
        var estates_response_dto = await this.estates_service.readAllAsync();

        if (estates_response_dto.processed)
            this.estate_entities = estates_response_dto.estates;
        else
            this.estate_entities = new List<EstateEntity>();
        
        this.selected_estate_entities = new List<EstateEntity>();
    }

    private void create()
    {
        this.navigation_manager.NavigateTo("Estates/0");
    }

    private async Task update()
    {
        var selected_estate_entity = this.selected_estate_entities.FirstOrDefault();

        if (selected_estate_entity is not null)
        {
            var id = await this.estates_service.getIdAsync(selected_estate_entity);
         
            this.navigation_manager.NavigateTo("Estates/" + id);
        }
    }

    private async void delete()
    {
        var selected_estate_entity = this.selected_estate_entities.FirstOrDefault();

        if (selected_estate_entity is not null)
        {
            await this.estates_service.deleteAsync(selected_estate_entity);

            this.selected_estate_entities.Clear();

            var estates_response_dto = await this.estates_service.readAllAsync();

            if (estates_response_dto.processed)
                this.estate_entities = estates_response_dto.estates;
            else
                this.estate_entities = new List<EstateEntity>();

            this.StateHasChanged();
        }
    }
}