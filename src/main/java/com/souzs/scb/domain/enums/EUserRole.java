package com.souzs.scb.domain.enums;

public enum EUserRole {
    ROLE_ADMIN(1L),
    ROLE_LIBRARY(2L),
    ROLE_MEMBER(3L);

    private long id;

    EUserRole(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
