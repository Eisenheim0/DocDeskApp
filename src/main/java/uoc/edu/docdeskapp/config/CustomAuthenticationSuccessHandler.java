package uoc.edu.docdeskapp.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
        for (GrantedAuthority authority: authorities) {
            String auth = authority.getAuthority();
            if ("Administrador".equals(auth)) {
                response.sendRedirect("/docdesk/users");
                return;
            } else if ("Médico".equals(auth)) {
                response.sendRedirect("/docdesk/patients");
                return;
            }
        }

        response.sendRedirect("/docdesk/dates");
    }
}
