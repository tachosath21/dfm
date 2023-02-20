using Blazor.Estates.Entities;
using Blazor.Users.Entities;

namespace Blazor.Transfers.Entities;

public class TransferEntity
{
    public UserEntity notary { get; set; }
    public UserEntity buyer { get; set; }
    public EstateEntity estate { get; set; }
    public DateTime date_created { get; set; }
    public bool has_seller_signed { get; set; }
    public bool has_buyer_signed {get; set; }
    public bool is_finalized { get; set; }
    public DateTime date_finalized { get; set; }
    public bool is_payed { get; set; }
    public bool is_posted { get; set; }
}
