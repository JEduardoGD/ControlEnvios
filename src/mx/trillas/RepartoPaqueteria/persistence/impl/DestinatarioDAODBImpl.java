package mx.trillas.RepartoPaqueteria.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mx.trillas.RepartoPaqueteria.persistence.HibernateUtil;
import mx.trillas.RepartoPaqueteria.persistence.dao.DestinatarioDAO;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Departamento;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Destinatario;

public class DestinatarioDAODBImpl implements DestinatarioDAO {

	@Override
	public void altaDestinatario(Destinatario destinatario) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(destinatario);
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
	public Destinatario getDestinatarioByName(String nombre) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Destinatario destinatario = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Destinatario.class);
			criteria.add(Restrictions.and(Restrictions.eq("nombre", nombre)));
			Object destinatarioObj = criteria.uniqueResult();
			if (destinatarioObj != null && destinatarioObj instanceof Destinatario) {
				destinatario = (Destinatario) destinatarioObj;
				HibernateUtil.initializeObject(destinatario.getDepartamento()); 
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return destinatario;
	}

	@Override
	public List<Destinatario> getDestinatarioFromDeptoList(Departamento departamento) throws Exception {
		Session session = null;
		List<Destinatario> destinatarios = new ArrayList<Destinatario>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Destinatario.class);
			criteria.add(Restrictions.and(Restrictions.eq("departamento", departamento)));
			
			List<?> objList = criteria.list();
			for (Object destinatarioObj : objList) {
				if (destinatarioObj != null && destinatarioObj instanceof Destinatario) {
					Destinatario destinatarioCast = (Destinatario) destinatarioObj;
					destinatarios.add(destinatarioCast);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return destinatarios;
	}
	
	@Override
	public List<Destinatario> getDestinatarioList() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		List<Destinatario>  destinatarios = new ArrayList<Destinatario>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Destinatario.class);

			List<?> objList = criteria.list();
			for (Object destinatarioObj : objList) {
				if (destinatarioObj != null && destinatarioObj instanceof Destinatario) {
					Destinatario destinatario = (Destinatario) destinatarioObj;
					destinatarios.add(destinatario);
					HibernateUtil.initializeObject(destinatario.getDepartamento());
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return destinatarios;
	}
	
	@Override
	public void updateDestinatarioByList(List<Destinatario> destinatarios) throws Exception {
		try {
			for (Destinatario element : destinatarios) {
				updateDestinatario(element);
			} 
		} catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void updateDestinatario(Destinatario destinatario) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(destinatario);
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
	public Destinatario getDestinatarioById(int id) throws Exception {
		Session session = null;
		Destinatario destinatario = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Destinatario.class);
			criteria.add(Restrictions.and(Restrictions.eq("id", id)));
			Object destinatarioObj = criteria.uniqueResult();
			if (destinatarioObj != null && destinatarioObj instanceof Destinatario) {
				destinatario = (Destinatario) destinatarioObj;
				HibernateUtil.initializeObject(destinatario.getDepartamento());
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return destinatario;
	}
	
	@Override
	public Departamento getDepartamentoByDestinatario(Destinatario destinatario) throws Exception {
		Session session = null;
		Departamento departamento = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Departamento.class);
			criteria.add(Restrictions.and(Restrictions.eq("destinatario", destinatario)));
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
}	