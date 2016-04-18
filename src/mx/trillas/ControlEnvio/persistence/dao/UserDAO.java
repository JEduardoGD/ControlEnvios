package mx.trillas.ControlEnvio.persistence.dao;

import java.util.List;

import mx.trillas.ControlEnvio.persistence.pojos.Usuario;

public abstract interface UserDAO {

	public Usuario getUser(String username, String password) throws Exception;
	public Usuario getUser(String username) throws Exception;
	public boolean usernameExists(String username) throws Exception;
	public Usuario getByUsername(String username) throws Exception;
	public List<Usuario> getAllUsers() throws Exception;
}