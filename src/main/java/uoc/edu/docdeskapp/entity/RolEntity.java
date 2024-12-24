package uoc.edu.docdeskapp.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "rol")
public class RolEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "nombre_rol")
    private String nombreRol;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UsuarioEntity> usuarios;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Rol_Permiso",
            joinColumns = @JoinColumn(name = "id_rol"),
            inverseJoinColumns = @JoinColumn(name = "id_permiso")
    )
    private Set<PermisoEntity> permisos;

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Set<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioEntity> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<PermisoEntity> getPermisos() {
        return permisos;
    }

    public void setPermisos(Set<PermisoEntity> permisos) {
        this.permisos = permisos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolEntity rolEntity = (RolEntity) o;
        return idRol != null && idRol.equals(rolEntity.idRol);
    }

    @Override
    public int hashCode() {
        return idRol != null ? idRol.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RolEntity{" +
                "idRol=" + idRol +
                ", nombreRol='" + nombreRol + '\'' +
                '}';
    }
}
