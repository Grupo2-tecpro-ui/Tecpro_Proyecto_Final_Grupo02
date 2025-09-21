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
	
	  public void registrarEntrega(Modelado.Motorizado m, int cantidad) 
	          throws MotorizadoNoEncontrado, CartasInsuficientes {
	      if (m == null) throw new MotorizadoNoEncontrado("Motorizado nulo.");
	      registrarEntrega(m.getDni(), cantidad); 
	  }
		  
	  public void registrarEntrega(String dni, int cantidad) 
	          throws MotorizadoNoEncontrado, CartasInsuficientes {
	     
	      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	      String hoy = sdf.format(new java.util.Date());
	      registrarEntrega(dni, cantidad, hoy); 
	  }


	 
	  public void registrarEntrega(int id, int cantidad, String fecha) 
	          throws MotorizadoNoEncontrado, CartasInsuficientes {
	      Modelado.Motorizado m = InMemoryDatabase.findById(id);
	      if (m == null) throw new MotorizadoNoEncontrado("No existe motorizado con id: " + id);
	  
	      registrarEntrega(m.getDni(), cantidad, fecha);
	  }
	  

	 public void registrarEntrega(String dni, int cantidad, String fecha) 
	          throws MotorizadoNoEncontrado, CartasInsuficientes {
	      if (dni == null || dni.trim().isEmpty()) throw new MotorizadoNoEncontrado("DNI inválido.");
	      Modelado.Motorizado m = InMemoryDatabase.findByDniObj(dni.trim());
	      if (m == null) throw new MotorizadoNoEncontrado("No existe motorizado con DNI: " + dni);

	      
	      

	      if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");

	      
	      int disponibles = m.getTarjetasAsignadas();
	      if (cantidad > disponibles) {
	         
	          throw new CartasInsuficientes("No hay suficientes tarjetas. Disponibles: " 
	                                               + disponibles + ", solicitado: " + cantidad);
	      }

	      
	      Modelado.Entrega e = new Modelado.Entrega(0, dni.trim(), cantidad, fecha);
	      boolean added = InMemoryDatabase.addEntrega(e);
	      if (!added) {
	          throw new RuntimeException("No se pudo registrar la entrega (error interno).");
	      }

	      
	      m.setTarjetasAsignadas(disponibles - cantidad);

	    
	  }

}

