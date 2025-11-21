package repositorio;


import Modelado.HistorialEntrega;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAO {

    public List<HistorialEntrega> listarHistorial() {
        List<HistorialEntrega> lista = new ArrayList<>();

        String sql = "SELECT * FROM vw_historial_entregas";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new HistorialEntrega(
                        rs.getString("fecha"),
                        rs.getString("cliente"),
                        rs.getString("direccion"),
                        rs.getString("banco"),
                        rs.getString("mensajeroNombre"),
                        rs.getString("mensajeroDni"),
                        rs.getBoolean("conforme")
                ));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lista;
    }
}
