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

        // Primero obtenemos los datos del estudiante
        String sqlEst = "SELECT * FROM Estudiantes WHERE idEstudiante = ?";
        PreparedStatement psEst = con.prepareStatement(sqlEst);
        psEst.setInt(1, idEstudiante);
        ResultSet rsEst = psEst.executeQuery();

        if (!rsEst.next()) {
            con.close();
            return null;
        }

        EstudiantesConMaterias ecm = new EstudiantesConMaterias();
        ecm.setIdEstudiante(rsEst.getInt("idEstudiante"));
        ecm.setIdentificacion(rsEst.getString("identificacion"));
        ecm.setNombre(rsEst.getString("nombre"));
        // Ahora obtenemos sus materias
        String sqlMat = "SELECT m.idMateria, m.nombre " +
                        "FROM MateriasEstudiantes me " +
                        "JOIN Materias m ON me.idMateria = m.idMateria " +
                        "WHERE me.idEstudiante = ?";
        PreparedStatement psMat = con.prepareStatement(sqlMat);
        psMat.setInt(1, idEstudiante);
        ResultSet rsMat = psMat.executeQuery();

        List<Materia> materias = new ArrayList<>();
        while (rsMat.next()) {
            Materia m = new Materia();
            m.setIdMateria(rsMat.getInt("idMateria"));
            m.setNombre(rsMat.getString("nombre"));
            materias.add(m);
        }

        ecm.setMaterias(materias);

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
