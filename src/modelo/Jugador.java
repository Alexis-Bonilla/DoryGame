package modelo;

import java.io.Serializable;

public class Jugador implements Serializable{
/*Atributos de la clase*/
private String nombre;
private int puntaje;
private int nivelAlcanzado;
/*Metodo constructor de la clase, encargado de inicializar los atributos*/
public Jugador(String nombre, int puntaje, int nivelAlcanzado) {
	this.nombre=nombre;
	this.puntaje=puntaje;
	this.nivelAlcanzado=nivelAlcanzado;
}

/*Metodos getters and setters*/
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public int getPuntaje() {
	return puntaje;
}
public void setPuntaje(int puntaje) {
	this.puntaje = puntaje;
}
public int getNivelAlcanzado() {
	return nivelAlcanzado;
}
public void setNivelAlcanzado(int nivelAlcanzado) {
	this.nivelAlcanzado = nivelAlcanzado;
}
/*Fin de getters and setters*/

/*Metodo encargado de comparar el puntaje de un jugador que entra como parámetro,
 * con el jugador actual, retorna 0 si los puntajes son iguales, 1 si el del parámetro es 
 * mayor que el actual y -1 en caso contrario.*/
public int compararPorPuntaje(Jugador aComparar) {
	int salida;
	if(aComparar.puntaje==this.puntaje) {
		salida=0;
	}else if(aComparar.puntaje>this.puntaje) {
		salida=1;
	}
	else {
		salida=-1;
	}
	return salida;
}
/*Metodo encargado de comparar los nombres del jugador ingresado por parámetro y
 * el jugador actual, retorna 0 si sus nombres son iguales, 1 si el nombre actual es 
 * mayor lexicográficamente que el entrado por parámetro y -1 de lo contrario.*/
public int compararPorNombre(Jugador aComparar) {
	int salida=0;
	if(aComparar.nombre.compareTo(nombre)>0) {
		salida=1;
	}
	else if(aComparar.nombre.compareTo(nombre)==0){
		salida=0;
	}
	else {
		salida=-1;
	}
	return salida;
}
/*Metodo encargado de comparar dos puntajes.*/
public int compararPuntaje(int puntaje) {
	int salida;
	if(this.puntaje==puntaje) {
		salida=0;
	}
	else if(this.puntaje<puntaje) {
		salida=1;
	}
	else {
		salida=-1;
	}
	
	
	
	return salida;
}

}
