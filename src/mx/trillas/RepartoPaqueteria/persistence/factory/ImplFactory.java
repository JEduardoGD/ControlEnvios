package mx.trillas.RepartoPaqueteria.persistence.factory;

import mx.trillas.RepartoPaqueteria.persistence.dao.UsuarioDAO;
import mx.trillas.RepartoPaqueteria.persistence.impl.UsuarioDAODBImpl;

public abstract class ImplFactory {
	private static IMPLEMENT imp = ImplFactory.IMPLEMENT.HIBERNATE;

	public static enum IMPLEMENT {
		HIBERNATE
	}

	public static UsuarioDAO getUsuarioDAO() {
		switch (imp) {
		case HIBERNATE:
			return new UsuarioDAODBImpl();
		default:
			break;
		}
		return null;
	}
}
