package br.com.unipe.newsFeed.controller.dataModel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.unipe.newsFeed.model.beans.Noticia;

public class NoticiaDataModel extends ListDataModel<Noticia> implements
		SelectableDataModel<Noticia> {

	public NoticiaDataModel() {
	}

	public NoticiaDataModel(List<Noticia> data) {
		super(data);
	}

	@Override
	public Noticia getRowData(String rowKey) {
		// In a real app, a more efficient way like a query by rowKey should be
		// implemented to deal with huge data

		List<Noticia> noticiaList = (List<Noticia>) getWrappedData();

		for (Noticia noticia : noticiaList) {
			if (noticia.getTitulo().equals(rowKey))
				return noticia;
		}

		return null;
	}

	@Override
	public Object getRowKey(Noticia noticia) {
		return noticia;
	}
}
