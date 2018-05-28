package modelo.Excepciones;

/**

 * Esta clase se encarga de representar una excepcion que especifica que un nombre es muy largo y no se puede agregar.

 * @author: Nicolas Martinez - Juan Manuel Castillo.

 * @version: 24/05/2018 A

 * @see <a href = "http://www.instagram.com/Nicolasmr21" /> Haznos saber en que nos puedes ayudar – Nicolás Mártinez </a>
 
 * @see <a href = "http://www.instagram.com/Juan17castillo" /> Haznos saber en que nos puedes ayudar – Juan Manuel Castillo </a>

 */
public class NombreMuyLargoException extends Exception{
	private String nombre;

	public NombreMuyLargoException(String nombre) {
		super("El nombre digitado no debe superar 15 caracteres");
		this.nombre= nombre;
	}

	public String getNombre() {
		return nombre;
	}
}
