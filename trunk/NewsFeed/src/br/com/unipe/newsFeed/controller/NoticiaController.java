package br.com.unipe.newsFeed.controller;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.com.unipe.newsFeed.model.beans.Noticia;
import br.com.unipe.newsFeed.model.service.NoticiaService;

@Named("noticiaController")
@Scope("request")
public class NoticiaController implements Serializable {
	
	private Noticia noticia;
	
	private NoticiaService noticiaService;

}
