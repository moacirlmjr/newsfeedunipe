package br.com.unipe.newsFeed.model.service;

import java.util.Calendar;
import java.util.List;

import javax.inject.Named;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.unipe.newsFeed.model.beans.Noticia;
import br.com.unipe.newsFeed.model.dao.NoticiaDAO;

@Named
public class NoticiaServiceImpl implements NoticiaService {
	
	@Autowired
	private NoticiaDAO noticiaDAO;

	@Override
	public Noticia createOrUpdate(Noticia objeto) throws Exception {
		return noticiaDAO.createOrUpdate(objeto);
	}

	@Override
	public Noticia findById(Long id) throws Exception {
		return noticiaDAO.findById(id);
	}

	@Override
	public List<Noticia> list() throws Exception {
		return noticiaDAO.list();
	}

	@Override
	public void remove(Noticia objeto) throws Exception {
		noticiaDAO.remove(objeto);
	}

	@Override
	public List<Noticia> listByExample(Noticia bean) throws Exception {
		return noticiaDAO.getBeansByExample(bean);
	}

	@Override
	public List<Noticia> listBySize(Integer tamanho) throws Exception{
		return noticiaDAO.listBySize(tamanho);
	}
	
	@Override
	public List<Noticia> listNewsDay() throws Exception{
		return noticiaDAO.listNewsDay();
	}

	@Override
	public List<Noticia> listBySize(Integer tamanho, String categoria)
			throws Exception {
		return noticiaDAO.listBySize(tamanho, categoria);
	}
	

}
