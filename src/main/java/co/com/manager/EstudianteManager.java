package co.com.manager;

import co.com.bd.BaseDatos;
import co.com.model.Estudiante;
import co.com.model.EstudiantesConMaterias;
import co.com.model.Materia;

import java.sql.*;
import java.util.*;

public class EstudianteManager {

    public List<Estudiante> getAll() throws Exception {
        List<Estudiante> lista = new ArrayList<>();
        Connection con = BaseDatos.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Estudiantes");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Estudiante e = new Estudiante();
            e.setIdEstudiante(rs.getInt("idEstudiante"));
            e.setIdentificacion(rs.getString("identificacion"));
            e.setNombre(rs.getString("nombres"));
            lista.add(e);
        }
        con.close();
        return lista;
    }

    public Estudiante getByIdentificacion(String identificacion) throws Exception {
        Connection con = BaseDatos.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Estudiantes WHERE identificacion=?");
        ps.setString(1, identificacion);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Estudiante e = new Estudiante();
            e.setIdEstudiante(rs.getInt("idEstudiante"));
            e.setIdentificacion(rs.getString("identificacion"));
            e.setNombre(rs.getString("nombres"));
            con.close();
            return e;
        }
        con.close();
        return null;
    }
    
    public EstudiantesConMaterias getEstudiantesConMaterias(int idEstudiante) throws Exception {
        Connection con = BaseDatos.getConnection();

        String sql = "SELECT e.idEstudiante, e.identificacion, e.nombres, " +
                     "m.idMateria, m.nombre AS nombreMateria " +
                     "FROM Estudiantes e " +
                     "INNER JOIN MateriasEstudiantes me ON e.idEstudiante = me.idEstudiante " +
                     "INNER JOIN Materias m ON me.idMateria = m.idMateria " +
                     "WHERE e.idEstudiante = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEstudiante);
        ResultSet rs = ps.executeQuery();

        EstudiantesConMaterias ecm = null;
        List<Materia> materias = new ArrayList<>();

        while (rs.next()) {
            if (ecm == null) {
                ecm = new EstudiantesConMaterias();
                ecm.setIdEstudiante(rs.getInt("idEstudiante"));
                ecm.setIdentificacion(rs.getString("identificacion"));
                ecm.setNombre(rs.getString("nombre"));
            }

            Materia m = new Materia();
            m.setIdMateria(rs.getInt("idMateria"));
            m.setNombre(rs.getString("nombre"));
            materias.add(m);
        }

        if (ecm != null) {
            ecm.setMaterias(materias);
        }

        con.close();
        return ecm;
    }
    
    public boolean insertarEstudiante(Estudiante e) throws Exception {
        Connection con = BaseDatos.getConnection();
        String sql = "INSERT INTO Estudiantes (identificacion, nombre) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, e.getIdentificacion());
        ps.setString(2, e.getNombre());
        int filas = ps.executeUpdate();
        con.close();
        return filas > 0;
    }



}
