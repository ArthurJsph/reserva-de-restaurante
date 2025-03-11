package com.myproject.reserva_restaurantes.Entity;

import jakarta.persistence.Entity;

@Entity
public class Role {
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
