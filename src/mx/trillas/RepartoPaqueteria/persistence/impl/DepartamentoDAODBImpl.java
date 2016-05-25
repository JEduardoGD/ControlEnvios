package mx.trillas.RepartoPaqueteria.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mx.trillas.RepartoPaqueteria.persistence.HibernateUtil;
import mx.trillas.RepartoPaqueteria.persistence.dao.DepartamentoDAO;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Departamento;

public class DepartamentoDAODBImpl implements DepartamentoDAO {

	@Override
	public void altaDepartamento(Departamento departamento) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(departamento);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public Departamento getDepartamento(String nombre) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Departamento departamento = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Departamento.class);
			criteria.add(Restrictions.and(Restrictions.eq("nombre", nombre)));
			Object departamentoObj = criteria.uniqueResult();
			if (departamentoObj != null && departamentoObj instanceof Departamento) {
				departamento = (Departamento) departamentoObj;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return departamento;
	}
	
	@Override
	public List<Departamento> getDepartamentoList() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		List<Departamento> departamentos = new ArrayList<Departamento>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Departamento.class);

			List<?> objList = criteria.list();
			for (Object departamentoObj : objList) {
				if (departamentoObj != null && departamentoObj instanceof Departamento) {
					Departamento departamento = (Departamento) departamentoObj;
					departamentos.add(departamento);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return departamentos;
	}

	@Override
	public Departamento getDepartamentoById(int id) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Departamento departamento = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Departamento.class);
			criteria.add(Restrictions.and(Restrictions.eq("id", id)));
			Object departamentoObj = criteria.uniqueResult();
			if (departamentoObj != null && departamentoObj instanceof Departamento) {
				departamento = (Departamento) departamentoObj;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return departamento;
	}

	@Override
	public void altaDepartamentoByList(List<Departamento> departamentos) throws Exception {
		// TODO Auto-generated method stub
		try {
			for (Departamento element : departamentos) {
				updateDepartamento(element);
			} 
		} catch(Exception e){
			throw e;
		}
	}

	@Override
	public void updateDepartamento(Departamento departamento) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(departamento);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
	}
}
