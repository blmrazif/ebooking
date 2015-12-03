package my.unimas.ebooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Ldap Authentication
     *
     * @param auth
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
//                .inMemoryAuthentication()
//                .withUser("blmrazif").password("razen6").authorities("ROLE_ADMIN");
//
                .ldapAuthentication()
                .userDnPatterns("cn={0},cn=Users,dc=dc,dc=unimas,dc=my")
                .contextSource(contextSource());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                    .loginPage("/login")
                    .permitAll()

                .and().logout().logoutSuccessUrl("/")

                .and().authorizeRequests()
//
                .antMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()

                .and().csrf().disable();

    }

    @Bean
    @ConfigurationProperties(prefix = "ldap.contextSource")
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(ContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }


    /*
     For CSRF TOKEN #2
     */
    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                    HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                        .getName());
                if (csrf != null) {
                    String token = csrf.getToken();
                    Cookie cookie = new Cookie("csrftoken", token);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    /*  
     FOR CSRF TOKEN
     private CsrfTokenRepository csrfTokenRepository() {
     HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
     repository.setHeaderName("X-CSRFToken");
     System.out.println("csrfTokenRepository()");
     return repository;
     }    */
}
