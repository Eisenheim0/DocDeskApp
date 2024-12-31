package uoc.edu.docdeskapp.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "consulta", schema = "docdesk", catalog = "docdesk")
public class ConsultaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_consulta")
    private Long idConsulta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente", nullable = false)
    private PacienteEntity paciente;
    @Basic
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
    @Basic
    @Column(name = "consulta")
    private String consulta;
    @Basic
    @Column(name = "subjetivo")
    private String subjetivo;
    @Basic
    @Column(name = "objetivo")
    private String objetivo;
    @Basic
    @Column(name = "analisis")
    private String analisis;
    @Basic
    @Column(name = "tratamiento")
    private String tratamiento;
    @Basic
    @Column(name = "interaccion_medicamentos")
    private String interaccionMedicamentos;
    @Basic
    @Column(name = "consejos_paciente")
    private String consejosPaciente;
    @Basic
    @Column(name = "epicrisis")
    private String epicrisis;
    @Basic
    @Column(name = "seguimiento_protocolos")
    private String seguimientoProtocolos;

    public ConsultaEntity() {
    }

    public ConsultaEntity(PacienteEntity paciente, Timestamp fechaCreacion, String consulta, String subjetivo, String objetivo, String analisis, String tratamiento) {
        this.paciente = paciente;
        this.fechaCreacion = fechaCreacion;
        this.consulta = consulta;
        this.subjetivo = subjetivo;
        this.objetivo = objetivo;
        this.analisis = analisis;
        this.tratamiento = tratamiento;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getSubjetivo() {
        return subjetivo;
    }

    public void setSubjetivo(String subjetivo) {
        this.subjetivo = subjetivo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getInteraccionMedicamentos() {
        return interaccionMedicamentos;
    }

    public void setInteraccionMedicamentos(String interaccionMedicamentos) {
        this.interaccionMedicamentos = interaccionMedicamentos;
    }

    public String getConsejosPaciente() {
        return consejosPaciente;
    }

    public void setConsejosPaciente(String consejosPaciente) {
        this.consejosPaciente = consejosPaciente;
    }

    public String getEpicrisis() {
        return epicrisis;
    }

    public void setEpicrisis(String epicrisis) {
        this.epicrisis = epicrisis;
    }

    public String getSeguimientoProtocolos() {
        return seguimientoProtocolos;
    }

    public void setSeguimientoProtocolos(String seguimientoProtocolos) {
        this.seguimientoProtocolos = seguimientoProtocolos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultaEntity that = (ConsultaEntity) o;

        if (idConsulta != that.idConsulta) return false;
        if (paciente != that.paciente) return false;
        if (fechaCreacion != null ? !fechaCreacion.equals(that.fechaCreacion) : that.fechaCreacion != null)
            return false;
        if (consulta != null ? !consulta.equals(that.consulta) : that.consulta != null) return false;
        if (subjetivo != null ? !subjetivo.equals(that.subjetivo) : that.subjetivo != null) return false;
        if (objetivo != null ? !objetivo.equals(that.objetivo) : that.objetivo != null) return false;
        if (analisis != null ? !analisis.equals(that.analisis) : that.analisis != null) return false;
        if (tratamiento != null ? !tratamiento.equals(that.tratamiento) : that.tratamiento != null) return false;
        if (interaccionMedicamentos != null ? !interaccionMedicamentos.equals(that.interaccionMedicamentos) : that.interaccionMedicamentos != null)
            return false;
        if (consejosPaciente != null ? !consejosPaciente.equals(that.consejosPaciente) : that.consejosPaciente != null)
            return false;
        if (epicrisis != null ? !epicrisis.equals(that.epicrisis) : that.epicrisis != null) return false;
        if (seguimientoProtocolos != null ? !seguimientoProtocolos.equals(that.seguimientoProtocolos) : that.seguimientoProtocolos != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (idConsulta != null ? idConsulta.hashCode() : null);
        result = 31 * result + paciente.hashCode();
        result = 31 * result + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        result = 31 * result + (consulta != null ? consulta.hashCode() : 0);
        result = 31 * result + (subjetivo != null ? subjetivo.hashCode() : 0);
        result = 31 * result + (objetivo != null ? objetivo.hashCode() : 0);
        result = 31 * result + (analisis != null ? analisis.hashCode() : 0);
        result = 31 * result + (tratamiento != null ? tratamiento.hashCode() : 0);
        result = 31 * result + (interaccionMedicamentos != null ? interaccionMedicamentos.hashCode() : 0);
        result = 31 * result + (consejosPaciente != null ? consejosPaciente.hashCode() : 0);
        result = 31 * result + (epicrisis != null ? epicrisis.hashCode() : 0);
        result = 31 * result + (seguimientoProtocolos != null ? seguimientoProtocolos.hashCode() : 0);
        return result;
    }
}
