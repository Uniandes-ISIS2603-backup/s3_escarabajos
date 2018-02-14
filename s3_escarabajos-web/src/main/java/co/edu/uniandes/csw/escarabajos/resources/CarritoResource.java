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

@Path("clientes/{id: \\d+}/carrito")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoResource {
    
     /**
     * <h1>POST /api/cliente/{id}/carrito : Agrega el carrito.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link CarritoDetailDTO}.
     * 
     * Agrega un carrito
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Agrego el carrito.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: El cliente no se encuentra.
     * </code>
     * </pre>
     * @return JSON {@link CarritoDTO}  - El carrito del cliente dado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica.
     */
    
    @POST
    public CarritoDetailDTO agregarCarrito(){
        
        return null;
    }
    
    /**
     * <h1>DELETE /api/cliente/{id}/carrito/{id2} : Borrar el carrito.</h1>
     * 
     * <pre>Borra el carrito.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el carrito.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe el cliente.
     * </code>
     * </pre>
     */
    @DELETE
    @Path("{id2: \\d+}")
     public void deleteBicicleta() {
        // Void
    }
}
