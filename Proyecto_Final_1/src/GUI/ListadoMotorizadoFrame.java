package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Controlador.ControladorMotorizado;
import Modelado.Motorizado;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;

public class ListadoMotorizadoFrame extends JFrame implements ActionListener {

    private ControladorMotorizado controlador;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField txtBuscarDni;
    private JTextField txtdni;
    private JButton btnElimianr;
    private JButton btnEditarMotorizado;
    private JButton btnRegistrarEntrega;

    public ListadoMotorizadoFrame(ControladorMotorizado controlador) {
        this.controlador = controlador;
        

        setTitle("Listado de Motorizados");
        setSize(926, 499);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Panel búsqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBounds(0, 0, 910, 33);
        JLabel label = new JLabel("Buscar por DNI:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 9));
        txtBuscarDni = new JTextField(15);
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        btnRegistrarEntrega = new JButton("Registrar Entrega");
        panelBusqueda.add(btnRegistrarEntrega);

        JButton btnBuscar = new JButton("Buscar");
        JButton btnMostrarTodos = new JButton("Mostrar Todos");

        // Tabla
        String[] columnas = {"ID","DNI","Nombres","Apellidos","Celular","Placa","Estado","Tarjetas en Ruta","Fecha Tarjetas"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(0, 33, 910, 427);
        getContentPane().setLayout(null);

        getContentPane().add(panelBusqueda);
        
        JLabel lblEliminarPorDni = new JLabel("Eliminar por DNI:");
        lblEliminarPorDni.setFont(new Font("Tahoma", Font.PLAIN, 9));
        
        txtdni = new JTextField(15);
        
        btnElimianr = new JButton("Eliminar");
        btnElimianr.addActionListener(this);
        
        btnEditarMotorizado = new JButton("Editar Motorizado");
        btnEditarMotorizado.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnEditarMotorizado.addActionListener(this);
        panelBusqueda.add(label);
        panelBusqueda.add(txtBuscarDni);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnMostrarTodos);
        panelBusqueda.add(lblEliminarPorDni);
        panelBusqueda.add(txtdni);
        panelBusqueda.add(btnElimianr);
        panelBusqueda.add(btnEditarMotorizado);
        getContentPane().add(scroll);

        // Eventos
        btnBuscar.addActionListener(e -> buscarPorDni());
        btnMostrarTodos.addActionListener(e -> cargarMotorizados());

        // Cargar al inicio
        cargarMotorizados();
        btnRegistrarEntrega.addActionListener(e ->{
    	    
    	    String dniSeleccionado = null;
    	    int fila = tabla.getSelectedRow();
    	    if (fila >= 0) {
    	        Object val = tabla.getValueAt(fila, 1); 
    	        if (val != null) dniSeleccionado = val.toString().trim();
    	    }

    	   
    	    if (dniSeleccionado == null || dniSeleccionado.isEmpty()) {
    	        dniSeleccionado = txtBuscarDni.getText().trim();
    	    }

    	   
    	    if (dniSeleccionado == null || !dniSeleccionado.matches("\\d{8}")) {
    	        dniSeleccionado = JOptionPane.showInputDialog(this, "Ingrese DNI del motorizado para registrar entrega:");
    	        if (dniSeleccionado == null) return;
    	        dniSeleccionado = dniSeleccionado.trim();
    	    }

    	
    	    Entrega ef = new Entrega(this.controlador != null ? this.controlador : new Controlador.ControladorMotorizado(), dniSeleccionado);
    	    ef.setVisible(true);

    	    
    	    ef.addWindowListener(new java.awt.event.WindowAdapter() {
    	        @Override
    	        public void windowClosed(java.awt.event.WindowEvent e) {
    	            cargarMotorizados(); 
    	        }
    	        @Override
    	        public void windowDeactivated(java.awt.event.WindowEvent e) {
    	            cargarMotorizados();
    	        }
    	    });
    	});
    }
    

    private void cargarMotorizados() {
        modeloTabla.setRowCount(0);
        List<Motorizado> lista = controlador.listarMotorizados();
        for (Motorizado m : lista) {
            modeloTabla.addRow(new Object[]{
                    m.getId(),
                    m.getDni(),
                    m.getNombres(),
                    m.getApellidos(),
                    m.getCelular(),
                    m.getPlaca(),
                    m.getEstado(),
                    m.getTarjetasAsignadas(),
                    m.getFechaTarjetas()
            });
        }
    }

    private void buscarPorDni() {
        String dni = txtBuscarDni.getText().trim();
        modeloTabla.setRowCount(0);
        List<Motorizado> lista = controlador.listarMotorizados();
        int encontrados = 0;
        for (Motorizado m : lista) {
            if (dni.isEmpty() || m.getDni().equalsIgnoreCase(dni)) {
                modeloTabla.addRow(new Object[]{
                        m.getId(),
                        m.getDni(),
                        m.getNombres(),
                        m.getApellidos(),
                        m.getCelular(),
                        m.getPlaca(),
                        m.getEstado(),
                        m.getTarjetasAsignadas(),
                        m.getFechaTarjetas()
                });
                encontrados ++;
            }
          
        }
        if (!dni.isEmpty() && encontrados == 0) {
            JOptionPane.showMessageDialog(this, "No se encontró motorizado con DNI: " + dni);

    }


    }
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEditarMotorizado) {
			do_btnEditarMotorizado_actionPerformed(e);
		}
		if (e.getSource() == btnElimianr) {
			do_btnElimianr_actionPerformed(e);
		}
	}
	protected void do_btnElimianr_actionPerformed(ActionEvent e) {
		String dni = txtdni.getText().trim();
		 boolean existe = false;
		    if (this.controlador != null) {
		        existe = this.controlador.existeDni(dni);
		    } else {
		        existe = repositorio.InMemoryDatabase.existeDni(dni);
		    }

		    if (!existe) {
		        JOptionPane.showMessageDialog(this, "No existe motorizado con DNI: " + dni);
		        return;
		    }
		    int confirm = JOptionPane.showConfirmDialog(this,
		            "¿Confirmas eliminar al motorizado con DNI: " + dni + "?",
		            "Confirmar eliminación",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.WARNING_MESSAGE);
		    if (confirm != JOptionPane.YES_OPTION) {
		        return;
		    }
		    boolean ok;
		    if (this.controlador != null) {
		        ok = this.controlador.eliminarPorDni(dni);
		    } else {
		        ok = repositorio.InMemoryDatabase.EliminarPorDni(dni);
		    }
		    if (ok) {
		        JOptionPane.showMessageDialog(this, "Motorizado eliminado correctamente.");
		        cargarMotorizados(); 
		        txtdni.setText("");
		    } else {
		        JOptionPane.showMessageDialog(this, "No se pudo eliminar el motorizado. Intente nuevamente.");
		    }
	}
	 

	
	
	
	protected void do_btnEditarMotorizado_actionPerformed(ActionEvent e) {
		
		String dniToEdit = txtBuscarDni.getText().trim();
	    if (dniToEdit.isEmpty()) {
	        dniToEdit = JOptionPane.showInputDialog(this, "Ingrese DNI del motorizado a editar:");
	        if (dniToEdit == null) return;
	        dniToEdit = dniToEdit.trim();
	    }

	       
	    Motorizado found = null;
	    List<Motorizado> lista = controlador.listarMotorizados();
	    for (Motorizado m : lista) {
	        if (dniToEdit.equals(m.getDni())) {
	            found = m;
	            break;
	        }
	    }

	    if (found == null) {
	        JOptionPane.showMessageDialog(this, "No se encontró motorizado con DNI: " + dniToEdit);
	        return;
	    }

	
	    EditarMotorizado editFrame = new EditarMotorizado(controlador, found);
	    editFrame.setVisible(true);

	      editFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosed(java.awt.event.WindowEvent e) {
	            cargarMotorizados();
	        }

	        @Override
	        public void windowDeactivated(java.awt.event.WindowEvent e) {

	            cargarMotorizados();
	        }
	    });
	}
	
}

	
		
		
		

