package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.BicicletaDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ItemDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ItemDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.ItemLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ItemLogic;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

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
    
    @Inject
    ItemLogic itemLogic;
    
      /**
     * <h1>GET /api/items : Obtener todas los items.</h1>
     * 
     * <pre>Busca y devuelve todos los items que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los items de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link ItemDetailDTO} - Los item encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ItemDetailDTO> getItems() {
         return listItemEntity2DetailDTO(itemLogic.getItems());
    }

    /**
     * <h1>GET /api/items/{id} : Obtener el item por id.</h1>
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
     * @return JSON {@link ItemDetailDTO} - El item buscado
     * throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra el item
     */
    @GET
    @Path("{id: \\d+}")
    public ItemDetailDTO getItem(@PathParam("id") Long id) {
        ItemEntity entity = itemLogic.getItem(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /items/" + id + " no existe.", 404);
        }
        return new ItemDetailDTO(entity);
    }
    
      
    private List<ItemDetailDTO> listItemEntity2DetailDTO(List<ItemEntity> entityList) {
        List<ItemDetailDTO> list = new ArrayList<>();
        for (ItemEntity entity : entityList) {
            list.add(new ItemDetailDTO(entity));
        }
        return list;
    }
    
    
    /**
     * Conexión con el servicio de fotos para un item. {@link ItemFotosResource}
     * 
     * Este método conecta la ruta de /items con las rutas de /fotos que dependen
     * del item, es una redirección al servicio que maneja el segmento de la 
     * URL que se encarga de las fotos.
     * 
     * @param id El ID del id con respecto al cual se accede al servicio.
     * @return El servicio de fotos para ese item en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra el item.
     */
    @Path("{id: \\d+}/fotos")
    public Class<ItemFotosResource> getItemFotosResource(@PathParam("id") Long id) {
        ItemEntity entity = itemLogic.getItem(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /modelos/" + id + "/items no existe", 404);
        }
        return ItemFotosResource.class;
    }
}
