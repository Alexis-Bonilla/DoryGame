package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Juego;
import modelo.Jugador;

class testJuego {
	
	
	private Juego elJuego;
	
	
	
	private void setupEscenario1() {
		elJuego= new Juego();
		elJuego.getJugadores().add(new Jugador("jug1",-10,0));
		elJuego.getJugadores().add(new Jugador("jug2",10,0));
		elJuego.getJugadores().add(new Jugador("jug3",20,0));
		elJuego.getJugadores().add(new Jugador("jug4",-20,0));
		elJuego.getJugadores().add(new Jugador("jug5",50,0));
	}
	
	
	private void setupEscenario2() {
		elJuego= new Juego();
		elJuego.getJugadores().add(new Jugador("z",0,0));
		elJuego.getJugadores().add(new Jugador("b",0,0));
		elJuego.getJugadores().add(new Jugador("c",0,0));
		elJuego.getJugadores().add(new Jugador("r",0,0));
		elJuego.getJugadores().add(new Jugador("a",0,0));
	}
	
	private void setupEscenario3() {
		elJuego= new Juego();
		elJuego.getJugadores().add(new Jugador("z",10,0));
		elJuego.getJugadores().add(new Jugador("b",0,0));
		elJuego.getJugadores().add(new Jugador("c",2,0));
		elJuego.getJugadores().add(new Jugador("r",16,0));
		elJuego.getJugadores().add(new Jugador("a",8,0));
	}
	
	
	
	
	
	@Test
	public void testOrdenarPorPuntaje() {
		setupEscenario1();
		elJuego.ordenarPorPuntaje();
		for (int i = 0; i < elJuego.getJugadores().size()-1; i++) {
			assertTrue(elJuego.getJugadores().get(i).compararPorPuntaje(elJuego.getJugadores().get(i+1))==-1);
		}	
	}
	
	@Test
	public void testOrdenarPorNombre() {
		setupEscenario2();
		elJuego.ordenarPorNombre();
		for (int i = 0; i < elJuego.getJugadores().size()-1; i++) {
			assertTrue(elJuego.getJugadores().get(i).compararPorNombre(elJuego.getJugadores().get(i+1))==1);
		}	
	}
	
	@Test
	public void testBuscarJugador() {
		setupEscenario3();
		assertEquals(elJuego.getJugadores().get(elJuego.buscarJugador(16)).getNombre(),"r");
		assertTrue(elJuego.buscarJugador(5)==-1);
	}
	
	
		
		


}
