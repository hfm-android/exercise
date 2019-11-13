package com.omid.exercise.catalog.entity;

import com.omid.exercise.catalog.entity.enums.AuthorityType;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private AuthorityType type;

    public Authority() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorityType getType() {
        return type;
    }

    public void setType(AuthorityType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return id == authority.id &&
                type == authority.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", type=" + type.name() +
                '}';
    }
}
