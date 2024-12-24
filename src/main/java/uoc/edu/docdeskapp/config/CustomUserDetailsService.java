package uoc.edu.docdeskapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uoc.edu.docdeskapp.entity.UsuarioEntity;
import uoc.edu.docdeskapp.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = userRepository.findByUsername(username);
        if (usuarioEntity == null) {
            throw new UsernameNotFoundException("Username or Password not found");
        }
        logger.debug("|||||||||| " + usuarioEntity);
        return new CustomUserDetails(usuarioEntity);
    }

    public Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }
}
