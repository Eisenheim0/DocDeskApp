package uoc.edu.docdeskapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.edu.docdeskapp.dto.HistoriaclinicaDTO;
import uoc.edu.docdeskapp.entity.HistoriaclinicaEntity;
import uoc.edu.docdeskapp.entity.PacienteEntity;
import uoc.edu.docdeskapp.repositories.HistoriaclinicaRepository;

import java.sql.Date;
import java.time.Instant;

@Service
public class HistoriaclinicaServiceImpl implements HistoriaclinicaService{

    @Autowired
    HistoriaclinicaRepository historiaclinicaRepository;

    @Autowired
    PatientService patientService;

    @Override
    public HistoriaclinicaDTO findByPaciente(PacienteEntity paciente) {
        HistoriaclinicaEntity historiaclinicaEntity = historiaclinicaRepository.findByPaciente(paciente);
        return historiaclinicaEntity != null ? new HistoriaclinicaDTO(historiaclinicaEntity.getPaciente().getIdPaciente(), historiaclinicaEntity.getAntecedentesFamiliaresHeredados(), historiaclinicaEntity.getAntecedentesPatologicosPersonales(), historiaclinicaEntity.getAntecedentesPersonalesNoPatológicos(), historiaclinicaEntity.getAntecedentesQuirurgicosTraumaticos(), historiaclinicaEntity.getOtros()) : new HistoriaclinicaDTO();
    }

    @Override
    public void save(HistoriaclinicaDTO historiaclinicaDTO) {
        PacienteEntity pacienteEntity = patientService.findById(historiaclinicaDTO.getIdPaciente());
        HistoriaclinicaEntity historiaclinicaEntity = historiaclinicaRepository.findByPaciente(pacienteEntity);
        if (historiaclinicaEntity == null) {
            historiaclinicaEntity = new HistoriaclinicaEntity();
            historiaclinicaEntity.setPaciente(pacienteEntity);
        }
        historiaclinicaEntity.setFecha(new Date(System.currentTimeMillis()));
        historiaclinicaEntity.setAntecedentesFamiliaresHeredados(historiaclinicaDTO.getAntecedentesFamiliaresHeredados());
        historiaclinicaEntity.setAntecedentesPatologicosPersonales(historiaclinicaDTO.getAntecedentesPatologicosPersonales());
        historiaclinicaEntity.setAntecedentesPersonalesNoPatológicos(historiaclinicaDTO.getAntecedentesPersonalesNoPatológicos());
        historiaclinicaEntity.setAntecedentesQuirurgicosTraumaticos(historiaclinicaDTO.getAntecedentesQuirurgicosTraumaticos());
        historiaclinicaEntity.setOtros(historiaclinicaDTO.getOtros());
        historiaclinicaRepository.save(historiaclinicaEntity);
    }
}
