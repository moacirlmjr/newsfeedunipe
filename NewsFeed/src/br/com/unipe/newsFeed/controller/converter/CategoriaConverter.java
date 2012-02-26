package br.com.unipe.newsFeed.controller.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import br.com.unipe.newsFeed.model.beans.Categoria;
import br.com.unipe.newsFeed.model.service.CategoriaService;
import br.com.unipe.newsFeed.model.util.NewsFeedLog;

@Named
@Scope("request")
public class CategoriaConverter implements Converter{

	@Autowired
	private CategoriaService categoriaService;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		Categoria categoria = new Categoria();
		categoria.setNome(arg2);
		
		try {
			List<Categoria> list = categoriaService.listByExample(categoria);
			if(list.size() != 0){
				categoria = new Categoria();
				categoria = list.get(0);
			}
			
		} catch (Exception e) {
			NewsFeedLog.error(e);
		}
		
		
		return categoria;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Categoria categoria = (Categoria) arg2;
		
		return categoria.getNome();
	}

}
