package br.com.unipe.newsFeed.controller;

import java.io.Serializable;

import javax.faces.model.DataModel;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.unipe.newsFeed.model.beans.Usuario;
import br.com.unipe.newsFeed.model.service.UsuarioService;

@Named
@Scope("session")
public class UsuarioController implements Serializable {

	private Usuario usuario;

	@Autowired
	private UsuarioService usuarioService;

	public UsuarioController() {
		usuario = new Usuario();
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof Authentication) {
				usuario.setEmail(((User) authentication.getPrincipal())
						.getUsername());
			}
		}
	}

}
