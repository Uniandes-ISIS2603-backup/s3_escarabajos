/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.AccesorioDTO;
import co.edu.uniandes.csw.escarabajos.dtos.CarritoDTO;
import co.edu.uniandes.csw.escarabajos.ejb.CarritoLogic;
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
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

/**
 * <pre>Clase que implementa el recurso "carrito".
 * URL: /api/carritos
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
@Path("carritos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoResource {
    
    //TODO: No entiendo para qué sirve esta clase. Cuántos carritos hay?
    @Inject
    CarritoLogic logic;

    /**
     * <h1>POST /api/carritos : Crear un carrito.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link AccesorioDTO}.
     * 
     * Crea una nuevo carrito vacio
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo carrito.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el carrito.
     * </code>
     * </pre>
     * @param carrito {@link CarritoDTO} - el carrito que se desea guardar.
     * @return JSON {@link CarritoDTO}  - el carrito guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el carrito.
     */
    
    @POST
    public CarritoDTO createCarrito( CarritoDTO carrito ) throws BusinessLogicException {
        
        CarritoEntity temp = logic.createCarrito(carrito.toEntity());
        return new CarritoDTO(temp);
    }

    /**
     * <h1>GET /api/carritos/{id} : Obtener el carrito por id.</h1>
     * 
     * <pre>Busca el accesorio con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el carrito correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un carrito con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del carrito que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link CarritoDTO} - El carrito buscado
     */
    @GET
    @Path("{id: \\d+}")
    public CarritoDTO getAccesorio(@PathParam("id") Long id) {
        return new CarritoDTO( logic.findCarrito(id) );
    }
    
    /**
     * <h1>PUT /api/carritos/{id} : Actualizar el carrito con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link CarritoDTO}.
     * 
     * Actualiza el carrito con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el carrito con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un carrito con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del carrito que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param carrito {@link CarritoDTO} - El carrito que se desea guardar.
     * @return JSON {@link CarritoDTO} - El carrito guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el carrito.
     */
    @PUT
    @Path("{id: \\d+}")
    public CarritoDTO updateCarrito(@PathParam("id") Long id, CarritoDTO carrito) throws BusinessLogicException {
        return new CarritoDTO( logic.updateCarrito(carrito.toEntity()) );
    }
    
    /**
     *No existe metodo delete carrito porque un carrito nunca debe ser borrado 
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteAccesorio(@PathParam("id") Long id) throws BusinessLogicException {
    }
}
