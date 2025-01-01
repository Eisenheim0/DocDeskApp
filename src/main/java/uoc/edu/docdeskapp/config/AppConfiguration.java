package uoc.edu.docdeskapp.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfiguration {

    @Value("${OPENAI_API_KEY:}")
    private String apiKey;

    @Value("${DB_URL}")
    private String dbUrl;

    @Value("${DB_USER}")
    private String dbUser;

    @Value("${DB_PASSWORD}")
    private String dbPassword;

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

    @PostConstruct
    public void validateDbUrl() {
        if (dbUrl == null || dbUrl.isEmpty()) {
            throw new IllegalStateException("DB_URL is not set in the environment");
        }

        System.out.println("DB URL Key is set: " + dbUrl);
    }

    @PostConstruct
    public void validateDbUser() {
        if (dbUser == null || dbUser.isEmpty()) {
            throw new IllegalStateException("DB_USER is not set in the environment");
        }

        System.out.println("DB USER Key is set: " + dbUser);
    }

    @PostConstruct
    public void validateDbPassword() {
        if (dbPassword == null || dbPassword.isEmpty()) {
            throw new IllegalStateException("DB_PASSWORD is not set in the environment");
        }

        System.out.println("DB PASSWORD Key is set: " + dbPassword);
    }
}
