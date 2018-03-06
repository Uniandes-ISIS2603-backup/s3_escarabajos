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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import co.edu.uniandes.csw.escarabajos.dtos.*;
import co.edu.uniandes.csw.escarabajos.ejb.*;
import co.edu.uniandes.csw.escarabajos.entities.*;
import com.sun.javafx.scene.control.skin.VirtualFlow;

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
@Path("modelos/{modelosId: \\d+}/calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ModeloCalificacionResource {

    @Inject
    CalificacionLogic cal;

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
     * <h1>POST /api/modelos/{modelosId}/calificaciones/{calificacionesId} :
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
     * @param id el id de la calificacion
     * @return JSON {@link CalificacionDetailDTO} - la calificacion guardado con
     * el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe un reclamo.
     */
    @POST
    @PathParam("modelosId: \\d+")
    public CalificacionDetailDTO createCalificacion(CalificacionDetailDTO calificacion,
             @PathParam("modelosId") Long modeloId, @PathParam("clientesId") Long clienteId, @PathParam("calificacionesId") Long id) throws BusinessLogicException {

        return new CalificacionDetailDTO(
                cal
                        .crearCalificacion(
                                calificacion
                                        .toEntity(),
                                modeloId,
                                clienteId));
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
    @PathParam("modelosId: \\d+")
    public List<CalificacionDetailDTO> getCalificaciones(@PathParam("modelosId") Long modeloId) {
        return list2DTO(cal.getCalificacionesPorModelo(modeloId));
    }

    /**
     * <h1>GET /api/modelos/{modelosId}/calificaciones/{calificacionesId} :
     * Obtener calificacion por id.</h1>
     *
     * <pre>Busca la calificacion con el id y el modelo asociado recibido en la URL y la devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la calificacion correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una calificacion con el id dado.
     * </code>
     * </pre>
     *
     * @param modeloId el id del modelo que al que hace parte la calificacion
     * @param clienteId el id del cliente al que hace parte la calificacion
     * @param id Identificador de la calificacion que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link CalificacionDetailDTO} - El reclamo buscada
     */
    @GET
    @Path("{modelosId: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("modelosId") Long modeloId, @PathParam("clientesId") Long clienteId, @PathParam("calificacionesId") Long id) throws WebApplicationException, BusinessLogicException {
        CalificacionEntity calificacion = cal.find(id);
        if (calificacion == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + id + " no existe.", 404);
        }
        CalificacionDetailDTO cali = new CalificacionDetailDTO(cal.getCalificacionesPorClienteAndModelo(clienteId, modeloId).get(0));
        return cali;
    }

    /**
     * <h1>PUT /api/modelos/{modelosId}/calificaciones/{calificacionesId} :
     * Actualizar la calificacion con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ReclamoDetailDTO}.
     *
     * Actualiza la calificacion con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la calificacion con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una calificacion con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de la calificacion que se desea actualizar.Este
     * debe ser una cadena de dígitos.
     * @param calificacion {@link CalificacionDetailDTO} La calificacion que se
     * desea guardar.
     * @param modeloId El modelo de la calificación que se quiere actualizar.
     * @param clienteId {@link ClienteDetailDTO} El cliente de la calificación
     * que se quiere actualizar.
     * @return JSON {@link CalificacionDetailDTO} - La calificacion guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar la calificacion
     * porque ya existe una con ese id.
     */
    @PUT
    @Path("{modelosId: \\d+}")
    public CalificacionDetailDTO updateCalificacion(CalificacionDetailDTO calificacion,
            @PathParam("modelosId") Long modeloId, @PathParam("calificacionesId") Long id, @PathParam("clientesId") Long clienteId) throws BusinessLogicException {
        CalificacionEntity cali = cal.find(id);
        if (cali == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + id + " no existe.", 404);
        }
        return new CalificacionDetailDTO(cal.updateCalificacion(calificacion.toEntity(), modeloId, clienteId));
    }

    /**
     * <h1>DELETE /api/modelos/{modelosId}/calificaicones/{calificacionesId} :
     * Borrar calificacion por id.</h1>
     *
     * <pre>Borra la calificacion con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la calificacion correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una calificacion con el id dado.
     * </code>
     * </pre>
     *
     * @param modeloId el modelo al que se le quiere eliminar la calificación
     * @param clienteId el cliente que quiere eliminar la calificacioón
     * @param id Identificador de la calificacion que se desea borrar. Este debe
     * ser una cadena de dígitos.
     */
    @DELETE
    @Path("{modelosId: \\d+}")
    public void deleteCalificacion(@PathParam("modelosId") Long modeloId, Long clienteId, @PathParam("calificacionesId") Long id) {
        CalificacionEntity cali = cal.find(id);
        if (cali == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + id + " no existe.", 404);
        }
        cal.removeCalificacion(id, clienteId, modeloId);
    }
}
