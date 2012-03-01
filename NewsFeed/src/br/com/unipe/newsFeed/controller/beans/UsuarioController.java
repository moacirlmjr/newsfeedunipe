package br.com.unipe.newsFeed.controller.beans;

import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.unipe.newsFeed.controller.beans.comum.NewsFeedController;
import br.com.unipe.newsFeed.model.beans.Usuario;
import br.com.unipe.newsFeed.model.service.AutorizacaoService;
import br.com.unipe.newsFeed.model.service.UsuarioService;
import br.com.unipe.newsFeed.model.util.FacesUtil;
import br.com.unipe.newsFeed.model.util.NewsFeedLog;

@Named("usuarioController")
@Scope("request")
public class UsuarioController extends NewsFeedController {

	@Valid
	private Usuario usuario;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AutorizacaoService autorizacaoService;

	private String nomeBotao;

	private boolean renderedAtualizarCadastrar;
	private boolean disabledAtualizarCadastrar;

	private DataModel<Usuario> usuarioDataModel;

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
		
		nomeBotao = "Cadastrar";
		renderedAtualizarCadastrar = true;
		disabledAtualizarCadastrar = false;
	}

	public void setUsuarioDataModel(DataModel<Usuario> usuarioDataModel) {
		this.usuarioDataModel = usuarioDataModel;
	}

	public DataModel<Usuario> getUsuarioDataModel() {
		try {
			usuarioDataModel = new ListDataModel<Usuario>(usuarioService.list());
			return usuarioDataModel;

		} catch (Exception e) {
			FacesUtil.registrarErro("erro.listar");
			return null;
		}

	}

	public String prepararCadastroUsuario() {
		usuario = new Usuario();
		nomeBotao = "Cadastrar";
		renderedAtualizarCadastrar = true;
		disabledAtualizarCadastrar = false;
		return "cadastroUsuario";
	}

	public void prepararAtualizarUsuario(ActionEvent actionEvent) {
		nomeBotao = "Atualizar";
		this.usuario = (Usuario) (usuarioDataModel.getRowData());
	}

	public String carregarAtualizarUsuario() {
		renderedAtualizarCadastrar = false;
		disabledAtualizarCadastrar = true;
		return "cadastroUsuario";
	}

	public String excluirUsuario() {
		try {
			this.usuario = (Usuario) (usuarioDataModel.getRowData());
			usuarioService.remove(usuario);

			registrarSucessoExclusao();
		} catch (Exception e) {
			FacesUtil.registrarErro("erro.exclusao");
			NewsFeedLog.error(e);
		}

		return "listUsuario";
	}
	
	public void alterarSenha(){
		System.out.println("teste");		
	}

	public String cadastrarUsuario() {
		try {

			if (usuario.getId() != 0) {

				Usuario usuarioAux = usuarioService.findById(usuario.getId());
				usuarioAux.setNome(usuario.getNome());
				usuarioService.createOrUpdate(usuarioAux);
			} else {
				usuario.setAutorizacao(autorizacaoService
						.getAutorizacaoByName("ADMIN"));
				usuarioService.createOrUpdate(usuario);
			}

			registrarSucessoInclusao();

		} catch (Exception e) {
			FacesUtil.registrarErro("erro.inclusao");
			NewsFeedLog.error(e);
		}

		return "listNews";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public String getNomeBotao() {
		return nomeBotao;
	}

	public void setNomeBotao(String nomeBotao) {
		this.nomeBotao = nomeBotao;
	}

	public boolean isRenderedAtualizarCadastrar() {
		return renderedAtualizarCadastrar;
	}

	public void setRenderedAtualizarCadastrar(boolean renderedAtualizarCadastrar) {
		this.renderedAtualizarCadastrar = renderedAtualizarCadastrar;
	}

	public boolean isDisabledAtualizarCadastrar() {
		return disabledAtualizarCadastrar;
	}

	public void setDisabledAtualizarCadastrar(boolean disabledAtualizarCadastrar) {
		this.disabledAtualizarCadastrar = disabledAtualizarCadastrar;
	}
}
