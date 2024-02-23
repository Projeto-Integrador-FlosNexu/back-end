package br.com.flosnexu.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {

	@Autowired
	private JwtAuthFilter authFilter;

	// Configuração do UserDetailsService
	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	// Configuração do PasswordEncoder (BCrypt)
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Configuração do AuthenticationProvider com UserDetailsService e
	// PasswordEncoder
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	// Configuração do AuthenticationManager
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	// Configuração principal da segurança
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// Configuração da política de criação de sessão sem estado (STATELESS)
		http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable()) // Desabilita proteção contra CSRF
				.cors(withDefaults()); // Configura suporte para CORS

		http.authorizeHttpRequests((auth) -> auth.requestMatchers("/usuarios/logar").permitAll()
				.requestMatchers("/usuarios/cadastrar").permitAll().requestMatchers("/usuarios/produtos").permitAll()
				.requestMatchers("/usuarios/categorias").permitAll().requestMatchers("/error/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/produtos/**").permitAll() // Permite acesso sem autenticação ao método GET de /categorias
				.requestMatchers(HttpMethod.GET, "/categorias/**").permitAll() // Permite acesso sem autenticação ao método GET de /categorias
				.requestMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated()) //  Configura regras de
														// acesso para
														// diferentes URLs
			
				.authenticationProvider(authenticationProvider()) // Configura o AuthenticationProvider
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o JwtAuthFilter
													//  antes do
													//  UsernamePasswordAuthenticationFilter
				.httpBasic(withDefaults()); // Configura o método de autenticação como HTTP Basic

		return http.build();
	}
}
