package br.com.unipe.newsFeed.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.unipe.newsFeed.model.beans.comum.NewsFeedEntityMaster;

@Entity
public class Categoria extends NewsFeedEntityMaster {

	@NotEmpty(message = "{campo.nulo}")
	@Column(unique=true)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
