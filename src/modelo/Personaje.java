package modelo;


import java.awt.Rectangle;
import java.io.Serializable;

import interfaz.InterfazJuego;

public class Personaje {
	
/*Constantes, atributos y relaciones de la clase*/
public static final String DORY = "dory";
public static final String DELFIN = "delfin";
public static final String NEMO = "nemo";
public static final String TORTUGA = "tortuga";
public static final String AMIGO_NEMO_1="amigoNemo1";
public static final String ESTRELLA="estrella";
public static final String GLOBO="globo";
public static final String MANTARAYA="mantaraya";
public static final String MARTILLO="martillo";
public static final String PAPA_NEMO="papaNemo";
public static final String PULPO="pulpo";
public static final String TIBURON="tiburon";
public static final String TORTUGA_PEQUENA="tortugaPequena";
public static final int ANCHO = 100;
public static final int ALTO= 100;
private String urlImagen;
private String nombre;
private boolean atrapado;
private int coordenadaX;
private int coordenadaY;
private Rectangle area;


/*Metodo constructor de la clase, encargado de inicializar los atributos*/
public Personaje(String url, String nombre, boolean atrapado,int coordenadaX, int coordenadaY) {
	urlImagen=url;
	this.nombre=nombre;
	this.atrapado=atrapado;
	this.coordenadaX=coordenadaX;
	this.coordenadaY=coordenadaY;
	area= new Rectangle(coordenadaX,coordenadaY,ANCHO,ALTO);
}



/*Metodos getters and setters*/
public String getUrlImagen() {
	return urlImagen;
}
public void setUrlImagen(String urlImagen) {
	this.urlImagen = urlImagen;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public boolean isAtrapado() {
	return atrapado;
}
public void setAtrapado(boolean atrapado) {
	this.atrapado = atrapado;
}
public int getCoordenadaX() {
	return coordenadaX;
}
public void setCoordenadaX(int coordenadaX) {
	this.coordenadaX = coordenadaX;
}
public int getCoordenadaY() {
	return coordenadaY;
}
public void setCoordenadaY(int coordenadaY) {
	this.coordenadaY = coordenadaY;
}
public Rectangle getArea() {
	return area;
}
public void setArea(Rectangle area) {
	this.area = area;
}
/*Fin de getters and setters*/

/*Metodo encargado de cambiar la coordenada X de un personaje, dependiendo del parámetro que le entra
 * asi como cada que llegue al ancho máximo del panel, devolver la coordenada X a -20*/
public void moverPersonaje(int indice) {
	coordenadaX+=indice;
	cambiarArea(this.coordenadaX,this.getCoordenadaY());
	if(coordenadaX>=InterfazJuego.ANCHO_MAXIMO) {
		coordenadaX=-20;
		double random= Math.random()*InterfazJuego.ALTO_MAXIMO;
		coordenadaY=(int)random;
	}
}
/*Metodo encargado de cambiar el area del rectangulo asociado a cada personaje*/
public void cambiarArea(int x, int y) {
 area=new Rectangle(x,y,ANCHO,ALTO);
}


}
