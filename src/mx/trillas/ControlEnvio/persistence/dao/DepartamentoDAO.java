package mx.trillas.ControlEnvio.persistence.dao;

import java.util.List;

import mx.trillas.ControlEnvio.persistence.pojos.Departamento;

public interface DepartamentoDAO {

	public void altaDepartamento(Departamento departamento) throws Exception;
	public Departamento getDepartamento(String nombre) throws Exception;
	public List<Departamento> getDepartamentoList() throws Exception;
}
