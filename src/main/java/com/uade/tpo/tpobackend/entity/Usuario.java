package com.uade.tpo.tpobackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
// import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {
    public Usuario() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuario_id;
    @Column
    private String nombre;
    @Column
    private String pass;
    @Column
    private Boolean admin;
    @Column
    private Double saldo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "duenio")
    private List<Libro> librosPublicados;

    @OneToMany(mappedBy = "comprador", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Venta> ventas;// -

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {

        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    public int getId() {

        return usuario_id;
    }
}
