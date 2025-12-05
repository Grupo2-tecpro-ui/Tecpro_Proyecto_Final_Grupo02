package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Controlador.ControladorMotorizado;
import Modelado.Motorizado;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoMotorizadoFrame extends JDialog implements ActionListener {

  
    private static final Color PROSEGUR_YELLOW = new Color(255, 209, 0);
    private static final Color PROSEGUR_BLACK  = new Color(25, 25, 25);
    private static final Color PROSEGUR_GRAY   = new Color(230, 230, 230);

    private ControladorMotorizado controlador;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField txtBuscarDni;
    private JTextField txtdni;
    private JButton btnElimianr;
    private JButton btnEditarMotorizado;
    private JButton btnRegistrarEntrega;

    public ListadoMotorizadoFrame(JFrame parent, ControladorMotorizado controlador) {

        super(parent, "📋 Listado de Motorizados", false); // NO MODAL

        this.controlador = controlador;

        setSize(1082, 508);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

      
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBounds(0, 0, 1056, 41);
        panelBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 8));

        panelBusqueda.setBackground(PROSEGUR_BLACK);

    
        btnRegistrarEntrega = new JButton("🚚 Registrar Entrega");
        styleButton(btnRegistrarEntrega);

        JLabel lblBuscar = new JLabel("🔎 Buscar DNI:");
        lblBuscar.setForeground(PROSEGUR_YELLOW);

        txtBuscarDni = new JTextField(10);

        JButton btnBuscar = new JButton("Buscar");
        styleButton(btnBuscar);

        JButton btnMostrarTodos = new JButton("📄 Mostrar Todos");
        styleButton(btnMostrarTodos);

        JLabel lblEliminarPorDni = new JLabel("🗑️ Eliminar DNI:");
        lblEliminarPorDni.setForeground(PROSEGUR_YELLOW);

        txtdni = new JTextField(10);

        btnElimianr = new JButton("Eliminar");
        btnElimianr.setEnabled(false);
        styleButton(btnElimianr);
        btnElimianr.addActionListener(this);

        btnEditarMotorizado = new JButton("✏️ Editar");
        styleButton(btnEditarMotorizado);
        btnEditarMotorizado.addActionListener(this);

 
        panelBusqueda.add(btnRegistrarEntrega);
        panelBusqueda.add(lblBuscar);
        panelBusqueda.add(txtBuscarDni);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnMostrarTodos);
        panelBusqueda.add(lblEliminarPorDni);
        panelBusqueda.add(txtdni);
        panelBusqueda.add(btnElimianr);
        panelBusqueda.add(btnEditarMotorizado);

        getContentPane().add(panelBusqueda);

    
        String[] columnas = {
                "ID", "DNI", "Nombres", "Apellidos", "Celular", "Placa",
                "Estado", "Tarjetas en Ruta", "Fecha Asignación"
        };

        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);

        tabla.getTableHeader().setBackground(PROSEGUR_YELLOW);
        tabla.getTableHeader().setForeground(Color.BLACK);
        tabla.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 13));

        tabla.setBackground(PROSEGUR_GRAY);
        tabla.setForeground(Color.BLACK);
        tabla.setFont(new Font("Montserrat", Font.PLAIN, 12));
        tabla.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(0, 52, 1056, 403);
        getContentPane().add(scroll);

        
        btnBuscar.addActionListener(e -> buscarPorDni());
        btnMostrarTodos.addActionListener(e -> cargarMotorizados());

        cargarMotorizados();

       
        btnRegistrarEntrega.addActionListener(e -> abrirRegistrarEntrega());
    }



    private void styleButton(JButton btn) {
        btn.setBackground(PROSEGUR_YELLOW);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Montserrat", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }


  
    private void abrirRegistrarEntrega() {

        String dniSeleccionado = null;

        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            dniSeleccionado = tabla.getValueAt(fila, 1).toString().trim();
        }

        if (dniSeleccionado == null || dniSeleccionado.isEmpty()) {
            dniSeleccionado = txtBuscarDni.getText().trim();
        }

        if (!dniSeleccionado.matches("\\d{8}")) {
            dniSeleccionado = JOptionPane.showInputDialog(this,
                    "Ingrese DNI del motorizado:");

            if (dniSeleccionado == null) return;
            dniSeleccionado = dniSeleccionado.trim();
        }

        Entrega ef = new Entrega(this.controlador, dniSeleccionado);

        this.setEnabled(false);

        ef.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                cargarMotorizados();
                setEnabled(true);
                toFront();
            }
        });

        ef.setVisible(true);
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
                    m.getFechaIngreso()
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
                encontrados++;
            }
        }

        if (!dni.isEmpty() && encontrados == 0) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ No se encontró motorizado con DNI: " + dni);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEditarMotorizado) {
            editarMotorizado();
        }
        if (e.getSource() == btnElimianr) {
            eliminarMotorizado();
        }
    }

 
    private void eliminarMotorizado() {
        String dni = txtdni.getText().trim();

        if (!controlador.existeDni(dni)) {
            JOptionPane.showMessageDialog(this,
                    "❌ No existe motorizado con DNI: " + dni);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "🗑️ ¿Eliminar motorizado con DNI: " + dni + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        if (controlador.eliminarPorDni(dni)) {
            JOptionPane.showMessageDialog(this, "Motorizado eliminado.");
            cargarMotorizados();
            txtdni.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar.");
        }
    }

   
    private void editarMotorizado() {

        String dniToEdit = txtBuscarDni.getText().trim();

        if (dniToEdit.isEmpty()) {
            dniToEdit = JOptionPane.showInputDialog(this,
                    "Ingrese DNI a editar:");

            if (dniToEdit == null) return;
            dniToEdit = dniToEdit.trim();
        }

        Motorizado found = null;
        for (Motorizado m : controlador.listarMotorizados()) {
            if (dniToEdit.equals(m.getDni())) {
                found = m;
                break;
            }
        }

        if (found == null) {
            JOptionPane.showMessageDialog(this,
                    "❌ No existe motorizado con DNI: " + dniToEdit);
            return;
        }

        EditarMotorizado editFrame = new EditarMotorizado(controlador, found);

        this.setEnabled(false);

        editFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                cargarMotorizados();
                setEnabled(true);
                toFront();
            }
        });

        editFrame.setVisible(true);
    }
}
