package mx.trillas.ControlEnvio.persistence.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import mx.trillas.ControlEnvio.persistence.HibernateUtil;
import mx.trillas.ControlEnvio.persistence.dao.DepartamentoDAO;
import mx.trillas.ControlEnvio.persistence.pojos.Departamento;
import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;

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
}
