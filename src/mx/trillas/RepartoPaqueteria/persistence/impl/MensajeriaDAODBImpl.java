package mx.trillas.RepartoPaqueteria.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mx.trillas.RepartoPaqueteria.persistence.HibernateUtil;
import mx.trillas.RepartoPaqueteria.persistence.dao.MensajeriaDAO;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Mensajeria;

public class MensajeriaDAODBImpl implements MensajeriaDAO {

	@Override
	public void altaMensajeria(Mensajeria mensajeria) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(mensajeria);
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
	public void updateMensajeria(Mensajeria mensajeria) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(mensajeria);
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
	public Mensajeria getMensajeria(String nombre) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Mensajeria mensajeria = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Mensajeria.class);
			criteria.add(Restrictions.and(Restrictions.eq("nombre", nombre)));
			Object mensajeriaObj = criteria.uniqueResult();
			if (mensajeriaObj != null && mensajeriaObj instanceof Mensajeria) {
				mensajeria = (Mensajeria) mensajeriaObj;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return mensajeria;
	}
	
	@Override
	public List<Mensajeria> getMensajeriaList() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		List<Mensajeria>  mensajerias = new ArrayList<Mensajeria>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Mensajeria.class);

			List<?> objList = criteria.list();
			for (Object mensajeriaObj : objList) {
				if (mensajeriaObj != null && mensajeriaObj instanceof Mensajeria) {
					Mensajeria mensajeria = (Mensajeria) mensajeriaObj;
					mensajerias.add(mensajeria);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return mensajerias;
	}

	@Override
	public void altaMensajeriaByList(List<Mensajeria> mensajerias) throws Exception {
		// TODO Auto-generated method stub
		try {
			for (Mensajeria element : mensajerias) {
				updateMensajeria(element);
			} 
		} catch(Exception e){
			throw e;
		}
	}

	@Override
	public Mensajeria getMensajeriaById(int id) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Mensajeria mensajeria = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Mensajeria.class);
			criteria.add(Restrictions.and(Restrictions.eq("id", id)));
			Object mensajeriaObj = criteria.uniqueResult();
			if (mensajeriaObj != null && mensajeriaObj instanceof Mensajeria) {
				mensajeria = (Mensajeria) mensajeriaObj;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return mensajeria;
	}
}
