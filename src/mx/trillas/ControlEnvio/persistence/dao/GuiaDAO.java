package mx.trillas.ControlEnvio.persistence.dao;

import java.util.Date;
import java.util.List;

import mx.trillas.ControlEnvio.persistence.pojos.Guia;

public interface GuiaDAO {
	 public void altaGuia(Guia guia) throws Exception;
	 public Guia getGuia(String numero) throws Exception;
	 public List<Guia> getGuiaList() throws Exception;
	 public List<Guia> getGuiaListByDate(Date fechaInicio, Date fechaFin) throws Exception;
}
