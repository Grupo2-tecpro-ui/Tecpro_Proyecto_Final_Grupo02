package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

import Controlador.ControladorMotorizado;
import Modelado.ReporteEntrega;

public class ReporteEntregasFrame extends JDialog {

    private static final Color PROSEGUR_YELLOW = new Color(255, 209, 0);
    private static final Color PROSEGUR_BLACK = new Color(25, 25, 25);
    private static final Color PROSEGUR_GRAY = new Color(240, 240, 240);

    private ControladorMotorizado controlador;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public ReporteEntregasFrame(JFrame parent, ControladorMotorizado controlador) {

        super(parent, "📊 Reporte de Entregas por Motorizado", true);

        this.controlador = controlador;

        setSize(1000, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(PROSEGUR_GRAY);

      
        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.setBackground(PROSEGUR_YELLOW);
        panelTop.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel titulo = new JLabel("📦 Reporte de Entregas por Motorizado");
        titulo.setFont(new Font("Montserrat", Font.BOLD, 20));
        titulo.setForeground(PROSEGUR_BLACK);

        panelTop.add(titulo, BorderLayout.WEST);

   
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setOpaque(false);

        JButton btnRefrescar = new JButton("🔄 Refrescar");
        JButton btnCerrar = new JButton("❌ Cerrar");

        stylizeButton(btnRefrescar, PROSEGUR_BLACK, PROSEGUR_YELLOW);
        stylizeButton(btnCerrar, new Color(60, 60, 60), Color.WHITE);

        panelBotones.add(btnRefrescar);
        panelBotones.add(btnCerrar);

        panelTop.add(panelBotones, BorderLayout.EAST);

        add(panelTop, BorderLayout.NORTH);

       
        String[] columnas = {
                "🪪 DNI", "👤 Nombres", "📦 Inicial",
                "📬 Entregado", "📉 Restante", "📈 % Cumplimiento"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Montserrat", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(PROSEGUR_BLACK);
        tabla.getTableHeader().setForeground(PROSEGUR_YELLOW);
        tabla.setRowHeight(25);
        tabla.setGridColor(new Color(200, 200, 200));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(Color.WHITE);

        add(scroll, BorderLayout.CENTER);

     
        btnRefrescar.addActionListener(e -> cargarReporte());
        btnCerrar.addActionListener(e -> dispose());

    
        cargarReporte();
    }


    private void stylizeButton(JButton btn, Color bg, Color fg) {
        btn.setFont(new Font("Montserrat", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
    }


    private void cargarReporte() {
        modeloTabla.setRowCount(0);

        if (controlador == null)
            controlador = new ControladorMotorizado();

        List<ReporteEntrega> lista = controlador.generarReporteEntregas();

        for (ReporteEntrega r : lista) {
            modeloTabla.addRow(new Object[]{
                    r.getDni(),
                    r.getNombres(),
                    r.getInicial(),
                    r.getEntregado(),
                    r.getRestante(),
                    String.format("%.2f%%", r.getPorcentaje())
            });
        }
    }
}
