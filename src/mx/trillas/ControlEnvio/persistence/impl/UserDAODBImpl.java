package mx.trillas.ControlEnvio.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import mx.trillas.ControlEnvio.persistence.HibernateUtil;
//import mx.trillas.ControlEnvio.persistence.dao.TipousuarioDAO;
import mx.trillas.ControlEnvio.persistence.dao.UserDAO;
import mx.trillas.ControlEnvio.persistence.pojos.Usuario;
import mx.trillas.ControlEnvio.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDAODBImpl implements mx.trillas.ControlEnvio.persistence.dao.UserDAO {

	@Override
	public Usuario getUser(String username, String password) throws Exception {
		Session session = null;
		Usuario user = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.and(
					Restrictions.eq("username", username),
					Restrictions.eq("password", Cripto.getSha256(password))));
			Object UserObj = criteria.uniqueResult();
			if (UserObj != null && UserObj instanceof Usuario) {
				user = (Usuario) UserObj;
				HibernateUtil.initializeObject(user.getTiposusuario());
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
		return user;
	}
	
	@Override
	public Usuario getUser(String username) throws Exception {
		Session session = null;
		Usuario user = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.and(Restrictions.eq("username", username)));
			Object UserObj = criteria.uniqueResult();
			if (UserObj != null && UserObj instanceof Usuario) {
				user = (Usuario) UserObj;
				HibernateUtil.initializeObject(user.getTiposusuario());
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
		return user;
	}
/*
	@Override
	public void altaUsuario(Usuario usuario) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.save(usuario);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	public void altaUsuarioFromList(List<Usuario> list) throws Exception {
		for (Usuario usuario : list) {
			if (usuario != null){
				altaUsuario(usuario);
			}
		}
	}

	*/
	
	@Override
	public boolean usernameExists(String username) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.like("username", username));
			List<?> listObj = criteria.list();
			if (listObj.size() > 0)
				return true;
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
		return false;
	}


	@Override
	public List<Usuario> getAllUsers() throws Exception {
		Session session = null;
		List<Usuario> listaUsuarios = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			List<?> listObj = criteria.list();
			for (Object usuarioObj : listObj) {
				if (usuarioObj != null && usuarioObj instanceof Usuario) {
					Usuario usuario = (Usuario) usuarioObj;
					HibernateUtil.initializeObject(usuario.getTiposusuario());
					listaUsuarios.add(usuario);
					
				}
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
		return listaUsuarios;
	}

	@Override
	public Usuario getByUsername(String username) throws Exception {
		Session session = null;
		Usuario usuario = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("username", username));
			Object userObj = criteria.uniqueResult();
			if (userObj != null && userObj instanceof Usuario) {
				usuario = (Usuario) userObj;
				HibernateUtil.initializeObject(usuario.getTiposusuario());
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
		return usuario;
	}
}
