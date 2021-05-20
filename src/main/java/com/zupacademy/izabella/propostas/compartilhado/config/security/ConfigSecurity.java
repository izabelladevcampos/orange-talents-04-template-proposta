
package com.zupacademy.izabella.propostas.compartilhado.config.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
@Configuration
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http.authorizeRequests(authorizeRequests -> authorizeRequests
                        .mvcMatchers("**/actuator/**").permitAll()
                        .mvcMatchers(HttpMethod.GET, "/teste/**").hasAuthority("SCOPE_proposta")
                        .mvcMatchers(HttpMethod.POST, "/api/propostas/**").hasAuthority("SCOPE_proposta")
                        .mvcMatchers(HttpMethod.PATCH, "/api/cartoes/**").hasAuthority("SCOPE_proposta")

        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}