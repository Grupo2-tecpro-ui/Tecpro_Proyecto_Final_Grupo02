package repositorio;

import Modelado.Entrega;
import Modelado.Motorizado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotorizadoDAO {

    
    public boolean insertar(Motorizado m) {
        String sql = "{CALL agregarMotorizado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try (Connection cn = Conexion.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, m.getDni());
            cs.setString(2, m.getNombres());
            cs.setString(3, m.getApellidos());
            cs.setString(4, m.getCelular());
            cs.setString(5, m.getPlaca());
            cs.setString(6, m.getMarca());
            cs.setString(7, m.getModelo());
            cs.setString(8, m.getBrevete());
            cs.setString(9, m.getVencBrevete());
            cs.setBoolean(10, m.isSoat());
            cs.setString(11, m.getEstado());
            cs.setString(12, m.getFechaIngreso());
            cs.setString(13, m.getContrato());
            cs.setInt(14, m.getTarjetasAsignadas());
            cs.setBoolean(15, m.isDiaRuta());
            cs.setString(16, m.getFechaTarjetas());
            cs.setInt(17, m.getIdSede());

            return cs.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

 
    public List<Motorizado> obtenerTodos() {
        String sql = "SELECT * FROM motorizado";
        List<Motorizado> lista = new ArrayList<>();

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Motorizado(
                        rs.getInt("id"),
                        rs.getString("dni"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("celular"),
                        rs.getString("placa"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("brevete"),
                        rs.getString("vencBrevete"),
                        rs.getBoolean("soat"),
                        rs.getString("estado"),
                        rs.getString("fechaIngreso"),
                        rs.getString("contrato"),
                        rs.getInt("tarjetasAsignadas"),
                        rs.getBoolean("diaRuta"),
                        rs.getString("fechaTarjetas"),
                        rs.getInt("idSede")
                ));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    
    public boolean existeDni(String dni) {
        String sql = "SELECT dni FROM motorizado WHERE dni = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

   
    public boolean eliminarPorDni(String dni) {
    	 try {
    	     
    	        EntregaDAO entregaDAO = new EntregaDAO();
    	        DetalleEntregaDAO detalleDAO = new DetalleEntregaDAO();

    	        List<Entrega> entregas = entregaDAO.listarPorDni(dni);

    	      
    	        for (Entrega e : entregas) {
    	            detalleDAO.eliminarPorIdEntrega(e.getId());
    	        }

    	        
    	        for (Entrega e : entregas) {
    	            entregaDAO.eliminarEntrega(e.getId());
    	        }

    	       
    	        String sql = "DELETE FROM motorizado WHERE dni = ?";
    	        try (Connection con = Conexion.getConexion();
    	             PreparedStatement pst = con.prepareStatement(sql)) {

    	            pst.setString(1, dni);
    	            return pst.executeUpdate() > 0;
    	        }

    	    } catch (Exception ex) {
    	        ex.printStackTrace();
    	        return false;
    	    }
    }

    
    public boolean editarPorDni(String dni, String nombres, String apellidos,
                                String celular, int tarjetas, String estado) {
        String sql = "{CALL editarMotorizadoPorDni(?,?,?,?,?,?)}";

        try (Connection cn = Conexion.getConexion();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, dni);
            cs.setString(2, nombres);
            cs.setString(3, apellidos);
            cs.setString(4, celular);
            cs.setInt(5, tarjetas);
            cs.setString(6, estado);

            return cs.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

 
    public int obtenerTarjetas(String dni) {
        String sql = "SELECT tarjetasAsignadas FROM motorizado WHERE dni = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    public boolean actualizarTarjetas(String dni, int nuevasTarjetas) {
        String sql = "UPDATE motorizado SET tarjetasAsignadas = ? WHERE dni = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, nuevasTarjetas);
            ps.setString(2, dni);

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public Motorizado buscarPorDni(String dni) {
        String sql = "SELECT * FROM motorizado WHERE dni = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Motorizado(
                    rs.getInt("id"),
                    rs.getString("dni"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("celular"),
                    rs.getString("placa"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("brevete"),
                    rs.getString("vencBrevete"),
                    rs.getBoolean("soat"),
                    rs.getString("estado"),
                    rs.getString("fechaIngreso"),
                    rs.getString("contrato"),
                    rs.getInt("tarjetasAsignadas"),
                    rs.getBoolean("diaRuta"),
                    rs.getString("fechaTarjetas"),
                    rs.getInt("idSede")
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public boolean agregar(Motorizado m) {
        return insertar(m); 
    }
    public List<Motorizado> listarTodos() {
        return obtenerTodos();
    }
    public boolean existeCelular(String celular) {
        String sql = "SELECT celular FROM motorizado WHERE celular = ?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, celular);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean existePlaca(String placa) {
        String sql = "SELECT placa FROM motorizado WHERE placa = ?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizarTarjetasYFecha(String dni, int nuevasTarjetas, String fechaTarjetas) {
        String sql = "UPDATE motorizado SET tarjetasAsignadas = ?, fechaTarjetas = ? WHERE dni = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, nuevasTarjetas);
            ps.setString(2, fechaTarjetas);
            ps.setString(3, dni);

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
