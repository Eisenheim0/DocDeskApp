package uoc.edu.docdeskapp.services;

import uoc.edu.docdeskapp.dto.HistoriaclinicaDTO;
import uoc.edu.docdeskapp.entity.PacienteEntity;

public interface HistoriaclinicaService {

    HistoriaclinicaDTO findByPaciente(PacienteEntity paciente);

    void save(HistoriaclinicaDTO historiaclinicaDTO);
}
