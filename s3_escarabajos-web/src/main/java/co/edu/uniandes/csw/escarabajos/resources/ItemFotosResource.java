package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.FotoDTO;
import co.edu.uniandes.csw.escarabajos.ejb.FotoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ItemLogic;
import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;

/**
 * <pre>Clase que implementa el recurso "items/{id}/fotos".
 * URL: /api/items/{itemsId}/fotos
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "itemss/{itemsId}/fotos".</i>
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
@Path("items/{itemsId: \\d+}/fotos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemFotosResource {

    @Inject
    private FotoLogic fotoLogic;
    
    @Inject
    private ItemLogic itemLogic;

    /**
     * Convierte un lista de FotoEntity a un lista de FotoDTO.
     *
     * @param entityList Lista de FotoEntity a convertir.
     * @return Lista de FotoDTO convertida.
     *
     */
    private List<FotoDTO> fotosListEntity2DTO(List<FotoEntity> entityList) {
        List<FotoDTO> list = new ArrayList<>();
        for (FotoEntity entity : entityList) {
            list.add(new FotoDTO(entity));
        }
        return list;
    }

    /**
     * <h1>GET /api/items/{itemsId}/fotos : Obtener todos los fotos de un
     * item.</h1>
     *
     * <pre>Busca y devuelve todos los fotos que existen en el item.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los fotos del aplicacion.</code>
     * </pre>
     *
     * @param itemsId Identificador del item que se esta buscando. Este debe
     * ser un cadena de dígitos.
     * @return JSONArray {@link FotoDTO} - Los fotos encontrados en el
     * item. Si no hay ninguno retorna un lista vacía.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el item.
     */
    @GET
    public List<FotoDTO> listFotos(@PathParam("itemsId") Long itemsId) {
        try {
            return fotosListEntity2DTO(itemLogic.listFotos(itemsId));
        } catch (BusinessLogicException ex) {
            throw new WebApplicationException("No deberia passar? Verificando que el otro resource mire si existe.", 404);
        }
    }

    /**
     * <h1>GET /api/items/{itemsId}/fotos/{fotosId} : Obtener foto por id
     * del item por id.</h1>
     *
     * <pre>Busca el foto con el id asociado dentro del item con id asociado.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el foto correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un foto con el id dado dentro del item.
     * </code>
     * </pre>
     *
     * @param itemsId Identificador del item que se esta buscando. Este debe
     * ser un cadena de dígitos.
     * @param fotosId Identificador del foto que se esta buscando. Este debe ser
     * un cadena de dígitos.
     * @return JSON {@link FotoDTO} - El foto buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el item.
     */
    @GET
    @Path("{fotosId: \\d+}")
    public FotoDTO getFotos(@PathParam("itemsId") Long itemsId, @PathParam("fotosId") Long fotosId) {
        try {
            return new FotoDTO(fotoLogic.getFoto(itemsId, fotosId,FotoLogic.ITEM));
        } catch (BusinessLogicException ex) {
            throw new WebApplicationException("No existe este foto en este item", 404);
        }
    }

     /**
     * <h1>POST /api/items/{idItem}/fotos : Crear una foto de un item.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link FotoDTO}.
     * 
     * Crea una nueva foto con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva foto .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la foto.
     * </code>
     * </pre>
     * @param idItem El ID del item del cual se guarda la foto
     * @param foto {@link FotoDTO} - La foto que se desea guardar.
     * @return JSON {@link FotoDTO}  - La foto guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la foto.
     */
    @POST
    public FotoDTO createFoto(@PathParam("itemsId") Long idItem, FotoDTO foto) throws BusinessLogicException {
        return new FotoDTO(fotoLogic.createFoto(idItem, foto.toEntity(),FotoLogic.ITEM));
    }

    /**
     * <h1>PUT /api/items/{idItem}/fotos/{id} : Actualizar una foto de un item.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link ReviewDTO}.
     * 
     * Actualiza una foto con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa el objeto actualizado.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la foto
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la foto
     * </code>
     * </pre>
     * @param id El ID de la foto que se va a actualizar
     * @param foto {@link FotoDTO} - La foto que se desea guardar.
     * @return JSON {@link FotoDTO}  - La foto actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no existe la foto.
     */
    @PUT
    @Path("{id: \\d+}")
    public FotoDTO updateFoto( @PathParam("id") Long id, FotoDTO foto) throws BusinessLogicException {
        foto.setId(id);
        return new FotoDTO(fotoLogic.updateFoto(foto.toEntity()));
    }
    
    
    
    /**
     * <h1>DELETE /api/items/{idItem}/fotos/{fotosId} : Elimina un foto
     * dentro del item.</h1>
     *
     * <pre> Elimina la referencia del foto asociado al ID dentro del item
     * con la informacion que recibe el la URL.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se eliminó la referencia del foto.
     * </code>
     * </pre>
     *
     * @param id Identificador de la foto que se desea borrar. Este debe ser
     * un cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el item.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void removeFoto( @PathParam("id") Long id){
        fotoLogic.deleteFoto(id);
    }
}
