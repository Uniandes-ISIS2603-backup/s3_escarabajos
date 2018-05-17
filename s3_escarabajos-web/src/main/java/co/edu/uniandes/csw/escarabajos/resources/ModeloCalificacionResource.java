/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import co.edu.uniandes.csw.escarabajos.dtos.*;
import co.edu.uniandes.csw.escarabajos.ejb.*;
import co.edu.uniandes.csw.escarabajos.entities.*;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 * <pre>Clase que implementa el recurso "calificaciones".
 * URL: /api/modelos/{modelosId}/calificaciones
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta
 * "api/modelos/{modelosId}/calificaciones".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author n.gaitan
 */
@Path("modelos/{modelosId: \\d+}/clientes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ModeloCalificacionResource {

    /**
     * Inyecta la logica de calificacion.
     */
    @Inject
    CalificacionLogic cal;

    /**
     * Inyecta la logica de modelo.
     */
    @Inject
    ModeloLogic mod;

    /**
     * Inyecta la logica de cliente.
     */
    @Inject
    ClienteLogic cli;

    /**
     * Convierte una lista de entities a una lista de DTOs
     *
     * @param l la lista de entidades
     * @return la lista de DTOs
     */
    private List<CalificacionDetailDTO> list2DTO(List<CalificacionEntity> l) {
        List<CalificacionDetailDTO> answ = new ArrayList<>();
        l.forEach((c) -> {
            answ.add(new CalificacionDetailDTO(c));
        });
        return answ;
    }

    /**
     * <h1>POST /api/modelos/{modelosId}/clientes/{clientesId}/calificaciones :
     * Crear una calificacion.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link CalificacionDetailDTO}.
     *
     * Crea una nueva calificacion con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se añadió la calificacion .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe una calificacion para esta bicicleta.
     * </code>
     * </pre>
     *
     * @param calificacion {@link CalificacionDetailDTO} - la calificacion que
     * se desea guardar.
     * @param modeloId el modelo al que hacer referencia la calificacion
     * @param clienteId el cliente que hace la calificacion
     * @return JSON {@link CalificacionDetailDTO} - la calificacion guardado con
     * el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe un reclamo.
     */
    @POST
    @Path("/{clientesId: \\d+}/calificaciones")
    public CalificacionDetailDTO createCalificacion(CalificacionDetailDTO calificacion,
            @PathParam("modelosId") Long modeloId, @PathParam("clientesId") Long clienteId) throws BusinessLogicException {
        if (cli.getCliente(clienteId) == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        if (mod.getModelo(modeloId) == null) {
            throw new WebApplicationException("El modelo no existe", 404);
        }
        return new CalificacionDetailDTO(cal.crearCalificacion(calificacion.toEntity(), modeloId, clienteId));

    }

    /**
     * <h1>GET /api/modelos/{modelosId}/calificaciones : Obtener todas las
     * calificaciones del modelo.</h1>
     *
     * <pre>Busca y devuelve todas las calificaicones que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las calificaciones de la aplicacion.</code>
     * </pre>
     *
     * @param modeloId id del modelo que se quiere consultar
     * @return JSONArray {@link CalificacionDetailDTO} - Las calificaciones
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    @Path("/calificaciones")
    public List<CalificacionDetailDTO> getCalificaciones(@PathParam("modelosId") Long modeloId) {
        if (mod.getModelo(modeloId) == null) {
            throw new WebApplicationException("El modelo no existe", 404);
        }
        return list2DTO(cal.getCalificacionesPorModelo(modeloId));
    }
}
