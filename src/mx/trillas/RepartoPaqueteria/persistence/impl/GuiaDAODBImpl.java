package mx.trillas.RepartoPaqueteria.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import mx.trillas.RepartoPaqueteria.persistence.HibernateUtil;
import mx.trillas.RepartoPaqueteria.persistence.dao.GuiaDAO;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Destinatario;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Guia;

public class GuiaDAODBImpl implements GuiaDAO {

	@Override
	public void altaGuia(Guia guia) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(guia);
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
	public Guia getGuia(String numero) throws Exception {
		Session session = null;
		Guia guia = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Guia.class);
			criteria.add(Restrictions.and(Restrictions.eq("numero", numero)));
			Object guiaObj = criteria.uniqueResult();
			if (guiaObj != null && guiaObj instanceof Guia) {
				guia = (Guia) guiaObj;
				HibernateUtil.initializeObject(guia.getOrigen());
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return guia;
	}

	@Override
	public List<Guia> getGuiaList() throws Exception {
		Session session = null;
		List<Guia> guias = new ArrayList<Guia>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Guia.class);

			List<?> objList = criteria.list();
			for (Object guiaObj : objList) {
				if (guiaObj != null && guiaObj instanceof Guia) {
					Guia guia = (Guia) guiaObj;
					guias.add(guia);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return guias;
	}

	public List<String> getotrosDeptosList() throws Exception {
		Session session = null;
		List<String> otrosDeptos = new ArrayList<String>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Guia.class);

			List<?> objList = criteria.list();
			for (Object guiaObj : objList) {
				if (guiaObj != null && guiaObj instanceof Guia) {
					Guia guia = (Guia) guiaObj;
					otrosDeptos.add(guia.getOtrodepartamento());
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return otrosDeptos;
	}
	
	@Override
	public List<Guia> getGuiaListBySortedDepto(Date fechaInicio, Date fechaFin) throws Exception {

		Session session = null;
		List<Guia> guias = new ArrayList<Guia>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Guia.class);

			criteria.add(Restrictions.ge("fecha", fechaInicio)); 
			criteria.add(Restrictions.le("fecha", fechaFin));
			criteria.addOrder(Order.asc("destinatario.departamento"));
			
			List<?> objList = criteria.list();
			for (Object guiaObj : objList) {
				if (guiaObj != null && guiaObj instanceof Guia) {
					Guia guia = (Guia) guiaObj;
					if (guia.getMensajeria() != null) {
						HibernateUtil.initializeObject(guia.getMensajeria());
					}
					if (guia.getOrigen() != null) {
						HibernateUtil.initializeObject(guia.getOrigen());
					}
					if (guia.getDestinatario() != null) {
						HibernateUtil.initializeObject(guia.getDestinatario());
					}
					guias.add(guia);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return guias;
	}
	

	@Override
	public List<Guia> getGuiaListByDateyDestinatario(Date fechaInicio, Date fechaFin, Destinatario destinatario ) throws Exception {

		Session session = null;
		List<Guia> guias = new ArrayList<Guia>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Guia.class);

			criteria.add(Restrictions.ge("fecha", fechaInicio)); 
			criteria.add(Restrictions.le("fecha", fechaFin));
			criteria.add(Restrictions.eq("destinatario", destinatario));
			
			List<?> objList = criteria.list();
			for (Object guiaObj : objList) {
				if (guiaObj != null && guiaObj instanceof Guia) {
					Guia guia = (Guia) guiaObj;
					if (guia.getMensajeria() != null) {
						HibernateUtil.initializeObject(guia.getMensajeria());
					}
					if (guia.getOrigen() != null) {
						HibernateUtil.initializeObject(guia.getOrigen());
					}
					if (guia.getDestinatario() != null) {
						HibernateUtil.initializeObject(guia.getDestinatario());
						HibernateUtil.initializeObject(guia.getDestinatario().getDepartamento());
					}
					guias.add(guia);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return guias;
	}

	
	@Override
	public List<Guia> getGuiaListByDateOtroDepto(Date fechaInicio, Date fechaFin, String nombreDepartamento ) throws Exception {

		Session session = null;
		List<Guia> guias = new ArrayList<Guia>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Guia.class);

			criteria.add(Restrictions.ge("fecha", fechaInicio)); 
			criteria.add(Restrictions.le("fecha", fechaFin));
			criteria.add(Restrictions.eq("otrodepartamento", nombreDepartamento));
			
			List<?> objList = criteria.list();
			for (Object guiaObj : objList) {
				if (guiaObj != null && guiaObj instanceof Guia) {
					Guia guia = (Guia) guiaObj;
					if (guia.getMensajeria() != null) {
						HibernateUtil.initializeObject(guia.getMensajeria());
					}
					if (guia.getOrigen() != null) {
						HibernateUtil.initializeObject(guia.getOrigen());
					}
					guias.add(guia);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return guias;
	}
	
	
	@Override
	public List<Guia> getGuiaListByDate(Date fechaInicio, Date fechaFin) throws Exception {

		Session session = null;
		List<Guia> guias = new ArrayList<Guia>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Guia.class);

			criteria.add(Restrictions.ge("fecha", fechaInicio)); 
			criteria.add(Restrictions.le("fecha", fechaFin));
			
			List<?> objList = criteria.list();
			for (Object guiaObj : objList) {
				if (guiaObj != null && guiaObj instanceof Guia) {
					Guia guia = (Guia) guiaObj;
					if (guia.getMensajeria() != null) {
						HibernateUtil.initializeObject(guia.getMensajeria());
					}
					if (guia.getOrigen() != null) {
						HibernateUtil.initializeObject(guia.getOrigen());
					}
					if (guia.getDestinatario() != null) {
						HibernateUtil.initializeObject(guia.getDestinatario());
					}
					guias.add(guia);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return guias;
	}
}
