package uoc.edu.docdeskapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.edu.docdeskapp.dto.HistoriaclinicaDTO;
import uoc.edu.docdeskapp.entity.HistoriaclinicaEntity;
import uoc.edu.docdeskapp.entity.PacienteEntity;
import uoc.edu.docdeskapp.repositories.HistoriaclinicaRepository;

@Service
public class HistoriaclinicaServiceImpl implements HistoriaclinicaService{

    @Autowired
    HistoriaclinicaRepository historiaclinicaRepository;

    @Override
    public HistoriaclinicaDTO findByPaciente(PacienteEntity paciente) {
        HistoriaclinicaEntity historiaclinicaEntity = historiaclinicaRepository.findByPaciente(paciente);
        return historiaclinicaEntity != null ? new HistoriaclinicaDTO(historiaclinicaEntity.getPaciente().getIdPaciente(), historiaclinicaEntity.getAntecedentesFamiliaresHeredados(), historiaclinicaEntity.getAntecedentesPatologicosPersonales(), historiaclinicaEntity.getAntecedentesPersonalesNoPatológicos(), historiaclinicaEntity.getAntecedentesQuirurgicosTraumaticos(), historiaclinicaEntity.getOtros()) : new HistoriaclinicaDTO();
    }
}
