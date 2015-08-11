package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import entity.Dados;

public class DadosDAO  {
	
	public void insertDados(List<Dados> p_dados, SessionFactory p_sessionFactory) {
		for (Dados dado : p_dados) {
			p_sessionFactory.getCurrentSession().saveOrUpdate(dado);
		}
	}
	
	public List<Dados> getTodosDados(SessionFactory p_sessionFactory) {
		Criteria criteria = p_sessionFactory.getCurrentSession().createCriteria(Dados.class);
		return criteria.list();
	}

}
