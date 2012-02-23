package br.com.unipe.newsFeed.model.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.unipe.newsFeed.model.beans.Categoria;
import br.com.unipe.newsFeed.model.dao.CategoriaDAO;

@Named
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CategoriaDAO categoriaDAO;

	@Override
	public Categoria createOrUpdate(Categoria objeto) throws Exception {
		return categoriaDAO.createOrUpdate(objeto);
	}

	@Override
	public Categoria findById(Long id) throws Exception {
		return categoriaDAO.findById(id);
	}

	@Override
	public List<Categoria> list() throws Exception {
		return categoriaDAO.list();
	}

	@Override
	public void remove(Categoria objeto) throws Exception {
		categoriaDAO.remove(objeto);
	}

	@Override
	public List<Categoria> listByExample(Categoria bean) throws Exception {
		return categoriaDAO.getBeansByExample(bean);
	}

}
