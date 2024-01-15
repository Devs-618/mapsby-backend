package by.maps.backend.security.provider.keycloak.config;

import by.maps.backend.security.common.SecurityUrlConfig;
import by.maps.backend.security.provider.keycloak.KeycloakLogoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@Profile({"local", "prod"})
@ConditionalOnProperty(name = "security.auth_provider", havingValue = "keycloak")
@RequiredArgsConstructor
@Log4j2
public class KeycloakSecurityConfig {
    private final KeycloakLogoutHandler keycloakLogoutHandler;
    private final SecurityUrlConfig securityUrlConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] patternsArr = securityUrlConfig.getUrls().toArray(new String[0]);
        http.oauth2Login(Customizer.withDefaults());
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(patternsArr).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .addLogoutHandler(keycloakLogoutHandler)
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .and()
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()))
                .formLogin().disable()
                .cors(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    protected LogoutSuccessHandler getLogoutSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        };
    }
}