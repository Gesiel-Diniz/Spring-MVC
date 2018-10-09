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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "medicos")
public class Medico{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="nome", length = 100, nullable = false)
	@Size(min=3, max=100, message="O nome do médico deve conter no mínimo 3 caracteres!")
	@NotEmpty(message="O nome do médico é obrigatório!")
	private String nome;
	
	@Column(name="especialidade", length = 50, nullable = false)
	@NotEmpty(message="A especialidade é obrigatória!")
	private String especialidade;
	
	@Column(name="numero_conselho", length = 50, nullable = false)
	@NotEmpty(message="O número do conselho é obrigatório!")
	private String numeroConselho;
	
	@Column(name="username", length = 50, nullable = false)
	@NotEmpty(message="O login é obrigatório!")
	private String username;
	
	@Column(name="senha", length = 150, nullable = false)
	@NotEmpty(message="A senha é obrigatória!")
	@NotNull(message="A senha é obrigatória!")
	private String senha;

	@JsonIgnore
	@OneToMany(mappedBy="medico", fetch=FetchType.EAGER, cascade=CascadeType.MERGE, orphanRemoval=false)
	private Set<Prontuario> prontuarios;
	
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

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getNumeroConselho() {
		return numeroConselho;
	}

	public void setNumeroConselho(String numeroConselho) {
		this.numeroConselho = numeroConselho;
	}

	public Set<Prontuario> getProntuario() {
		return prontuarios;
	}

	public void setProntuario(Set<Prontuario> prontuarios) {
		this.prontuarios = prontuarios;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}