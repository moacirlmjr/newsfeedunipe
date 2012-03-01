package br.com.unipe.newsFeed.model.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.unipe.newsFeed.model.beans.comum.NewsFeedEntityMaster;

@Entity
public class Usuario extends NewsFeedEntityMaster {

	@NotEmpty(message = "{campo.nulo}")
	private String nome;
	
	@Email(message = "{email.invalido}")
	@NotEmpty(message = "{campo.nulo}")
	@Column(unique=true)
	private String email;
	
	@NotEmpty(message = "{campo.nulo}")
	private String senha;

	@OneToOne(targetEntity=Autorizacao.class, cascade=CascadeType.ALL)
	private Autorizacao autorizacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Autorizacao getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(Autorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

}
