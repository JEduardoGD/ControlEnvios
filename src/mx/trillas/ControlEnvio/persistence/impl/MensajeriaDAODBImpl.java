package mx.trillas.ControlEnvio.persistence.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mx.trillas.ControlEnvio.persistence.HibernateUtil;
import mx.trillas.ControlEnvio.persistence.dao.MensajeriaDAO;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;

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
}
