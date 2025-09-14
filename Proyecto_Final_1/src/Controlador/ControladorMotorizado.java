package Controlador;
import repositorio.InMemoryDatabase;
import Modelado.Motorizado;
import javax.swing.JOptionPane;
import java.util.List;

public class ControladorMotorizado {
	
	
	
	public void guardarMotorizado(Motorizado m) {
		 InMemoryDatabase.addMotorizado(m);
        JOptionPane.showMessageDialog(null,
                "Motorizado guardado:\n" +
                "DNI: " + m.getDni() + "\n" +
                "Nombres: " + m.getNombres() + "\n" +
                "Apellidos: " + m.getApellidos() + "\n" +
                "Celular: " + m.getCelular() + "\n" +
                "Placa: " + m.getPlaca()
        );
    }
	  public List<Motorizado> listarMotorizados() {
	        return InMemoryDatabase.getMotorizados();
	    }
}

