package interfaz;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PanelComandos extends JPanel implements ActionListener{
	
/*Constantes, atributos y relaciones de la clase*/	
public static final String BOTON_NUEVO_JUEGO="nuevo juego";
public static final String BOTON_GUARDAR="guardar";
public static final String BOTON_PARAR = "parar";
public final static String BOTON_INICIAR = "iniciar";
private InterfazJuego principal;	
private JLabel labNombre;
private JLabel labNivel;
private JLabel labPuntaje;
private JTextField txtNombre;
private JTextField txtNivel;
private JTextField txtPuntaje;
private JButton butGuardar;
private JButton butIniciar;
private JButton butParar;
private JButton butNuevoJuego;
private JButton butOrdenarNombre;
private JButton butOrdenarPuntaje;

/*Metodo constructor de la clase, se encarga de inicializar los atributos y las relaciones,
 * y de configurar el panel y los paneles auxiliares dentro de este*/
public PanelComandos(InterfazJuego v) {
	principal=v;
	JPanel panelAux1=new JPanel();
	panelAux1.setLayout(new GridLayout(1,8));
	TitledBorder borde1= BorderFactory.createTitledBorder("Información");
	borde1.setTitleColor(Color.BLUE);
	panelAux1.setBorder(borde1);
	JPanel panelAux2= new JPanel();
	panelAux2.setLayout(new GridLayout(1,4));
	TitledBorder borde2= BorderFactory.createTitledBorder("Opciones");
	borde2.setTitleColor(Color.BLUE);
	panelAux2.setBorder(borde2);
	labNombre= new JLabel("Nombre:"+" ");
	labNombre.setHorizontalAlignment(JLabel.CENTER);
	labNivel= new JLabel("Nivel:"+" ");
	labNivel.setHorizontalAlignment(JLabel.CENTER);
	labPuntaje= new JLabel("Puntaje:"+" ");
	labPuntaje.setHorizontalAlignment(JLabel.CENTER);
	txtNombre = new JTextField("");
	txtNombre.setEditable(false);
	txtNivel = new JTextField("1");
	txtNivel.setEditable(false);
	txtPuntaje = new JTextField("0");
	txtPuntaje.setEditable(false);
	butGuardar= new JButton("Guardar");
	butGuardar.setBackground(new Color(238,232,239));
	butGuardar.addActionListener(this);
	butGuardar.setActionCommand(BOTON_GUARDAR);
	butIniciar= new JButton("Iniciar");
	butIniciar.setBackground(new Color(238,232,239));
	butIniciar.addActionListener(this);
	butIniciar.setActionCommand(BOTON_INICIAR);
	butNuevoJuego= new JButton("Nuevo Juego");
	butNuevoJuego.addActionListener(this);
	butNuevoJuego.setActionCommand(BOTON_NUEVO_JUEGO);
	butNuevoJuego.setBackground(new Color(238,232,239));
	butParar= new JButton("Parar");
	butParar.addActionListener(this);
	butParar.setActionCommand(BOTON_PARAR);
	butParar.setBackground(new Color(238,232,239));
	this.setLayout(new GridLayout(2,1));
	setPreferredSize(new Dimension(692,100));
	panelAux1.add(labNombre);
	panelAux1.add(txtNombre);
	panelAux1.add(labPuntaje);
	panelAux1.add(txtPuntaje);
	panelAux1.add(labNivel);
	panelAux1.add(txtNivel);

	panelAux2.add(butNuevoJuego);
	panelAux2.add(butIniciar);
	panelAux2.add(butParar);
	panelAux2.add(butGuardar);

	

	this.add(panelAux1);
	this.add(panelAux2);
}



/*Metodo encargado de refrescar el nombre que aparece en el JTextField de nombre*/
public void refrescarNombre(String nombre) {
	txtNombre.setText(nombre);
}
/*Metodo encargado de refrescar el puntaje en el JTextField de puntaje*/
public void refrescarPuntaje(int puntaje) {
	txtPuntaje.setText(puntaje+"");
}
/*Metodo encargado de obtener el puntaje que está en el JTextField de puntaje*/
public int darPuntaje() {
	int puntaje= Integer.parseInt(txtPuntaje.getText());
	return puntaje;
}

/*Metodo encargado de refrescar el JTextField de nivel*/
public void refrescarNivel(int nivel) {
	txtNivel.setText(nivel+"");
}
/*Metodo encargado de retornar el nombre que está en el JTextField txtNombre*/
public String darNombre() {
	return txtNombre.getText();
}



/*Metodo action performed, encargado de gestionar las instrucciones a realizar 
 * dependiendo de los eventos generados por los botones del panel*/
@Override
public void actionPerformed(ActionEvent evento) {
	String comando=evento.getActionCommand();

	if(comando.equals(this.BOTON_NUEVO_JUEGO)) {
		File f= new File("./archivos/juego.dat");
		int opcion=JOptionPane.showConfirmDialog(null, "Desea cargar alguna partida?");
		if(opcion==JOptionPane.YES_OPTION) {
			if(f.exists()) {
				String nombre= JOptionPane.showInputDialog("Ingrese el nombre del jugador que desea cargar");
				principal.cargarUnJugador(nombre);
			}	
			else {
				JOptionPane.showMessageDialog(null, "No hay ninguna partida guardada" );
				String nombre=JOptionPane.showInputDialog(null, "Introduce tu nombre");
				principal.iniciarJuego(nombre);	
			}
		}
		else if(opcion==JOptionPane.NO_OPTION){
				String nombre=JOptionPane.showInputDialog(null, "Introduce tu nombre");
				principal.iniciarJuego(nombre);	
		}
	}
	else if(comando.equals(this.BOTON_GUARDAR)) {
		principal.guardarJuego();
	}

	else if(comando.equals(this.BOTON_PARAR)) {
		principal.pararHilo();
	}
	else if(comando.equals(this.BOTON_INICIAR)) {
		principal.iniciarHilo();
	}
}
	
}
