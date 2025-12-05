package repositorio;

public class MotorizadoSedeDTO {
	private int idMotorizado;
    private String dni;
    private String nombreCompleto;
    private String sede;
    private String fecha;
    private int tarjetasDia;

    public MotorizadoSedeDTO(int idMotorizado, String dni, String nombreCompleto,
            String sede, String fecha, int tarjetasDia) {

this.idMotorizado = idMotorizado;
this.dni = dni;
this.nombreCompleto = nombreCompleto;
this.sede = sede;
this.fecha = fecha;
this.tarjetasDia = tarjetasDia;
}

    public int getIdMotorizado() { return idMotorizado; }
    public String getDni() { return dni; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getSede() { return sede; }
    public String getFecha() { return fecha; }
    public int getTarjetasDia() { return tarjetasDia; }
}
