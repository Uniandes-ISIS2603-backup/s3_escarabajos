/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;


import co.edu.uniandes.csw.escarabajos.dtos.ReclamoDetailDTO;
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

/**
 * <pre>Clase que implementa el recurso "reclamos".
 * URL: /api/bicis
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "bicis".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
  * @author n.gaitan
 */

@Path("reclamos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ReclamoResource 
{
    
     /**
     * <h1>POST /api/reclamos : Crear un reclamo.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link ReclamoDetailDTO}.
     * 
     * Crea un nuevo reclamo con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se añadió el reclamó .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe un reclamo para esta bicicleta.
     * </code>
     * </pre>
     * @param reclamo {@link ReclamoDetailDTO} - El reclamo que se desea guardar.
     * @return JSON {@link ReclamoDetailDTO}  - El reclamo guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe un reclamo.
     */
    @POST
    public ReclamoDetailDTO createReclamo(ReclamoDetailDTO reclamo) throws BusinessLogicException {
        return reclamo;
    }

    /**
     * <h1>GET /api/reclamos : Obtener todos los reclamos.</h1>
     * 
     * <pre>Busca y devuelve todas los reclamos que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los reclamos de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link ReclamoDetailDTO} - Los reclamos encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ReclamoDetailDTO> getReclamos() {
        return new ArrayList<ReclamoDetailDTO>();
    }
    
   /**
     * <h1>GET /api/reclamos/{id} : Obtener reclamo por id.</h1>
     * 
     * <pre>Busca el reclamo con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el reclamo correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un reclamo con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del reclamo que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ReclamoDetailDTO} - El reclamo buscado
     */
    @GET
    @Path("{id: \\d+}")
    public ReclamoDetailDTO getReclamo(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/reclamos/{id} : Actualizar el reclamo con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ReclamoDetailDTO}.
     * 
     * Actualiza el reclamo con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el reclamo con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un reclamo con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del reclamo que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param reclamo {@link ReclamoDetailDTO} El reclamo que se desea guardar.
     * @return JSON {@link ReclamoDetailDTO} - El reclamo guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el reclamo porque ya existe uno con ese id.
     */
    @PUT
    @Path("{id: \\d+}")
    public ReclamoDetailDTO updateReclamo(@PathParam("id") Long id, ReclamoDetailDTO reclamo) throws BusinessLogicException {
        return reclamo;
    }
    
    /**
     * <h1>DELETE /api/reclamos/{id} : Borrar reclamo por id.</h1>
     * 
     * <pre>Borra el reclamo con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el reclamo correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un reclamo con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del reclamo que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteReclamo(@PathParam("id") Long id) {
        // Void
    }
    
}
