package br.com.queue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Empresa {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotBlank(message="Campo nome não pode ficar em branco.")
	private String nome;
	
	@Column
	@NotBlank(message="Campo CNPJ não pode ficar em branco.")
	private String cnpj;
	
	@Column
	@NotBlank(message="Campo rua não pode ficar em branco.")
	private String rua;
	
	@Column
	@NotBlank(message="Campo bairro não pode ficar em branco.")
	private String bairro;
	
	@Column
	@NotBlank(message="Campo número não pode ficar em branco.")
	private String numero;
	
	@Column
	@NotBlank(message="Campo telefone não pode ficar em branco.")
	private String telefone;

	public Empresa() {
		
	}
	
	//Getters & Setters
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
