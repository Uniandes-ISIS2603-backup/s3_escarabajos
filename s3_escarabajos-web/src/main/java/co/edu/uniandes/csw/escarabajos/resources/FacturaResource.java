/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.FacturaDetailDTO;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 *
 * @author jp.carreno
 */
@Path("transacciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class FacturaResource {
    
     /**
     * <h1>POST /api/bicis : Crear una transaccion.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link FacturaDetailDTO}.
     * 
     * Crea una nueva transaccion con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva transaccion .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la transferencia.
     * </code>
     * </pre>
     * @param transferencia {@link FacturaDetailDTO} - La transaccion que se desea guardar.
     * @return JSON {@link FacturaDetailDTO}  - La transferencia guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la transferencia.
     */
    @POST
    public FacturaDetailDTO createBicicleta(FacturaDetailDTO transferencia) throws BusinessLogicException {
        return transferencia;
    }

    /**
     * <h1>GET /api/bicis : Obtener todas las transferencias.</h1>
     * 
     * <pre>Busca y devuelve todas las transferencias que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las transferencias de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link FacturaDetailDTO} - Las transferencias encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<FacturaDetailDTO> getTransferencias() {
        return new ArrayList<FacturaDetailDTO>();
    }
    
   /**
     * <h1>GET /api/transferencias/{id} : Obtener transferencia por id.</h1>
     * 
     * <pre>Busca la transferencia con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la transferencia correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una transferencia con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la transferencia que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link FacturaDetailDTO} - La transferencia buscada
     */
    @GET
    @Path("{id: \\d+}")
    public FacturaDetailDTO getTransferencia(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/transferencias/{id} : Actualizar transferencia con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link BicicletaDetailDTO}.
     * 
     * Actualiza la transferencia con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la transferencia con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una transferencia con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la transferencia que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param city {@link FacturaDetailDTO} La transferencia que se desea guardar.
     * @return JSON {@link FacturaDetailDTO} - La transferencia guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la transferencia porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public FacturaDetailDTO updateTransferencia(@PathParam("id") Long id, FacturaDetailDTO city) throws BusinessLogicException {
        return city;
    }
    
    /**
     * <h1>DELETE /api/transferencias/{id} : Borrar transferencia por id.</h1>
     * 
     * <pre>Borra la transferencia con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la transferencia correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una transferencia con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la transferencia que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteTransferencia(@PathParam("id") Long id) {
        // Void
    }
    
}
