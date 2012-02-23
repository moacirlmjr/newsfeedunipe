package br.com.unipe.newsFeed.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unipe.newsFeed.model.beans.Categoria;
import br.com.unipe.newsFeed.model.util.HibernateUtil;

@Repository
@Transactional
public class CategoriaDAOImpl implements CategoriaDAO {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public CategoriaDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Categoria createOrUpdate(Categoria objeto) throws Exception {
		return (Categoria) sessionFactory.getCurrentSession().merge(objeto);
	}

	@Override
	public Categoria findById(Long id) throws Exception {
		return (Categoria) sessionFactory.getCurrentSession().get(Categoria.class, id);
	}

	@Override
	public List<Categoria> list() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(Categoria.class).list();
	}

	@Override
	public void remove(Categoria objeto) throws Exception {
		sessionFactory.getCurrentSession().delete(objeto);
	}

	@Override
	public List<Categoria> getBeansByExample(Categoria bean) throws Exception {
		Example example = HibernateUtil.getExample(bean);
		List<Categoria> result = sessionFactory.getCurrentSession()
				.createCriteria(Categoria.class).add(example).list();
		return result;
	}

}
