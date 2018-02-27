package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.BicicletaUsadaDetailDTO;
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
 * <pre>Clase que implementa el recurso "bicisUsadas".
 * URL: /api/bicisUsadas
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "bicisUsadas".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
  * @author c.santacruza
 */

@Path("bicisUsadas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BicicletaUsadaResource {
    
     /**
     * <h1>POST /api/bicisUsadas : Crear una bicicleta usada.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link BicicletaUsadaDetailDTO}.
     * 
     * Crea una nueva bicicleta usada con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva bicicleta usada .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la bicicleta usada.
     * </code>
     * </pre>
     * @param bici {@link BicicletaUsadaDetailDTO} - La bicicleta usada que se desea guardar.
     * @return JSON {@link BicicletaUsadaDetailDTO}  - La bicicleta usada guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la bicicleta usada.
     */
    @POST
    public BicicletaUsadaDetailDTO createBicicletaUsada(BicicletaUsadaDetailDTO bici) throws BusinessLogicException {
        return bici;
    }

    /**
     * <h1>GET /api/bicisUsadas : Obtener todas las bicicletas usadas.</h1>
     * 
     * <pre>Busca y devuelve todas las bicicletas usadas que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las bicicletas usadas de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link BicicletaUsadaDetailDTO} - Las bicicletas usadas encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<BicicletaUsadaDetailDTO> getBicicletaUsadas() {
        return new ArrayList<BicicletaUsadaDetailDTO>();
    }
    
   /**
     * <h1>GET /api/bicisUsadas/{id} : Obtener bicicleta usada por id.</h1>
     * 
     * <pre>Busca la bicicleta usada con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la bicicleta usada correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una bicicleta usada con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la bicicleta usada que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link BicicletaUsadaDetailDTO} - La bicicleta usada buscada
     */
    @GET
    @Path("{id: \\d+}")
    public BicicletaUsadaDetailDTO getBicicletaUsada(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/bicisUsadas/{id} : Actualizar bicicleta usada con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link BicicletaUsadaDetailDTO}.
     * 
     * Actualiza la bicicleta usada con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la bicicleta usada con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una bicicleta usada con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la bicicleta usada que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param bici {@link BicicletaUsadaDetailDTO} La bicicleta usada que se desea guardar.
     * @return JSON {@link BicicletaUsadaDetailDTO} - La bicicleta usada guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la bicicleta usada porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public BicicletaUsadaDetailDTO updateBicicletaUsada(@PathParam("id") Long id, BicicletaUsadaDetailDTO bici) throws BusinessLogicException {
        return bici;
    }
    
    /**
     * <h1>DELETE /api/bicisUsadas/{id} : Borrar bicicleta usada por id.</h1>
     * 
     * <pre>Borra la bicicleta usada con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la bicicleta usada correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una bicicleta usada con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la bicicleta usada que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteBicicletaUsada(@PathParam("id") Long id) {
        // Void
    }
    
}