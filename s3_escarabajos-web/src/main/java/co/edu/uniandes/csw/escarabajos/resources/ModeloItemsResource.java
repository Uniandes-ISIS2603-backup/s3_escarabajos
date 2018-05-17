package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.ItemDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * <pre>Clase que implementa el recurso "modelos/{id}/items".
 * URL: /api/modelos/{modelosId}/items
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "modeloss/{modelosId}/items".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia un transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author Andres
 * @version 1.0
 */
@Path("modelos/{modelosId: \\d+}/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModeloItemsResource {

    /**
     * Inyecta la logica de modelo.
     */
    @Inject
    private ModeloLogic modeloLogic;

    /**
     * Convierte un lista de ItemEntity a un lista de ItemDetailDTO.
     *
     * @param entityList Lista de ItemEntity a convertir.
     * @return Lista de ItemDetailDTO convertida.
     *
     */
    private List<ItemDetailDTO> itemsListEntity2DTO(List<ItemEntity> entityList) {
        List<ItemDetailDTO> list = new ArrayList<>();
        for (ItemEntity entity : entityList) {
            list.add(new ItemDetailDTO(entity, modeloLogic.getReferenciaItem(entity)));
        }
        return list;
    }

    /**
     * <h1>GET /api/modelos/{modelosId}/items/{itemsId} : Obtener item por id
     * del modelo por id.</h1>
     *
     * <pre>Busca el item con el id asociado dentro del modelo con id asociado.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el item correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un item con el id dado dentro del modelo.
     * </code>
     * </pre>
     *
     * @param modelosId Identificador del modelo que se esta buscando. Este debe
     * ser un cadena de dígitos.
     * @param itemsId Identificador del item que se esta buscando. Este debe ser
     * un cadena de dígitos.
     * @return JSON {@link ItemDetailDTO} - El item buscado
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     * si falla la logica
     */
    @GET
    @Path("{itemsId: \\d+}")
    public ItemDetailDTO getItem(@PathParam("modelosId") Long modelosId, @PathParam("itemsId") Long itemsId) throws BusinessLogicException {
        ItemEntity entity = modeloLogic.getItem(modelosId, itemsId);
        return new ItemDetailDTO(entity, modeloLogic.getReferenciaItem(entity));
    }

    /**
     * <h1>GET /api/modelos/{modelosId}/items : Obtener item por id del modelo
     * por id.</h1>
     *
     * <pre>Busca el item con el id asociado dentro del modelo con id asociado.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el item correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un item con el id dado dentro del modelo.
     * </code>
     * </pre>
     *
     * @param modelosId Identificador del modelo que se esta buscando. Este debe
     * ser un cadena de dígitos.
     * @return
     */
    @GET
    public List<ItemDetailDTO> getItems(@PathParam("modelosId") Long modelosId) throws BusinessLogicException {
        return itemsListEntity2DTO(modeloLogic.listItems(modelosId));
    }
}
