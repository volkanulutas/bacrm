package tr.com.bacompany.bacrm.configuration.security;

import lombok.Getter;

@Getter
public class AuthToken {
    private String token;

    public AuthToken() {
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
