package modelo.Excepciones;

/**

 * Esta clase se encarga de representar una excepcion que especifica que un jugador ya existe y por ende 
 * no puede ser creado de nuevo.

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */
public class JugadorYaExisteException extends Exception{

	String nombre;

	public JugadorYaExisteException(String nombre) {
		super(" Un jugador con tal nombre ya existe ");
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
