/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.ModeloDetailDTO;
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
 * <pre>Clase que implementa el recurso "modelos".
 * URL: /api/modelos
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "modelos".</i>
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
@Path("modelos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ModeloResource {

    /**
     * <h1>POST /api/modelos : Crear un modelo.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link ModeloDetailDTO}.
     * 
     * Crea una nuevo modelo con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo modelo.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el modelo.
     * </code>
     * </pre>
     * @param modelo {@link ModeloDetailDTO} - el modelo que se desea guardar.
     * @return JSON {@link ModeloDetailDTO}  - el modelo guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el modelo.
     */
    @POST
    public ModeloDetailDTO createCity(ModeloDetailDTO modelo) throws BusinessLogicException {
        return modelo;
    }

    /**
     * <h1>GET /api/modelos : Obtener todas los modelos.</h1>
     * 
     * <pre>Busca y devuelve todos los modelos que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los modelos de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link ModeloDetailDTO} - Los modelo encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ModeloDetailDTO> getModelos() {
        return new ArrayList<>();
    }

    /**
     * <h1>GET /api/modelos/{id} : Obtener el modelo por id.</h1>
     * 
     * <pre>Busca el modelo con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el modelo correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un modelo con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del modelo que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ModeloDetailDTO} - El modelo buscado
     */
    @GET
    @Path("{id: \\d+}")
    public ModeloDetailDTO getModelo(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/modelos/{id} : Actualizar el modelo con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ModelooDetailDTO}.
     * 
     * Actualiza el modelo con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el modelo con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un modelo con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del modelo que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param modelo {@link ModeloDetailDTO} - El modelo que se desea guardar.
     * @return JSON {@link ModeloDetailDTO} - El modelo guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el modelo.
     */
    @PUT
    @Path("{id: \\d+}")
    public ModeloDetailDTO updateModelo(@PathParam("id") Long id, ModeloDetailDTO modelo) throws BusinessLogicException {
        return modelo;
    }
    
    /**
     * <h1>DELETE /api/modelo/{id} : Borrar modelo por id.</h1>
     * 
     * <pre>Borra el modelo con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el modelo correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un modelo con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del modelo que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteModelo(@PathParam("id") Long id) {
        // Void
    }
}