package GUI;
import javax.swing.*;
import java.awt.*;
import Controlador.ControladorMotorizado;
public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnRegistroMotorizado;
    private ControladorMotorizado controlador;

    public MainMenu() {
        controlador = new ControladorMotorizado();

        setTitle("Menú Principal - Sistema");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        btnRegistroMotorizado = new JButton("Registrar Motorizado");
        btnRegistroMotorizado.setBounds(73, 46, 143, 25);
        panel.add(btnRegistroMotorizado);

        getContentPane().add(panel);
        
        JButton btnListadoMotorizados = new JButton("Listado de Motorizados");
        btnListadoMotorizados.setBounds(73, 82, 143, 25);
        panel.add(btnListadoMotorizados);

        btnListadoMotorizados.addActionListener(e -> {
            ListadoMotorizadoFrame listadoFrame = new ListadoMotorizadoFrame(controlador);
            listadoFrame.setVisible(true);
        });
       
        btnRegistroMotorizado.addActionListener(e -> {
            RegistroMotorizadoFrame registroFrame = new RegistroMotorizadoFrame(controlador);
            registroFrame.setVisible(true);
        });
    }
    }
