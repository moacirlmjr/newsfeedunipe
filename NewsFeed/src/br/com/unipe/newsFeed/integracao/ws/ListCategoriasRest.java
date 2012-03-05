package br.com.unipe.newsFeed.integracao.ws;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.unipe.newsFeed.model.beans.Noticia;
import br.com.unipe.newsFeed.model.service.CategoriaService;
import br.com.unipe.newsFeed.model.service.NoticiaService;
import br.com.unipe.newsFeed.model.util.JSONUtil;
import br.com.unipe.newsFeed.model.util.NewsFeedLog;

@Component
@Path("/qtenoticiaslist")
@Produces(MediaType.TEXT_PLAIN)
public class ListCategoriasRest {

	@Autowired
	private NoticiaService service;

	@Autowired
	private CategoriaService categoriaService;

	@POST
	public String listByTamanho() {
		try {

			List<Noticia> listNoticia = this.service.listNewsDay();

			return JSONUtil.montarJsonQteNoticiaByCategoria(listNoticia,
					categoriaService.list()).toString();

		} catch (Exception e) {
			NewsFeedLog.error(e);
		}
		return "erro";

	}

	@GET
	public String listAll() {
		try {

			List<Noticia> listNoticia = this.service.listNewsDay();

			return JSONUtil.montarJsonQteNoticiaByCategoria(listNoticia,
					categoriaService.list()).toString();

		} catch (Exception e) {
			NewsFeedLog.error(e);
		}
		return "erro";

	}

}
