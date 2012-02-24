package br.com.unipe.newsFeed.model.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.util.Date;

/**
 * @author moacir
 * 
 */
public class Utilidades {

	@SuppressWarnings("deprecation")
	public static Time dateDiff(Date dataInicial, Date dataFinal) {
		Long periodo = (dataFinal.getTime() - dataInicial.getTime());
		Long total = periodo / 1000;
		int horas = (int) (total / 60 * 60);
		total -= horas * 60 * 60;
		int min = (int) (total / 60);
		total -= min * 60;
		return new Time(horas, min, (int) (total / 1));
	}

	@SuppressWarnings("deprecation")
	public static Time dateDiff(Long periodo) {
		Long total = periodo / 1000;
		int horas = (int) (total / 60 * 60);
		total -= horas * 60 * 60;
		int min = (int) (total / 60);
		total -= min * 60;
		return new Time(horas, min, (int) (total / 1));
	}

	public static String criarHashSenha(String senha) {
		byte[] hashSenha;
		StringBuilder s = new StringBuilder();
		try {
			hashSenha = MessageDigest.getInstance("SHA-256").digest(
					senha.getBytes());
			for (int i = 0; i < hashSenha.length; i++) {
				int parteAlta = ((hashSenha[i] >> 4) & 0xf) << 4;
				int parteBaixa = hashSenha[i] & 0xf;
				if (parteAlta == 0)
					s.append('0');
				s.append(Integer.toHexString(parteAlta | parteBaixa));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(Utilidades.criarHashSenha("admin"));
		
	}

}
