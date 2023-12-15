package br.com.flosnexu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.flosnexu.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
	public List <Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome); //buscar por nome
	public List <Produto> findAllByMarcaContainingIgnoreCase(@Param("marca") String marca); //buscar por marca
	
}
