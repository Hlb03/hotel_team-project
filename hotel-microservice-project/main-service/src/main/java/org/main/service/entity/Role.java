package org.main.service.entity;


import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role implements Serializable {
    USER (Set.of(AppPermission.READ, AppPermission.UPDATE)),
    ADMIN (Set.of(AppPermission.READ, AppPermission.UPDATE, AppPermission.DELETE, AppPermission.WRITE));

    private final Set<AppPermission> permissions;

    Role(Set<AppPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return permissions
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
