package uoc.edu.docdeskapp.services;

import uoc.edu.docdeskapp.dto.ConsultDTO;
import uoc.edu.docdeskapp.entity.PacienteEntity;

public interface ConsultService {

    ConsultDTO findByPacienteOrderByFechaCreacion(PacienteEntity pacienteEntity);

    void save(ConsultDTO consultDTO);

    void saveMedicamentos(ConsultDTO consultDTO);

    void saveConsejos(ConsultDTO consultDTO);

    void saveEpicrisis(ConsultDTO consultDTO);

    void saveProtocolos(ConsultDTO consultDTO);
}
