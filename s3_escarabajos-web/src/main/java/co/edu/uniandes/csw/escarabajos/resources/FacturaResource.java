/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.FacturaDTO;
import co.edu.uniandes.csw.escarabajos.dtos.FacturaDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.FacturaLogic;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 *
 * @author jp.carreno
 */
@Path("facturas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class FacturaResource {
    
    @Inject
    FacturaLogic logic;
    
     /**
     * <h1>POST /api/facturas : Crear una factura.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link FacturaDetailDTO}.
     * 
     * Crea una nueva factura con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva factura .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la factura.
     * </code>
     * </pre>
     * @param factura {@link FacturaDetailDTO} - La factura que se desea guardar.
     * @return JSON {@link FacturaDetailDTO}  - La factura guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la factura.
     */
    @POST
    public FacturaDetailDTO createFactura(FacturaDetailDTO factura) throws BusinessLogicException {
        return factura;
    }

    /**
     * <h1>GET /api/facturas : Obtener todas las facturas.</h1>
     * 
     * <pre>Busca y devuelve todas las facturas que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las facturas de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link FacturaDetailDTO} - Las facturas encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<FacturaDetailDTO> getFacturas() {
        return new ArrayList<FacturaDetailDTO>();
    }
    
   /**
     * <h1>GET /api/facturas/{id} : Obtener factura por id.</h1>
     * 
     * <pre>Busca la factura con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la factura correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una factura con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la factura que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link FacturaDetailDTO} - La factura buscada
     */
    @GET
    @Path("{id: \\d+}")
    public FacturaDetailDTO getFactura(@PathParam("id") Long id) {
        FacturaEntity entity = logic.getFactura(id);
        if(entity == null){
            throw new WebApplicationException("El recurso /facturas/" + id + " no existe.", 404);
        }
        return new FacturaDetailDTO(entity) ;
    }
    
    /**
     * <h1>PUT /api/facturas/{id} : Actualizar factura con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link BicicletaDetailDTO}.
     * 
     * Actualiza la factura con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la factura con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una factura con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la factura que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param city {@link FacturaDetailDTO} La factura que se desea guardar.
     * @return JSON {@link FacturaDetailDTO} - La factura guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la factura porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public FacturaDetailDTO updateFactura(@PathParam("id") Long id, FacturaDetailDTO city) throws BusinessLogicException {
        city.setId(id);
        FacturaEntity entity = logic.getFactura(id);
        if(entity == null){
            throw new WebApplicationException("El recurso /bicis/" + id + " no existe.", 404);
        }
        return new FacturaDetailDTO(logic.updateFactura(entity));
    }
    
    /**
     * <h1>DELETE /api/facturas/{id} : Borrar factura por id.</h1>
     * 
     * <pre>Borra la factura con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la factura correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una factura con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la factura que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteFactura(@PathParam("id") Long id) {
       FacturaEntity entity = logic.getFactura(id);
       if(entity == null){
           throw new WebApplicationException("El recurso /facturas/" + id + " no existe.", 404);
       }
       logic.deleteFactura(entity);
    }
}
