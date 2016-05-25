package mx.trillas.RepartoPaqueteria.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mx.trillas.RepartoPaqueteria.persistence.HibernateUtil;
import mx.trillas.RepartoPaqueteria.persistence.dao.OrigenesDAO;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Origen;

public class OrigenesDAODBImpl implements OrigenesDAO {

	@Override
	public void altaOrigen(Origen origen) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(origen);
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
	public Origen getOrigen(String nombre) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Origen origen = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Origen.class);
			criteria.add(Restrictions.and(Restrictions.eq("nombre", nombre)));
			Object origenObj = criteria.uniqueResult();
			if (origenObj != null && origenObj instanceof Origen) {
				origen = (Origen) origenObj;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return origen;
	}
	
	@Override
	public List<Origen> getOrigenList() throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		List<Origen> origenes = new ArrayList<Origen>();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Origen.class);

			List<?> objList = criteria.list();
			for (Object origenObj : objList) {
				if (origenObj != null && origenObj instanceof Origen) {
					Origen origen = (Origen) origenObj;
					origenes.add(origen);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return origenes;
	}
	
	@Override
	public void altaOrigenByList(List<Origen> origenes) throws Exception {
		// TODO Auto-generated method stub
		try {
			for (Origen element : origenes) {
				updateOrigen(element);
			} 
		} catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void updateOrigen(Origen origen) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(origen);
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