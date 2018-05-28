package hilos;

import interfaz.VentanaPrincipal;
import modelo.Vehiculo;

public class HiloVehiculo extends Thread{

	VentanaPrincipal ventana;
	Vehiculo vehiculo;
	
	public HiloVehiculo(VentanaPrincipal ventana, Vehiculo vehiculo) {
		this.ventana = ventana;
		this.vehiculo = vehiculo;
	}
	
	public void run() {
		for(int i=0; ; i++) {
			vehiculo.mover(1);	
			ventana.refrescar();
			ventana.verificarInterseccionVehiculo();
	
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
}
