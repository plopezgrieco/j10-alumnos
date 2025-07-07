package vista.swing;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.Alumno;
import negocio.GestionAlumnos;
import negocio.GestionAlumnosImpl;

public class VInicial extends JFrame {
	private static final long serialVersionUID = 1L;
	private String separadorPrincipal=";", separadorSecundario="/";
	private JTextField tBuscaAlumno;
	private JButton bBuscar;
	private JTextField tBuscaPorDNI;
	private JButton bBuscarDNI;
	private JLabel cantidadAlumnos;
	private JButton bAgregar;
	private JButton bEliminar;
	private JButton bEditar;
	private JButton bConsultar;
	private JButton bSalir;
	private JLabel nombre;
	private JLabel dni;
	private ArrayList<Alumno> listaAlumnos;
	private Font labelFont = new Font(Font.DIALOG, Font.BOLD, 16);
	private Font textFont = new Font(Font.DIALOG_INPUT, Font.ITALIC, 16);
	private Font listaFont = new Font(Font.DIALOG, Font.PLAIN, 16);
	JTable tabla;
	private DefaultTableModel modelo;
	private Object[][] datosTabla;// = new String[0][0];
	private String[] titulosTabla = { "Primer Apellido", "Segundo Apellido", "Nombre",
			"DNI/DOC", "Nota" };
	private Alumno alumnoActual;
	private int AGREGAR = 0;
	private int EDITAR = 1;
	private int CONSULTAR = 2;
	private int ELIMINAR = 3;
	int filaActualTabla;
	
	private GestionAlumnos negocio = new GestionAlumnosImpl();

	public VInicial() {
		super("Gestión de Alumnos");
		setResizable(false);
		setBounds(100, 10, 918, 746);
		getContentPane().setLayout(null);

		ManejaEventos manejador = new ManejaEventos();

		armaMenu();
		
		nombre = new JLabel("Alumno: ");
		nombre.setBounds(10, 10, 80, 30);
		nombre.setFont(labelFont);
		add(nombre);

		dni = new JLabel("Documento: ");
		dni.setBounds(500, 10, 140, 30);
		dni.setFont(labelFont);
		add(dni);

		tBuscaAlumno = new JTextField();
		tBuscaAlumno.setBounds(10, 40, 350, 30);
		tBuscaAlumno.setFont(textFont);
		add(tBuscaAlumno);
		tBuscaAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bBuscar.doClick();
			}
		});

		bBuscar = new JButton("Buscar");
		bBuscar.setBounds(370, 40, 80, 30);
		add(bBuscar);
		bBuscar.addActionListener(manejador);
		
		tBuscaPorDNI = new JTextField();
		tBuscaPorDNI.setBounds(500, 40, 300, 30);
		tBuscaPorDNI.setFont(textFont);
		add(tBuscaPorDNI);
		tBuscaAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bBuscarDNI.doClick();
			}
		});

		bBuscarDNI = new JButton("Buscar");
		bBuscarDNI.setBounds(810, 40, 80, 30);
		add(bBuscarDNI);
		bBuscarDNI.addActionListener(manejador);
		
		cantidadAlumnos = new JLabel();
		cantidadAlumnos.setBounds(300, 63, 250, 30);
		cantidadAlumnos.setFont(labelFont);
		add(cantidadAlumnos);

		modelo = new DefaultTableModel(datosTabla, titulosTabla);
		tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setFont(this.listaFont);
		tabla.addMouseListener(manejador);
		tabla.addKeyListener(manejador);
		tabla.setAutoCreateRowSorter(true);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaSetEditableFalse();
		actulizaTabla();

		JScrollPane barras = new JScrollPane(tabla,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barras.setBounds(10, 90, 890, 530);
		add(barras);

		bAgregar = new JButton("Agregar");
		bAgregar.setBounds(10, 10, 80, 30);
		bAgregar.addActionListener(manejador);

		bEditar = new JButton("Editar");
		bEditar.setBounds(100, 10, 80, 30);
		bEditar.addActionListener(manejador);
		bEditar.setEnabled(false);

		bEliminar = new JButton("Eliminar");
		bEliminar.setBounds(190, 10, 80, 30);
		bEliminar.addActionListener(manejador);
		bEliminar.setEnabled(false);

		bConsultar = new JButton("Detalle");
		bConsultar.setBounds(280, 10, 80, 30);
		bConsultar.addActionListener(manejador);
		bConsultar.setEnabled(false);

		bSalir = new JButton("Salir");
		bSalir.setBounds(800, 10, 80, 30);
		bSalir.addActionListener(manejador);

		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(10, 630, 890, 50);
		panelBotones.setLayout(null);
		panelBotones.setBackground(Color.LIGHT_GRAY);
		panelBotones.add(bAgregar);
		panelBotones.add(bEditar);
		panelBotones.add(bEliminar);
		panelBotones.add(bConsultar);
		panelBotones.add(bSalir);

		add(panelBotones);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				bSalir.doClick();
			}
		});

	}
	
	private void armaMenu(){
		JMenu menuArchivo = new JMenu( "Archivo" ); 
		menuArchivo.setMnemonic( 'A' ); 

//		JMenuItem importar = new JMenuItem( "Importar..." );
//		importar.setMnemonic( 'I' );
//		menuArchivo.add(importar);
//		importar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				importar();
//			}
//		});
//
//		JMenuItem exportar = new JMenuItem( "Exportar..." );
//		importar.setMnemonic( 'E' );
//		menuArchivo.add(exportar);
//		exportar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				exportar();
//			}
//		});

		JMenuItem opciones = new JMenuItem( "Opciones..." );
		opciones.setMnemonic( 'O' );
		menuArchivo.add(opciones);
		opciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				opciones();
			}
		});

		JMenuItem elementoAcercaDe = new JMenuItem( "Acerca de..." );
		elementoAcercaDe.setMnemonic( 'c' );
		menuArchivo.add( elementoAcercaDe );
		elementoAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Gestion Alumnos 1.0\n");				
			}
		});
		
		JMenuItem elementoSalir = new JMenuItem("Salir");
		elementoSalir.setMnemonic('S');
		menuArchivo.add(elementoSalir);
		elementoSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bSalir.doClick();
			}
		});
		
		JMenuBar barra = new JMenuBar();
		setJMenuBar( barra );
		barra.add( menuArchivo );
		
		
	}
	
	private void tablaSetEditableFalse(){
		for (int c = 0; c < tabla.getColumnCount(); c++)
		{
		    Class<?> col_class = tabla.getColumnClass(c);
		    tabla.setDefaultEditor(col_class, null);        // remove editor
		}
	}

	private void actulizaTabla() {
		DefaultTableCellRenderer alDerecha = new DefaultTableCellRenderer();
		alDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer alCentrado = new DefaultTableCellRenderer();
		alCentrado.setHorizontalAlignment(SwingConstants.CENTER);

		modelo.setDataVector(datosTabla, titulosTabla);
		tabla.getColumn("Primer Apellido").setMinWidth(200);
		tabla.getColumn("Segundo Apellido").setMinWidth(200);
		tabla.getColumn("Nombre").setMinWidth(200);
		tabla.getColumn("DNI/DOC").setMinWidth(120);
		tabla.getColumn("Nota").setMinWidth(80);

		tabla.getColumn("DNI/DOC").setCellRenderer(alCentrado);
		tabla.getColumn("Nota").setCellRenderer(alDerecha);
	}
	
	void cargaTabla(){
		if (datosTabla.length != 0) {
			alumnoActual = listaAlumnos.get(tabla
				.convertRowIndexToModel(tabla.getSelectedRow()));
		}else{
			alumnoActual = null;
			bEditar.setEnabled(false);
			bEliminar.setEnabled(false);
			bConsultar.setEnabled(false);
			tBuscaAlumno.requestFocus();

		}
	}
	
//	private void importar(){
//		File f=null;
//		JFileChooser chooser = new JFileChooser();
//		String[] datosImportar;
//
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Texto *.txt, *.csv", "txt", "csv");
//		chooser.setFileFilter(filter);
//		
//		int returnVal = chooser.showOpenDialog(this);
//		
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
//			try {
//				f = new File(chooser.getSelectedFile().getCanonicalPath());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		else{
//			return;
//		}
//		if (JOptionPane.showConfirmDialog(null,
//				"Desea Importar el fichero " + f.getAbsolutePath(), "Importar csv", 2) == 0){
//			datosImportar = new String[3];
//			datosImportar[0] = f.getAbsolutePath();
//			datosImportar[1] = separadorPrincipal;
//			datosImportar[2] = separadorSecundario;
//			int cant = 0;
//			try {
//				cant = negocio.importarCSV(f.getAbsolutePath());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}// probar el 2
//			JOptionPane.showMessageDialog(null,	"Se han importado " + cant + " contactos con Exito");
//			bBuscar.doClick();
//		} else {
//			return;
//		}
//		
//	}
//
//	private void exportar(){
//		File f=null;
//		JFileChooser chooser = new JFileChooser();
//		String[] datosExportar;
//
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Texto *.txt, *.csv", "txt", "csv");
//		chooser.setFileFilter(filter);
//		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
//		
//		int returnVal = chooser.showOpenDialog(this);
//		
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
//			try {
//				f = new File(chooser.getSelectedFile().getCanonicalPath());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		else{
//			return;
//		}
//		if (JOptionPane.showConfirmDialog(null,
//				"Desea Exportar la Base de Datos al fichero " + f.getAbsolutePath(), "Exportar csv", 2) == 0){
//			datosExportar = new String[2];
//			datosExportar[0] = f.getAbsolutePath();
//			datosExportar[1] = separadorPrincipal;
////			datosExportar[2] = separadorSecundario;
////			int cant = control.exportar(datosExportar);
//			int cant = 0;
//			JOptionPane.showMessageDialog(null,	"Se han exportado " + cant + " contactos con Exito");
//			bBuscar.doClick();
//		} else {
//			return;
//		}
//	}

	private void opciones(){
		new VentanaOpciones(this);
	}

	private class ManejaEventos extends MouseAdapter implements ActionListener,
			KeyListener {

		public void keyPressed(KeyEvent arg0) {
		}

		public void keyReleased(KeyEvent arg0) {
		}
		
		public void mouseClicked(MouseEvent eve) {
			if (eve.getSource() == tabla && eve.getClickCount() == 2) {
				bConsultar.doClick();
			}
		}

		public void mousePressed(MouseEvent eve) {
			if (eve.getSource() == tabla) {
				alumnoActual = listaAlumnos.get(tabla
						.convertRowIndexToModel(tabla.getSelectedRow()));
				bEditar.setEnabled(true);
				bEliminar.setEnabled(true);
				bConsultar.setEnabled(true);
				bConsultar.requestFocus();
			}
		}

		public void keyTyped(KeyEvent eve) {
			if (eve.getSource() == tabla) {
				if (eve.getKeyChar() == KeyEvent.VK_ENTER) {
					alumnoActual = listaAlumnos.get(tabla
							.convertRowIndexToModel(tabla.getSelectedRow()));
					bEditar.setEnabled(true);
					bEliminar.setEnabled(true);
					bConsultar.setEnabled(true);
					bConsultar.requestFocus();
				} else if (eve.getKeyChar() == KeyEvent.VK_TAB) {
					bEditar.setEnabled(true);
					bEditar.requestFocus();
					bEliminar.setEnabled(true);
					bEliminar.requestFocus();
					bConsultar.setEnabled(true);
					bConsultar.requestFocus();
				}
			}
		}

		private Object[][] alumnosToMatriz() {
			ArrayList<Alumno> lista = listaAlumnos;
			Object[] filas = new Object[lista.size()];
			Object[][] resultado;
			resultado = new Object[filas.length][];
			for (int i = 0; i < filas.length; i++) {
				resultado[i] = alumnoToArray(lista.get(i));
			}
			return resultado;
		}
		
		private Object[] alumnoToArray(Alumno alu){
			Object[] res = null;
			if (alu != null) {
				res = new Object[5];
				res[0] = alu.getApellido1();
				res[1] = alu.getApellido2();
				res[2] = alu.getNombre();
				res[3] = alu.getDni();
				res[4] = String.format("%5.2f", alu.getNota());
			}
			return res;
		}

		public void actionPerformed(ActionEvent ev) {
			if (ev.getSource() == bBuscar || ev.getSource() == bBuscarDNI) {
				listaAlumnos = new ArrayList<>();
				if(ev.getSource() == bBuscar)
					if(!tBuscaAlumno.getText().equals("")){
						listaAlumnos.addAll(negocio.getAlumnosByNombre(tBuscaAlumno.getText()));
					}else{
						listaAlumnos.addAll(negocio.getAlumnos());
					}
				else
					listaAlumnos.add(negocio.getAlumnoByDni(tBuscaPorDNI.getText()));
				datosTabla = alumnosToMatriz();
				cantidadAlumnos.setText("Encontrados: " + datosTabla.length + " alumnos");
				actulizaTabla();
				if (datosTabla.length == 0) {
					bEditar.setEnabled(false);
					bEliminar.setEnabled(false);
					bConsultar.setEnabled(false);
					;
				}
			}
//			if (ev.getSource() == bAgregar) {
//				new VentanaEdicion(negocio, alumnoActual, AGREGAR, VInicial.this);
//			}
//			if (ev.getSource() == bEditar) {
//				filaActualTabla = tabla.getSelectedRow();
//				new VentanaEdicion(negocio, alumnoActual, EDITAR, VInicial.this);
//			}
//			if (ev.getSource() == bEliminar) {
//				filaActualTabla = tabla.getSelectedRow();
//				new VentanaEdicion(negocio, alumnoActual, ELIMINAR, VInicial.this);
//			}
//			if (ev.getSource() == bConsultar) {
//				filaActualTabla = tabla.getSelectedRow();
//				new VentanaEdicion(negocio, alumnoActual, CONSULTAR, VInicial.this);
//			}
			if (ev.getSource() == bSalir) {
				if (JOptionPane.showConfirmDialog(null,
						"Desea salir de la aplicación", "Salir de la App", 2) == 0){
					System.exit(0);
				}
			}
		}
	}
}
