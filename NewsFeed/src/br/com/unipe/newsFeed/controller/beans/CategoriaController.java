package br.com.unipe.newsFeed.controller.beans;

import java.util.Collection;

import javax.inject.Named;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import br.com.unipe.newsFeed.controller.beans.comum.NewsFeedController;
import br.com.unipe.newsFeed.model.beans.Categoria;
import br.com.unipe.newsFeed.model.service.CategoriaService;
import br.com.unipe.newsFeed.model.util.FacesUtil;
import br.com.unipe.newsFeed.model.util.NewsFeedLog;

@Named("categoriaController")
@Scope("request")
public class CategoriaController extends NewsFeedController {

	@Valid
	private Categoria categoria;

	@Autowired
	private CategoriaService categoriaService;
	
	public CategoriaController() {
		categoria = new Categoria();
	}

	public Collection<Categoria> getListCategorias() {
		try {

			return categoriaService.list();

		} catch (Exception e) {
			FacesUtil.registrarErro("erro.listar");
			return null;
		}

	}

	public String prepararCadastroCategoria() {
		categoria = new Categoria();
		return "cadastroCategoria";
	}

	public String cadastrarCategoria() {
		try {
			categoriaService.createOrUpdate(categoria);
		} catch (Exception e) {
			FacesUtil.registrarErro("erro.inclusao");
			NewsFeedLog.error(e);
		}

		registrarSucessoInclusao();
		return "listCategoria";
	}
	
	/**
	 * @return the noticia
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param noticia
	 *            the noticia to set
	 */
	public void setCategoria(Categoria noticia) {
		this.categoria = noticia;
	}

}
