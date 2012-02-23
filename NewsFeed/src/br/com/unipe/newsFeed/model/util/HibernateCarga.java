package br.com.unipe.newsFeed.model.util;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import br.com.unipe.newsFeed.model.beans.Autorizacao;
import br.com.unipe.newsFeed.model.beans.Categoria;

@Component
public class HibernateCarga {

	public void carregarCategorias() throws Exception{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
				
		Categoria categoria = new Categoria();
		categoria.setNome("Novidade");
		session.save(categoria);
		
		categoria = new Categoria();
		categoria.setNome("Aviso");
		session.save(categoria);
		
		categoria = new Categoria();
		categoria.setNome("Urgente");
		session.save(categoria);
		
		categoria = new Categoria();
		categoria.setNome("Geek");
		session.save(categoria);
		
		t.commit();
		session.close();
	}
	
	public void carregarAutorizacoes() throws Exception{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
				
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setNome("ADMIN");
		session.save(autorizacao);
		
		autorizacao = new Autorizacao();
		autorizacao.setNome("USER");
		session.save(autorizacao);
		
		t.commit();
		session.close();
	}
	
	
	public static void main(String[] args) {
		HibernateCarga hc = new HibernateCarga();
		
		try {
			hc.carregarCategorias();
			hc.carregarAutorizacoes();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
