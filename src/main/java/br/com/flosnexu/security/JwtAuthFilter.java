package br.com.flosnexu.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService; // Serviço responsável por operações relacionadas a JWT

	@Autowired
	private UserDetailsServiceImpl userDetailsService; // Implementação customizada de UserDetailsService

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		try {
			// Verifica se o cabeçalho de autorização está presente e inicia com "Bearer "
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				token = authHeader.substring(7);
				username = jwtService.extractUsername(token); // Extrai o nome de usuário do token
			}

			// Verifica se o nome de usuário foi extraído do token e se o contexto de
			// segurança não contém autenticação
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// Carrega os detalhes do usuário com base no nome de usuário
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				// Valida o token JWT
				if (jwtService.validateToken(token, userDetails)) {
					// Cria um objeto de autenticação e o configura no contexto de segurança
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}

			// Chama o próximo filtro na cadeia
			filterChain.doFilter(request, response);

		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| ResponseStatusException e) {
			// Manipula exceções relacionadas ao token JWT (por exemplo, token expirado,
			// formato inválido, assinatura inválida)
			response.setStatus(HttpStatus.FORBIDDEN.value()); // Define o status da resposta como proibido (403)
			return;
		}
	}
}
