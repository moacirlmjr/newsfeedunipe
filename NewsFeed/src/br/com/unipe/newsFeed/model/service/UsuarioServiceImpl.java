package br.com.unipe.newsFeed.model.service;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.unipe.newsFeed.model.beans.Usuario;
import br.com.unipe.newsFeed.model.dao.UsuarioDAO;

@Named
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public Usuario createOrUpdate(Usuario objeto) throws Exception {
		return usuarioDAO.createOrUpdate(objeto);
	}

	@Override
	public Usuario findById(Long id) throws Exception {
		return usuarioDAO.findById(id);
	}

	@Override
	public List<Usuario> list() throws Exception {
		return usuarioDAO.list();
	}

	@Override
	public void remove(Usuario objeto) throws Exception {
		usuarioDAO.remove(objeto);
	}

	@Override
	public List<Usuario> listByExample(Usuario bean) throws Exception {
		return usuarioDAO.getBeansByExample(bean);
	}

}
