package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import Modelado.ReporteEntrega;

public class ReporteDAO {

    public List<ReporteEntrega> generarReporte() {
        List<ReporteEntrega> lista = new ArrayList<>();

        String sql = """
            SELECT m.dni, CONCAT(m.nombres,' ', m.apellidos) AS nombres,
                   m.tarjetasAsignadas AS inicial,
                   COALESCE(SUM(e.cantidad),0) AS entregado,
                   (m.tarjetasAsignadas - COALESCE(SUM(e.cantidad),0)) AS restante,
                   (COALESCE(SUM(e.cantidad),0) / m.tarjetasAsignadas) * 100 AS porcentaje
            FROM motorizado m
            LEFT JOIN entrega e ON m.dni = e.dniMotorizado
            GROUP BY m.dni
        """;

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new ReporteEntrega(
                        rs.getString("dni"),
                        rs.getString("nombres"),
                        rs.getInt("inicial"),
                        rs.getInt("entregado"),
                        rs.getInt("restante"),
                        rs.getDouble("porcentaje")
                ));
            }
        } catch (Exception ex) { ex.printStackTrace(); }

        return lista;
    }
}
