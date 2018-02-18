/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.AccesorioDetailDTO;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
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
 * <pre>Clase que implementa el recurso "accesorios".
 * URL: /api/accesorios
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "accesorios".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author Andres  
 * @version 1.0
 */
@Path("accesorios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AcessorioResource {

    /**
     * <h1>POST /api/accesorios : Crear un accesorio.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link AccesorioDetailDTO}.
     * 
     * Crea una nuevo accesorio con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo accesorio.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el accesorio.
     * </code>
     * </pre>
     * @param accesorio {@link accesorioDetailDTO} - el accesorio que se desea guardar.
     * @return JSON {@link AccesorioDetailDTO}  - el accesorio guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el accesorio.
     */
    @POST
    public AccesorioDetailDTO createAccesorio(AccesorioDetailDTO accesorio) throws BusinessLogicException {
        return accesorio;
    }

    /**
     * <h1>GET /api/accesorios : Obtener todos los accesorios.</h1>
     * 
     * <pre>Busca y devuelve todos los accesorios que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los accesorios de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link AccesorioDetailDTO} - Los accesorios encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<AccesorioDetailDTO> getAccesorios() {
        return new ArrayList<>();
    }

    /**
     * <h1>GET /api/accesorios/{id} : Obtener el accesorio por id.</h1>
     * 
     * <pre>Busca el accesorio con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el accesorio correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un accesorio con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del accesorio que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link AccesorioDetailDTO} - El accesorio buscado
     */
    @GET
    @Path("{id: \\d+}")
    public AccesorioDetailDTO getAccesorio(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/accesorios/{id} : Actualizar el accesorio con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link AccesorioDetailDTO}.
     * 
     * Actualiza el accesorio con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el accesorio con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un accesorio con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del accesorio que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param accesorio {@link AccesorioDetailDTO} - El accesorio que se desea guardar.
     * @return JSON {@link AccesorioDetailDTO} - El accesorio guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el accesorio.
     */
    @PUT
    @Path("{id: \\d+}")
    public AccesorioDetailDTO updateAccesorio(@PathParam("id") Long id, AccesorioDetailDTO accesorio) throws BusinessLogicException {
        return accesorio;
    }
    
    /**
     * <h1>DELETE /api/accesorio/{id} : Borrar accesorio por id.</h1>
     * 
     * <pre>Borra el accesorio con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el accesorio correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un accesorio con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del accesorio que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteAccesorio(@PathParam("id") Long id) {
        // Void
    }
}