package unitec;


import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import sun.security.krb5.Credentials;

import javax.inject.Inject;
import java.security.Principal;
import java.util.*;

/**
 * Created by campitos on 5/08/15.
 */
@RestController
@RequestMapping("/")
@SpringBootApplication
public class CenevalV3Application {



    @Inject  ServicioProfesor servicioProfesor;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(CenevalV3Application.class, args);
    }
    @RequestMapping("/recurso")
    public Map<String, Object> home () {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("contenido", "Historial (proximamente)");
        return model;
    }

    @RequestMapping("/user")
    public Map<String, Object> user (Principal user){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("username", user.getName());
        Profesor pro = servicioProfesor.obtenerPorLogin(user.getName());
        System.out.println("si lo dijo??" + pro.getLogin());
        Profesor propo= servicioProfesor.obtenerPorLogin(user.getName());
        String autoridad = propo.getAutoridad();
        map.put("autoridad", autoridad);
        return map;
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    @EnableWebSecurity
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Inject
        ServicioProfesor servicioProfesor;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/index.html", "/home.html", "/ingresar.html", "/", "/bower_components/**", "/build/**", "/fundacion/**").permitAll()
                    .anyRequest().authenticated().and()

                    .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                    .csrf().csrfTokenRepository(csrfTokenRepository());
        }

        private CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            //Checamos password


            auth
                    .userDetailsService(new UsuarioAutenticado());
/*
            auth.inMemoryAuthentication()
                    .withUser("Alumno").password("1234").roles("USER").and()
                     .withUser("Profesor").password("72020").roles("USER").and()
                    .withUser("Administrador").password("unitec2015").roles("USER", "ADMIN");

            auth.inMemoryAuthentication()
                    .withUser("Alumno").password("4321").roles("USER");

            auth.inMemoryAuthentication()
                    .withUser("Profesor").password("57430").roles("USER");

            auth.inMemoryAuthentication()
                    .withUser("Profesor").password("92156").roles("USER");

            auth.inMemoryAuthentication()
                    .withUser("Profesor").password("33868").roles("USER");
                    */
        }


    }

    static class UsuarioAutenticado implements UserDetailsService {


        public UsuarioAutenticado() {
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            ProfesorListas profesorListas = new ProfesorListas();
            Profesor p=new Profesor();
            MongoCero mongoCero=new MongoCero();
            try {
                p= mongoCero.buscarPorLogin(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //  System.out.println("si lo dijo internaaaa??"+pro.getLogin());
            //  Profesor p = profesorListas.buscarPorLogin(username);
            if (p != null) {
                List<GrantedAuthority> authorities =
                        new ArrayList<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("USER"));
                return new User(
                        p.getLogin(),
                        p.getPassword(),
                        authorities);
            }
            throw new UsernameNotFoundException(
                    "User '" + username + "' not found.");
        }
    }




}
