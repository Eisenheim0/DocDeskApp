package uoc.edu.docdeskapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rol_permiso", schema = "public", catalog = "DocDesk")
@IdClass(RolPermisoEntityPK.class)
public class RolPermisoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rol")
    private int idRol;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_permiso")
    private int idPermiso;

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolPermisoEntity that = (RolPermisoEntity) o;

        if (idRol != that.idRol) return false;
        if (idPermiso != that.idPermiso) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRol;
        result = 31 * result + idPermiso;
        return result;
    }
}
