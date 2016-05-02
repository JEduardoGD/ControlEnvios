package mx.trillas.ControlEnvio.persistence.dao;

import mx.trillas.ControlEnvio.persistence.pojos.Usuario;

public interface UsuarioDAO {
	public Usuario getByUsernameAndPassword(String username, String password) throws Exception;
	public Usuario getUsuarioByUsername(String username) throws Exception;
}