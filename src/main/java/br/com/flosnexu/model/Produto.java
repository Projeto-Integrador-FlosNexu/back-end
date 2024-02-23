package br.com.flosnexu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O Atributo nome é Obrigatorio!")
	@Size(max = 255, message = "O Atributo Nome deve ter no minimo 3 e no maximo 50 Caracteres")
	@Column(length = 255)
	private String nome;

	@NotBlank(message = "O Atributo Descrição é obrigatório")
	@Size(max = 1000, message = "O Atributo Nome deve ter no maximo 1000 Caracteres")
	@Column(length = 1000)
	private String descricao;

	@NotNull(message = "O atributo preço é Obrigatorio!")
	private Double preco;

	@Size(min = 3, max = 255, message = "O Atributo Nome deve ter no minimo 3 e no maximo 50 Caracteres")
	private String foto;

	@Size(max = 255, message = "O Atributo Nome deve ter no minimo 3 e no maximo 50 Caracteres")
	@Column(length = 255)
	private String marca;

	private int quantidade;

	// Relacionando com tabela categoria
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}