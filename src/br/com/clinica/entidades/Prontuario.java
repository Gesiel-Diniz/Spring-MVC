package br.com.clinica.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "protuarios")
public class Prontuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_animal")
	@NotNull(message = "O animal é obrigatório!")
	@JsonBackReference
	private Animal animal;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_medico")
	@NotNull(message = "O medico é obrigatório!")
	@JsonBackReference
	private Medico medico;

	@Column(name = "data_atendimento", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo data do atendimento é obrigatório!")
	private Date dataAtendimento;

	@Column(name = "observacoes", length = 255, nullable = true)
	private String observacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public Long getId_animal() {
		return animal.getId();
	}
	
	public Long getId_medico() {
		return medico.getId();
	}

}