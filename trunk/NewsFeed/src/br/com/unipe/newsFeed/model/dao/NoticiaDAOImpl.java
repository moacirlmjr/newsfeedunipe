package br.com.unipe.newsFeed.model.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unipe.newsFeed.model.beans.Noticia;
import br.com.unipe.newsFeed.model.util.HibernateUtil;

@Repository
@Transactional
public class NoticiaDAOImpl implements NoticiaDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public NoticiaDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Noticia createOrUpdate(Noticia objeto) throws Exception {
		return (Noticia) sessionFactory.getCurrentSession().merge(objeto);
	}

	@Override
	public Noticia findById(Long id) throws Exception {
		return (Noticia) sessionFactory.getCurrentSession().get(
				Noticia.class, id);
	}

	@Override
	public List<Noticia> list() throws Exception {
		return sessionFactory.getCurrentSession()
				.createCriteria(Noticia.class).addOrder(Order.desc("date")).list();
	}

	@Override
	public void remove(Noticia objeto) throws Exception {
		sessionFactory.getCurrentSession().delete(objeto);
	}

	@Override
	public List<Noticia> getBeansByExample(Noticia bean) throws Exception {
		Example example = HibernateUtil.getExample(bean);
		List<Noticia> result = sessionFactory.getCurrentSession()
				.createCriteria(Noticia.class).add(example).list();
		return result;
	}

	@Override
	public List<Noticia> listBySize(Integer tamanho) throws Exception{
		return sessionFactory.getCurrentSession()
				.createCriteria(Noticia.class).addOrder(Order.desc("date")).setFirstResult(tamanho).setMaxResults(10).list();
	}
	
	@Override
	public List<Noticia> listNewsDay() throws Exception{
		return sessionFactory.getCurrentSession().createQuery("from Noticia where DATEDIFF(now(), date) < 1 order by date desc").list();
	}
	
	@Override
	public List<Noticia> listBySize(Integer tamanho, String categoria) throws Exception{
		return sessionFactory.getCurrentSession().createQuery("from Noticia n where n.categoria.nome = '"+ categoria +"' order by date desc").setFirstResult(tamanho).setMaxResults(10).list();
	}

}
