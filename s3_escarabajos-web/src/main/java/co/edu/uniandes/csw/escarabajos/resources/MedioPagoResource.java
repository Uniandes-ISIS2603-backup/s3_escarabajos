/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.CityDetailDTO;
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
 * <pre>Clase que implementa el recurso "MetodoPago".
 * URL: /api/transacciones/MedioPago
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "MetodoPago".</i>
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
@Path("mediopago")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

/**
 *
 * @author Mateo
 */
public class MedioPagoResource {
    
     /**
     * <h1>GET /api/transacciones/{id}/mediopago : Obtener el medio de pago de la transaccion.</h1>
     * 
     * <pre>Devuelve el metodo de pago de la transaccion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el metodo de pago de la transaccion.</code> 
     * </pre>
     * @return JSONArray {@link MedioPagoDetailDTO} - El medio de pago de la transaccion.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una transaccion con el id dado.
     */
    @GET
    public List<CityDetailDTO> getCities() {
        return new ArrayList<CityDetailDTO>();
    }
    
}
