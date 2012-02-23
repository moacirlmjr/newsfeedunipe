

package br.com.unipe.newsFeed.model.beans;

import javax.persistence.Entity;

import br.com.unipe.newsFeed.model.beans.comum.NewsFeedEntityMaster;


@Entity
public class Autorizacao extends NewsFeedEntityMaster{

    private String nome;

    public Autorizacao() {
    }
    
    public Autorizacao(String nome) {
    	this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
