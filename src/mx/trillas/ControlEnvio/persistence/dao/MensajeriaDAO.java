package mx.trillas.ControlEnvio.persistence.dao;

import java.util.List;

import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;

public interface MensajeriaDAO {
	
	public void altaMensajeria(Mensajeria mensajeria) throws Exception;
	public Mensajeria getMensajeria(String nombre) throws Exception;
	public List<Mensajeria> getMensajeriaList() throws Exception;
}
