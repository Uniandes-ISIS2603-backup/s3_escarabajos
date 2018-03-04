/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.ItemDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.CarritoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ItemLogic;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * <pre> Clase que implementa el recurso "items" del recurso "carrito".
 * URL: /api/clientes/{idCliente}/carrito/items
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "items".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * @author Mateo
 */

@Path("clientes/{idCliente}/carrito/items")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoItemsResource {
    
    @Inject
    ItemLogic itemLogic;
    
    @Inject
    CarritoLogic carritoLogic;
    
     /**
     * <h1>POST /api/clientes/{idCLiente}/carrito/items/{idItem} : Agrega un item al carrito.</h1>
     * 
     * <pre>agrega el item con el id asociado recibido en la URL con el cliente con el id asociado recibido en la URL.
     * 
     * Agrega un item al carrito y regresa el item agregado
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK se agrego el item.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No existe el item.
     * </code>
     * </pre>
     * @param idCliente Identificador del cliente dueño del carrito al que se desea agregar. Este debe ser una cadena de dígitos.
     * @param idItem Identificador del item que se desea agregar. Este debe ser una cadena de dígitos.
     * @return JSON {@link ItemDetailDTO} - el item agregado.
     */
    @POST
    @Path("{idItem: \\d+}")
    public ItemDetailDTO addItemCarrito(@PathParam("idCliente") Long idCliente, @PathParam("idItem") Long idItem ){
        
        try {
            return new ItemDetailDTO( carritoLogic.addItem(idCliente, idItem) );
        } catch (BusinessLogicException ex) {
            throw new WebApplicationException("El recurso /items/" + idItem + " no existe.", 404);
        }
    }
    
    /**
     * <h1>DELETE /api/clientes/{idCLiente}/carrito/items/{idItem} : Borra un item del carrito.</h1>
     * 
     * <pre>borra el item con el id asociado recibido en la URL con el cliente con el id asociado recibido en la URL.
     * 
     * Borra un item del carrito y regresa el item borrado
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK se borro el item.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No existe el item.
     * </code>
     * </pre>
     * @param idCliente Identificador del cliente dueño del carrito al que se desea agregar. Este debe ser una cadena de dígitos.
     * @param idItem Identificador del item que se desea borrar. Este debe ser una cadena de dígitos.
     * @return JSON {@link ItemDetailDTO} - el item borrado.
     */
    @DELETE
    @Path("{idItem: \\d+}")
    public ItemDetailDTO deleteItemCarrito(@PathParam("idCliente") Long idCliente, @PathParam("idItem") Long idItem ){
        
        try {
            return new ItemDetailDTO( carritoLogic.removeItem(idCliente, idItem) );
        } catch (BusinessLogicException ex) {
            throw new WebApplicationException("El recurso /items/" + idItem + " no existe.", 404);
        }
    }
    
     /**
     * <h1>GET /api/clientes/{idCliente}/carrito/items : Obtener los items del carrito del cliente.</h1>
     * 
     * <pre>Busca y devuelve los items del carrito del cliente.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devueve los items del carrito del cliente.</code> 
     * </pre>
     * @param idCLiente El ID del cliente dueño del carrito del cual se buscan los items
     * @return JSONArray {@link ItemDetailDTO} - los items del carrito del cliente. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ItemDetailDTO> getItemsCarrito( @PathParam("idCliente") Long idCliente ) {
        
        try {
            return listEntity2DTO(carritoLogic.getItems(idCliente));
        } catch (BusinessLogicException ex) {
            throw new WebApplicationException("El recurso /cliente/" + idCliente + " no existe.", 404);
        }
    }
    
    private List<ItemDetailDTO> listEntity2DTO(List<ItemEntity> entityList) {
        List<ItemDetailDTO> list = new ArrayList<>();
        for (ItemEntity entity : entityList) {
            list.add(new ItemDetailDTO(entity));
        }
        return list;
    }   
 }
