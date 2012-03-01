package br.com.unipe.newsFeed.model.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.unipe.newsFeed.model.beans.Autorizacao;
import br.com.unipe.newsFeed.model.dao.AutorizacaoDAO;

@Named
public class AutorizacaoServiceImpl implements AutorizacaoService {
	
	@Autowired
	private AutorizacaoDAO autorizacaoDAO;

	@Override
	public Autorizacao createOrUpdate(Autorizacao objeto) throws Exception {
		return autorizacaoDAO.createOrUpdate(objeto);
	}

	@Override
	public Autorizacao findById(Long id) throws Exception {
		return autorizacaoDAO.findById(id);
	}

	@Override
	public List<Autorizacao> list() throws Exception {
		return autorizacaoDAO.list();
	}

	@Override
	public void remove(Autorizacao objeto) throws Exception {
		autorizacaoDAO.remove(objeto);
	}

	@Override
	public List<Autorizacao> listByExample(Autorizacao bean) throws Exception {
		return autorizacaoDAO.getBeansByExample(bean);
	}
	
	public Autorizacao getAutorizacaoByName(String nome) throws Exception{
		return autorizacaoDAO.getAutorizacaoByName(nome);
	}

}
