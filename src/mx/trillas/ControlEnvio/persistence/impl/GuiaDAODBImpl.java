package mx.trillas.ControlEnvio.persistence.impl;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mx.trillas.ControlEnvio.persistence.HibernateUtil;
import mx.trillas.ControlEnvio.persistence.dao.GuiaDAO;
import mx.trillas.ControlEnvio.persistence.pojos.Guia;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;

public class GuiaDAODBImpl implements GuiaDAO {

	@Override
	public void altaGuia(Guia guia) throws Exception {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		Session session = null;
		Guia guia = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Guia.class);
			criteria.add(Restrictions.and(Restrictions.eq("numero", numero)));
			Object guiaObj = criteria.uniqueResult();
			if (guiaObj != null && guiaObj instanceof Guia) {
				guia = (Guia) guiaObj;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return guia;
	}
}
