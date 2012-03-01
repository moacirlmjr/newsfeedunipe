package br.com.unipe.newsFeed.model.dao;

import br.com.unipe.newsFeed.model.beans.Autorizacao;
import br.com.unipe.newsFeed.model.dao.comum.NewsFeedDAO;

public interface AutorizacaoDAO extends NewsFeedDAO<Autorizacao>{

	public Autorizacao getAutorizacaoByName(String nome) throws Exception;

}
