package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.AlexisException;

public class PanelTabla extends JPanel implements ActionListener{
	/*Constantes, atributos y relaciones de la clase*/
	public final static String BUSCAR="buscar";
	public final static String BORRAR_REGISTRO="borrar";
	public final static String BOTON_ORDENAR_NOMBRE= "ordenar nombre";
	public final static String BOTON_ORDENAR_PUNTAJE="ordenar puntaje";
	private JTable jTblPuntajes;
	private JScrollPane scroll;
	private DefaultTableModel dtm;
	private JButton butBuscar;
	private InterfazJuego principal;
	private JButton butBorrar;
	private JButton butOrdenarNombre;
	private JButton butOrdenarPuntaje;

	
	/*Metodo constructor de la clase, se encarga de inicializar los atributos y configurar el panel*/
	public PanelTabla(InterfazJuego principal) {
		this.principal=principal;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(302,700));
		inicializarTabla();
		this.add(scroll,BorderLayout.CENTER);
		butBuscar= new JButton("Buscar");
		butBuscar.addActionListener(this);
		butBuscar.setActionCommand(BUSCAR);
		butBuscar.setBackground(new Color(238,232,239));

		butBorrar= new JButton("Borrar Registro");
		butBorrar.addActionListener(this);
		butBorrar.setActionCommand(BORRAR_REGISTRO);
		butBorrar.setBackground(new Color(238,232,239));
		this.add(butBorrar, BorderLayout.NORTH);
		JPanel panelAux= new JPanel();
		panelAux.setLayout(new GridLayout(1,3));
		this.add(panelAux,BorderLayout.SOUTH);
		butOrdenarPuntaje= new JButton("O. Puntaje");
		butOrdenarPuntaje.addActionListener(this);
		butOrdenarPuntaje.setActionCommand(BOTON_ORDENAR_PUNTAJE);
		butOrdenarPuntaje.setBackground(new Color(238,232,239));
		butOrdenarNombre= new JButton("O. Nombre");
		butOrdenarNombre.addActionListener(this);
		butOrdenarNombre.setActionCommand(this.BOTON_ORDENAR_NOMBRE);
		butOrdenarNombre.setBackground(new Color(238,232,239));
		panelAux.add(butBuscar);
		panelAux.add(butOrdenarNombre);
		panelAux.add(butOrdenarPuntaje);
	}
	/*Metodo encargado de inicializar el jTable de la clase y darle un formato adecuado para la visualizacion*/
	public void inicializarTabla() {
		
		String[][] data= {};
		String[] titulos= new String[]{"Jugador","Puntaje"};
		dtm= new DefaultTableModel(data,titulos);
		jTblPuntajes=new JTable(dtm);
		jTblPuntajes.setPreferredSize(new Dimension(302, 700));
		scroll= new JScrollPane(jTblPuntajes);	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		jTblPuntajes.getColumnModel().getColumn(0).setCellRenderer(tcr);
		jTblPuntajes.getColumnModel().getColumn(1).setCellRenderer(tcr);
	
	}
	/*Metodo encargado de añadir una fila en la tabla, le ingresa el nombre y puntaje del jugador que desea añadir
	 * a la tabla*/
	public void aniadirFila(String nombre, int puntaje) {
		Object[] fila = {nombre,puntaje};
		dtm.addRow(fila);
	}
	

	/*Metodo encargado de borrar todas las columnas de la tabla*/
	public void borrarTabla() {
		for (int i = dtm.getRowCount()-1; i >=0; i--) {
		dtm.removeRow(i);	
		}
	}
	/*Metodo action performed, encargado de gestionar las instrucciones a realizar 
	 * dependiendo de los eventos generados por los botones del panel*/
	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando= evento.getActionCommand();
		if(comando.equals(BUSCAR)) {
			int puntaje=0;
			String puntajeString=JOptionPane.showInputDialog("Ingrese el puntaje que desea buscar");
			if(puntajeString!=null) {
				if(!puntajeString.equals("")) {
					puntaje=Integer.parseInt(puntajeString);
				}
			}
		
			 
			try {
				principal.buscarJugador(puntaje);
			} catch (AlexisException e) {
				JOptionPane.showMessageDialog(null, "No se encontro el jugador con puntaje de "+ puntaje);
			}
		}
		else if(comando.equals(BORRAR_REGISTRO)) {
			principal.borrarJugadores();
		}
		else if(comando.equals(this.BOTON_ORDENAR_PUNTAJE)) {
			principal.ordenarPorPuntaje();
		}
		else if(comando.equals(this.BOTON_ORDENAR_NOMBRE)) {
			principal.ordenarPorNombre();
		}
		
	}
	
	
	

}
