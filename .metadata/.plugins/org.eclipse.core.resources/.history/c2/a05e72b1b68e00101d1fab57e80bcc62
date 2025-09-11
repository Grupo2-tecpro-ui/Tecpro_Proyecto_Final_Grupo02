package GUI;
import javax.swing.*;  
import java.awt.*;     

public class LoginGUI extends JFrame { 
	private static final long serialVersionUID = 1L;
	  // Constructor
    public LoginGUI() {
        setTitle("Login - Registro de Visitas");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel userLabel = new JLabel("Usuario:");
        JTextField userText = new JTextField();

        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passText = new JPasswordField();

        JButton loginButton = new JButton("Ingresar");
        loginButton.addActionListener(e -> {
           
            MainMenu menu = new MainMenu(null);
            menu.setVisible(true);

           
            dispose();
        });

    
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passLabel);
        panel.add(passText);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel); 
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginGUI().setVisible(true);
        });
    }

}
