package mx.trillas.ControlEnvio;

public class CapturarRegistro {
	 private static final String NUMERO_GUIA_PATTERN = "([a-zA-Z]{1}[0-9]{12,44})";
	 private static final String STRING_PATTERN = "([a-zA-Z]){4,45}";
	 
	 public static boolean verificarNumeroGuia(){
		 
		 return true;
	 }
	 
	 public static boolean verificarDepartamento(){
		 return false;
	 }
	 
	 public static boolean verificarOrigen(){
		 return false;
	 }
	 
	 public static boolean verificarDestinatario() {
		 return false;
	 }
}
