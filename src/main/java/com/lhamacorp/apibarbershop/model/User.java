package com.lhamacorp.apibarbershop.model;

import com.lhamacorp.apibarbershop.model.DTOs.Users.UserDTO;
import com.lhamacorp.apibarbershop.model.DTOs.Users.UserRegisterDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity(name = "User")
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String name;
    private String email;
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean active;

    public User() {
    }

    public User(Long idUser, String name, String email, String password, String phone, UserRole role) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.active = true;
    }

    public User(UserRegisterDTO userRegisterDTO){
        this.name = userRegisterDTO.name();
        this.email = userRegisterDTO.email();;
        this.password = userRegisterDTO.password();
        this.phone = userRegisterDTO.phone();
        this.role = UserRole.CLIENT;
        this.active = true;
    }

    public User(UserDTO userDTO){
        this.idUser = userDTO.idUser();
        this.name = userDTO.name();
        this.email = userDTO.email();

    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        switch(this.role){

            case ADMIN:
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_BARBER"), new SimpleGrantedAuthority("ROLE_CLIENT"));

            case BARBER:
                return List.of(new SimpleGrantedAuthority("ROLE_BARBER"), new SimpleGrantedAuthority("ROLE_CLIENT"));

            case CLIENT:
                return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));

            default:
                return List.of();
        }
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
