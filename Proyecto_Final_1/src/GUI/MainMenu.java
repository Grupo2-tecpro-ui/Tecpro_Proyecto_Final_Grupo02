package GUI;
import javax.swing.*;
import java.awt.*;
import Controlador.ControladorMotorizado;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MainMenu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnRegistroMotorizado;
    private ControladorMotorizado controlador;
    private JButton btnRegistrarEntregas;

    public MainMenu() {
        controlador = new ControladorMotorizado();

        setTitle("Menú Principal - Sistema");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        btnRegistroMotorizado = new JButton("Registrar Motorizado");
        btnRegistroMotorizado.setBounds(73, 29, 143, 25);
        panel.add(btnRegistroMotorizado);

        getContentPane().add(panel);
        
        JButton btnListadoMotorizados = new JButton("Listado de Motorizados");
        btnListadoMotorizados.setBounds(73, 65, 143, 25);
        panel.add(btnListadoMotorizados);
        
        btnRegistrarEntregas = new JButton("Registrar Entregas");
        btnRegistrarEntregas.addActionListener(this);
        btnRegistrarEntregas.setBounds(73, 101, 143, 25);
        panel.add(btnRegistrarEntregas);

        btnListadoMotorizados.addActionListener(e -> {
            ListadoMotorizadoFrame listadoFrame = new ListadoMotorizadoFrame(controlador);
            listadoFrame.setVisible(true);
        });
       
        btnRegistroMotorizado.addActionListener(e -> {
            RegistroMotorizadoFrame registroFrame = new RegistroMotorizadoFrame(controlador);
            registroFrame.setVisible(true);
        });
    }
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrarEntregas) {
			do_btnRegistrarEntregas_actionPerformed(e);
		}
	}
	protected void do_btnRegistrarEntregas_actionPerformed(ActionEvent e) {
		 // 1) Intentamos pedir DNI directamente al usuario (MainMenu no tiene tabla seleccionada)
	    String dniSeleccionado = JOptionPane.showInputDialog(this, "Ingrese DNI del motorizado para registrar entrega:");
	    if (dniSeleccionado == null) return; // usuario canceló
	    dniSeleccionado = dniSeleccionado.trim();

	    // 2) Validación de formato: exactamente 8 dígitos
	    if (!dniSeleccionado.matches("\\d{8}")) {
	        JOptionPane.showMessageDialog(this, "DNI inválido: debe contener exactamente 8 dígitos.");
	        return;
	    }

	    // 3) Abrir la GUI de entregas con prefill de DNI
	    Entrega ef = new Entrega(this.controlador != null ? this.controlador : new ControladorMotorizado(), dniSeleccionado);
	    ef.setVisible(true);

	    // 4) Opcional: refrescar listados si hace falta (ver nota abajo)
	    ef.addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosed(java.awt.event.WindowEvent e) {
	            // No podemos refrescar la tabla desde acá porque MainMenu no tiene la tabla.
	            // Si deseas refrescar un Listado abierto, ver la sección "Actualizar listado abierto" abajo.
	        }
	    });
		
		
	}
    }
