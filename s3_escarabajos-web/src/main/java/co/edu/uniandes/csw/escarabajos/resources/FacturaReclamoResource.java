/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

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
import co.edu.uniandes.csw.escarabajos.dtos.*;
import co.edu.uniandes.csw.escarabajos.ejb.*;
import co.edu.uniandes.csw.escarabajos.entities.*;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 * <pre>Clase que implementa el recurso "reclamos".
 * URL: /api/facturas/{facturasId}/reclamos
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "api/facturas/{facturasId}/reclamos".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author n.gaitan
 */

@Path("facturas/{facturasId: \\d+}/reclamos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FacturaReclamoResource {

    @Inject
    ReclamoLogic reclamo;

    /**
     * Convierte una lista de entities a una lista de DTOs
     * @param l la lista de entidades
     * @return la lista de DTOs
     */
    private List<ReclamoDetailDTO> list2DTO(List<ReclamoEntity> l) {
        List<ReclamoDetailDTO> answ = new ArrayList<>();
        l.forEach((c) -> {
            answ.add(new ReclamoDetailDTO(c));
        });
        return answ;
    }

    /**
     * <h1>POST /api/facturas/{facturasId}/reclamos/{reclamosId} : Crear una Reclamo.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link ReclamoDetailDTO}.
     *
     * Crea una nueva Reclamo con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se añadió la Reclamo .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe una Reclamo para esta bicicleta.
     * </code>
     * </pre>
     *
     * @param Reclamo {@link ReclamoDetailDTO} - la Reclamo que
     * se desea guardar.
     * @param facturaId la factura al que hacer referencia la Reclamo
     * @param id el id del Reclamo
     * @return JSON {@link ReclamoDetailDTO} - la Reclamo guardado con
     * el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe un reclamo.
     */
    @POST
    @PathParam("facturasId: \\d+")
    public ReclamoDetailDTO createReclamo(ReclamoDetailDTO Reclamo
            , @PathParam("facturasId")Long facturaId, @PathParam("reclamosId")Long id) throws BusinessLogicException {

        return new ReclamoDetailDTO(reclamo.createReclamo(Reclamo.toEntity(), facturaId));
    }

    /**
     * <h1>GET /api/facturas/{facturasId}/reclamos : Obtener todas las reclamos del factura.</h1>
     *
     * <pre>Busca y devuelve todas las reclamoificaicones que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las reclamos de la aplicacion.</code>
     * </pre>
     * @param facturaId id del factura que se quiere consultar
     * @return JSONArray {@link ReclamoDetailDTO} - Las reclamos
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    @PathParam("facturasId: \\d+")
    public ReclamoDetailDTO getreclamos(@PathParam("facturasId")Long facturaId) {
        return new ReclamoDetailDTO(reclamo.findByFactura(facturaId).get(0));
    }
    /**
     * <h1>PUT /api/facturas/{facturasId}/reclamos/{reclamosId} : Actualizar la Reclamo con el id
     * dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ReclamoDetailDTO}.
     *
     * Actualiza la Reclamo con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la Reclamo con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una Reclamo con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de la Reclamo que se desea actualizar.Este
     * debe ser una cadena de dígitos.
     * @param Reclamo {@link ReclamoDetailDTO} La Reclamo que se
     * desea guardar.
     * @param facturaId El factura de la reclamoificación que se quiere actualizar.
     * @return JSON {@link ReclamoDetailDTO} - La Reclamo guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar la Reclamo
     * porque ya existe una con ese id.
     */
    @PUT
    @Path("{facturasId: \\d+}")
    public ReclamoDetailDTO updateReclamo(ReclamoDetailDTO Reclamo, 
           @PathParam("facturasId") Long facturaId,  @PathParam("reclamosId") Long id) throws BusinessLogicException {
        ReclamoEntity reclamoi = reclamo.find(id);
        if (reclamoi == null) 
        {
            throw new WebApplicationException("El recurso /reclamos/" + id + " no existe.", 404);
        }
        return new ReclamoDetailDTO(reclamo.updateReclamo(Reclamo.toEntity(), facturaId));
    }

    /**
     * <h1>DELETE /api/facturas/{facturasId}/reclamos/{reclamosId} : Borrar Reclamo por id.</h1>
     *
     * <pre>Borra la Reclamo con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la Reclamo correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una Reclamo con el id dado.
     * </code>
     * </pre>
     *
     * @param facturaId el factura al que se le quiere eliminar la reclamoificación
     * @param id Identificador de la Reclamo que se desea borrar. Este debe
     * ser una cadena de dígitos.
     */
    @DELETE
    @Path("{facturasId: \\d+}")
    public void deleteReclamo( @PathParam("facturasId")Long facturaId, @PathParam("reclamosId") Long id) {
       ReclamoEntity reclamos = reclamo.find(id);
        if (reclamos == null) 
        {
            throw new WebApplicationException("El recurso /reclamos/" + id + " no existe.", 404);
        }
        reclamo.delete(id);
    }
    /**
     * <h1>GET /api/facturas/{facturasId}/reclamos : Buscar los Reclamos en progreso.</h1>
     * @return la lista
     */
    @GET
    public List<ReclamoDetailDTO> reclamosEnProgreso()
    {
        return list2DTO(reclamo.getReclamosEnProceso());
    }
   /**
     * <h1>GET /api/facturas/{facturasId}/reclamos : Buscar los Reclamos terminados.</h1>
     * @return la lista
     */
    @GET
    public List<ReclamoDetailDTO> reclamosTerminados()
    {
        return list2DTO(reclamo.getReclamosTerminados());
    }
}
