
package com.zupacademy.izabella.propostas.compartilhado.config.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers("**/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST, "api/propostas/**").hasAuthority("SCOPE_proposta")
                .antMatchers(HttpMethod.GET, "api/propostas/**").hasAuthority("SCOPE_proposta")
                .antMatchers(HttpMethod.POST, "api/cartoes/**").hasAuthority("SCOPE_proposta")
                .anyRequest().authenticated()

        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);


    }
}