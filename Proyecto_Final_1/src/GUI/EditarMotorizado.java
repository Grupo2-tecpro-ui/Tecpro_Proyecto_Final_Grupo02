package GUI;

import javax.swing.*;
import java.awt.*;
import Controlador.ControladorMotorizado;
import Modelado.Motorizado;

public class EditarMotorizado extends JFrame {

    private static final Color PROSEGUR_YELLOW = new Color(255, 209, 0);
    private static final Color PROSEGUR_BLACK  = new Color(25, 25, 25);
    private static final Color PROSEGUR_GRAY   = new Color(240, 240, 240);

    private JTextField txtDni;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtCelular;
    private JTextField txtTarjetas;
    private JComboBox<String> cboEstado;
    private JButton btnGuardar, btnCancelar;

    private ControladorMotorizado controlador;
    private String originalDni;

    public EditarMotorizado(ControladorMotorizado controlador, Motorizado m) {

        setAlwaysOnTop(true);
        this.controlador = controlador;
        this.originalDni = m.getDni();

        setTitle("✏️ Editar Motorizado — DNI: " + originalDni);
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        getContentPane().setBackground(PROSEGUR_GRAY);
        getContentPane().setLayout(null);

        Font lblFont = new Font("Montserrat", Font.BOLD, 13);

       
        JLabel lblDni = new JLabel("🪪 DNI:");
        lblDni.setBounds(20, 10, 120, 25);
        lblDni.setFont(lblFont);
        lblDni.setForeground(PROSEGUR_BLACK);
        getContentPane().add(lblDni);

        txtDni = new JTextField(originalDni);
        txtDni.setBounds(150, 10, 200, 25);
        txtDni.setEditable(false);
        txtDni.setBackground(new Color(220, 220, 220));
        getContentPane().add(txtDni);

      
        JLabel lblNombres = new JLabel("👤 Nombres:");
        lblNombres.setBounds(20, 45, 120, 25);
        lblNombres.setFont(lblFont);
        getContentPane().add(lblNombres);

        txtNombres = new JTextField(m.getNombres());
        txtNombres.setBounds(150, 45, 200, 25);
        getContentPane().add(txtNombres);

        
        JLabel lblApellidos = new JLabel("👥 Apellidos:");
        lblApellidos.setBounds(20, 80, 120, 25);
        lblApellidos.setFont(lblFont);
        getContentPane().add(lblApellidos);

        txtApellidos = new JTextField(m.getApellidos());
        txtApellidos.setBounds(150, 80, 200, 25);
        getContentPane().add(txtApellidos);

      
        JLabel lblCelular = new JLabel("📱 Celular:");
        lblCelular.setBounds(20, 115, 120, 25);
        lblCelular.setFont(lblFont);
        getContentPane().add(lblCelular);

        txtCelular = new JTextField(m.getCelular());
        txtCelular.setBounds(150, 115, 200, 25);
        getContentPane().add(txtCelular);

      
        JLabel lblTarjetas = new JLabel("📦 Tarjetas:");
        lblTarjetas.setBounds(20, 150, 140, 25);
        lblTarjetas.setFont(lblFont);
        getContentPane().add(lblTarjetas);

        txtTarjetas = new JTextField(String.valueOf(m.getTarjetasAsignadas()));
        txtTarjetas.setBounds(150, 150, 200, 25);
        getContentPane().add(txtTarjetas);

        
        JLabel lblEstado = new JLabel("⚙️ Estado:");
        lblEstado.setBounds(20, 185, 120, 25);
        lblEstado.setFont(lblFont);
        getContentPane().add(lblEstado);

        cboEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});
        cboEstado.setBounds(150, 185, 200, 25);
        cboEstado.setSelectedItem(m.getEstado() != null ? m.getEstado() : "Activo");
        getContentPane().add(cboEstado);

     
        btnGuardar = new JButton("💾 Guardar");
        btnGuardar.setBounds(70, 220, 120, 28);
        styleButton(btnGuardar, PROSEGUR_YELLOW, PROSEGUR_BLACK);
        getContentPane().add(btnGuardar);

        btnCancelar = new JButton("❌ Cancelar");
        btnCancelar.setBounds(210, 220, 120, 28);
        styleButton(btnCancelar, PROSEGUR_BLACK, PROSEGUR_YELLOW);
        getContentPane().add(btnCancelar);

       
        btnCancelar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(e -> guardarCambios(m));
    }


  
    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Montserrat", Font.BOLD, 13));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }


 
    private void guardarCambios(Motorizado mOriginal) {

        String nuevosNombres = txtNombres.getText().trim();
        String nuevosApellidos = txtApellidos.getText().trim();
        String nuevoCelular = txtCelular.getText().trim();
        String tarjetasStr = txtTarjetas.getText().trim();
        String nuevoEstado = (String) cboEstado.getSelectedItem();

        if (nuevosNombres.isEmpty() || nuevosApellidos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Nombres y apellidos no pueden estar vacíos.");
            return;
        }

        int nuevasTarjetas;
        try {
            nuevasTarjetas = Integer.parseInt(tarjetasStr);
            if (nuevasTarjetas < 0) {
                JOptionPane.showMessageDialog(this, "⚠️ Las tarjetas no pueden ser negativas.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Tarjetas inválidas. Debe ser número.");
            return;
        }

        boolean ok = controlador.EditarporDNI(
                originalDni,
                nuevosNombres,
                nuevosApellidos,
                nuevoCelular,
                nuevasTarjetas,
                nuevoEstado
        );

        if (ok) {
            JOptionPane.showMessageDialog(this, "✔️ Motorizado actualizado correctamente.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "❌ No se pudo actualizar el motorizado.");
        }
    }
}
