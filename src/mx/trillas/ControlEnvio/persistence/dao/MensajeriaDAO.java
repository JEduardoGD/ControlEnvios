package mx.trillas.ControlEnvio.persistence.dao;

import mx.trillas.ControlEnvio.persistence.pojos.Mensajeria;

public interface MensajeriaDAO {
	
	public void altaMensajeria(Mensajeria mensajeria) throws Exception;
	public Mensajeria getMensajeria(String nombre) throws Exception;
}
