package br.com.unipe.newsFeed.model.service;

import java.util.List;

import br.com.unipe.newsFeed.model.beans.Noticia;
import br.com.unipe.newsFeed.model.service.comum.NewsFeedService;

public interface NoticiaService extends NewsFeedService<Noticia> {

	List<Noticia> listBySize(Integer tamanho);

}
