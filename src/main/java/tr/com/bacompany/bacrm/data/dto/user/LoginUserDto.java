package tr.com.bacompany.bacrm.data.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUserDto {
    private String username;

    private String password;
}
