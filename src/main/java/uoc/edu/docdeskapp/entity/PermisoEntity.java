package uoc.edu.docdeskapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "permiso", schema = "docdesk", catalog = "docdesk")
public class PermisoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_permiso")
    private int idPermiso;
    @Basic
    @Column(name = "nombre_permiso")
    private String nombrePermiso;

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermisoEntity that = (PermisoEntity) o;

        if (idPermiso != that.idPermiso) return false;
        if (nombrePermiso != null ? !nombrePermiso.equals(that.nombrePermiso) : that.nombrePermiso != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPermiso;
        result = 31 * result + (nombrePermiso != null ? nombrePermiso.hashCode() : 0);
        return result;
    }
}
