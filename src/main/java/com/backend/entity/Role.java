package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    private Long id;

    @NaturalId
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private transient Set<User> users;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Role role = (Role) o;
        return getId() != null && Objects.equals(getId(), role.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
