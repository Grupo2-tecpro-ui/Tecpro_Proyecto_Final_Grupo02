package GUI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoginGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Color PROSEGUR_YELLOW = new Color(255, 209, 0);
    private static final Color PROSEGUR_BLACK = new Color(18, 18, 18);

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginGUI() {

    
        setTitle("Login - Sistema de Mensajería");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

      
        JPanel background = new JPanel(new BorderLayout());
        background.setBackground(PROSEGUR_BLACK);
        setContentPane(background);


        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(520, 720));
        leftPanel.setBackground(PROSEGUR_BLACK);

        JLabel lblBanner = new JLabel();
        lblBanner.setHorizontalAlignment(SwingConstants.CENTER);

       
        ImageIcon img = new ImageIcon("C:\\Users\\USER\\Desktop\\Tecpro_Proyecto_Final_Grupo02\\Proyecto_Final_1\\src\\images\\prosegur.jpg");
        lblBanner.setIcon(new ImageIcon(img.getImage().getScaledInstance(800, 720, Image.SCALE_SMOOTH)));

        leftPanel.add(lblBanner, BorderLayout.CENTER);
        background.add(leftPanel, BorderLayout.WEST);

       
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(PROSEGUR_BLACK);
        rightPanel.setBorder(new EmptyBorder(60, 120, 60, 120));  
        rightPanel.setLayout(new BorderLayout());

     
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PROSEGUR_YELLOW);
        headerPanel.setBorder(new EmptyBorder(25, 30, 25, 30));

        JLabel lblTitulo = new JLabel("🏍️ Bienvenido a Prosegur GDT", SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Montserrat", Font.BOLD, 28));
        lblTitulo.setForeground(PROSEGUR_BLACK);

        JLabel lblSubtitulo = new JLabel("Control seguro de motorizados y entregas de tarjetas de crédito", SwingConstants.LEFT);
        lblSubtitulo.setFont(new Font("Montserrat", Font.PLAIN, 16));
        lblSubtitulo.setForeground(PROSEGUR_BLACK);

        headerPanel.add(lblTitulo, BorderLayout.NORTH);
        headerPanel.add(lblSubtitulo, BorderLayout.SOUTH);

        rightPanel.add(headerPanel, BorderLayout.NORTH);

      
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PROSEGUR_BLACK);
        formPanel.setBorder(new EmptyBorder(40, 20, 40, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        JLabel lblUsuario = new JLabel("👤 Usuario");
        lblUsuario.setForeground(PROSEGUR_YELLOW);
        lblUsuario.setFont(new Font("Montserrat", Font.BOLD, 18));
        formPanel.add(lblUsuario, gbc);

        gbc.gridy = 1;
        txtUsuario = new JTextField();
        stylizeTextField(txtUsuario);
        formPanel.add(txtUsuario, gbc);

        gbc.gridy = 2;
        JLabel lblContrasena = new JLabel("🔒 Contraseña");
        lblContrasena.setForeground(PROSEGUR_YELLOW);
        lblContrasena.setFont(new Font("Montserrat", Font.BOLD, 18));
        formPanel.add(lblContrasena, gbc);

        gbc.gridy = 3;
        txtPassword = new JPasswordField();
        stylizeTextField(txtPassword);
        formPanel.add(txtPassword, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(25, 0, 0, 0);
        btnLogin = new JButton("Acceder al Panel 📈");
        stylizePrimaryButton(btnLogin);
        formPanel.add(btnLogin, gbc);

        rightPanel.add(formPanel, BorderLayout.CENTER);

  
        JLabel footer = new JLabel("⚫ Credenciales de prueba: admin / admin", SwingConstants.CENTER);
        footer.setForeground(PROSEGUR_YELLOW);
        footer.setFont(new Font("Montserrat", Font.PLAIN, 14));
        footer.setBorder(new EmptyBorder(10, 0, 20, 0));

        rightPanel.add(footer, BorderLayout.SOUTH);

        background.add(rightPanel, BorderLayout.CENTER);

        
        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String contrasena = new String(txtPassword.getPassword()).trim();

            if (usuario.equals("admin") && contrasena.equals("admin")) {
                dispose();
                new MainMenu().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                        this, 
                        "Usuario o contraseña incorrectos", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                );
                txtPassword.setText("");
                txtUsuario.requestFocus();
            }
        });
    }

    private void stylizeTextField(JTextField field) {
        field.setFont(new Font("Montserrat", Font.PLAIN, 18));
        field.setForeground(PROSEGUR_BLACK);
        field.setBackground(Color.WHITE);
        field.setBorder(new CompoundBorder(
                new LineBorder(PROSEGUR_YELLOW, 2, true),
                new EmptyBorder(12, 14, 12, 14)
        ));
    }

    private void stylizePrimaryButton(JButton button) {
        button.setFont(new Font("Montserrat", Font.BOLD, 20));
        button.setForeground(PROSEGUR_BLACK);
        button.setBackground(PROSEGUR_YELLOW);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 20, 12, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }
}
