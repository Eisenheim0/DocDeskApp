package uoc.edu.docdeskapp.dto;


public class HistoriaclinicaDTO {

    Long idPaciente;
    private String antecedentesFamiliaresHeredados;

    private String antecedentesPatologicosPersonales;


    private String antecedentesPersonalesNoPatológicos;


    private String antecedentesQuirurgicosTraumaticos;

    private String otros;

    public HistoriaclinicaDTO() {
    }

    public HistoriaclinicaDTO(Long idPaciente, String antecedentesFamiliaresHeredados, String antecedentesPatologicosPersonales, String antecedentesPersonalesNoPatológicos, String antecedentesQuirurgicosTraumaticos, String otros) {
        this.idPaciente = idPaciente;
        this.antecedentesFamiliaresHeredados = antecedentesFamiliaresHeredados;
        this.antecedentesPatologicosPersonales = antecedentesPatologicosPersonales;
        this.antecedentesPersonalesNoPatológicos = antecedentesPersonalesNoPatológicos;
        this.antecedentesQuirurgicosTraumaticos = antecedentesQuirurgicosTraumaticos;
        this.otros = otros;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
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
}
