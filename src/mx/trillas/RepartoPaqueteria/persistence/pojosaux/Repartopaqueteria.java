package mx.trillas.RepartoPaqueteria.persistence.pojosaux;

import mx.trillas.RepartoPaqueteria.persistence.pojos.Guia;

public class Repartopaqueteria implements Comparable<Repartopaqueteria> {
	
	private Integer id;
	private Guia guia;
	private String departamento;
	
	public Repartopaqueteria(){
		
	}
	
	public Repartopaqueteria(Integer id, Guia guia, String departamento) {
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
	public int compareTo(Repartopaqueteria repartopaqueteria) {
		
//		System.out.println("this.getDepartamento()=" +this.getDepartamento()
//		+ "  controlenvio.getDepartamento()=" + controlenvio.getDepartamento() 
//		+ "  compareTo=" + this.getDepartamento().compareTo(controlenvio.getDepartamento()));
		
		if (this.getDepartamento().compareTo(repartopaqueteria.getDepartamento()) == 0)
			return 0;
		if (this.getDepartamento().compareTo(repartopaqueteria.getDepartamento()) > 0)
			return 1;
		if (this.getDepartamento().compareTo(repartopaqueteria.getDepartamento()) < 0)
			return -1;
		
		return 0;
	}
	
	@Override
	public String toString() {
		return "RepartoPaqueteria [id=" + id + ", guiaNumber=" + guia.getNumero()  + ", departamento=" + departamento + "]";
	}
}
