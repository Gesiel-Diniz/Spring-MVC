package br.com.clinica.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.clinica.entidades.Medico;

public interface RepositorioMedico extends JpaRepository<Medico, Long>{

	List<Medico> findById(@Param("id") Long id);
	
	@Query("SELECT a FROM Medico a WHERE a.nome LIKE %:nome%")
	List<Medico> findByNome(@Param("nome") String nome);
	
}
