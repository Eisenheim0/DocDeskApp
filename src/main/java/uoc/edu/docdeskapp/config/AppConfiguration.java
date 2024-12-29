package uoc.edu.docdeskapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfiguration {

    @Value("${uoc.edu.docdesk.prompt.principal}")
    private String promptPrincipal;

    public String getPromptPrincipal() {
        return promptPrincipal;
    }
}
