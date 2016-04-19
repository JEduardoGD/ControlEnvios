package mx.trillas.ControlEnvio.persistence.factory;

import mx.trillas.ControlEnvio.persistence.dao.UsuarioDAO;
import mx.trillas.ControlEnvio.persistence.impl.UsuarioDAODBImpl;

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
