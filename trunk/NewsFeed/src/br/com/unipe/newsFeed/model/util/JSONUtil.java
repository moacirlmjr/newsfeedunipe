package br.com.unipe.newsFeed.model.util;

import java.text.SimpleDateFormat;
import java.util.List;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import br.com.unipe.newsFeed.model.beans.Categoria;
import br.com.unipe.newsFeed.model.beans.Noticia;

/**
 * @author moacir
 *
 */
public class JSONUtil {

	public static JSONObject montarJsonNoticiaList(List<Noticia> listNoticia) throws JSONException {
		JSONObject jsonNoticias = new JSONObject();
		JSONArray jsonArrayNoticias = new JSONArray();
		
		for(Noticia n : listNoticia){
			jsonArrayNoticias.put(montarJsonNoticia(n));
		}
		
		jsonNoticias.put("mensagens", jsonArrayNoticias);
		
		return jsonNoticias;
	}

	
	public static JSONObject montarJsonNoticia(Noticia noticia) throws JSONException {
		JSONObject jsonNoticia = new JSONObject();
		
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				
		
		jsonNoticia.put("id", noticia.getId());
		jsonNoticia.put("titulo", noticia.getTitulo());
		jsonNoticia.put("data",noticia.getDate() == null? " " : formater.format(noticia.getDate().getTime()));
		jsonNoticia.put("categoria",noticia.getCategoria() == null ? " " : noticia.getCategoria().getNome());
		jsonNoticia.put("html", noticia.getMensagem());
		
		
		return jsonNoticia;
	}
	
	public static JSONArray montarJsonNoticiaListByCategoria(List<Noticia> listNoticia, List<Categoria> listCategoria) throws JSONException {
		JSONObject jsonCategoriasAux = new JSONObject();
		JSONArray jsonArrayCategorias = new JSONArray();
		
		JSONObject jsonNoticias = new JSONObject();
		JSONArray jsonArrayNoticias = new JSONArray();
		
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		for(Categoria c : listCategoria){
			jsonCategoriasAux = new JSONObject();
			jsonArrayNoticias = new JSONArray();
			jsonCategoriasAux.put("id", c.getId());
			jsonCategoriasAux.put("nome", c.getNome());
			
			for(Noticia n : listNoticia){
				if(n.getCategoria().getNome().equalsIgnoreCase(c.getNome())){
					jsonNoticias = new JSONObject();
					jsonNoticias.put("id", n.getId());
					jsonNoticias.put("titulo", n.getTitulo());
					jsonNoticias.put("data",n.getDate() == null? " " : formater.format(n.getDate().getTime()));
					jsonNoticias.put("html", n.getMensagem());
					jsonArrayNoticias.put(jsonNoticias);
				}
			}
			jsonCategoriasAux.put("mensagens", jsonArrayNoticias);
			jsonArrayCategorias.put(jsonCategoriasAux);
		}
		
		
		return jsonArrayCategorias;
	}
	
}
