package mx.trillas.ControlEnvio.persistence.pojos;

import java.util.Date;

public class Controlenvio {
	private Integer id;
	private String mensajeria;
	private String origen;
	private String destinatario;
	private String departamento;
	private String observacion;
	private Date fecha;
	
	public Controlenvio(){
		
	}
	public Controlenvio(Integer id, String mensajeria, String origen, String destinatario, String departamento,
			String observacion, Date fecha) {
		super();
		this.id = id;
		this.mensajeria = mensajeria;
		this.origen = origen;
		this.destinatario = destinatario;
		this.departamento = departamento;
		this.observacion = observacion;
		this.fecha = fecha;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMensajeria() {
		return mensajeria;
	}
	public void setMensajeria(String mensajeria) {
		this.mensajeria = mensajeria;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "Controlenvio [id=" + id + ", mensajeria=" + mensajeria + ", origen=" + origen + ", destinatario="
				+ destinatario + ", departamento=" + departamento + ", observacion=" + observacion + ", fecha=" + fecha
				+ "]";
	}
}
