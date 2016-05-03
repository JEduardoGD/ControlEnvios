package mx.trillas.ControlEnvio.persistence.dao;

import java.util.List;

import mx.trillas.ControlEnvio.persistence.pojos.Origen;

public interface OrigenesDAO {
	public void altaOrigen(Origen origen) throws Exception;
	public Origen getOrigen(String nombre) throws Exception;
	public List<Origen> getOrigenList() throws Exception;
}
