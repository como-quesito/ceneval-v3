package unitec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by campitos on 8/09/15.
 */

public class UsuarioAutenticado /*implements UserDetailsService*/{
    public UsuarioAutenticado() {
    }
/*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
ProfesorListas profesorListas=new ProfesorListas();

    Profesor p=    profesorListas.buscarPorLogin(username);
        if (p != null) {
            List<GrantedAuthority> authorities =
                    new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            return new User(
                    p.getLogin(),
                    p.getPassword(),
                    authorities);
        }throw new UsernameNotFoundException(
                "User '" + username + "' not found.");
    }
    */
}
