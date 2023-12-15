package br.com.flosnexu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Size(min = 3, max = 255, message = "O Atributo Nome deve ter no minimo 3 e no maximo 50 Caracteres")
	private String nome;
	
	@NotBlank(message = "O Atributo Descrição é obrigatório")
	@Size(min = 20, max = 1000, message = "O Atributo Nome deve ter no minimo 20 e no maximo 1000 Caracteres")
	private String descricao;
	
	@NotNull(message = "O atributo preço é Obrigatorio!")
	private Double preco;
	
	
}


/*		produtos precisa ter 
 * 
 * 		id		PK IA
 * 		Nome   NN
 * 		descricao  NN
 * 		preco  NN   
 * 		foto  Max string 255
 * 		marca Max string 255
 * 		quantidade Max string 255
 * 		
 * 		Integração com Categoria
 * 		
 * 
 * 
 */