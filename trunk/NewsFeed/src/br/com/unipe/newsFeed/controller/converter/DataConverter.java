package br.com.unipe.newsFeed.controller.converter;

import java.util.Date;

import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Date.class)
public class DataConverter extends DateTimeConverter{

	public DataConverter() {
		super();
		this.setPattern("dd/MM/yyyy");
	}
	
}