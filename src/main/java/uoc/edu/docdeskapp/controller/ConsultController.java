package uoc.edu.docdeskapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uoc.edu.docdeskapp.config.AppConfiguration;
import uoc.edu.docdeskapp.config.CustomUserDetails;
import uoc.edu.docdeskapp.dto.ConsultDTO;
import uoc.edu.docdeskapp.dto.ConsultaRequest;
import uoc.edu.docdeskapp.dto.HistoriaclinicaDTO;
import uoc.edu.docdeskapp.entity.PacienteEntity;
import uoc.edu.docdeskapp.services.ChatGPTService;
import uoc.edu.docdeskapp.services.ConsultService;
import uoc.edu.docdeskapp.services.HistoriaclinicaService;
import uoc.edu.docdeskapp.services.PatientService;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ConsultController {

    private static final Logger logger = LoggerFactory.getLogger(ConsultController.class);

    @Autowired
    PatientService patientService;

    @Autowired
    ChatGPTService chatGPTService;

    @Autowired
    AppConfiguration appConfiguration;

    @Autowired
    ConsultService consultService;

    @Autowired
    HistoriaclinicaService historiaclinicaService;

    @RequestMapping("/consult")
    public String consultIdPaciente(@RequestParam(required = false) Long idPaciente, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.debug("|||||||||| " + ((CustomUserDetails) principal).getFullname());
        if (principal instanceof UserDetails) {
            String username = ((CustomUserDetails) principal).getFullname();
            model.addAttribute("username", username);
        } else {
            model.addAttribute("username", "Guest");
        }

        PacienteEntity pacienteEntity = null;

        if (idPaciente != null) {
            logger.debug("|||||||||| id: " + idPaciente);
            pacienteEntity = patientService.findById(idPaciente);
        }

        model.addAttribute("paciente", pacienteEntity);

        return "consult";
    }

    @PostMapping("/consult/ai/principal")
    @ResponseBody
    public List<String> aiPrincipal(@RequestBody ConsultaRequest request) {
        List<String> response = new ArrayList<>();
        String prompt;

        logger.debug("|||||||||| aiPrincipal consult: " + request.getConsult());
        logger.debug("|||||||||| aiPrincipal idPaciente: " + request.getIdPaciente());

        prompt = MessageFormat.format(appConfiguration.getPromptPrincipal(), request.getConsult());
        logger.debug("|||||||||| aiPrincipal prompt: " + prompt);
        String r = chatGPTService.getChatGPTResponse(prompt);
        logger.debug("|||||||||| response :" + r);

        String[] sec = r.split("(?m)^\\d+\\s+");
        for (int i = 1; i < sec.length; i++) {
            response.add(sec[i].trim());
        }

        ConsultDTO consultDTO = new ConsultDTO(request.getIdPaciente(), request.getConsult(), sec[1], sec[2], sec[3], sec[4]);
        consultService.save(consultDTO);

        return response;
    }

    @PostMapping("/consult/ai/medicamentos")
    @ResponseBody
    public Map<String, String> aiMedicamentos(@RequestBody ConsultaRequest request) {
        Map<String, String> response = new HashMap<>();
        String prompt;
        String consulta, historiaClinica;

        logger.debug("|||||||||| aiMedicamentos idPaciente: " + request.getIdPaciente());

        PacienteEntity pacienteEntity = patientService.findById(request.getIdPaciente());
        ConsultDTO consultDTO = consultService.findByPacienteOrderByFechaCreacion(pacienteEntity);
        String[] consultas = {consultDTO.getConsulta(), consultDTO.getSubjetivo(), consultDTO.getObjetivo(), consultDTO.getAnalisis(), consultDTO.getTratamiento()};
        consulta = String.join(" ", consultas);
        HistoriaclinicaDTO historiaclinicaDTO = historiaclinicaService.findByPaciente(pacienteEntity);
        String[] hc = {historiaclinicaDTO.getAntecedentesFamiliaresHeredados(), historiaclinicaDTO.getAntecedentesPatologicosPersonales(), historiaclinicaDTO.getAntecedentesPersonalesNoPatológicos(), historiaclinicaDTO.getAntecedentesQuirurgicosTraumaticos(), historiaclinicaDTO.getOtros()};
        historiaClinica = String.join(" ", hc);

        prompt = MessageFormat.format(appConfiguration.getPromptMedicamentos(), consulta, historiaClinica);
        logger.debug("|||||||||| aiMedicamentos prompt: " + prompt);
        String r = chatGPTService.getChatGPTResponse(prompt);
        logger.debug("|||||||||| response :" + r);

        response.put("title", "Interacción Medicamentosa");
        response.put("body", r);

        ConsultDTO newConsultDTO = new ConsultDTO();
        newConsultDTO.setIdPaciente(pacienteEntity.getIdPaciente());
        newConsultDTO.setInteraccionMedicamentos(toNoHtml(r));
        consultService.saveMedicamentos(newConsultDTO);

        return response;
    }

    @PostMapping("/consult/ai/consejos")
    @ResponseBody
    public Map<String, String> aiConsejos(@RequestBody ConsultaRequest request) {
        Map<String, String> response = new HashMap<>();
        String prompt;
        String consulta, historiaClinica;

        logger.debug("|||||||||| aiConsejos idPaciente: " + request.getIdPaciente());

        PacienteEntity pacienteEntity = patientService.findById(request.getIdPaciente());
        ConsultDTO consultDTO = consultService.findByPacienteOrderByFechaCreacion(pacienteEntity);
        String[] consultas = {consultDTO.getConsulta(), consultDTO.getSubjetivo(), consultDTO.getObjetivo(), consultDTO.getAnalisis(), consultDTO.getTratamiento()};
        consulta = String.join(" ", consultas);
        HistoriaclinicaDTO historiaclinicaDTO = historiaclinicaService.findByPaciente(pacienteEntity);
        String[] hc = {historiaclinicaDTO.getAntecedentesFamiliaresHeredados(), historiaclinicaDTO.getAntecedentesPatologicosPersonales(), historiaclinicaDTO.getAntecedentesPersonalesNoPatológicos(), historiaclinicaDTO.getAntecedentesQuirurgicosTraumaticos(), historiaclinicaDTO.getOtros()};
        historiaClinica = String.join(" ", hc);

        prompt = MessageFormat.format(appConfiguration.getPromptConsejos(), consulta, historiaClinica);
        logger.debug("|||||||||| aiConsejos prompt: " + prompt);
        String r = chatGPTService.getChatGPTResponse(prompt);
        logger.debug("|||||||||| response :" + r);

        response.put("title", "Consejos Paciente");
        response.put("body", r);

        ConsultDTO newConsultDTO = new ConsultDTO();
        newConsultDTO.setIdPaciente(pacienteEntity.getIdPaciente());
        newConsultDTO.setConsejosPaciente(toNoHtml(r));
        consultService.saveConsejos(newConsultDTO);

        return response;
    }

    @PostMapping("/consult/ai/epicrisis")
    @ResponseBody
    public Map<String, String> aiEpicrisis(@RequestBody ConsultaRequest request) {
        Map<String, String> response = new HashMap<>();
        String prompt;
        String consulta, historiaClinica;

        logger.debug("|||||||||| aiEpicrisis idPaciente: " + request.getIdPaciente());

        PacienteEntity pacienteEntity = patientService.findById(request.getIdPaciente());
        ConsultDTO consultDTO = consultService.findByPacienteOrderByFechaCreacion(pacienteEntity);
        String[] consultas = {consultDTO.getConsulta(), consultDTO.getSubjetivo(), consultDTO.getObjetivo(), consultDTO.getAnalisis(), consultDTO.getTratamiento()};
        consulta = String.join(" ", consultas);
        HistoriaclinicaDTO historiaclinicaDTO = historiaclinicaService.findByPaciente(pacienteEntity);
        String[] hc = {historiaclinicaDTO.getAntecedentesFamiliaresHeredados(), historiaclinicaDTO.getAntecedentesPatologicosPersonales(), historiaclinicaDTO.getAntecedentesPersonalesNoPatológicos(), historiaclinicaDTO.getAntecedentesQuirurgicosTraumaticos(), historiaclinicaDTO.getOtros()};
        historiaClinica = String.join(" ", hc);

        prompt = MessageFormat.format(appConfiguration.getPromptEpicrisis(), consulta, historiaClinica);
        logger.debug("|||||||||| aiEpicrisis prompt: " + prompt);
        String r = chatGPTService.getChatGPTResponse(prompt);
        logger.debug("|||||||||| response :" + r);

        response.put("title", "EPICRISIS");
        response.put("body", r);

        ConsultDTO newConsultDTO = new ConsultDTO();
        newConsultDTO.setIdPaciente(pacienteEntity.getIdPaciente());
        newConsultDTO.setEpicrisis(toNoHtml(r));
        consultService.saveEpicrisis(newConsultDTO);

        return response;
    }

    @PostMapping("/consult/ai/protocolos")
    @ResponseBody
    public Map<String, String> aiProtocolos(@RequestBody ConsultaRequest request) {
        Map<String, String> response = new HashMap<>();
        String prompt;
        String historiaClinica;

        logger.debug("|||||||||| aiProtocolos idPaciente: " + request.getIdPaciente());

        PacienteEntity pacienteEntity = patientService.findById(request.getIdPaciente());
        HistoriaclinicaDTO historiaclinicaDTO = historiaclinicaService.findByPaciente(pacienteEntity);
        String[] hc = {historiaclinicaDTO.getAntecedentesFamiliaresHeredados(), historiaclinicaDTO.getAntecedentesPatologicosPersonales(), historiaclinicaDTO.getAntecedentesPersonalesNoPatológicos(), historiaclinicaDTO.getAntecedentesQuirurgicosTraumaticos(), historiaclinicaDTO.getOtros()};
        historiaClinica = String.join(" ", hc);

        prompt = MessageFormat.format(appConfiguration.getPromptProtocolos(), historiaClinica);
        logger.debug("|||||||||| aiProtocolos prompt: " + prompt);
        String r = chatGPTService.getChatGPTResponse(prompt);
        logger.debug("|||||||||| response :" + r);

        response.put("title", "Seguimiento Protocolos");
        response.put("body", r);

        ConsultDTO newConsultDTO = new ConsultDTO();
        newConsultDTO.setIdPaciente(pacienteEntity.getIdPaciente());
        newConsultDTO.setSeguimientoProtocolos(toNoHtml(r));
        consultService.saveProtocolos(newConsultDTO);

        return response;
    }

    private String toNoHtml(String response) {
        String prompt = MessageFormat.format(appConfiguration.getPromptNoHtml(), response);
        logger.debug("|||||||||| toNoHtml prompt: " + prompt);
        String r = chatGPTService.getChatGPTResponse(prompt);
        logger.debug("|||||||||| toNoHtml response: " + r);
        return r;
    }
}
