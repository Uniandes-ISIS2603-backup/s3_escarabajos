package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.BicicletaDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ItemDetailDTO;
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
 * <pre>Clase que implementa el recurso "items".
 * URL: /api/items
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "items".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
  * @author Andres
 */

@Path("items")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ItemResource {
    
     /**
     * <h1>POST /api/items : Crear un Item.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link ItemDetailDTO}.
     * 
     * Crea un nuevo item con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo item .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el item.
     * </code>
     * </pre>
     * @param item {@link ItemDetailDTO} - El item que se desea guardar.
     * @return JSON {@link ItemDetailDTO}  - El item guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el item.
     */
    @POST
    public ItemDetailDTO createITEM(ItemDetailDTO item) throws BusinessLogicException {
        //ESTE METODO NO SE DEBE CREAR ya que una instancia de item no deberia existir sola
        return item;
    }

    /**
     * <h1>GET /api/item : Obtener todos los items.</h1>
     * 
     * <pre>Busca y devuelve todos los items que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los items de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link ItemDetailDTO} - Los items encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ItemDetailDTO> getItems() {
        return new ArrayList<ItemDetailDTO>();
    }
    
   /**
     * <h1>GET /api/items/{id} : Obtener item por id.</h1>
     * 
     * <pre>Busca el item con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el item correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un item con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del item que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ItemDetailDTO} - el item buscado
     */
    @GET
    @Path("{id: \\d+}")
    public ItemDetailDTO getItem(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/items/{id} : Actualizar item con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ItemDetailDTO}.
     * 
     * Actualiza el item con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el item con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un item con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del item que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param item {@link ItemDetailDTO} el item que se desea guardar.
     * @return JSON {@link ItemDetailDTO} - el item guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el item porque ya existe uno con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public ItemDetailDTO updateItem(@PathParam("id") Long id, ItemDetailDTO item) throws BusinessLogicException {
        return item;
    }
    
    /**
     * <h1>DELETE /api/item/{id} : Borrar item por id.</h1>
     * 
     * <pre>Borra el item con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el item correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un item con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del ite, que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteItem(@PathParam("id") Long id) {
        // Void
    }
    
}
