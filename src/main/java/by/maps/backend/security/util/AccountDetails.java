package by.maps.backend.security.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDetails {
    private String sub;
    private String scope;
    private boolean email_verified;
    private String name;
    private String preferred_username;
    private String given_name;
    private String family_name;
    private String email;
}
