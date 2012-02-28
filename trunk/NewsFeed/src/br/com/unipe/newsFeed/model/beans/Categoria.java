package br.com.unipe.newsFeed.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.unipe.newsFeed.model.beans.comum.NewsFeedEntityMaster;

@Entity
public class Categoria extends NewsFeedEntityMaster {

	@Column(unique=true)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
