package co.com.ws.rest;

import co.com.manager.EstudianteManager;
import co.com.model.Estudiante;
import co.com.model.EstudiantesConMaterias;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/WsEstudiante")
public class WsEstudiante {

    private EstudianteManager manager = new EstudianteManager();

    // 1. Consultar todos los estudiantes
    @GET
    @Path("/ConsultarTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> getAll() throws Exception {
        return manager.getAll();
    }

    // 2. Consultar estudiante por identificación
    @GET
    @Path("/{identificacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Estudiante getByIdentificacion(@PathParam("identificacion") String identificacion) throws Exception {
        return manager.getByIdentificacion(identificacion);
    }
    
 // 3. Consultar estudiante con sus materias
    @GET
    @Path("/conMaterias/{idEstudiante}")
    @Produces(MediaType.APPLICATION_JSON)
    public EstudiantesConMaterias getConMaterias(@PathParam("idEstudiante") int idEstudiante) throws Exception {
        return manager.getEstudiantesConMaterias(idEstudiante);
    }
    
 // 4. Insertar estudiante
    @POST
    @Path("/CrearEstudiante")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertarEstudiante(Estudiante e) throws Exception {
        boolean ok = manager.insertarEstudiante(e);
        if (ok) {
            return "{\"mensaje\":\"Estudiante insertado correctamente\"}";
        } else {
            return "{\"mensaje\":\"Error al insertar\"}";
        }
    }

    // Trabajo Segunda Clase. Conseguir promedios
    @GET
    @Path("/promedios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudiantesConMaterias> getPromediosEstudiantes() throws Exception {
        return manager.getPromediosEstudiantes();
    }
    
}
