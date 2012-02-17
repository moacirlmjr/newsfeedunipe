package br.com.unipe.newsFeed.model.service.comum;

import java.io.Serializable;
import java.util.List;


/**
 * @author moacir
 *
 */
public interface NewsFeedService<T> extends Serializable {

	public T createOrUpdate(T objeto) throws Exception;

	public T findById(Long id) throws Exception;

	public List<T> list() throws Exception;

	public void remove(T objeto) throws Exception;
	
	public List<T> listByExample(T bean) throws Exception;
	
}
