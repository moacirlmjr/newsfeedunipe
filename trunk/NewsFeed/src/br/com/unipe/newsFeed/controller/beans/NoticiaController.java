package br.com.unipe.newsFeed.controller.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import br.com.unipe.newsFeed.controller.beans.comum.NewsFeedController;
import br.com.unipe.newsFeed.model.beans.Categoria;
import br.com.unipe.newsFeed.model.beans.Noticia;
import br.com.unipe.newsFeed.model.beans.Usuario;
import br.com.unipe.newsFeed.model.service.CategoriaService;
import br.com.unipe.newsFeed.model.service.NoticiaService;
import br.com.unipe.newsFeed.model.util.FacesUtil;
import br.com.unipe.newsFeed.model.util.NewsFeedLog;
import br.com.unipe.newsFeed.model.util.SpringUtil;

@Named("noticiaController")
@Scope("request")
public class NoticiaController extends NewsFeedController {

	private Noticia noticia;

	@Autowired
	private NoticiaService noticiaService;

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private SpringUtil springUtil;

	public NoticiaController() {
		noticia = new Noticia();
	}

	public Collection<Noticia> getListNoticias() {
		try {

			return noticiaService.list();

		} catch (Exception e) {
			FacesUtil.registrarErro("erro.listar");
			return null;
		}

	}

	public Collection<SelectItem> getCategoriaSelectItens() {

		try {
			List<SelectItem> selectItens = new ArrayList<SelectItem>();
			List<Categoria> categorias = categoriaService.list();

			for (Categoria c : categorias) {
				selectItens.add(new SelectItem(c,c.getNome()));
			}

			return selectItens;

		} catch (Exception e) {
			FacesUtil.registrarErro("erro.carregar.categoria");
			NewsFeedLog.error(e);
		}

		return null;
	}

	public String prepararCadastroNoticia() {
		return "cadastroNoticia";
	}

	public String cadastrarNoticia() {
		try {
			Usuario userLogado = springUtil.getSessionUser();
			noticia.setUser(userLogado);
			noticia.setDate(Calendar.getInstance());
			noticiaService.createOrUpdate(noticia);
		} catch (Exception e) {
			FacesUtil.registrarErro("erro.inclusao");
			NewsFeedLog.error(e);
		}

		registrarSucessoInclusao();
		return "listNews";
	}

	/**
	 * @return the noticia
	 */
	public Noticia getNoticia() {
		return noticia;
	}

	/**
	 * @param noticia
	 *            the noticia to set
	 */
	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

}
