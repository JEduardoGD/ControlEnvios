package mx.trillas.ControlEnvio.persistence.pojosaux;

import javafx.scene.layout.VBox;

public class VboxTable {
	
	private Integer id;
	private VBox vbox;
	private int rowsNumber;
	
	public VboxTable(){
		
	}
	
	public VboxTable(Integer id, VBox vbox, int rowsNumber) {
		super();
		this.id = id;
		this.vbox = vbox;
		this.rowsNumber = rowsNumber;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public VBox getVbox() {
		return vbox;
	}

	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}

	public int getRowsNumber() {
		return rowsNumber;
	}

	public void setRowsNumber(int rowsNumber) {
		this.rowsNumber = rowsNumber;
	}


	
	@Override
	public String toString() {
		return "VBox [id=" + id + ", vbox=" + vbox  + ", RowsNumber=" + rowsNumber + "]";
	}
}

