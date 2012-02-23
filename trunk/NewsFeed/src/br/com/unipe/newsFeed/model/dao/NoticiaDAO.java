package br.com.unipe.newsFeed.model.dao;

import java.util.List;

import br.com.unipe.newsFeed.model.beans.Noticia;
import br.com.unipe.newsFeed.model.dao.comum.NewsFeedDAO;

public interface NoticiaDAO extends NewsFeedDAO<Noticia> {

	public List<Noticia> listBySize(Integer tamanho);
}
