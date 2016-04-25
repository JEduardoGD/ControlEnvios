package mx.trillas.ControlEnvio.persistence.dao;

import mx.trillas.ControlEnvio.persistence.pojos.Tiposusuario;

public abstract interface TipousuarioDAO {

	public enum TIPOS_USUARIO {
		
		TIPOUSUARIO_ADMINISTRADOR, 
		TIPOUSUARIO_CAPTURISTA
	}
	public Tiposusuario getTipoDeusuario(TIPOS_USUARIO tipoUsuario) throws Exception;
}
