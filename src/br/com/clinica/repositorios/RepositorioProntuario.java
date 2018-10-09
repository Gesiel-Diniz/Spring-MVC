package br.com.clinica.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.clinica.entidades.Prontuario;

public interface RepositorioProntuario extends JpaRepository<Prontuario, Long>{

	List<Prontuario> findById(@Param("id") Long id);
	
	@Query("SELECT a FROM Prontuario a WHERE a.observacoes LIKE %:nome%")
	List<Prontuario> findByNome(@Param("nome") String nome);
	
}
