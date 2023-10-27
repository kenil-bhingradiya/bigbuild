package project.bigbuild.endpoints.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest
{
    String name;
    String givenName;
    String surname;
    String samAccName;
    String passwd;
    String department;
    String displayName;
    String email;
}
