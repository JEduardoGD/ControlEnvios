package mx.trillas.ControlEnvio.persistence.dao;

import mx.trillas.ControlEnvio.persistence.pojos.Departamento;

public interface DepartamentoDAO {

	public void altaDepartamento(Departamento departamento) throws Exception;
	public Departamento getDepartamento(String nombre) throws Exception;
}
