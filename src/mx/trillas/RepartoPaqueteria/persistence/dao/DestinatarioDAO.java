package mx.trillas.RepartoPaqueteria.persistence.dao;

import java.util.List;

import mx.trillas.RepartoPaqueteria.persistence.pojos.Departamento;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Destinatario;

public interface DestinatarioDAO {
	public void altaDestinatario(Destinatario destinatario) throws Exception;
	public void updateDestinatarioByList(List<Destinatario> destinatarios) throws Exception;
	public Destinatario getDestinatarioByName(String nombre) throws Exception;
	public List<Destinatario> getDestinatarioFromDeptoList(Departamento departamento) throws Exception;
	public List<Destinatario> getDestinatarioList() throws Exception;
	public void updateDestinatario(Destinatario destinatario) throws Exception;
	public Destinatario getDestinatarioById(int id) throws Exception ;
	public Departamento getDepartamentoByDestinatario(Destinatario destinatario) throws Exception;
}
