package modelo.Excepciones;

/**

 * Esta clase se encarga de representar una excepcion que especifica que un jugador no ha sido encontrado.

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */
public class JugadorNoEncontradoException extends Exception{

	String nombre;
	int puntos;

	public JugadorNoEncontradoException(int puntos) {
		super(" No existe un jugador con tal puntaje");
		this.puntos = puntos;
	}

	public JugadorNoEncontradoException(String nombre) {
		super(" No existe un jugador con tal nombre");
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntos() {
		return puntos;
	}
	
	
}
