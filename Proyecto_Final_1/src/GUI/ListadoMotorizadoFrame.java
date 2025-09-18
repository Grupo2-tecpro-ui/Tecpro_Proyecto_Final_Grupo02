package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Controlador.ControladorMotorizado;
import Modelado.Motorizado;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoMotorizadoFrame extends JFrame implements ActionListener {

    private ControladorMotorizado controlador;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField txtBuscarDni;
    private JTextField txtdni;
    private JButton btnElimianr;

    public ListadoMotorizadoFrame(ControladorMotorizado controlador) {
        this.controlador = controlador;

        setTitle("Listado de Motorizados");
        setSize(926, 499);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Panel búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBounds(0, 0, 910, 33);
        panelBusqueda.add(new JLabel("Buscar por DNI:"));
        txtBuscarDni = new JTextField(15);
        panelBusqueda.add(txtBuscarDni);

        JButton btnBuscar = new JButton("Buscar");
        JButton btnMostrarTodos = new JButton("Mostrar Todos");
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnMostrarTodos);

        // Tabla
        String[] columnas = {"ID","DNI","Nombres","Apellidos","Celular","Placa","Estado","Tarjetas","Fecha Tarjetas"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(0, 33, 910, 427);
        getContentPane().setLayout(null);

        getContentPane().add(panelBusqueda);
        
        JLabel lblEliminarPorDni = new JLabel("Eliminar por DNI:");
        panelBusqueda.add(lblEliminarPorDni);
        
        txtdni = new JTextField(15);
        panelBusqueda.add(txtdni);
        
        btnElimianr = new JButton("Eliminar");
        btnElimianr.addActionListener(this);
        panelBusqueda.add(btnElimianr);
        getContentPane().add(scroll);

        // Eventos
        btnBuscar.addActionListener(e -> buscarPorDni());
        btnMostrarTodos.addActionListener(e -> cargarMotorizados());

        // Cargar al inicio
        cargarMotorizados();
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
	 

	
	
	
	
	
}