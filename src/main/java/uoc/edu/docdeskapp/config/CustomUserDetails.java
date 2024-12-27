package uoc.edu.docdeskapp.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uoc.edu.docdeskapp.entity.RolEntity;
import uoc.edu.docdeskapp.entity.UsuarioEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final UsuarioEntity usuarioEntity;

    public CustomUserDetails(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public String getFullname() {
        return usuarioEntity.getNombre() + " " + usuarioEntity.getApellido();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        RolEntity rol = usuarioEntity.getRol();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(rol.getNombreRol()));
        usuarioEntity.getRol().getPermisos().forEach(permiso -> authorities.add(new SimpleGrantedAuthority(permiso.getNombrePermiso())));

        return authorities;
    }

    @Override
    public String getPassword() {
        return usuarioEntity.getContrasenya();
    }

    @Override
    public String getUsername() {
        return usuarioEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
