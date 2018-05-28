package test;

import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import modelo.Vehiculo;


class VehiculoTest {
	
	private Vehiculo v;
	
	private void escenario1() {
		v = new Vehiculo(100, 100, Vehiculo.urls[0], Vehiculo.DERECHA);
	}

	@Test
	void testMover() {
		escenario1();
		int x = v.getPosicionX();
		v.mover(100);
		assertNotEquals(x, v.getPosicionX());
	}

}
