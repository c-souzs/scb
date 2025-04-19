package com.souzs.scb.domain.enums;

public enum EUserRole {
    ADMIN(1L),
    LIBRARY(2L),
    MEMBER(3L);

    private long id;

    EUserRole(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
