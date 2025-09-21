package repositorio;

import java.util.ArrayList;
import java.util.List;

import Modelado.Motorizado;

public class InMemoryDatabase {
	
	 private static int seqMotorizado = 1; 
	    private static final List<Motorizado> MOTORIZADOS = new ArrayList<>();

	    public static int nextIdMotorizado() {
	        return seqMotorizado++;
	    }

	    public static void addMotorizado(Motorizado m) {
	        MOTORIZADOS.add(m);
	    }

	    public static List<Motorizado> getMotorizados() {
	        return new ArrayList<>(MOTORIZADOS);
	    }
	    public static synchronized boolean existeDni(String dni) {
	        if (dni == null) return false;
	        String d = dni.trim();
	        for (Motorizado m : MOTORIZADOS) {
	            if (m.getDni() != null && m.getDni().trim().equals(d)) {
	                return true;
	            }
	        }
	        return false;
}
	    public static synchronized boolean EliminarPorDni(String dni) {
	     
	        if (dni == null) {
	            return false;
	        }

	       String d = dni.trim();

	        
	        for (int i = 0; i < MOTORIZADOS.size(); i++) {
	            Motorizado m = MOTORIZADOS.get(i);

	            
	            if (m.getDni() != null) {
	                
	                if (m.getDni().trim().equals(d)) {
	                    
	                    MOTORIZADOS.remove(i);
	                    return true; 
	                }
	            }
	        }
	        return false;
	    }
	    public static synchronized boolean Editar (String dni, String nuevosNombres, String nuevosApellidos, String nuevoCelular, int nuevasTarjetas, String nuevoEstado) {
	    	if (dni == null) return false;
	    	String d = dni.trim();
	        for (int i = 0; i < MOTORIZADOS.size(); i++) {
	            Motorizado m = MOTORIZADOS.get(i);
	            if (m.getDni() != null) {
	          if (m.getDni().trim().equals(d)) {
	                m.setNombres(nuevosNombres);
	    			m.setApellidos(nuevosApellidos);
	    			m.setCelular(nuevoCelular);
	    			m.setTarjetasAsignadas(nuevasTarjetas);
	    			m.setEstado(nuevoEstado);
	    			return true;
	    									}
	            
	  	}
	            		
	     		}    
	        return false;
	    
	    }

}


