package mx.trillas.ControlEnvio.persistence.dao;

import mx.trillas.ControlEnvio.persistence.pojos.Destinatario;

public interface DestinatarioDAO {
	public void altaDestinatario(Destinatario destinatario) throws Exception;
	public Destinatario getDestinatarioByName(String nombre) throws Exception;
}
