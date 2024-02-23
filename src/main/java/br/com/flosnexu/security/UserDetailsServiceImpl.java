package br.com.flosnexu.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.flosnexu.model.Usuario;
import br.com.flosnexu.repository.UsuarioRepository;

//Importação de bibliotecas omitida para brevidade

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Método obrigatório da interface UserDetailsService, usado para carregar
	// detalhes do usuário pelo nome de usuário
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		// Busca um usuário no repositório pelo nome de usuário (email)
		Optional<Usuario> usuario = usuarioRepository.findByEmail(userName);

		// Verifica se o usuário foi encontrado
		if (usuario.isPresent()) {
			// Retorna uma instância de UserDetailsImpl, que implementa a interface
			// UserDetails
			return new UserDetailsImpl(usuario.get());
		} else {
			// Se o usuário não for encontrado, lança uma exceção indicando que o nome de
			// usuário não foi encontrado
			// Isso pode ser tratado como uma resposta de status FORBIDDEN (403)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
	}
}
