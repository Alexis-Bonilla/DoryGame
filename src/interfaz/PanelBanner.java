package interfaz;
import javax.swing.*;
import java.awt.*;
public class PanelBanner extends JPanel{
	/*atributo de la clase, utilizado para ponerle un banner como fondo*/
	private JLabel imagen;
	/*Metodo constructor de la clase, inicializa el atributo y configura el panel*/
	public PanelBanner(ImageIcon imagen) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(692,194));
		this.imagen= new JLabel();
		this.imagen.setIcon(imagen);
		add(this.imagen,BorderLayout.CENTER);
	}	
}
