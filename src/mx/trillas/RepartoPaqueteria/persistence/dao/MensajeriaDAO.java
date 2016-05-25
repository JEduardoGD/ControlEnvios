package mx.trillas.RepartoPaqueteria.persistence.dao;

import java.util.List;

import mx.trillas.RepartoPaqueteria.persistence.pojos.Mensajeria;

public interface MensajeriaDAO {
	
	public void altaMensajeria(Mensajeria mensajeria) throws Exception;
	public void altaMensajeriaByList(List<Mensajeria> mensajerias) throws Exception;
	public Mensajeria getMensajeria(String nombre) throws Exception;
	public List<Mensajeria> getMensajeriaList() throws Exception;
	public Mensajeria getMensajeriaById(int id) throws Exception;
	public void updateMensajeria(Mensajeria mensajeria) throws Exception;
}
