package hilos;

import java.util.ArrayList;

import interfaz.*;
import modelo.*;

public class HiloMover extends Thread{
	/*Atributos y relaciones de la clase*/
	private Personaje personaje;
	private InterfazJuego principal;
	/*Metodo contructor de la clase se encarga de inicializar las dos relaciones*/
	public HiloMover(Personaje personaje, InterfazJuego principal) {
		this.personaje=personaje;
		this.principal=principal;
	}
	/*Metodo correr del hilo de ejecución, indica las instrucciones a seguir cuando se invoque
	 * el método start() en otra clase*/
	public void run() {
	if(!principal.getJuego().getJugadores().isEmpty()) {
			while(true) {
				int nivel=principal.darNivel();
					personaje.moverPersonaje(1*nivel);
					try {
						sleep(5);
					} catch (Exception e) {
						e.printStackTrace();
					}
					principal.refrescarPanel();
		}
	}

		

	}
	
	

}





