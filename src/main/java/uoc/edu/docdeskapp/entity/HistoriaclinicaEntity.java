package uoc.edu.docdeskapp.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "historiaclinica", schema = "docdesk", catalog = "docdesk")
public class HistoriaclinicaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_historia")
    private Long idHistoria;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente", nullable = false)
    private PacienteEntity paciente;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "antecedentes_familiares_heredados")
    private String antecedentesFamiliaresHeredados;
    @Basic
    @Column(name = "antecedentes_patologicos_personales")
    private String antecedentesPatologicosPersonales;
    @Basic
    @Column(name = "antecedentes_personales_no_patológicos")
    private String antecedentesPersonalesNoPatológicos;
    @Basic
    @Column(name = "antecedentes_quirurgicos_traumaticos")
    private String antecedentesQuirurgicosTraumaticos;
    @Basic
    @Column(name = "otros")
    private String otros;

    public Long getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Long idHistoria) {
        this.idHistoria = idHistoria;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAntecedentesFamiliaresHeredados() {
        return antecedentesFamiliaresHeredados;
    }

    public void setAntecedentesFamiliaresHeredados(String antecedentesFamiliaresHeredados) {
        this.antecedentesFamiliaresHeredados = antecedentesFamiliaresHeredados;
    }

    public String getAntecedentesPatologicosPersonales() {
        return antecedentesPatologicosPersonales;
    }

    public void setAntecedentesPatologicosPersonales(String antecedentesPatologicosPersonales) {
        this.antecedentesPatologicosPersonales = antecedentesPatologicosPersonales;
    }

    public String getAntecedentesPersonalesNoPatológicos() {
        return antecedentesPersonalesNoPatológicos;
    }

    public void setAntecedentesPersonalesNoPatológicos(String antecedentesPersonalesNoPatológicos) {
        this.antecedentesPersonalesNoPatológicos = antecedentesPersonalesNoPatológicos;
    }

    public String getAntecedentesQuirurgicosTraumaticos() {
        return antecedentesQuirurgicosTraumaticos;
    }

    public void setAntecedentesQuirurgicosTraumaticos(String antecedentesQuirurgicosTraumaticos) {
        this.antecedentesQuirurgicosTraumaticos = antecedentesQuirurgicosTraumaticos;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoriaclinicaEntity that = (HistoriaclinicaEntity) o;

        if (idHistoria != that.idHistoria) return false;
        if (paciente != that.paciente) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (antecedentesFamiliaresHeredados != null ? !antecedentesFamiliaresHeredados.equals(that.antecedentesFamiliaresHeredados) : that.antecedentesFamiliaresHeredados != null)
            return false;
        if (antecedentesPatologicosPersonales != null ? !antecedentesPatologicosPersonales.equals(that.antecedentesPatologicosPersonales) : that.antecedentesPatologicosPersonales != null)
            return false;
        if (antecedentesPersonalesNoPatológicos != null ? !antecedentesPersonalesNoPatológicos.equals(that.antecedentesPersonalesNoPatológicos) : that.antecedentesPersonalesNoPatológicos != null)
            return false;
        if (antecedentesQuirurgicosTraumaticos != null ? !antecedentesQuirurgicosTraumaticos.equals(that.antecedentesQuirurgicosTraumaticos) : that.antecedentesQuirurgicosTraumaticos != null)
            return false;
        if (otros != null ? !otros.equals(that.otros) : that.otros != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (idHistoria != null ? idHistoria.hashCode() : 0);
        result = 31 * result + paciente.hashCode();
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (antecedentesFamiliaresHeredados != null ? antecedentesFamiliaresHeredados.hashCode() : 0);
        result = 31 * result + (antecedentesPatologicosPersonales != null ? antecedentesPatologicosPersonales.hashCode() : 0);
        result = 31 * result + (antecedentesPersonalesNoPatológicos != null ? antecedentesPersonalesNoPatológicos.hashCode() : 0);
        result = 31 * result + (antecedentesQuirurgicosTraumaticos != null ? antecedentesQuirurgicosTraumaticos.hashCode() : 0);
        result = 31 * result + (otros != null ? otros.hashCode() : 0);
        return result;
    }
}
