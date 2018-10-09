package br.com.clinica.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.clinica.entidades.Cliente;

public interface RepositorioCliente extends JpaRepository<Cliente, Long>{

	List<Cliente> findById(@Param("id") Long id);
	
	@Query("SELECT a FROM Cliente a WHERE a.nome LIKE %:nome%")
	List<Cliente> findByNome(@Param("nome") String nome);
	
}
