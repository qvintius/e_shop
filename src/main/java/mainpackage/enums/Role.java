package mainpackage.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {//для Security
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
