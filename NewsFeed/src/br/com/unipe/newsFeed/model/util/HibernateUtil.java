package br.com.unipe.newsFeed.model.util;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

/**
 * @author moacir
 *
 */
public class HibernateUtil {

	public static <T> Example getExample(T bean) throws Exception {
		Example example = Example.create(bean);
		example.enableLike(MatchMode.START);
		example.ignoreCase();
		example.excludeZeroes();
		return example;
	}
	
}
