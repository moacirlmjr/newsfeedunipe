package br.com.unipe.newsFeed.model.service;

import br.com.unipe.newsFeed.model.beans.Autorizacao;
import br.com.unipe.newsFeed.model.service.comum.NewsFeedService;

public interface AutorizacaoService extends NewsFeedService<Autorizacao> {
	
	public Autorizacao getAutorizacaoByName(String nome) throws Exception;

}
