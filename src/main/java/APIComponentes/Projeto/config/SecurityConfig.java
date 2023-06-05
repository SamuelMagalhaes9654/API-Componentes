package APIComponentes.Projeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import APIComponentes.Projeto.service.UsuarioService;

@EnableWebSecurity
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
        .and().csrf().disable().authorizeHttpRequests(//(authz) -> authz
            authorizeConfig -> {
                authorizeConfig.requestMatchers("/componentes").permitAll();
                authorizeConfig.requestMatchers("/componentes/admin").hasRole("ADMIN");
                authorizeConfig.requestMatchers("/usuarios").permitAll();
                authorizeConfig.requestMatchers("/tokenauth").permitAll();
                authorizeConfig.requestMatchers("/tokenverif").permitAll();
                authorizeConfig.anyRequest().authenticated();
            }
                
        ).httpBasic();
        return http.build();
        //.formLogin().and()
    }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsService() {
    //     PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    //     UserDetails user = User.withUsername("admin")
    //             .password(encoder.encode("admin"))
    //             .roles("USER","ADMIN")
    //             .build();
    //     return new InMemoryUserDetailsManager(user);
    // }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UsuarioService usuarioService) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
 
    //.passwordEncoder(passwordEncoder) adicionar antes do end

}






