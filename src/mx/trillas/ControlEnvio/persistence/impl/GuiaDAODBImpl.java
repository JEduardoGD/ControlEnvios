package mx.trillas.ControlEnvio.persistence.impl;


import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.trillas.ControlEnvio.persistence.HibernateUtil;
import mx.trillas.ControlEnvio.persistence.dao.GuiaDAO;
import mx.trillas.ControlEnvio.persistence.pojos.Guia;

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
		} catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
//			ex.printStackTrace();
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
	}
}
