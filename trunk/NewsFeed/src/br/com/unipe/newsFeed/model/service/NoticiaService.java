package br.com.unipe.newsFeed.model.service;

import java.util.List;

import br.com.unipe.newsFeed.model.beans.Noticia;
import br.com.unipe.newsFeed.model.service.comum.NewsFeedService;

public interface NoticiaService extends NewsFeedService<Noticia> {

	public List<Noticia> listBySize(Integer tamanho) throws Exception;
	public List<Noticia> listNewsDay() throws Exception;
	public List<Noticia> listBySize(Integer tamanho, String categoria) throws Exception;

}
