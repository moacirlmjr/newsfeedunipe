package br.com.unipe.newsFeed.model.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import br.com.unipe.newsFeed.model.beans.Autorizacao;
import br.com.unipe.newsFeed.model.beans.Categoria;
import br.com.unipe.newsFeed.model.beans.Noticia;
import br.com.unipe.newsFeed.model.beans.Usuario;

/**
 * @author moacir
 * 
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static <T> Example getExample(T bean) throws Exception {
		Example example = Example.create(bean);
		example.enableLike(MatchMode.START);
		example.ignoreCase();
		example.excludeZeroes();
		return example;
	}

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {
				// Create the SessionFactory from standard (hibernate.cfg.xml)
				// config file.
				AnnotationConfiguration ac = new AnnotationConfiguration();

				
				ac.addAnnotatedClass(Noticia.class)
						.addAnnotatedClass(Categoria.class)
						.addAnnotatedClass(Usuario.class)
						.addAnnotatedClass(Autorizacao.class);

				sessionFactory = ac.configure().buildSessionFactory();

			} catch (Throwable ex) {
				// Log the exception.
				System.err.println("Initial SessionFactory creation failed."
						+ ex);
				throw new ExceptionInInitializerError(ex);
			}

			return sessionFactory;

		} else {
			return sessionFactory;
		}

	}

}
