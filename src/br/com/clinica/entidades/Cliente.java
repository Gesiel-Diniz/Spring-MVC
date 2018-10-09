package br.com.clinica.entidades;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="nome", length = 100, nullable = false)
	@Size(min=3, max=100, message="O nome do cliente deve conter no mínimo 3 caracteres!")
	@NotEmpty(message="O nome do cliente é obrigatório!")
	private String nome;

	@Column(name="telefone", length = 15, nullable = false)
	@Size(min=8, max=15, message="O telefone do cliente deve conter no mínimo 8 dígitos!")
	@NotEmpty(message="O telefone do cliente é obrigatório!")
	private String telefone;
	
	@Column(name="email", length = 50, nullable = true)
	private String email;

    @JsonIgnore
	@OneToMany(mappedBy="cliente", fetch=FetchType.EAGER, cascade=CascadeType.MERGE, orphanRemoval=false)
	private Set<Animal> animais;

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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Animal> getAnimal() {
		return animais;
	}
	public void setAnimal(Set<Animal> animais) {
		this.animais = animais;
	}
	
}
