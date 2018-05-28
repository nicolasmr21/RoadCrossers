package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Mapa;
import modelo.Moneda;
import modelo.Vehiculo;

class MapaTest {

	private Mapa m;
	
	private void escenario1() {
		m = new Mapa(0, 0, Mapa.MAPA1, 1);
	}
	
	private void escenario2() {
		m = new Mapa(0, 0, Mapa.MAPA2, 2);
	}
	
	private void escenario3() {
		m = new Mapa(0, 0, Mapa.MAPA3, 3);
	}
	
	@Test
	void testCargarVehiculos() {
		escenario1();
		assertNull(m.obtenerListaVehiculos());
		m.cargarCoordenadas();
		m.cargarVehiculos();
		assertNotNull(m.obtenerListaVehiculos());
		
		escenario2();
		assertNull(m.obtenerListaVehiculos());
		m.cargarCoordenadas();
		m.cargarVehiculos();
		assertNotNull(m.obtenerListaVehiculos());
		
		escenario3();
		assertNull(m.obtenerListaVehiculos());
		m.cargarCoordenadas();
		m.cargarVehiculos();
		assertNotNull(m.obtenerListaVehiculos());

	}
	
	@Test
	void testCargarMonedas() {
		escenario1();
		assertNull(m.obtenerListaMonedas());
		m.cargarMonedas();
		assertNotNull(m.obtenerListaMonedas());
		
		escenario2();
		assertNull(m.obtenerListaMonedas());
		m.cargarMonedas();
		assertNotNull(m.obtenerListaMonedas());
		
		escenario3();
		assertNull(m.obtenerListaMonedas());
		m.cargarMonedas();
		assertNotNull(m.obtenerListaMonedas());
		
	}
	
	@Test
	void testCargarCoordenadas() {
		escenario1();
		assertNull(m.getCoordenadasY());
		m.cargarCoordenadas();
		assertNotNull(m.getCoordenadasY());
		
	}
	
	@Test
	void testAgregarVehiculo() {
		escenario1();
		assertNull(m.obtenerListaVehiculos());
		m.agregarVehiculo(new Vehiculo(100, 100, Vehiculo.urls[0], Vehiculo.DERECHA));
		assertNotNull(m.obtenerListaVehiculos());
		assertEquals(1, m.obtenerListaVehiculos().size());
	}
	
	@Test
	void testAgregarMoneda() {
		escenario1();
		assertNull(m.obtenerListaMonedas());
		m.agregarMoneda(new Moneda(0, 10, Moneda.URL, 50));
		assertNotNull(m.obtenerListaMonedas());
		assertEquals(1, m.obtenerListaMonedas().size());

	}
	
	
}
