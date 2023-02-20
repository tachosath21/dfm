using Blazor.Transfers.Entities;
using Blazor.Transfers.Services;
using Blazor.Users.Services;
using Microsoft.AspNetCore.Components;

namespace Blazor.Transfers.Components;

public partial class TransfersComponent : ComponentBase
{
    [Inject]
    private ITransfersService transfers_service { get; set; }
    [Inject]
    private IUsersService users_service { get; set; }
    [Inject]
    private NavigationManager navigation_manager { get; set; }

    private IEnumerable<TransferEntity> transfer_entities { get; set; }    
    private IList<TransferEntity> selected_transfer_entities { get; set; }

    private bool loading;
    private string error;

    protected override async Task OnInitializedAsync()
    {
        var transfers_response_dto = await this.transfers_service.readAllAsync();

        if (transfers_response_dto.processed)
            this.transfer_entities = transfers_response_dto.transfers;
        else
            this.transfer_entities = new List<TransferEntity>();

        this.selected_transfer_entities = new List<TransferEntity>();
    }

    private void create()
    {
        this.navigation_manager.NavigateTo("Transfers/0");
    }

    private async Task update()
    {
        var selected_transfer_entity = this.selected_transfer_entities.FirstOrDefault();

        if (selected_transfer_entity is not null)
        {
            var id = await this.transfers_service.getIdAsync(selected_transfer_entity);
         
            this.selected_transfer_entities.Clear();
            this.navigation_manager.NavigateTo("Transfers/" + id);
        }
    }

    private async void delete()
    {
        var selected_transfer_entity = this.selected_transfer_entities.FirstOrDefault();

        if (selected_transfer_entity is not null)
        {
            await this.transfers_service.deleteAsync(selected_transfer_entity);

            this.selected_transfer_entities.Clear();

            var transfers_response_dto = await this.transfers_service.readAllAsync();

            if (transfers_response_dto.processed)
                this.transfer_entities = transfers_response_dto.transfers;
            else
                this.transfer_entities = new List<TransferEntity>();

            this.StateHasChanged();
        }
    }
}