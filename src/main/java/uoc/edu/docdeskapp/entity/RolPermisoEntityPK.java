package uoc.edu.docdeskapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class RolPermisoEntityPK implements Serializable {
    @Column(name = "id_rol")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    @Column(name = "id_permiso")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        RolPermisoEntityPK that = (RolPermisoEntityPK) o;

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
