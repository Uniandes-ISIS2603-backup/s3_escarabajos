/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.BicicletaDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.CarritoDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.CityDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ClienteDetailDTO;
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
 * <pre>Clase que implementa el recurso "carrito".
 * URL: /api/cliente/{id}/carrito
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

@Path("cities")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoResource {
    
     /**
     * <h1>POST /api/cliente/{id}/carrito : Agrega una bicicleta al carrito.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link CarritoDetailDTO}.
     * 
     * Agrega una bicicleta con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Agrego una bicicleta al carrito.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: La bicicleta no se encuentra en el inventario.
     * </code>
     * </pre>
     * @param bicicleta {@link BicicletaDTO} - La bicicleta que se desea agregar al carrto.
     * @return JSON {@link BicicletaDTO}  - La bicicleta que se agregó.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando la bicicleta no se encuentra en el inventario.
     */
    
    @POST
    public BicicletaDetailDTO agregarBicicleta(BicicletaDetailDTO bicicleta){
        
        return bicicleta;
    }
    
     /**
     * <h1>GET /api/cliente/{id}/carrito : Obtener el carrito del cliente dado.</h1>
     * 
     * <pre>Busca y devuelve el carrito del cliente dado.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el carrito del cliente dado.</code> 
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: El cliente no se encontro en la lista de clientes registrados.
     * </pre>
     * @return JSONArray {@link CarritoDetailDTO} - El carrito del cliente dado.
     */
    
    @GET
    public CarritoDetailDTO getCarrito(){
        
        return null;
    }
    
        /**
     * <h1>GET /api/cliente/{id}/carrito : Obtener una bicicleta del carrito por id.</h1>
     * 
     * <pre>Busca la bicicleta con el id recibido en el carrito del cliente dado.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la bicicleta con el id recibido.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una bicicleta con el id dado en el carrito.
     * </code> 
     * </pre>
     * @param id Identificador de la bicicleta.
     * @return JSON {@link BicicletaDetailDTO} - La ciudad buscada
     */
    @GET
    @Path("{id: \\d+}")
    public BicicletaDetailDTO getBicicleta(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>DELETE /api/cliente/{id}/carrito/{id} : Borrar bicicleta del carrito por id.</h1>
     * 
     * <pre>Borra la bicicleta con el id asociado recibido en la URL del carrito.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la bicicleta correspondiente al id dado de el carrito.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una bicicleta con el id dado en el carrito.
     * </code>
     * </pre>
     * @param id Identificador de la bicicleta que se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteBicicleta(@PathParam("id") Long id) {
        // Void
    }
}
