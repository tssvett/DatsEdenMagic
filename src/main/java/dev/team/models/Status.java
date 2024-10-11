package dev.team.models;

import lombok.Getter;

@Getter
public enum Status {

    ALIVE("alive"),
    DEAD("dead");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
