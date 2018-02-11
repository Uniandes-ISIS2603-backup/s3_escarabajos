/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.BicicletaDetailDTO;
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
 * <pre>Clase que implementa el recurso "bicis".
 * URL: /api/bicis
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "bicis".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
  * @author c.santacruza
 */

@Path("bicis")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class BicicletaResource {
    
     /**
     * <h1>POST /api/bicis : Crear una bicicleta.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link BicicletaDetailDTO}.
     * 
     * Crea una nueva bicicleta con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva bicicleta .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la bicicleta.
     * </code>
     * </pre>
     * @param bicicleta {@link BicicletaDetailDTO} - La bicicleta que se desea guardar.
     * @return JSON {@link BicicletaDetailDTO}  - La bicicleta guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la bicicleta.
     */
    @POST
    public BicicletaDetailDTO createBicicleta(BicicletaDetailDTO bicicleta) throws BusinessLogicException {
        return bicicleta;
    }

    /**
     * <h1>GET /api/bicis : Obtener todas las bicicletas.</h1>
     * 
     * <pre>Busca y devuelve todas las bicicletas que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las bicicletas de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link BicicletaDetailDTO} - Las bicicletas encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<BicicletaDetailDTO> getBicicletas() {
        return new ArrayList<BicicletaDetailDTO>();
    }
    
   /**
     * <h1>GET /api/bicicletas/{id} : Obtener bicicleta por id.</h1>
     * 
     * <pre>Busca la bicicleta con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la bicicleta correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una bicicleta con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la bicicleta que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link BicicletaDetailDTO} - La bicicleta buscada
     */
    @GET
    @Path("{id: \\d+}")
    public BicicletaDetailDTO getBicicleta(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/bicicletas/{id} : Actualizar bicicleta con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link BicicletaDetailDTO}.
     * 
     * Actualiza la bicicleta con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la bicicleta con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una bicicleta con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la bicicleta que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param city {@link BicicletaDetailDTO} La bicicleta que se desea guardar.
     * @return JSON {@link BicicletaDetailDTO} - La bicicleta guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la bicicleta porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public BicicletaDetailDTO updateBicicleta(@PathParam("id") Long id, BicicletaDetailDTO city) throws BusinessLogicException {
        return city;
    }
    
    /**
     * <h1>DELETE /api/bicicletas/{id} : Borrar bicicleta por id.</h1>
     * 
     * <pre>Borra la bicicleta con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la bicicleta correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una bicicleta con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la bicicleta que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteBicicleta(@PathParam("id") Long id) {
        // Void
    }
    
}