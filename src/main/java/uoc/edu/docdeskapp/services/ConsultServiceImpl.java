package uoc.edu.docdeskapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uoc.edu.docdeskapp.dto.ConsultDTO;
import uoc.edu.docdeskapp.entity.ConsultaEntity;
import uoc.edu.docdeskapp.entity.PacienteEntity;
import uoc.edu.docdeskapp.entity.UsuarioEntity;
import uoc.edu.docdeskapp.repositories.ConsultRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Service
public class ConsultServiceImpl implements ConsultService{

    @Autowired
    ConsultRepository consultRepository;

    @Autowired
    PatientService patientService;

    @Override
    public ConsultDTO findByPacienteOrderByFechaCreacion(PacienteEntity pacienteEntity) {
        ConsultaEntity consultaEntity = consultRepository.findByPacienteOrderByFechaCreacionDesc(pacienteEntity).get(0);
        return new ConsultDTO(consultaEntity.getPaciente().getIdPaciente(), consultaEntity.getConsulta(), consultaEntity.getSubjetivo(), consultaEntity.getObjetivo(), consultaEntity.getAnalisis(), consultaEntity.getTratamiento());
    }

    @Override
    public void save(ConsultDTO consultDTO) {
        PacienteEntity paciente = patientService.findById(consultDTO.getIdPaciente());
        ConsultaEntity consultaEntity = new ConsultaEntity(paciente, Timestamp.from(Instant.now()), consultDTO.getConsulta(), consultDTO.getSubjetivo(), consultDTO.getObjetivo(), consultDTO.getAnalisis(), consultDTO.getTratamiento());
        consultRepository.save(consultaEntity);
    }

    @Override
    public void saveMedicamentos(ConsultDTO consultDTO) {
        ConsultaEntity consultaEntity = consultRepository.findByPacienteOrderByFechaCreacionDesc(patientService.findById(consultDTO.getIdPaciente())).get(0);
        consultaEntity.setInteraccionMedicamentos(consultDTO.getInteraccionMedicamentos());
        consultRepository.save(consultaEntity);
    }

    @Override
    public void saveConsejos(ConsultDTO consultDTO) {
        ConsultaEntity consultaEntity = consultRepository.findByPacienteOrderByFechaCreacionDesc(patientService.findById(consultDTO.getIdPaciente())).get(0);
        consultaEntity.setConsejosPaciente(consultDTO.getConsejosPaciente());
        consultRepository.save(consultaEntity);
    }

    @Override
    public void saveEpicrisis(ConsultDTO consultDTO) {
        ConsultaEntity consultaEntity = consultRepository.findByPacienteOrderByFechaCreacionDesc(patientService.findById(consultDTO.getIdPaciente())).get(0);
        consultaEntity.setEpicrisis(consultDTO.getEpicrisis());
        consultRepository.save(consultaEntity);
    }

    @Override
    public void saveProtocolos(ConsultDTO consultDTO) {
        ConsultaEntity consultaEntity = consultRepository.findByPacienteOrderByFechaCreacionDesc(patientService.findById(consultDTO.getIdPaciente())).get(0);
        consultaEntity.setSeguimientoProtocolos(consultDTO.getSeguimientoProtocolos());
        consultRepository.save(consultaEntity);
    }
}
