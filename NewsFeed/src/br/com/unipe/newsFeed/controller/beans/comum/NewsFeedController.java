package br.com.unipe.newsFeed.controller.beans.comum;

import java.io.Serializable;

import br.com.unipe.newsFeed.model.util.FacesUtil;

public class NewsFeedController implements Serializable {
	private static final String SUCESSO_EXCLUSAO = "sucesso.exclusao";
	private static final String SUCESSO_ALTERACAO = "sucesso.alteracao";
	private static final String SUCESSO_INCLUSAO = "sucesso.inclusao";

	protected void registrarSucessoInclusao() {
		FacesUtil.registrarMensagem(SUCESSO_INCLUSAO);
	}

	protected void registrarSucessoExclusao() {
		FacesUtil.registrarMensagem(SUCESSO_EXCLUSAO);
	}

	protected void registrarSucessoAlteracao() {
		FacesUtil.registrarMensagem(SUCESSO_ALTERACAO);
	}
}
