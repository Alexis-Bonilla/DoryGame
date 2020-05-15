package interfaz;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import modelo.*;
import javax.swing.*;

public class PanelMar extends JPanel implements MouseListener{
/*Relacion con la ventana principal*/
private InterfazJuego principal;

	/*Metodo constructor de la clase, se encarga de configurar el panel e inicializar la relacion*/
	public PanelMar( InterfazJuego p) {
		principal=p;
		setPreferredSize(new Dimension(692,260));
		setLayout(new BorderLayout());
	    addMouseListener(this);
		
	}
	/*Metodo encargado de pintar la imagen de fondo y de los personajes*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D)g;
		ImageIcon imagenMar= new ImageIcon("./imagenes/Marsito.jpg");
		g2.drawImage(imagenMar.getImage(),0 , 0, null);
		ArrayList<Personaje> personajes =principal.getJuego().getPersonajes();
		for (int i = 0; i < personajes.size(); i++) {
			ImageIcon imagen= new ImageIcon(personajes.get(i).getUrlImagen());
			
			g2.drawImage(imagen.getImage(),personajes.get(i).getCoordenadaX(),
					personajes.get(i).getCoordenadaY(),Personaje.ANCHO,Personaje.ALTO,null);
		}
	}
	
	/*Metodos de la interfaz MouseListener, encargados de gestionar
	 * los eventos generados al dar click en los componentes del panel*/
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {	
	}
	@Override
	public void mouseExited(MouseEvent arg0) {	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		principal.modificarPuntaje(e.getX(), e.getY());
		principal.subirNivel();
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
