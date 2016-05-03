package mx.trillas.ControlEnvio.persistence.dao;

import java.util.List;

import mx.trillas.ControlEnvio.persistence.pojos.Departamento;
import mx.trillas.ControlEnvio.persistence.pojos.Destinatario;

public interface DestinatarioDAO {
	public void altaDestinatario(Destinatario destinatario) throws Exception;
	public Destinatario getDestinatarioByName(String nombre) throws Exception;
	public List<Destinatario> getDestinatarioFromDeptoList(Departamento departamento) throws Exception;
}
