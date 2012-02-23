package br.com.unipe.newsFeed.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unipe.newsFeed.model.beans.Usuario;
import br.com.unipe.newsFeed.model.util.HibernateUtil;

@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public UsuarioDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario createOrUpdate(Usuario objeto) throws Exception {
		return (Usuario) sessionFactory.getCurrentSession().merge(objeto);
	}

	@Override
	public Usuario findById(Long id) throws Exception {
		return (Usuario) sessionFactory.getCurrentSession().get(
				Usuario.class, id);
	}

	@Override
	public List<Usuario> list() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Usuario.class).list();
	}

	@Override
	public void remove(Usuario objeto) throws Exception {
		sessionFactory.getCurrentSession().delete(objeto);
	}

	@Override
	public List<Usuario> getBeansByExample(Usuario bean) throws Exception {
		Example example = HibernateUtil.getExample(bean);
		List<Usuario> result = sessionFactory.getCurrentSession()
				.createCriteria(Usuario.class).add(example).list();
		return result;
	}

}
