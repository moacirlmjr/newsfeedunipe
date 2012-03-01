package br.com.unipe.newsFeed.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl.CriterionEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unipe.newsFeed.model.beans.Autorizacao;
import br.com.unipe.newsFeed.model.util.HibernateUtil;

@Repository
@Transactional
public class AutorizacaoDAOImpl implements AutorizacaoDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public AutorizacaoDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Autorizacao createOrUpdate(Autorizacao objeto) throws Exception {
		return (Autorizacao) sessionFactory.getCurrentSession().merge(objeto);
	}

	@Override
	public Autorizacao findById(Long id) throws Exception {
		return (Autorizacao) sessionFactory.getCurrentSession().get(
				Autorizacao.class, id);
	}

	@Override
	public List<Autorizacao> list() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Autorizacao.class).list();
	}

	@Override
	public void remove(Autorizacao objeto) throws Exception {
		sessionFactory.getCurrentSession().delete(objeto);
	}

	@Override
	public List<Autorizacao> getBeansByExample(Autorizacao bean) throws Exception {
		Example example = HibernateUtil.getExample(bean);
		List<Autorizacao> result = sessionFactory.getCurrentSession()
				.createCriteria(Autorizacao.class).add(example).list();
		return result;
	}
	
	@Override
	public Autorizacao getAutorizacaoByName(String nome) throws Exception {
		Autorizacao result = (Autorizacao) sessionFactory.getCurrentSession()
				.createCriteria(Autorizacao.class).add(Restrictions.eq("nome", nome)).uniqueResult();
		return result;
	}

}
