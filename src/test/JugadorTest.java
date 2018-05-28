package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Jugador;

class JugadorTest {

	Jugador j;
	
	private void escenario1() {
		j = new Jugador("NicolasMartinez", 0);
		j.insertar(new Jugador("Alejandro", 0));
		j.insertar(new Jugador("Santiago", 0));
	}
	
	
	@Test
	void testInorder() {
		escenario1();
		ArrayList<Jugador> jugadores = new ArrayList<>();
		j.inorden(jugadores);
		
		assertTrue(jugadores.size()!=0);
		assertEquals(3, jugadores.size());
		assertEquals("Santiago", jugadores.get(jugadores.size()-1).getNombre());
		
	}
	
	@Test
	void testInsertar() {
		escenario1();
		j.insertar(new Jugador("Victor", 0));
		
		Jugador insertado = j.buscarJugador("Victor");
		assertNotNull(insertado);
		assertEquals("Victor", insertado.getNombre());
		
	}
	
	@Test
	void testEliminarJugador() {
		escenario1();
		j.eliminarJugador("Santiago");
		Jugador eliminado = j.buscarJugador("Santiago");
		assertNull(eliminado);
		Jugador buscado  = j.buscarJugador("Alejandro");
		assertNotNull(buscado);
		j.eliminarJugador("Alejandro");
		eliminado = j.buscarJugador("Alejandro");
		assertNull(eliminado);
	}
	
	@Test
	void testCompareTo() {
		escenario1();
		Jugador nuevo = new Jugador("Zandro", 8);
		assertTrue(j.compareTo(nuevo)<0);

	}
	
	@Test
	void testDarMayor() {
		escenario1();
		Jugador mayor = j.darMayor();
		assertEquals("Santiago", mayor.getNombre());
		
	}
	
	@Test
	void testDarMenor() {
		escenario1();
		Jugador menor = j.darMenor();
		assertEquals("Alejandro", menor.getNombre());
	}
	
	@Test
	void testEsHoja() {
		escenario1();
		assertFalse(j.esHoja());
		assertTrue(j.darMayor().esHoja());
		assertTrue(j.darMenor().esHoja());
		
	}
	
	@Test
	void testBuscarJugador() {
		escenario1();
		Jugador buscado  = j.buscarJugador("NicolasMartinez");
		assertNotNull(buscado);
		assertEquals(j.getNombre(), buscado.getNombre());		
	}
	
	
	

}
