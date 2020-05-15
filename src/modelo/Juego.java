package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import interfaz.InterfazJuego;

public class Juego  {
/*Atributos y relaciones de la clase*/

private ArrayList<Jugador> jugadores;
private ArrayList<Personaje> personajes;
private int actual;



/*Metodo constructor de la clase, se encarga de inicializar los atributos y relaciones*/
public Juego() {
	actual=0;
	jugadores=new ArrayList<Jugador>();
	personajes=new ArrayList<Personaje>();
	inicializarPersonajes();
}

/*Metodos getters and setters*/


public ArrayList<Jugador> getJugadores() {
	return jugadores;
}

public void setJugadores(ArrayList<Jugador> jugadores) {
	this.jugadores = jugadores;
}

public ArrayList<Personaje> getPersonajes() {
	return personajes;
}

public void setPersonajes(ArrayList<Personaje> personajes) {
	this.personajes = personajes;
}
public int getActual() {
	return actual;
}

public void setActual(int actual) {
	this.actual = actual;
}
/*Fin de los getters and setters*/

/*Metodo encargado de verificar si un punto de coordenadas x,y en el panel, pertenece a el
 * area que ocupa dory para saber si clickeo en dory*/
public boolean esDory(int x, int y) {
	boolean esDory=false;
	for (int i = 0; i < personajes.size(); i++) {
		if(personajes.get(i).getArea().contains(x, y)) {
			if(personajes.get(i).getNombre().equals(Personaje.DORY)) {
				esDory=true;
			}
		}
	}
	return esDory;
}
/*Metodo encargado de verificar si un punto de coordenadas x,y en el panel, pertenece a el
 * area que ocupa algun otro personaje para saber si clickeo un personaje distinto de dory*/
public boolean esEnemigo(int x, int y) {
	boolean esEnemigo=false;
	for (int i = 0; i < personajes.size(); i++) {
		if(personajes.get(i).getArea().contains(x, y)&&personajes.get(i).getNombre()!=Personaje.DORY) {
			esEnemigo=true;
		}
	}
	return esEnemigo;
}
/*Metodo encargado de cambiar las coordenadas x,y de Dory para cambiar su posición*/
public void moverDory() {
	for (int i = 0; i < personajes.size(); i++) {
		if(personajes.get(i).getNombre().equals(Personaje.DORY)) {
			personajes.get(i).setCoordenadaX((int)(Math.random()*InterfazJuego.ANCHO_MAXIMO));
			personajes.get(i).setCoordenadaY((int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
		}
	}
}
/*Metodo encargado de inicializar cada uno de los personajes del arrayList de personajes*/
public void inicializarPersonajes() {
	Personaje tortuga= new Personaje("./imagenes/Tortuga.png",Personaje.TORTUGA,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje delfin= new Personaje("./imagenes/Delfin.png",Personaje.DELFIN,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje nemo= new Personaje("./imagenes/Nemo.png",Personaje.NEMO,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje dory= new Personaje("./imagenes/Dory.png",Personaje.DORY,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje amigoNemo1= new Personaje("./imagenes/amigoNemo1.png",Personaje.AMIGO_NEMO_1,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje estrella= new Personaje("./imagenes/estrella.png",Personaje.ESTRELLA,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje globo= new Personaje("./imagenes/globo.png",Personaje.GLOBO,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje mantaraya= new Personaje("./imagenes/mantaraya.png",Personaje.MANTARAYA,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje martillo= new Personaje("./imagenes/martillo.png",Personaje.MARTILLO,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje papaNemo= new Personaje("./imagenes/papaNemo.png",Personaje.PAPA_NEMO,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje pulpo= new Personaje("./imagenes/pulpo.png",Personaje.PULPO,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje tiburon= new Personaje("./imagenes/tiburon.png",Personaje.TIBURON,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	Personaje tortugaPequena= new Personaje("./imagenes/tortugaPequeña.png",Personaje.TORTUGA_PEQUENA,false,
			(int)(Math.random()*InterfazJuego.ANCHO_MAXIMO),(int)(Math.random()*InterfazJuego.ALTO_MAXIMO));
	personajes.add(tortuga);
	personajes.add(delfin);
	personajes.add(nemo);
	personajes.add(dory);
	personajes.add(amigoNemo1);
	personajes.add(estrella);
	personajes.add(globo);
	personajes.add(mantaraya);
	personajes.add(martillo);
	personajes.add(papaNemo);
	personajes.add(pulpo);
	personajes.add(tiburon);
	personajes.add(tortugaPequena);
}
/*Metodo encargado de añadir un jugador al arraylist Jugadores*/
public void aniadirJugador(Jugador elJugador) {
	jugadores.add(elJugador);
}
/*Metodo encargado de buscar la posicion de un jugador por el nombre y devolverla*/
public int buscarPosicionJugadorACargar(String nombre) {
	int posicion=0;
	boolean encontrado=false;
	for (int i = 0; i < jugadores.size()&&!encontrado; i++) {
		if(jugadores.get(i).getNombre().equals(nombre)) {
			posicion=i;
			encontrado=true;
		}
	}
	
	return posicion;
	}
/*Metodo encargado de guardar el arrayList jugadores en un archivo serializable*/
public void guardarJugadores() {
	File ruta=new File ("./archivos/juego.dat");
	try {
	FileOutputStream fileOutS = new FileOutputStream(ruta);
	ObjectOutputStream salida = new ObjectOutputStream(fileOutS);
	salida.writeObject(jugadores);
	salida.close();
	fileOutS.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}


}
/*Metodo encargado de cargar un archivo serializable que contiene un arrayList de jugadores*/
public void cargarJugadores() {
	File ruta= new File("./archivos/juego.dat");
	if(ruta.exists()) {
		try {
			FileInputStream fileInS = new FileInputStream(ruta);
			ObjectInputStream entrada = new ObjectInputStream(fileInS);
			jugadores=((ArrayList<Jugador>)entrada.readObject());
			entrada.close();
			fileInS.close();
			}
			catch(Exception e) {
				System.out.println("Se empezo el juego por primera vez, el archivo serializable no tiene datos guardados");
			}
	}
	else {
		
	}
}
/*Metodo encargado de ordenar el ArrayList de jugadores de mayor a menor puntaje*/
public void ordenarPorPuntaje() {
	Jugador temp;
	for (int i = 0; i < jugadores.size(); i++) {
		for (int j = 0; j < jugadores.size()-1; j++) {
			if(jugadores.get(j).compararPorPuntaje(jugadores.get(j+1))==1) {
				temp= jugadores.get(j+1);
				jugadores.set(j+1, jugadores.get(j));
				jugadores.set(j, temp);
			}
		}
	}	
}
/*Metodo encargado de ordenar el Arraylist de Jugadores de menor a mayor orden
 * lexicográfico*/
public void ordenarPorNombre() {
	 Jugador temp;
	 Jugador menor;
	 int cual;
	 
	 for (int i = 0; i < jugadores.size(); i++) {
		 menor=jugadores.get(i);
		 cual=i;
	     for (int j = i; j < jugadores.size(); j++) {
			if(jugadores.get(j).compararPorNombre(menor)==1) {
				menor=jugadores.get(j);
				cual=j;
				temp=jugadores.get(i);
				jugadores.set(i, jugadores.get(cual));
				jugadores.set(j,temp);
			}
		}
	 }
}
/*Metodo encargado de buscar un jugador por su puntaje y retornar el nombre,
 * siempre que el Arraylist de jugdores esté ordenada por puntaje*/
public int buscarJugador(int puntaje) {
	int indice=-1;
	boolean encontre=false;

	int inicio=0;
	int fin=jugadores.size()-1;
	int medio=0;
	while(inicio<=fin&&!encontre) {
		 medio=(inicio+fin)/2;
			 if(jugadores.get(medio).compararPuntaje(puntaje)==0) {
					encontre=true;
					indice=medio;
			}
		else if(jugadores.get(medio).compararPuntaje(puntaje)==-1) {
				fin=medio-1;
			}
		else if(jugadores.get(medio).compararPuntaje(puntaje)==1) {
				inicio=medio+1;
					
			}
		 
	}

	
	return indice;
}
/*Metodo encargado de borrar el ArrayList de jugadores y dejar solo el jugador
 * cuyo nombre es el entrado por parámetro*/
public void borrarJugadores(String nombre) {
	if(!jugadores.isEmpty()) {
		Jugador jugador= jugadores.get(buscarPosicionJugadorACargar(nombre));
		jugadores.clear();
		jugadores.add(jugador);
		guardarJugadores();	
	}

}


}
