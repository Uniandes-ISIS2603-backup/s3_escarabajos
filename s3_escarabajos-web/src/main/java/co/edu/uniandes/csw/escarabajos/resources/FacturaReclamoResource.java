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
     * <h1>POST /api/facturas/{facturasId}/reclamos/{reclamosId} : Crear una reclamo.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link reclamoDetailDTO}.
     *
     * Crea una nueva reclamo con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se añadió la reclamo .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe una reclamo para esta factura.
     * </code>
     * </pre>
     *
     * @param reclamo {@link ReclamoDetailDTO} - el reclamo que
     * se desea guardar.
     * @param facturaId la factura al que hacer referencia la reclamo
     * @param id el id del reclamo
     * @return JSON {@link ReclamoDetailDTO} - la reclamo guardado con
     * el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe un reclamo.
     */
    @POST
    @PathParam("facturasId: \\d+")
    public ReclamoDetailDTO createreclamo(ReclamoDetailDTO reclamo
            , @PathParam("facturasId")Long facturaId, @PathParam("reclamosId")Long id) throws BusinessLogicException {

        return new ReclamoDetailDTO(this.reclamo.createReclamo(reclamo.toEntity(), facturaId));
    }

    /**
     * <h1>GET /api/facturas/{facturasId}/reclamos : Obtener el reclamo de la factura.</h1>
     *
     * <pre>Busca y devuelve el reclamo correspondiente a la factura.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el reclamo correspondiendte a la factura de la aplicacion.</code>
     * </pre>
     * @param facturaId id del factura que se quiere consultar
     * @return JSONArray {@link reclamoDetailDTO} - Las reclamos
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    @PathParam("facturasId: \\d+")
    public ReclamoDetailDTO getreclamos(@PathParam("facturasId")Long facturaId) 
    {//TODO si no existe el recurso factura debe disparar WebApplicationException
        return list2DTO(reclamo.getReclamoPorfactura(facturaId)).get(0);
    }

    /**
     * <h1>PUT /api/facturas/{facturasId}/reclamos/{reclamosId} : Actualizar la reclamo con el id
     * dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ReclamoDetailDTO}.
     *
     * Actualiza la reclamo con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la reclamo con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una reclamo con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de la reclamo que se desea actualizar.Este
     * debe ser una cadena de dígitos.
     * @param reclamo {@link reclamoDetailDTO} La reclamo que se
     * desea guardar.
     * @param facturaId El factura de la calificación que se quiere actualizar.
     * @return JSON {@link reclamoDetailDTO} - La reclamo guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar la reclamo
     * porque ya existe una con ese id.
     */
    @PUT
    @Path("{facturasId: \\d+}")
    public ReclamoDetailDTO updatereclamo(ReclamoDetailDTO reclamo, 
           @PathParam("facturasId") Long facturaId,  @PathParam("reclamosId") Long id) throws BusinessLogicException {
        //TODO si no existe el recurso factura debe disparar WebApplicationException
        ReclamoEntity recl = this.reclamo.find(id);
        if (recl == null) 
        {
            throw new WebApplicationException("El recurso /reclamos/" + id + " no existe.", 404);
        }
        return new ReclamoDetailDTO(this.reclamo.updateReclamo(reclamo.toEntity(), facturaId));
    }

    /**
     * <h1>DELETE /api/facturas/{facturasId}/reclamos/{reclamosId} : Borrar reclamo por id.</h1>
     *
     * <pre>Borra la reclamo con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la reclamo correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una reclamo con el id dado.
     * </code>
     * </pre>
     *
     * @param facturaId la factura al que se le quiere eliminar la calificación
     * @param id Identificador de la reclamo que se desea borrar. Este debe
     * ser una cadena de dígitos.
     */
    @DELETE
    @Path("{facturasId: \\d+}")
    public void deletereclamo( @PathParam("facturasId")Long facturaId, @PathParam("reclamosId") Long id) throws BusinessLogicException {
       //TODO si no existe el recurso factura debe disparar WebApplicationException
       ReclamoEntity cali = reclamo.find(id);
        if (cali == null) 
        {
            throw new WebApplicationException("El recurso /reclamos/" + id + " no existe.", 404);
        }
        reclamo.deleteReclamoByFacturaId(facturaId,id);
    }
}
