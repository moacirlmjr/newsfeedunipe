package br.com.unipe.newsFeed.model.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Component;

import br.com.unipe.newsFeed.model.beans.Autorizacao;
import br.com.unipe.newsFeed.model.beans.Categoria;
import br.com.unipe.newsFeed.model.beans.Usuario;

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
		categoria.setNome("Geek");
		session.save(categoria);
		
		categoria = new Categoria();
		categoria.setNome("ITJOB");
		session.save(categoria);
		
		categoria = new Categoria();
		categoria.setNome("Pós-Graduação");
		session.save(categoria);
		
		categoria = new Categoria();
		categoria.setNome("Mercado");
		session.save(categoria);
		
		categoria = new Categoria();
		categoria.setNome("Mobilidade");
		session.save(categoria);
		
		categoria = new Categoria();
		categoria.setNome("DicasTech");
		session.save(categoria);
		
		categoria = new Categoria();
		categoria.setNome("Mural Coordenação");
		session.save(categoria);
		
		categoria = new Categoria();
		categoria.setNome("Eventos");
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
	
	public void carregarAdministrador() throws Exception{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
		Autorizacao autorizacao = new Autorizacao();
		autorizacao.setNome("ADMIN");
		
		Example example = HibernateUtil.getExample(autorizacao);
		List<Autorizacao> result = session.createCriteria(Autorizacao.class).add(example).list();
		if(result.size() != 0){
			autorizacao = result.get(0);
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome("admin");
		usuario.setEmail("admin");
		usuario.setSenha(Utilidades.criarHashSenha("admin"));
		
		session.save(usuario);
		
		t.commit();
		session.close();
	}
	
	public void carregarNoticias(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		
	}
	
	
	public static void main(String[] args) {
		HibernateCarga hc = new HibernateCarga();
		
		try {
			hc.carregarCategorias();
			hc.carregarAutorizacoes();
			hc.carregarAdministrador();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
