package test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Juego;
import modelo.Jugador;
import modelo.Moneda;
import modelo.Personaje;
import modelo.Vehiculo;
import modelo.Excepciones.JugadorNoEncontradoException;
import modelo.Excepciones.JugadorYaExisteException;
import modelo.Excepciones.NombreMuyLargoException;


class JuegoTest {
	
	private Juego j;
	
	private void escenario1() {
		j = new Juego(new Jugador("NicolasM", 0));
		j.setActual(j.getJugadorRaiz());
		j.cargarPersonajes();
		j.getJugador().setPersonajeElegido(new Personaje("Goku", 0, 0, "goku.png"));
	}
	
	
	
	private void escenario2() {
		j = new Juego();
	}
	
	private void escenario3() {
		j = new Juego();
		j.setJugadorRaiz(null);
	}

	@Test
	void testIniciarNivel() {
		escenario1();
		j.iniciarNivel1("goku");
		assertNotNull(j.getMapa());
		assertEquals(1, j.getMapa().getNivel());
	
	}
	
	@Test
	void testIniciarNivel2() {
		escenario1();
		j.iniciarNivel2("Naruto");
		assertNotNull(j.getMapa());
		assertEquals(2, j.getMapa().getNivel());
	}
	
	@Test
	void testIniciarNivel3() {
		escenario1();
		j.iniciarNivel3("Kakashi");
		assertNotNull(j.getMapa());
		assertEquals(3, j.getMapa().getNivel());
	}
	
	@Test
	void testPasoDeNivel() {
		escenario1();
		j.getJugador().setPersonajeElegido(new Personaje("Goku", 0, 0, "goku.png"));
		assertTrue(j.pasoDeNivel());
		j.getJugador().setPersonajeElegido(new Personaje("Goku", 0, 50, "goku.png"));
		assertFalse(j.pasoDeNivel());
	}
	
	@Test
	void testSeIntersecto() {
		escenario1();
		j.iniciarNivel1("Goku");
		assertFalse(j.seIntersecto());
		j.getJugador().setPersonajeElegido(new Personaje("Goku", 0, 0, "goku.png"));
		j.getMapa().setPrimerVehiculo(new Vehiculo(0, 0, Vehiculo.TREN, Vehiculo.DERECHA));
		assertTrue(j.seIntersecto());
		assertEquals(Personaje.Y_INICIAL, j.getJugador().getPersonajeElegido().getPosicionY());
	}
	
	@Test
	void testAtrapoMoneda() {
		escenario1();
		j.iniciarNivel1("Goku");
		assertFalse(j.atrapoMoneda());
		j.getJugador().setPersonajeElegido(new Personaje("Goku", 0, 0, "goku.png"));
		j.getMapa().setPrimerMoneda(new Moneda(0, 0, Moneda.URL, 10));
		assertTrue(j.atrapoMoneda());
		assertEquals(10, j.getJugador().getPuntaje());
	}
	
	@Test
	void testBuscarPersonaje() {
		escenario1();
		Personaje buscado = j.buscarPersonaje("Sasuke");
		assertNull(buscado);
		escenario2();
		buscado = j.buscarPersonaje("Goku");
		assertNotNull(buscado);
	}
	
	@Test
	void testCargarPersonajes() {
		escenario1();
		j.cargarPersonajes();
		Personaje buscado = j.buscarPersonaje("Goku");
		assertNotNull(buscado);
		buscado = j.buscarPersonaje("Kakashi");
		assertNotNull(buscado);
		buscado = j.buscarPersonaje("Naruto");
		assertNotNull(buscado);
		
	}
	
	@Test
	void testCargarDatos() {
		escenario3();
		j.cargarDatos();
		assertNotNull(j.getJugadorRaiz());
		assertNotNull(j.obtenerListaJugadores());
		assertTrue(j.obtenerListaJugadores().size()>0);
	}
	
	@Test
	void testGuardarDatos() {
		escenario2();
		j.cargarDatos();
		
		int anterior = j.obtenerListaJugadores().size();
		try {
			j.agregarJugador(new Jugador("NALV", 15));
		} catch (NombreMuyLargoException e) {
			fail();
		} catch (JugadorYaExisteException e) {
			fail();
		}
		j.guardarDatos();
		j.cargarDatos();
		int actual = j.obtenerListaJugadores().size();
		assertTrue(anterior<actual);
		try {
			j.eliminarJugador("NALV");
			j.guardarDatos();
		} catch (JugadorNoEncontradoException e) {
			fail();
		}
		
	}
	
	@Test
	void testAgregarJugador() {
		escenario2();
		try {
			j.agregarJugador(new Jugador("NALV", 15));
		} catch (NombreMuyLargoException e) {
			fail();
		} catch (JugadorYaExisteException e) {
			fail();
		}
	
		Jugador insertado = j.buscarJugador("NALV");
		assertNotNull(insertado);
		assertEquals("NALV", insertado.getNombre());
		

	}
	
	
	@Test
	void testOrdenarPorPuntaje() {
		escenario1();
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(new Jugador("NicolasM", 0));
		jugadores.add(new Jugador("Drake", 565));
		jugadores.add(new Jugador("JuanM", 171));
		Jugador[] ordenado = j.ordenarPorPuntaje(jugadores);	
		assertEquals(565, ordenado[0].getPuntaje());
		assertEquals(171, ordenado[1].getPuntaje());
		assertEquals(0, ordenado[2].getPuntaje());

	}

	@Test
	void testOrdenarPorNombre() {
		escenario1();
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(new Jugador("NicolasM", 0));
		jugadores.add(new Jugador("Drake", 565));
		jugadores.add(new Jugador("JuanM", 171));
		Jugador[] ordenado = j.ordenarPorNombre(jugadores);
		assertEquals("Drake", ordenado[0].getNombre());
		assertEquals("JuanM", ordenado[1].getNombre());
		assertEquals("NicolasM", ordenado[2].getNombre());
	}
	
	

}
