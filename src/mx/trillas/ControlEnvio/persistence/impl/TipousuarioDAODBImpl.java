package mx.trillas.ControlEnvio.persistence.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import mx.trillas.ControlEnvio.persistence.HibernateUtil;
import mx.trillas.ControlEnvio.persistence.dao.TipousuarioDAO;
import mx.trillas.ControlEnvio.persistence.pojos.Tiposusuario;

public class TipousuarioDAODBImpl implements TipousuarioDAO{

	@Override
	public Tiposusuario getTipoDeusuario(TIPOS_USUARIO tipoUsuario) throws Exception {
		Session session = null;
		Tiposusuario tipousuario = null;
		String tipoDeUsuario = null;
		
		switch (tipoUsuario){
		case TIPOUSUARIO_ADMINISTRADOR:
			tipoDeUsuario = "administrador";
			break;
		case TIPOUSUARIO_CAPTURISTA:
			tipoDeUsuario = "capturista";
			break;
		}
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Tiposusuario.class);
			criteria.add(Restrictions.eq("tipo", tipoDeUsuario));
			Object tipousuarioObj = criteria.uniqueResult();
			if (tipousuarioObj != null && tipousuarioObj instanceof Tiposusuario) {
				tipousuario = (Tiposusuario) tipousuarioObj;
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (session != null)
				session.close();
		}
		return tipousuario;
	}
}
