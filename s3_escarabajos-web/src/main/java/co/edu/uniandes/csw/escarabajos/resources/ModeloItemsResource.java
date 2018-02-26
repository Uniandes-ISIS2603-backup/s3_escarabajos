/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.ItemDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.ModeloLogic;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <pre>Clase que implementa el recurso "modelos/items".
 * URL: /api/modelos/{modelosId}/items
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * que el servicio {@link ModeloResource} define este servicio de forma relativa 
 * con la ruta "items" con respecto un libro.</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @item ISIS2603
 * @version 1.0
 */
@Path("modelos/{modelosId: \\d+}/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModeloItemsResource {

    @Inject
    private ModeloLogic modeloLogic;

    /**
     * Convierte una lista de ItemEntity a una lista de ItemDetailDTO.
     *
     * @param entityList Lista de ItemEntity a convertir.
     * @return Lista de ItemDetailDTO convertida.
     * 
     */
    private List<ItemDetailDTO> itemsListEntity2DTO(List<ItemEntity> entityList) {
        List<ItemDetailDTO> list = new ArrayList<>();
        for (ItemEntity entity : entityList) {
            list.add(new ItemDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ItemDetailDTO a una lista de ItemEntity.
     *
     * @param dtos Lista de ItemDetailDTO a convertir.
     * @return Lista de ItemEntity convertida.
     * 
     */
    private List<ItemEntity> itemsListDTO2Entity(List<ItemDetailDTO> dtos) {
        List<ItemEntity> list = new ArrayList<>();
        for (ItemDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * <h1>GET /api/modelos/{modelosId}/items : Obtener todos los autores de un libro.</h1>
     *
     * <pre>Busca y devuelve todos los autores que existen en un libro.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los autores del libro.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un libro con el id dado.
     * </code>
     * @param modelosId El ID del libro del cual se buscan los autores
     * @return JSONArray {@link ItemDetailDTO} - Los autores encontrados en el libro. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ItemDetailDTO> listItems(@PathParam("modelosId") Long modelosId) {
        return itemsListEntity2DTO(modeloLogic.listItems(modelosId));
    }

    /**
     * <h1>GET /api/modelos/{modelosId}/items/{itemsId} : Obtener un autor de un libro.</h1>
     *
     * <pre>Busca y devuelve el autor con el ID recibido en la URL, relativo a un
     * libro.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el autor del libro.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un libro con el id dado.
     * </code>
     * @param itemsId El ID del autor que se busca
     * @param modelosId El ID del libro del cual se busca el autor
     * @return {@link ItemDetailDTO} - El autor encontrado en el libro.
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @GET
    @Path("{itemsId: \\d+}")
    public ItemDetailDTO getItems(@PathParam("modelosId") Long modelosId, @PathParam("itemsId") Long itemsId) throws BusinessLogicException {
        return new ItemDetailDTO(modeloLogic.getItem(modelosId, itemsId));
    }

    /**
     * <h1>POST /api/modelos/{modelosId}/items/{itemsId} : Aociar un autor a un libro.</h1>
     *
     * <pre> Asocia un autor existente con un libro existente
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Asoció el autor .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el libro o el autor
     * </code>
     * </pre>
     * @param itemsId El ID del autor que se va a asociar
     * @param modelosId El ID del libro al cual se le va a asociar el autor
     * @return JSON {@link ItemDetailDTO}  - El autor asociado.
     */
    @POST
    @Path("{itemsId: \\d+}")
    public ItemDetailDTO addItems(@PathParam("modelosId") Long modelosId, @PathParam("itemsId") Long itemsId) {
        return new ItemDetailDTO(modeloLogic.addItem(modelosId, itemsId));
    }

    /**
     * <h1>PUT /api/modelos/{modelosId}/items/ : Actualizar los autores de un libro..</h1>
     *
     * <pre>Cuerpo de petición: JSONArray {@link ItemDetailDTO}.
     * 
     * Actualiza la lista de autores de un libro con la lista que se recibe en el
     * cuerpo
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la lista de autores
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la lista
     * </code>
     * </pre>
     * @param modelosId El ID del libro al cual se le va a asociar la lista de autores
     * @param items JSONArray {@link ItemDetailDTO} - La lista de autores que se desea guardar.
     * @return JSONArray {@link ItemDetailDTO}  - La lista actualizada.
     */
    @PUT
    public List<ItemDetailDTO> replaceItems(@PathParam("modelosId") Long modelosId, List<ItemDetailDTO> items) {
        return itemsListEntity2DTO(modeloLogic.replaceItems(modelosId, itemsListDTO2Entity(items)));
    }

    /**
     * <h1>DELETE /api/modelos/{modelosId}/items/{itemsId} : Desasociar autor por id.</h1>
     *
     * <pre>Elimina la conexión entre el autor y el libro recibidos en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la referencia al autor.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un autor con el id dado en el libro.
     * </code>
     * </pre>
     * @param modelosId El ID del libro al cual se le va a desasociar el autor
     * @param itemsId El ID del autor que se desasocia
     */
    @DELETE
    @Path("{itemsId: \\d+}")
    public void removeItems(@PathParam("modelosId") Long modelosId, @PathParam("itemsId") Long itemsId) {
        modeloLogic.removeItem(modelosId, itemsId);
    }
}

