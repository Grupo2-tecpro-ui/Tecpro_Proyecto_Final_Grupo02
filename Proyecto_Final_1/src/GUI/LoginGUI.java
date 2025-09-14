package GUI;
import javax.swing.*;  
import java.awt.*;     

public class LoginGUI extends JFrame { 
	private static final long serialVersionUID = 1L;
	 private JTextField txtUsuario;
	    private JPasswordField txtPassword;
	    private JButton btnLogin;

	    public LoginGUI() {
	        setTitle("Login - Sistema de Mensajería");
	        setSize(300, 200);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        JPanel panel = new JPanel();
	        panel.setLayout(null);

	        JLabel label = new JLabel("Usuario:");
	        label.setFont(new Font("Times New Roman", Font.BOLD, 12));
	        label.setBounds(10, 15, 139, 50);
	        panel.add(label);
	        txtUsuario = new JTextField();
	        txtUsuario.setBounds(135, 23, 139, 34);
	        panel.add(txtUsuario);

	        JLabel label_1 = new JLabel("Contraseña:");
	        label_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
	        label_1.setBounds(10, 55, 139, 50);
	        panel.add(label_1);
	        txtPassword = new JPasswordField();
	        txtPassword.setBounds(135, 63, 139, 34);
	        panel.add(txtPassword);

	        btnLogin = new JButton("Ingresar");
	        btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 14));
	        btnLogin.setBounds(73, 116, 139, 34);
	        panel.add(btnLogin);

	        getContentPane().add(panel);


	        btnLogin.addActionListener(e -> {
	            dispose(); 
	            new MainMenu().setVisible(true); 
	        });
	    }
}

