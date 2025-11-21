package Modelado;

import java.util.List;

public class Entrega {
	 private int id;
	    private String dniMotorizado;
	    private int cantidad;
	    private int requestedCantidad; 
	    private String fecha;
	    private List<DetalleEntrega> detalles; 
	  

	   
	    public Entrega(int id, String dniMotorizado, int cantidad, int requestedCantidad, String fecha, List<DetalleEntrega> detalles) {
	        this.id = id;
	        this.dniMotorizado = dniMotorizado;
	        this.cantidad = cantidad;
	        this.requestedCantidad = requestedCantidad;
	        this.fecha = fecha;
	        this.detalles = detalles;
	    }
	    public Entrega(int id, String dniMotorizado, int cantidad, String fecha) {
	        this(id, dniMotorizado, cantidad, cantidad, fecha, null);
	    }


	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }

	    public String getDniMotorizado() { return dniMotorizado; }
	    public void setDniMotorizado(String dniMotorizado) { this.dniMotorizado = dniMotorizado; }

	  
	    public int getCantidad() { return cantidad; }
	    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

	    public int getRequestedCantidad() { return requestedCantidad; }
	    public void setRequestedCantidad(int requestedCantidad) { this.requestedCantidad = requestedCantidad; }

	    public String getFecha() { return fecha; }
	    public void setFecha(String fecha) { this.fecha = fecha; }

	    public List<DetalleEntrega> getDetalles() { return detalles; }
	    public void setDetalles(List<DetalleEntrega> detalles) { this.detalles = detalles; }

	 
	    public int getAcceptedCountFromDetalles() {
	        if (detalles == null) return 0;
	        int s = 0;
	        for (DetalleEntrega d : detalles) {
	            if (d != null && d.isConforme()) s++;			
	        }
	       return s;
	    }
	   
}
