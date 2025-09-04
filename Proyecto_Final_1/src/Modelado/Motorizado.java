package Modelado;

public class Motorizado {
    private int id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String celularTrabajo;
    private String placaMoto;
    private String marcaMoto;
    private String modeloMoto;
    private String breveteCategoria;
    private String vencBrevete;     
    private boolean soatVigente;
    private String estado;         
    private String fechaIngreso;   
    private String tipoContrato;  
    private int idSede;



    public Motorizado(int id, String dni, String nombres, String apellidos, String celularTrabajo, String placaMoto,
			String marcaMoto, String modeloMoto, String breveteCategoria, String vencBrevete, boolean soatVigente,
			String estado, String fechaIngreso, String tipoContrato, int idSede) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.celularTrabajo = celularTrabajo;
		this.placaMoto = placaMoto;
		this.marcaMoto = marcaMoto;
		this.modeloMoto = modeloMoto;
		this.breveteCategoria = breveteCategoria;
		this.vencBrevete = vencBrevete;
		this.soatVigente = soatVigente;
		this.estado = estado;
		this.fechaIngreso = fechaIngreso;
		this.tipoContrato = tipoContrato;
		this.idSede = idSede;
	}



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



	public String getCelularTrabajo() {
		return celularTrabajo;
	}



	public void setCelularTrabajo(String celularTrabajo) {
		this.celularTrabajo = celularTrabajo;
	}



	public String getPlacaMoto() {
		return placaMoto;
	}



	public void setPlacaMoto(String placaMoto) {
		this.placaMoto = placaMoto;
	}



	public String getMarcaMoto() {
		return marcaMoto;
	}



	public void setMarcaMoto(String marcaMoto) {
		this.marcaMoto = marcaMoto;
	}



	public String getModeloMoto() {
		return modeloMoto;
	}



	public void setModeloMoto(String modeloMoto) {
		this.modeloMoto = modeloMoto;
	}



	public String getBreveteCategoria() {
		return breveteCategoria;
	}



	public void setBreveteCategoria(String breveteCategoria) {
		this.breveteCategoria = breveteCategoria;
	}



	public String getVencBrevete() {
		return vencBrevete;
	}



	public void setVencBrevete(String vencBrevete) {
		this.vencBrevete = vencBrevete;
	}



	public boolean isSoatVigente() {
		return soatVigente;
	}



	public void setSoatVigente(boolean soatVigente) {
		this.soatVigente = soatVigente;
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



	public String getTipoContrato() {
		return tipoContrato;
	}



	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}



	public int getIdSede() {
		return idSede;
	}



	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}


	@Override
    public String toString() {
        return "Motorizado{id=" + id + ", dni=" + dni + ", nombres=" + nombres + " " + apellidos + "}";
    }
}