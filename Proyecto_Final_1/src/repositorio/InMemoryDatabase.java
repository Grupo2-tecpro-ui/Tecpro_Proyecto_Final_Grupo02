package repositorio;

import java.util.ArrayList;
import java.util.List;

import Modelado.Motorizado;

public class InMemoryDatabase {
	
	 private static int seqMotorizado = 1; 
	    private static final List<Motorizado> MOTORIZADOS = new ArrayList<>();
	  
	    
	    private static int seqEntrega = 1; 
	    private static final java.util.List<Modelado.Entrega> ENTREGAS = new java.util.ArrayList<>();
	    public static synchronized java.util.List<Modelado.Entrega> getEntregas() {
	        return new java.util.ArrayList<>(ENTREGAS);
	    }

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
	    public static synchronized boolean addEntrega(Modelado.Entrega e) {
	        if (e == null) return false;
	        if (e.getId() == 0) e.setId(seqEntrega++);
	        ENTREGAS.add(e);
	        return true;
	    }
	    public static synchronized Modelado.Motorizado findByDniObj(String dni) {
	        if (dni == null) return null;
	        String d = dni.trim();
	        for (Modelado.Motorizado m : MOTORIZADOS) {
	            if (m.getDni() != null && m.getDni().trim().equals(d)) return m;
	        }
	        return null;
	    }
	    public static synchronized Modelado.Motorizado findById(int id) {
	        for (Modelado.Motorizado m : MOTORIZADOS) {
	            if (m.getId() == id) return m;
	        }
	        return null;
	    }

}


