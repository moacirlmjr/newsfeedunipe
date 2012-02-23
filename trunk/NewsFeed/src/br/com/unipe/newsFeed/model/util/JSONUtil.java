package br.com.unipe.newsFeed.model.util;

import java.text.SimpleDateFormat;
import java.util.List;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

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
	
}
