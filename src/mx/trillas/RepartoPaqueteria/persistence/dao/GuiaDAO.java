package mx.trillas.RepartoPaqueteria.persistence.dao;

import java.util.Date;
import java.util.List;

import mx.trillas.RepartoPaqueteria.persistence.pojos.Destinatario;
import mx.trillas.RepartoPaqueteria.persistence.pojos.Guia;

public interface GuiaDAO {
	 public void altaGuia(Guia guia) throws Exception;
	 public Guia getGuia(String numero) throws Exception;
	 public List<Guia> getGuiaList() throws Exception;
	 public List<String> getotrosDeptosList() throws Exception;
	 public List<Guia> getReportList(Date fechaInicio, Date fechaFin, Destinatario destinatario,  String otroDepartamento) throws Exception;
}
