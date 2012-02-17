package br.com.unipe.newsFeed.model.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author moacir
 *
 */
public class URLUtil {
	
	public static String obterResultadoUrl(String urlString){
		String linha = "";
		String linhaRetorno = "";
		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			// Get the response
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			while ((linha = br.readLine()) != null) {
				linhaRetorno += linha;
			}

		} catch (MalformedURLException e) {
			NewsFeedLog.error(e);
		} catch (IOException e) {
			NewsFeedLog.error(e);
		}
		
		return linhaRetorno;
	}
	
}
