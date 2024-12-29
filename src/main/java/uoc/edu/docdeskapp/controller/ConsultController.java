package uoc.edu.docdeskapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uoc.edu.docdeskapp.config.AppConfiguration;
import uoc.edu.docdeskapp.entity.PacienteEntity;
import uoc.edu.docdeskapp.services.ChatGPTService;
import uoc.edu.docdeskapp.services.PatientService;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping("/consult")
    public String consultIdPaciente(@RequestParam(required = false) Long idPaciente, Model model) {
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
    public List<String> aiPrincipal(@RequestBody String consult) {
        List<String> response = new ArrayList<>();
        String prompt;

        logger.debug("|||||||||| aiPrincipal consult: " + consult);
        prompt = MessageFormat.format(appConfiguration.getPromptPrincipal(), consult);
        logger.debug("|||||||||| aiPrincipal prompt: " + prompt);
        String r = chatGPTService.getChatGPTResponse(prompt);
        logger.debug("|||||||||| response :" + r);

        String[] sec = r.split("(?m)^\\d+\\s+");
        for (int i = 1; i < sec.length; i++) {
            response.add(sec[i].trim());
        }

        return response;
    }
}
