package br.com.unipe.newsFeed.model.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIColumn;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 
 * Classe utilitária que abstrai métodos para disponibilizar o acesso aos
 * recursos do faces.
 * 
 * @author marcelo.borba, vinicius.souto.
 */
public class FacesUtil {


	private static final String CHAVE_BEAN_ACIONADOR = "beanAcionador";

	/**
	 * Adiciona um atributo no escopo de request.
	 * 
	 * @param chave
	 *            a chave que identifica o atributo
	 * @param valor
	 *            o valor do atributo
	 */
	public static void adicionarAtributoRequest(String chave, Object valor) {

		obterRequest().setAttribute(chave, valor);

	}

	/**
	 * Adiciona um atributo na sessão.
	 * 
	 * @param chave
	 *            a chave que identifica o atributo
	 * @param valor
	 *            o valor do atributo
	 */
	public static void adicionarAtributoSessao(String chave, Object valor) {

		obterSessao().setAttribute(chave, valor);

	}

	/**
	 * Obtém um atributo do request.
	 * 
	 * @param chave
	 *            a chave do atributo.
	 * @return o atributo.
	 */
	public static Object obterAtributoRequest(String chave) {

		return obterRequest().getAttribute(chave);
	}

	/**
	 * Obtém um atributo da sessão.
	 * 
	 * @param chave
	 *            a chave do atributo.
	 * @return o atributo.
	 */
	public static Object obterAtributoSessao(String chave) {

		return obterSessao().getAttribute(chave);
	}

	/**
	 * Obtém o caminho completo de um determinado caminho relativo da aplicação.
	 * 
	 * @param caminho
	 *            o caminho
	 * @return o caminho completo
	 */
	public static String obterCaminhoReal(String caminho) {

		ServletContext sc = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		return sc.getRealPath(caminho);
	}

	/**
	 * Obtém um componente.
	 * 
	 * @param idComponente
	 *            o id do componente
	 * @return o componente
	 */
	public static UIComponent obterComponente(String idComponente) {

		return obterFacesContext().getViewRoot().findComponent(idComponente);
	}

	/**
	 * Obtém o faces context do bean em execução.
	 * 
	 * @return FacesContext.
	 */
	public static FacesContext obterFacesContext() {

		return FacesContext.getCurrentInstance();
	}

	/**
	 * Obtém o managed bean solicitado.
	 * 
	 * @param nomeManagedBean
	 *            o nome do managed bean
	 * @return o managed bean de interesse
	 */
	public static Object obterManagedBean(String nomeManagedBean) {

		return obterFacesContext().getApplication().createValueBinding(
				"#{" + nomeManagedBean + "}").getValue(obterFacesContext());
	}
	
	
	
	/**
	 * Obtém o valor de um atributo solicitado.
	 * 
	 * @param idAtributo o identificador do atributo
	 * @return
	 */
	public static String obterAtributoComoString (String idAtributo){
				
		return (String)obterFacesContext().getExternalContext().
				getRequestParameterMap().get(idAtributo);
	}
	
	

	/**
	 * Obtém o nome do contexto.
	 * 
	 * @return o nome do contexto.
	 */
	public static String obterNomeContexto() {

		return obterFacesContext().getExternalContext().getRequestContextPath();
	}

	/**
	 * Obtém o arquivo de recursos configurado no faces.
	 * 
	 * @return arquivo de recursos.
	 */
	private static ResourceBundle obterResourceBundle() {

		String bundleName = obterFacesContext().getApplication()
				.getMessageBundle();

		ResourceBundle bundle = ResourceBundle.getBundle(bundleName);

		return bundle;
	}

	/**
	 * Retorna um HttpServletRequest contendo a requisição http atual.
	 * 
	 * @return um HttpServletRequest contendo a requisição http atual
	 */
	public static HttpServletRequest obterRequest() {

		return (HttpServletRequest) obterFacesContext().getExternalContext()
				.getRequest();
	}

	/**
	 * Obtém a sessão do usuário.
	 * 
	 * @return a sessão.
	 */
	public static HttpSession obterSessao() {

		return (HttpSession) obterFacesContext().getExternalContext()
				.getSession(false);
	}

	/**
	 * Obtém o conteúdo da chave no arquivo de mensagens. Caso não encontrar,
	 * retorna apenas o valor da key.
	 * 
	 * @param key
	 *            a chave a ser pesquisada.
	 * @return texto contido na key.
	 */
	public static String obterTexto(String key) {

		String texto = null;

		try {
			texto = obterResourceBundle().getString(key);
		} catch (MissingResourceException e) {
			texto = key;
		}

		return texto;
	}

	/**
	 * Obtém o conteúdo da chave no arquivo de mensagens parametizada. Caso não
	 * encontrar, retorna apenas o valor da key.
	 * 
	 * @param key
	 *            a chave a ser pesquisada.
	 * @param params
	 *            parametros a serem incluidos no texto.
	 * 
	 * @return texto da chave parametizado.
	 */
	public static String obterTexto(String key, Object params[]) {

		String texto = obterTexto(key);

		if (params != null) {
			MessageFormat mf = new MessageFormat(texto);
			texto = mf.format(params, new StringBuffer(), null).toString();
		}

		return texto;
	}
	
	
    /**
	 * Obtém as colunas de um HTMLDataTable passado como parametro
	 * 
	 * @param table
	 *            HTMLdataTable passado como parametro.
	 * @return um List contendo UIColumns.
	 */
	public static List obterColunas(HtmlDataTable table) {
		List columns = new ArrayList();

		for (int i = 0; i < table.getChildCount(); i++) {
			UIComponent child = (UIComponent) table.getChildren().get(i);
			if (child instanceof UIColumn
			        && !(child.getChildren().size() == 0 || child.getChildren()
			                .get(0) instanceof EditableValueHolder)) {
				columns.add(child);
			}
		}
		return columns;
	} 

	/**
	 * Registra mensagem de aviso no contexto.
	 * 
	 * @param key
	 *            a chave da mensagem.
	 */
	public static void registrarAviso(String key) {

		String texto = obterTexto(key);
		registrarFacesMessage(texto, FacesMessage.SEVERITY_WARN);
	}

	/**
	 * Registra mensagem de aviso no contexto.
	 * 
	 * @param key
	 *            a chave da mensagem.
	 * @param param
	 *            o(s) parametro(s) da mensagem.
	 */
	public static void registrarAviso(String key, String... param) {

		String texto = obterTexto(key, param);
		registrarFacesMessage(texto, FacesMessage.SEVERITY_WARN);
	}

	/**
	 * Retorna o nome do managed bean que executou a ação atual do sistema.
	 * 
	 * @return o nome do managed bean que executou a ação atual do sistema
	 */
	public static String obterBeanAcionador() {

		return obterAtributoRequest(CHAVE_BEAN_ACIONADOR).toString();
	}

	/**
	 * Registra o nome do managed bean que executou a ação atual do sistema.
	 * 
	 * @param evento
	 *            o evento executado no sistema
	 */
	public static void registrarBeanAcionador(ActionEvent evento) {

		UICommand acionador = (UICommand) evento.getSource();
		MethodBinding acao = acionador.getAction();
		String nomeManagedBeanAcionador;
		if (acao == null) {
			nomeManagedBeanAcionador = null;
		} else {
			String expressao = acionador.getAction().getExpressionString();
			nomeManagedBeanAcionador = obterNomeManagedBean(expressao);
			if (expressao.equals(nomeManagedBeanAcionador)) {
				nomeManagedBeanAcionador = (String) acionador.getAttributes()
						.get("nomeBean");
			}
		}
		adicionarAtributoRequest(CHAVE_BEAN_ACIONADOR, nomeManagedBeanAcionador);
	}

	/**
	 * Obtém o nome do managed bean a partir de uma determinada JSF-EL. Ex:
	 * obtém manterReceitaBean a partir de #{manterReceitaBean.carregar}
	 * 
	 * @param expressao
	 *            a JSF-EL a ser analisada
	 * @return o nome do managed bean
	 */
	public static String obterNomeManagedBean(String expressao) {

		String patternStr = "#\\{(.*)\\.";

		// Compile and use regular expression
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(expressao);
		boolean matchFound = matcher.find();

		if (matchFound) {
			return matcher.group(1);
		}
		return expressao;
	}

	/**
	 * Registra mensagem de erro no contexto.
	 * 
	 * @param key
	 *            a chave da mensagem.
	 */
	public static void registrarErro(String key) {

		String texto = obterTexto(key);
		registrarFacesMessage(texto, FacesMessage.SEVERITY_ERROR);
	}

	/**
	 * Registra mensagem de erro no contexto.
	 * 
	 * @param key
	 *            a chave da mensagem.
	 * @param param
	 *            o(s) parametro(s) da mensagem.
	 */
	public static void registrarErro(String key, String... param) {

		String texto = obterTexto(key, param);
		registrarFacesMessage(texto, FacesMessage.SEVERITY_ERROR);
	}

	/**
	 * Registra mensagem de erro fatal no contexto.
	 * 
	 * @param key
	 *            a chave da mensagem.
	 * @param param
	 *            o(s) parametro(s) da mensagem.
	 */
	public static void registrarErroFatal(String key, String... param) {

		String texto = obterTexto(key, param);
		registrarFacesMessage(texto, FacesMessage.SEVERITY_FATAL);
	}

	/**
	 * Registra a mensagem no contexto pelo tipo de severidade.
	 * 
	 * @param texto
	 *            da mensagem.
	 * @param severidade
	 *            da mensagem.
	 */
	public static void registrarFacesMessage(String texto,
			FacesMessage.Severity severidade) {

		FacesMessage mensagem = new FacesMessage();

		mensagem.setSummary(texto);
		mensagem.setSeverity(severidade);

		obterFacesContext().addMessage(null, mensagem);
	}

	/**
	 * Registra mensagem no contexto.
	 * 
	 * @param key
	 *            a chave da mensagem.
	 */
	public static void registrarMensagem(String key) {

		String texto = obterTexto(key);
		registrarFacesMessage(texto, FacesMessage.SEVERITY_INFO);
	}

	/**
	 * Registra mensagem no contexto.
	 * 
	 * @param key
	 *            a chave da mensagem.
	 * @param param
	 *            o(s) parametro(s) da mensagem.
	 */
	public static void registrarMensagem(String key, String... param) {

		String texto = obterTexto(key, param);
		registrarFacesMessage(texto, FacesMessage.SEVERITY_INFO);
	}

	/**
	 * Adiciona um atributo na sessão.
	 * 
	 * @param chave
	 *            a chave que identifica o atributo
	 */
	public static void removerAtributoSessao(String chave) {

		obterSessao().removeAttribute(chave);

	}

}
