using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace Blazor.Users.Entities;

public class UserEntity
{
    [Required]
    public string email { get; set; }
    [Required]
    public string password { get; set; }
    public string firstname { get; set; }
    public string lastname { get; set; }
    [Required]
    [JsonConverter(typeof(JsonStringEnumConverter))]
    public UserRoleEntity user_role { get; set; }
    [JsonIgnore]
    public string fullname => $"{firstname} {lastname}";
}
