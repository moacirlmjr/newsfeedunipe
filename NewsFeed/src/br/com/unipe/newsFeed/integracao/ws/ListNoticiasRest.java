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

import br.com.unipe.newsFeed.model.beans.Categoria;
import br.com.unipe.newsFeed.model.beans.Noticia;
import br.com.unipe.newsFeed.model.service.CategoriaService;
import br.com.unipe.newsFeed.model.service.NoticiaService;
import br.com.unipe.newsFeed.model.util.JSONUtil;
import br.com.unipe.newsFeed.model.util.NewsFeedLog;

@Component
@Path("/noticiaslist")
@Produces(MediaType.TEXT_PLAIN)
public class ListNoticiasRest {

	@Autowired
	private NoticiaService service;
	
	@Autowired
	private CategoriaService categoriaService;

	@POST
	public String listByTamanho(@FormParam("tamanho") @DefaultValue("0") Integer tamanho, @FormParam("categoria") String categoria) {
		try {

			List<Noticia> listNoticia = null;
			if(tamanho == 0){
				listNoticia = this.service.listBySize(tamanho, categoria);
			}else{
				listNoticia = this.service.listBySize(tamanho-1, categoria);
			}
			
			Categoria c = new Categoria();
			c.setNome(categoria);

			return JSONUtil.montarJsonNoticiaListByCategoria(listNoticia,categoriaService.listByExample(c))
					.toString();

		} catch (Exception e) {
			NewsFeedLog.error(e);
		}
		return "erro";
		
	}
	
	@GET
	public String listAll() {
		try {

			List<Noticia> listNoticia = null;

			listNoticia = this.service.list();

			return JSONUtil.montarJsonNoticiaListByCategoria(listNoticia,categoriaService.list())
					.toString();

		} catch (Exception e) {
			NewsFeedLog.error(e);
		}
		return "erro";
		
	}

}
