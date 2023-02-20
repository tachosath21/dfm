using Blazor.Estates.Entities;
using Blazor.Estates.Services;
using Blazor.Transfers.Entities;
using Blazor.Transfers.Services;
using Blazor.Users.Entities;
using Blazor.Users.Services;
using Microsoft.AspNetCore.Components;

namespace Blazor.Transfers.Components;

public partial class TransferComponent : ComponentBase
{
    [Inject]
    private IUsersService users_service { get; set; }
    [Inject]
    private ITransfersService transfers_service { get; set; }
    [Inject]
    private IEstatesService estates_service { get; set; }
    
    [Inject]
    private NavigationManager navigation_manager { get; set; }

    [Parameter]
    public int Id { get; set; }

    private bool loading;
    private string error;

    private TransferEntity transfer_entity { get; set; }
    private IEnumerable<EstateEntity> estate_entities { get; set; }
    private IEnumerable<UserEntity> notary_entities { get; set; }
    private IEnumerable<UserEntity> buyer_entities { get; set; }

    protected override async Task OnInitializedAsync()
    {
        this.loading = true;

        if (this.Id == 0 &&
            this.users_service.user_entity.user_role != UserRoleEntity.ADMIN &&
            this.users_service.user_entity.user_role != UserRoleEntity.NOTARY)
        {
            this.loading = false;
            this.navigation_manager.NavigateTo("Transfers");
            return;
        }
        
        if (this.Id != 0)
        {
            var transfer_response_dto = await this.transfers_service.readAsync(this.Id);

            if (transfer_response_dto.processed)
                this.transfer_entity = transfer_response_dto.transfer;
            else
                this.error = transfer_response_dto.error.message;
        }
        else
            this.transfer_entity = new TransferEntity();

        if (this.transfer_entity.notary is null && this.users_service.user_entity.user_role == UserRoleEntity.NOTARY)
            this.transfer_entity.notary = this.users_service.user_entity;
        
        // Get Notaries
        var notaries_response_dto = await this.users_service.readByUserRoleAsync(UserRoleEntity.NOTARY);

        if (!notaries_response_dto.processed)
            this.navigation_manager.NavigateTo("Transfers");

        this.notary_entities = notaries_response_dto.users.ToList();

        // Get Estates
        var estates_response_dto = await this.estates_service.readAllAsync();

        if (!estates_response_dto.processed)
            this.navigation_manager.NavigateTo("Transfers");

        this.estate_entities = estates_response_dto.estates;

        // Get Buyers
        var buyers_response_dto = await this.users_service.readByUserRoleAsync(UserRoleEntity.BUYER);

        if (!buyers_response_dto.processed)
            this.navigation_manager.NavigateTo("Transfers");

        this.buyer_entities = buyers_response_dto.users.ToList();

        this.loading = false;
        this.StateHasChanged();
    }

    private async Task save()
    {
        this.loading = true;

        var transfer_response_dto = this.Id == 0 ? await this.transfers_service.create(this.transfer_entity) : await this.transfers_service.update(this.Id, this.transfer_entity);

        this.loading = false;

        if (transfer_response_dto.processed)
            this.navigation_manager.NavigateTo("Transfers");
    }

    private void cancel()
    {
        this.navigation_manager.NavigateTo("Transfers");
    }
}