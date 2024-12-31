package uoc.edu.docdeskapp.dto;

public class ConsultDTO {

    private Long idPaciente;

    private String consulta;

    private String subjetivo;

    private String objetivo;

    private String analisis;

    private String tratamiento;

    private String interaccionMedicamentos;

    private String consejosPaciente;

    private String epicrisis;

    private String seguimientoProtocolos;

    public ConsultDTO() {
    }

    public ConsultDTO(Long idPaciente, String consulta, String subjetivo, String objetivo, String analisis, String tratamiento) {
        this.idPaciente = idPaciente;
        this.consulta = consulta;
        this.subjetivo = subjetivo;
        this.objetivo = objetivo;
        this.analisis = analisis;
        this.tratamiento = tratamiento;
    }

    public ConsultDTO(Long idPaciente, String consulta, String subjetivo, String objetivo, String analisis, String tratamiento, String interaccionMedicamentos, String consejosPaciente, String epicrisis, String seguimientoProtocolos) {
        this.idPaciente = idPaciente;
        this.consulta = consulta;
        this.subjetivo = subjetivo;
        this.objetivo = objetivo;
        this.analisis = analisis;
        this.tratamiento = tratamiento;
        this.interaccionMedicamentos = interaccionMedicamentos;
        this.consejosPaciente = consejosPaciente;
        this.epicrisis = epicrisis;
        this.seguimientoProtocolos = seguimientoProtocolos;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
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
}
