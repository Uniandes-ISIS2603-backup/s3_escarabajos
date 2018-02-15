/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.BicicletaDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.CarritoDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ItemDetailDTO;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.mappers.BusinessLogicExceptionMapper;
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
 * URL: /api/cliente/{id}/carrito/items
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "carrito".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author Mateo 
 * @version 1.0
 */

@Path("clientes/{id: \\d+}/carrito/items")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ItemResource 
{
    
        /**
     * <h1>POST /api/cliente/{id}/carrito/items : Agrega un item al carrito del cliente.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link ItemDetailDTO}.
     * 
     * Agrega un item al carrito.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Agrego el item al carrito.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: El item no se encuentra en el inventario.
     * </code>
     * </pre>
     * @return JSON {@link ItemDTO}  - El item que se agrego al carrito.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica.
     */
    
    @POST
    public CarritoDetailDTO agregarItem(){
        
        return null;
    }
    
    /**
     * <h1>GET /api/cliente/{id}/carrito/items : Obtener todos los items del carrito del cliente.</h1>
     * 
     * <pre>Busca y devuelve todos los items del carrito.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los items del carrito.</code> 
     * </pre>
     * @return JSONArray {@link ItemDTO} - Los items que hay en el carrito. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ItemDetailDTO> getItems() {
        return new ArrayList<ItemDetailDTO>();
    }
    
     /**
     * <h1>GET /api/cliente/{id}/carrito/items/{id2} : Obtener el item id2 del carrito.</h1>
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
     */
    @GET
    @Path("{id2: \\d+}")
    public ItemDetailDTO getItem(@PathParam("id2") Long id) {
        return null;
    }
    
        /**
     * <h1>PUT /cliente/{id}/carrito/items/{id2} : Actualizar el item con el id dado.</h1>
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
     * @param item {@link ItemDetailDTO} El item que se desea guardar.
     * @return JSON {@link ItemDetailDTO} - El item guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el item.
     */
    @PUT
    @Path("{id2: \\d+}")
    public ItemDetailDTO updateItem(@PathParam("id2") Long id, ItemDetailDTO item) throws BusinessLogicException {
        return item;
    }
    
    /**
     * <h1>DELETE /api/clientes/carrito/items/{id2} : Borrar ciudad por id.</h1>
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
     * @param id Identificador del item que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteItem(@PathParam("id") Long id) {
        // Void
    }
}
