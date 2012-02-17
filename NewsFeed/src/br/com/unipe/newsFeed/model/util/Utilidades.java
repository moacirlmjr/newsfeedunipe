package br.com.unipe.newsFeed.model.util;

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
        int horas = (int) (total/60*60);
        total -= horas*60*60;
        int min = (int) (total/60);
        total -= min*60;
        return new Time(horas, min, (int)(total/1));
	}
	
	@SuppressWarnings("deprecation")
	public static Time dateDiff(Long periodo) {
		Long total = periodo / 1000;
        int horas = (int) (total/60*60);
        total -= horas*60*60;
        int min = (int) (total/60);
        total -= min*60;
        return new Time(horas, min, (int)(total/1));
	}

}
