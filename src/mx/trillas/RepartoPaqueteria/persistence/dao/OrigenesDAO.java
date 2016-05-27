package mx.trillas.RepartoPaqueteria.persistence.dao;

import java.util.List;

import mx.trillas.RepartoPaqueteria.persistence.pojos.Origen;

public interface OrigenesDAO {
	public void altaOrigen(Origen origen) throws Exception;
	public Origen getOrigen(String nombre) throws Exception;
	public List<Origen> getOrigenList() throws Exception;
	public void altaOrigenByList(List<Origen> origenes) throws Exception;
	public void updateOrigen(Origen origen) throws Exception;
}
