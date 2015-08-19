package unitec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by campitos on 5/08/15.
 */
@RestController
@RequestMapping("/")
public class UiApplication {
    @RequestMapping("/recurso")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("contenido", "Hola mundo malo");
        return model;
    }

    @RequestMapping("/user")
    public Principal user(Principal user){
     return  user;
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/index.html","/home.html","/login.html","/").permitAll()
                    .anyRequest().authenticated().and()
                    .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                    .csrf().csrfTokenRepository(csrfTokenRepository());
        }
        private CsrfTokenRepository csrfTokenRepository(){
            HttpSessionCsrfTokenRepository repository=new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return  repository;
        }
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("campitos").password("yomero").roles("USER");
        }
    }


}
