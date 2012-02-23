/**
 * 
 */
package br.com.unipe.newsFeed.model.beans;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.unipe.newsFeed.model.beans.comum.NewsFeedEntityMaster;

/**
 * @author moacir
 * 
 */

@Entity
public class Noticia extends NewsFeedEntityMaster {

	private String titulo;
	private String mensagem;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;

	@OneToOne(targetEntity = Categoria.class)
	private Categoria categoria;

	@OneToOne(targetEntity = Usuario.class)
	private Usuario user;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}
