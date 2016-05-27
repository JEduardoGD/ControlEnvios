package mx.trillas.RepartoPaqueteria.persistence.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import mx.trillas.RepartoPaqueteria.persistence.HibernateUtil;
import mx.trillas.RepartoPaqueteria.persistence.dao.UsuarioDAO;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Usuario;

public class UsuarioDAODBImpl implements UsuarioDAO {

	@Override
	public Usuario getByUsernameAndPassword(String username, String password) throws Exception {
		Usuario usuario;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			SimpleExpression se1 = Restrictions.eq("username", username);
			SimpleExpression se2 = Restrictions.eq("password", password);
			LogicalExpression criterion = Restrictions.and(se1, se2);
			criteria.add(criterion);
			Object resObj = criteria.uniqueResult();
			if (resObj != null && resObj instanceof Usuario) {
				usuario = (Usuario) resObj;
				HibernateUtil.initializeObject(usuario.getTiposusuario());
				return usuario;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return null;
	}

	@Override
	public Usuario getUsuarioByUsername(String username) throws Exception {
		Usuario usuario;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			SimpleExpression se1 = Restrictions.eq("username", username);
			criteria.add(se1);
			Object resObj = criteria.uniqueResult();
			if (resObj != null && resObj instanceof Usuario) {
				usuario = (Usuario) resObj;
				HibernateUtil.initializeObject(usuario.getTiposusuario());
				return usuario;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		return null;
	}
}
