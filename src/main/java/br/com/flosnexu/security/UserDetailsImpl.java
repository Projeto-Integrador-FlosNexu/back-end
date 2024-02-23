package br.com.flosnexu.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.flosnexu.model.Usuario;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;

	// Construtor que recebe um objeto do tipo Usuario e inicializa as propriedades
	// userName e password
	public UserDetailsImpl(Usuario user) {
		this.userName = user.getEmail();
		this.password = user.getSenha();
	}

	// Construtor padrão vazio
	public UserDetailsImpl() {
	}

	// Retorna a lista de autoridades (papéis) associadas a este usuário
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	// Retorna a senha associada a este usuário
	@Override
	public String getPassword() {
		return password;
	}

	// Retorna o nome de usuário associado a este usuário
	@Override
	public String getUsername() {
		return userName;
	}

	// Indica se a conta deste usuário não está expirada (sempre retorna true neste
	// caso)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// Indica se a conta deste usuário não está bloqueada (sempre retorna true neste
	// caso)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// Indica se as credenciais deste usuário não estão expiradas (sempre retorna
	// true neste caso)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// Indica se este usuário está habilitado (sempre retorna true neste caso)
	@Override
	public boolean isEnabled() {
		return true;
	}

}
