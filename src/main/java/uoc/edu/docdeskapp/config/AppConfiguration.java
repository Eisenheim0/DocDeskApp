package uoc.edu.docdeskapp.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfiguration {

    @Value("${OPENAI_API_KEY:}")
    private String apiKey;

    @Value("${uoc.edu.docdesk.prompt.principal}")
    private String promptPrincipal;

    @Value("${uoc.edu.docdesk.prompt.medicamentos}")
    private String promptMedicamentos;

    @Value("${uoc.edu.docdesk.prompt.consejos}")
    private String promptConsejos;

    @Value("${uoc.edu.docdesk.prompt.epicrisis}")
    private String promptEpicrisis;

    @Value("${uoc.edu.docdesk.prompt.protocolos}")
    private String promptProtocolos;

    @Value("${uoc.edu.docdesk.prompt.nohtml}")
    private String promptNoHtml;

    public String getPromptPrincipal() {
        return promptPrincipal;
    }

    public String getPromptMedicamentos() {
        return promptMedicamentos;
    }

    public String getPromptConsejos() {
        return promptConsejos;
    }

    public String getPromptEpicrisis() {
        return promptEpicrisis;
    }

    public String getPromptProtocolos() {
        return promptProtocolos;
    }

    public String getPromptNoHtml() {
        return promptNoHtml;
    }

    @PostConstruct
    public void validateApiKey() {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("OPENAI_API_KEY is not set in the environment");
        }

        System.out.println("OPENAI API Key is set: " + apiKey);
    }
}
