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
	  public boolean eliminarPorDni(String dni) {
		    if (dni == null || dni.trim().isEmpty()) return false;
		    return InMemoryDatabase.EliminarPorDni(dni.trim());
		}
	  public boolean existeDni(String dni) {
		    return InMemoryDatabase.existeDni(dni);
		}
	  public boolean EditarporDNI(String dni, String nuevosNombres, String nuevosApellidos,
              String nuevoCelular, int nuevasTarjetas, String nuevoEstado) 
	  {
		  if (dni == null || dni.trim().isEmpty()) return false;
		  return repositorio.InMemoryDatabase.Editar(dni.trim(), nuevosNombres, nuevosApellidos,
                                  nuevoCelular, nuevasTarjetas, nuevoEstado);
	  }

}

