package org.main.service.entity;

import java.io.Serializable;

public enum Role implements Serializable {
    USER, ADMIN;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
