package Modelado;

public class Motorizado {
	private int id;
	private String dni;
	private String nombres;
	private String apellidos;
	private String celular;
	private String placa;
	private String marca;
	private String modelo;
	private String brevete;
	private String vencBrevete;
	private boolean soat;
	private String estado;
	private String fechaIngreso;
	private String contrato;
	private int tarjetasAsignadas; // NUEVO: cantidad de tarjetas asignadas
	private boolean diaRuta; // NUEVO: si marca el día de la ruta
	private int idSede;
	private String fechaTarjetas;


	


	public Motorizado(int id, String dni, String nombres, String apellidos, String celular, String placa, String marca,
			String modelo, String brevete, String vencBrevete, boolean soat, String estado, String fechaIngreso,
			String contrato, int tarjetasAsignadas, boolean diaRuta, String fechaTarjetas, int idSede) {
		
		this.id = id;
		this.dni = dni;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.celular = celular;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.brevete = brevete;
		this.vencBrevete = vencBrevete;
		this.soat = soat;
		this.estado = estado;
		this.fechaIngreso = fechaIngreso;
		this.contrato = contrato;
		this.tarjetasAsignadas = tarjetasAsignadas;
		this.diaRuta = diaRuta;
		 this.fechaTarjetas = fechaTarjetas;
		this.idSede = idSede;
	}
	   public Motorizado(int id, String dni, String nombres, String apellidos,
               String celular, String placa, String marca, String modelo,
               String brevete, String vencBrevete, boolean soat,
               String estado, String fechaIngreso, String contrato, int idSede) {
 this(id, dni, nombres, apellidos, celular, placa, marca, modelo,
      brevete, vencBrevete, soat, estado, fechaIngreso, contrato,
      0,      // tarjetasAsignadas por defecto
      false,  // diaRuta por defecto
      null,   // fechaTarjetas por defecto
      idSede);
}
	 public String getFechaTarjetas() { return fechaTarjetas; }
	 public void setFechaTarjetas(String fechaTarjetas) { this.fechaTarjetas = fechaTarjetas; }

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getBrevete() {
		return brevete;
	}


	public void setBrevete(String brevete) {
		this.brevete = brevete;
	}


	public String getVencBrevete() {
		return vencBrevete;
	}


	public void setVencBrevete(String vencBrevete) {
		this.vencBrevete = vencBrevete;
	}


	public boolean isSoat() {
		return soat;
	}


	public void setSoat(boolean soat) {
		this.soat = soat;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public String getContrato() {
		return contrato;
	}


	public void setContrato(String contrato) {
		this.contrato = contrato;
	}


	public int getTarjetasAsignadas() {
		return tarjetasAsignadas;
	}


	public void setTarjetasAsignadas(int tarjetasAsignadas) {
		this.tarjetasAsignadas = tarjetasAsignadas;
	}


	public boolean isDiaRuta() {
		return diaRuta;
	}


	public void setDiaRuta(boolean diaRuta) {
		this.diaRuta = diaRuta;
	}


	public int getIdSede() {
		return idSede;
	}


	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}

}