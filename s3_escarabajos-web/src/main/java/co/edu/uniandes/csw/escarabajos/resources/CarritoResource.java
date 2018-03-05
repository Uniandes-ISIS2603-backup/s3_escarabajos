/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.AccesorioDTO;
import co.edu.uniandes.csw.escarabajos.dtos.CarritoDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.CarritoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.mappers.BusinessLogicExceptionMapper;
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
 * <pre>Clase que implementa el recurso "carrito".
 * URL: /api/clientes/{idCliente}/carrito
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

@Path("clientes/{idCliente: \\d+}/carrito")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoResource {
    
    @Inject
    CarritoLogic logic;
    
    @Inject
    ClienteLogic logicCliente;
    
     /**
     * <h1>POST /api/clientes/{idCLiente}/carrito : Agrega el carrito.</h1>
     * no deberia haber esta solicitud http porque el carrito se agrega automaticamente cuando se crea el cliente
     * se creo solo por motivos de prueba ya que clienteResource aun no funciona. En un futuro deberia borrarse.
     */
//    @POST
//    public CarritoDetailDTO createCarrito(@PathParam("idCliente") Long idCliente) throws BusinessLogicException {
//        
//        ClienteEntity cliente = logicCliente.getCliente(idCliente);
//        
//        if( cliente == null ){
//            throw new WebApplicationException("El recurso /cliente/" + idCliente + " no existe.", 404);
//        }
//        
//        CarritoDetailDTO carrito = new CarritoDetailDTO();
//        
//        logic.createCarrito(carrito.toEntity());
//        
//        cliente.setCarrito(carrito.toEntity());
//        
//        logicCliente.updateCliente(cliente);
//        
//        return new CarritoDetailDTO(logic.findCarrito(cliente.getCarrito().getId()));
//    }

    
     /**
     * <h1>GET /api/clientes/{idCliente}/carrito : Obtener el carrito del cliente.</h1>
     * 
     * <pre>Busca y devuelve el carrito del cliente.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devueve el carrito del cliente.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No existe el cliente.
     * </code>
     * </pre>
     * @param idCliente Identificador del cliente dueño del carrito que se desa buscar. Este debe ser una cadena de dígitos.
     * @return JSON {@link CarritoDetailDTO} - el carrito buscado.
     */
    @GET
    public CarritoDetailDTO getCarrito(@PathParam("idCliente") Long idCliente) {
        
        ClienteEntity cliente = logicCliente.getCliente(idCliente);
        
        if( cliente == null ){
            throw new WebApplicationException("El recurso /cliente/" + idCliente + " no existe.", 404);
        }
        
        return new CarritoDetailDTO(logic.findCarrito(cliente.getCarrito().getId()));
    }
    
     /**
     * <h1>PUT /api/clientes/{idCLiente}/carrito : Actualizar el carrito del cliente.</h1>
     * <pre>Cuerpo de petición: JSON {@link CarritoDetailDTO}.
     * 
     * Actualiza el carrito del cliente.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el carrito del cliente. Retorna un objeto identico.</code> 
     * </pre>
     * @return JSON {@link CarritoDetailDTO} - El carrito actualizado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el carrito.
     */
    @PUT
    public CarritoDetailDTO updateCarrito(CarritoDetailDTO carrito) throws BusinessLogicException {
        return new CarritoDetailDTO( logic.updateCarrito(carrito.toEntity()) );
    }
    
     /**
     * <h1>DELETE /api/clientes/{idCLiente}/carrito : Agrega el carrito.</h1>
     * no deberia haber esta solicitud http porque el carrito se borra automaticamente cuando se borra el cliente
     */
}
