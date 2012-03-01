package br.com.unipe.newsFeed.integracao.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import br.com.unipe.newsFeed.model.util.HibernateCarga;
import br.com.unipe.newsFeed.model.util.NewsFeedLog;

@Component
@Path("/preencherbase")
@Produces(MediaType.TEXT_PLAIN)
public class PreencherBase {

	@GET
	public String preencherBase() {
		try {

			HibernateCarga hc = new HibernateCarga();

			hc.carregarCategorias();
			hc.carregarAutorizacoes();
			hc.carregarAdministrador();

			return "ok";
		} catch (Exception e) {
			NewsFeedLog.error(e);
		}
		return "erro";

	}

}
