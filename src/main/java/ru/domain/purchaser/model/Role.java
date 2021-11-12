package ru.domain.purchaser.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    MANAGER,
    CHIEF;

    @Override
    public String getAuthority() {
        return name();
    }
}
