package br.com.clinica.entidades;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "animais")
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="nome", length = 100, nullable = false)
	@NotEmpty(message="O nome do animal é obrigatório!")
	private String nome;
	
	@Column(name="idade", length = 3, nullable = false)
	private int idade;
	
	@Column(name="raca", length = 100, nullable = false)
	@NotEmpty(message="A raça do animal é obrigatória!")
	private String raca;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_cliente")
	@JsonBackReference
	private Cliente cliente;
	
	@JsonIgnore
	@OneToMany(mappedBy="animal", fetch=FetchType.EAGER, cascade=CascadeType.MERGE, orphanRemoval=false)
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
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Set<Prontuario> getProntuarios() {
		return prontuarios;
	}
	public void setProntuarios(Set<Prontuario> prontuarios) {
		this.prontuarios = prontuarios;
	}
	public Long getId_cliente() {
		return cliente.getId();
	}

}