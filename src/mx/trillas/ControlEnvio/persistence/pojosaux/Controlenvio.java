package mx.trillas.ControlEnvio.persistence.pojosaux;

import mx.trillas.ControlEnvio.persistence.pojos.Guia;

public class Controlenvio implements Comparable<Controlenvio> {
	
	private Integer id;
	private Guia guia;
	private String departamento;
	
	public Controlenvio(){
		
	}
	
	public Controlenvio(Integer id, Guia guia, String departamento) {
		super();
		this.id = id;
		this.departamento = departamento;
		this.guia = guia;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Guia getGuia() {
		return guia;
	}
	
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	@Override
	public int compareTo(Controlenvio controlenvio) {
		return this.getDepartamento().compareTo(controlenvio.getDepartamento());
	}
	
	@Override
	public String toString() {
		return "Controlenvio [id=" + id + ", guiaNumber=" + guia.getNumero()  + ", departamento=" + departamento + "]";
	}
}
