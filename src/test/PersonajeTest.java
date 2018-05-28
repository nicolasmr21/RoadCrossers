package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Personaje;

class PersonajeTest {

	Personaje p;
	
	private void escenario1() {
		p = new Personaje("Narurto", 100, 100, "naruto.png");
		p.insertar(new Personaje("Goku", 200, 200, "goku.png"));
	}
	
	
	@Test
	void testMover() {
		escenario1();
		int x = p.getPosicionX();
		int y = p.getPosicionY();
		p.mover(100, 100);
		assertNotEquals(x, p.getPosicionX());
		assertNotEquals(y, p.getPosicionY());
	}

	@Test
	void testSaleDelLimite() {
		escenario1();
		assertTrue(p.saleDelLimite(1000, 1000));
		assertFalse(p.saleDelLimite(100, 100));
	}
	
	@Test
	void testInsertar() {
		escenario1();
		p.insertar(new Personaje("Kakashi", 150, 150, "k.png"));
		Personaje insertado = p.buscarPersonaje("Kakashi");
		assertNotNull(insertado);
		assertEquals("Kakashi", insertado.getNombre());
	}
	
	@Test
	void testBuscarPersonaje() {
		escenario1();
		Personaje buscado = p.buscarPersonaje("Goku");
		assertNotNull(buscado);
		assertEquals(200, buscado.getPosicionX());
	}
	
	@Test
	void testCompareTo() {
		escenario1();
		Personaje nuevo = new Personaje("Kakashi", 150, 150, "k.png");
		assertTrue(p.compareTo(nuevo)>0);
	}
}
