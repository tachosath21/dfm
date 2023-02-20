using Blazor.Users.Entities;

namespace Blazor.Estates.Entities;

public class EstateEntity
{
    public string description { get; set; }
    public double price { get; set; }
    public UserEntity? seller { get; set; }
}
