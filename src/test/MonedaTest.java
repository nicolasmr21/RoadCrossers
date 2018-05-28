package test;

import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import modelo.Moneda;
class MonedaTest {

	private Moneda m;
	
	private void escenario1() {
		m = new Moneda(0, 10, Moneda.URL, 50);
	}

	@Test
	void testMover() {
		escenario1();
		int x = m.getPosicionX();
		int y = m.getPosicionY();
		m.mover(100);
		assertNotEquals(x, m.getPosicionX());
		assertNotEquals(y, m.getPosicionY());

	}
}
