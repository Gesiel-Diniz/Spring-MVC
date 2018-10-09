package br.com.clinica.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.clinica.entidades.Animal;

public interface RepositorioAnimal extends JpaRepository<Animal, Long>{
	
	List<Animal> findById(@Param("id") Long id);
	
	@Query("SELECT a FROM Animal a WHERE a.nome LIKE %:nome%")
	List<Animal> findByNome(@Param("nome") String nome);

}
