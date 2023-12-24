package br.com.flosnexu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo Nome de usuario é obrigatorio !!")
	@Size(max = 100, message = "O atributo Nome de usuario deve conter no máximo 100 caracteres")
	@Column(length = 100)
	private String nome;

	@NotBlank(message = "O atributo Foto é obrigatorio !!")
	@Size(max = 1000, message = "O atributo Foto deve conter no máximo 100 caracteres")
	@Column(length = 1000)
	private String foto;
	
	@NotBlank(message = "O atributo Tipo é obrigatorio !!")
	@Size(max = 100, message = "O atributo Tipo deve conter no máximo 100 caracteres")
	@Column(length = 100)
	private String tipo;

	@NotBlank(message = "O atributo E-mail é obrigatorio !!")
	@Size(max = 100, message = "O atributo E-mail deve conter no máximo 100 caracteres")
	@Column(length = 100)
	private String email;

	@NotBlank(message = "O atributo Senha é obrigatorio !!")
	@Size(min = 5, max = 100, message = "O atributo Senha deve conter no mínimo 05 e no máximo 100 caracteres")
	@Column(length = 100)
	private String senha;
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produto;

	public List<Produto> getProduto() {
		return produto;
	} 

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
