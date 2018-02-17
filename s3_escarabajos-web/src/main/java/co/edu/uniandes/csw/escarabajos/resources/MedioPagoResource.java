/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.MedioPagoDetailDTO;
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
 * <pre>Clase que implementa el recurso "MedioPago".
 * URL: /api/clientes/{id}/mediospago
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "mediospago".</i>
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
@Path("clientes/{id: \\d+}/mediospago")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

/**
 *
 * @author Mateo
 */
public class MedioPagoResource {
    
     /**
     * <h1>POST /api/clientes/{id}/mediospago : Crear una ciudad.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link MedioPagoDetailDTO}.
     * 
     * Crea un nuevo medio de pago con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo medio de pago.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el medio de pago.
     * </code>
     * </pre>
     * @param medioP {@link MedioPagoDetailDTO} - La ciudad que se desea guardar.
     * @return JSON {@link MedioPagoDetailDTO}  - La ciudad guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la ciudad.
     */
    @POST
    public MedioPagoDetailDTO createMedioPago(MedioPagoDetailDTO medioP) throws BusinessLogicException {
        return medioP;
    }

    /**
     * <h1>GET /api/clientes/{id}/mediospago : Obtener todos los medios de pago.</h1>
     * 
     * <pre>Busca y devuelve todos los medios de pago que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los medios de pago de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link MedioPagoDetailDTO} - Los medios de pago encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<MedioPagoDetailDTO> getMediosPago() {
        return new ArrayList<MedioPagoDetailDTO>();
    }

    /**
     * <h1>GET /api/clientes/{id}/mediospago/{id} : Obtener medio de pago por id.</h1>
     * 
     * <pre>Busca el medio de pago con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el medio de pago correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un medio de pago con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del medio de pago que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link MedioPagoDetailDTO} - El medio de pago buscado
     */
    @GET
    @Path("{id: \\d+}")
    public MedioPagoDetailDTO getMedioPago(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/clientes/{id}/mediospago/{id} : Actualizar medio de pago con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link MedioPagoDetailDTO}.
     * 
     * Actualiza el medio de pago con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el medio de pago con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un medio de pago con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del medio de pago que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param medioP {@link MedioPagoDetailDTO} El medio de pago que se desea guardar.
     * @return JSON {@link MedioPagoDetailDTO} - El medio de pago guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el medio de pago porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public MedioPagoDetailDTO updateMedioPago(@PathParam("id") Long id, MedioPagoDetailDTO medioP) throws BusinessLogicException {
        return medioP;
    }
    
    /**
     * <h1>DELETE /api/clientes/{id}/mediospago/{id} : Borrar medio de pago por id.</h1>
     * 
     * <pre>Borra el medio de pago con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el medio de pago correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un medio de pago con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del medio de pago que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteMedioPago(@PathParam("id") Long id) {
        // Void
    }
    
}
