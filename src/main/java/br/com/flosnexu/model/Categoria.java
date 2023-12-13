package br.com.flosnexu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

	@Entity
	@Table(name = "tb_categoria")
	public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo categoria é obrigatorio !!")
	@Size(min = 5, max = 100, message = "O atributo categoria deve conter no mínimo 05 e no máximo 100 caracteres")
	@Column(length = 100)
	private String nome_categoria; 
	
	@Size(max = 1000, message = "O atributo texto deve conter no máximo 1000 caracteres") 
	private String icone_categoria;
	
	@NotBlank(message = "O atributo descrição é obrigatório!")
	private String descricao_categoria;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome_categoria() {
		return nome_categoria;
	}


	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}


	public String getIcone_categoria() {
		return icone_categoria;
	}


	public void setIcone_categoria(String icone_categoria) {
		this.icone_categoria = icone_categoria;
	}


	public String getDescricao_categoria() {
		return descricao_categoria;
	}


	public void setDescricao_categoria(String descricao_categoria) {
		this.descricao_categoria = descricao_categoria;
	}
	
	
}