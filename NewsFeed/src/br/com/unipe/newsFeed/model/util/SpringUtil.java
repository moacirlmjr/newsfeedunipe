package br.com.unipe.newsFeed.model.util;

import java.util.List;

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
@Scope("request")
public class SpringUtil {

	@Autowired
	private UsuarioService usuarioService;

	public SpringUtil() {

	}

	public Usuario getSessionUser() {
		Usuario usuario = new Usuario();
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof Authentication) {
				usuario.setEmail(((User) authentication.getPrincipal())
						.getUsername());

				try {
					List<Usuario> list;
					list = usuarioService.listByExample(usuario);
					if (list.size() != 0) {
						usuario = list.get(0);
					}
				} catch (Exception e) {
					NewsFeedLog.error(e);
				}
			}
		}

		return usuario;
	}
}