package interfaz;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.*;

import modelo.*;
import hilos.*;



public class InterfazJuego extends JFrame{
	
	/*Constantes, atributos y relaciones de la clase*/
	public static final int ANCHO_MAXIMO=692;
	public static final int ALTO_MAXIMO=300;
	private PanelMar panelMar;
	private PanelBanner panelBanner;
	private PanelComandos panelComandos;
	private Juego juego;
	private HiloMover[] hiloMover;
	private PanelTabla panelTabla;


	
	/*Método constructor de la clase, se encarga de inicializar los atributos y las relaciones*/
	public InterfazJuego() {
		setTitle( "Buscando a Dory Alexis" );
	    setSize( 1000, 700 );
	    setResizable( false );
	    setDefaultCloseOperation( EXIT_ON_CLOSE );
		ImageIcon imagenDory= new ImageIcon("./imagenes/Buscandote.png");
		panelMar=new PanelMar(this);
		panelBanner= new PanelBanner(imagenDory);
		panelComandos= new PanelComandos(this);
		panelTabla = new PanelTabla(this);
		setLayout(new BorderLayout());
		add(panelTabla,BorderLayout.EAST);
		add(panelBanner,BorderLayout.NORTH);
		add(panelMar,BorderLayout.CENTER);
		add(panelComandos,BorderLayout.SOUTH);	
		juego=new Juego();
		cargarJuego();
		mostrarJugadores();
		
	}
	/*Métodos getters and setters*/
	public int darNivel() {
	return juego.getJugadores().get(juego.getActual()).getNivelAlcanzado();
	}
	public PanelMar getPanelMar() {
		return panelMar;
	}
	public void setPanelMar(PanelMar panelMar) {
		this.panelMar = panelMar;
	}
	public PanelBanner getPanelBanner() {
		return panelBanner;
	}
	public void setPanelBanner(PanelBanner panelBanner) {
		this.panelBanner = panelBanner;
	}
	public PanelComandos getPanelInformacion() {
		return panelComandos;
	}
	public void setPanelInformacion(PanelComandos panelInformacion) {
		this.panelComandos = panelInformacion;
	}
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego modelo) {
		this.juego = modelo;
	}
	
	//*Fin de los getters and setters*/
	
	
	
	
	/*Metodo que se encarga de modificar el puntaje dependiendo de si clickeó
	  en un enemigo o en dory*/
	public void modificarPuntaje(int x,int y) {
		int puntaje= panelComandos.darPuntaje();
		int posicion=juego.buscarPosicionJugadorACargar(panelComandos.darNombre());
		juego.setActual(posicion);
		if(juego.esDory(x, y)) {
			puntaje+=5;
			juego.moverDory();
		}
		else if(juego.esEnemigo(x, y)){
			puntaje-=10;
		}
		juego.getJugadores().get(juego.getActual()).setPuntaje(puntaje);
		panelComandos.refrescarPuntaje(puntaje);
		
	}
	/*Metodo encargado de subir el nivel y refrescarlo en el panel, dependiendo de si el usuario ya sumo mas de 30 puntos*/
	public void subirNivel() {
		int puntaje=panelComandos.darPuntaje();
		int nivel=juego.getJugadores().get(juego.getActual()).getNivelAlcanzado();
		nivel=(puntaje>=30)?(puntaje/30)+1:1;
		juego.getJugadores().get(juego.getActual()).setNivelAlcanzado(nivel);
		panelComandos.refrescarNivel(nivel);
	}
	/*Metodo encargado de guardar en estado actual del juego en la carpeta archivos de el projecto, serializando cada una de las
	 * clases del modelo necesarias*/
	public void guardarJuego()  {
		juego.guardarJugadores();
		guardarDatosJugador();
		mostrarJugadores();
	
	}
	/*Metodo encargado de cargar el último estado guardado del juego leyendo un archivo serializado donde se 
	 * encuentra toda la información necesaria*/
	public void cargarJuego() {
	juego.cargarJugadores();
	}
	
	/*Metodo encargado de crear un archivo de texto en la carpeta puntajes
	 * del modelo, donde se encuentra en nombre, puntaje y nivel alcanzado del último jugador*/
	public void guardarDatosJugador() {
		File ruta= new File("./puntajes/puntajes.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(ruta);
			BufferedWriter bw= new BufferedWriter(fw);
			for (int i = 0; i < juego.getJugadores().size(); i++) {
				bw.write("Nombre: "+juego.getJugadores().get(i).getNombre()+" ");
				bw.write("Puntaje: "+juego.getJugadores().get(i).getPuntaje()+" ");
				bw.write("Nivel Alcanzado: "+juego.getJugadores().get(i).getNivelAlcanzado());
				bw.newLine();
			}
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*Metodo encargado de iniciar el hilo de ejecución de los peces para empezar a pintar cada uno
	 * y dar el efecto de movimiento*/
	public void iniciarHilo() {
			hiloMover= new HiloMover[13];
			if(hiloMover[0]==null) {
				for (int i = 0; i < hiloMover.length; i++) {
					hiloMover[i]= new HiloMover(juego.getPersonajes().get(i),this);
					hiloMover[i].start();
				}
			}
		}	

	/*Metodo encargado de inicializar un nuevo juego con un nombre que el usuario ingresa
	 * el puntaje y nivel empiezan desde el menor(0 puntaje, nivel 1)*/
	public void iniciarJuego(String nombre) {	
		juego.aniadirJugador(new Jugador(nombre,0,1));
		int posicion = juego.buscarPosicionJugadorACargar(nombre);
		juego.setActual(posicion);		
		panelComandos.refrescarNombre(juego.getJugadores().get(juego.getActual()).getNombre());
		panelComandos.refrescarPuntaje(juego.getJugadores().get(juego.getActual()).getPuntaje());
		panelComandos.refrescarNivel(juego.getJugadores().get(juego.getActual()).getNivelAlcanzado());
		
	}
	/*Metodo encargado de hacer pintar de nuevo los graphics del panel para dar la ilusión de
	 * movimiento*/
	public void refrescarPanel() {
		panelMar.repaint();
	}
	/*Hilo main. por aquí empieza la ejecución del programa, crea una instancia de la interfaz, y la hace
	 * visible*/
	public static void main(String[]args) {
		InterfazJuego m= new InterfazJuego();
		m.setVisible(true);
	}
	/*Metodo encargado de mostrar la informacion de los jugadores en la tabla de panelTabla*/
	public void mostrarJugadores() {
		panelTabla.borrarTabla();
		
		String nombre="";
		int puntaje=0;
		juego.cargarJugadores();

		for (int i = 0; i < juego.getJugadores().size(); i++) {
			System.out.println(juego.getJugadores().get(i).getNombre()+" "+juego.getJugadores().get(i).getPuntaje());
		}
		for (int i = 0; i < juego.getJugadores().size(); i++) {
			nombre=juego.getJugadores().get(i).getNombre();
			puntaje = juego.getJugadores().get(i).getPuntaje();
			panelTabla.aniadirFila(nombre, puntaje);
		}
	}
	/*Metodo encargado de cargar algun jugador, con el nombre especificado por parámetro*/
	public void cargarUnJugador(String nombre) {
		if(!juego.getJugadores().isEmpty()) {
			int posicion=juego.buscarPosicionJugadorACargar(nombre);
			juego.setActual(posicion);
			panelComandos.refrescarNivel(juego.getJugadores().get(juego.getActual()).getNivelAlcanzado());
			panelComandos.refrescarNombre(juego.getJugadores().get(juego.getActual()).getNombre());
			panelComandos.refrescarPuntaje(juego.getJugadores().get(juego.getActual()).getPuntaje());
			subirNivel();
		}

	}
	/*Metodo encargado de detener los hilos de ejecucion de los peces*/
	public void pararHilo() {
		for (int i = 0; i < hiloMover.length; i++) {
			hiloMover[i].stop();
		}
	}
	/*Metodo encargado de buscar un jugador y mostrarlo por pantalla si fue encontrado, de lo contrario
	 * muestra un mensaje que dice que el jugador no fue encontrado*/
	public void buscarJugador(int puntaje) throws AlexisException {
		int indice=juego.buscarJugador(puntaje);
		if(indice!=-1) {
			JOptionPane.showMessageDialog(null,"El jugador con puntaje de "+puntaje+
					" es:  " +juego.getJugadores().get(indice).getNombre());
		}
		else {
			throw new AlexisException("");
		}
		
	}
	/*Metodo encargado de borrar todos los jugadores del ArrayList jugadores del modelo, a excepcion del 
	 * jugador actual*/
	public void borrarJugadores() {
		juego.borrarJugadores(panelComandos.darNombre());
		mostrarJugadores();
	}
	/* Metodo encargado de hacer la llamada al mundo y ordenar los jugadores de mayor
	 * a menor puntaje y de actualizar la tabla de jugadores de Panel Tabla*/
	public void ordenarPorPuntaje() {
		juego.ordenarPorPuntaje();
		juego.guardarJugadores();
		mostrarJugadores();
	}
	/* Metodo encargado de hacer la llamada al mundo de ordenar los jugadores por
	 * su nombre, de menor a mayor y de actualizar la tabla de jugadores de panel tabla*/
	public void ordenarPorNombre() {
		juego.ordenarPorNombre();
		juego.guardarJugadores();
		mostrarJugadores();
	}
	

}
