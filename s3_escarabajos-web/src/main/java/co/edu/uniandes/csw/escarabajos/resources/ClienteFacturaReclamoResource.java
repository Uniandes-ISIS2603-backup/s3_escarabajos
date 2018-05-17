/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.ejb.ReclamoLogic;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
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
@Path("clientes/reclamos")
@RequestScoped
@Produces("application/json")
@Consumes("application/json")
public class ClienteFacturaReclamoResource {

    @Inject
    ReclamoLogic reclamo;

    @Inject
    FacturaLogic factura;

    @Inject
    ClienteLogic cliente;

    /**
     * Convierte una lista de entities a una lista de DTOs
     *
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
     * <h1>PUT /api/facturas/{facturasId}/reclamos/{reclamosId} : Actualizar la
     * reclamo con el id dado.</h1>
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
     * @param id Identificador de la reclamo que se desea actualizar.Este debe
     * ser una cadena de dígitos.
     * @param reclamo {@link reclamoDetailDTO} La reclamo que se desea guardar.
     * @return JSON {@link reclamoDetailDTO} - La reclamo guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar la reclamo porque ya
     * existe una con ese id.
     */
    @PUT
    @Path("/{reclamosId: \\d+}")
    public ReclamoDetailDTO updatereclamo(ReclamoDetailDTO reclamo, @PathParam("reclamosId") Long id) throws BusinessLogicException {
        ReclamoEntity recl = this.reclamo.find(id);
        if (recl == null) {
            throw new WebApplicationException("El recurso /reclamos/" + id + " no existe.", 404);
        }
        return new ReclamoDetailDTO(this.reclamo.updateMensajeReclamo(reclamo.toEntity(), id));
    }

    @POST
    @Path("/{clientesId: \\d+}/facturas/%20{facturasId: \\d+}")
    public ReclamoDetailDTO createReclamo(ReclamoDetailDTO reclamo,
            @PathParam("clientesId") Long clienteId, @PathParam("facturasId") Long facturaId) throws BusinessLogicException {
        if (cliente.getCliente(clienteId) == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        if (factura.getFactura(facturaId) == null) {
            throw new WebApplicationException("La factura no existe", 404);
        }
        return new ReclamoDetailDTO(this.reclamo.createReclamo(reclamo.toEntity(), facturaId, clienteId));
    }

    @PUT
    @Path("/{reclamosId: \\d+}/finalizar")
    public void finalizarReclamo(@PathParam("reclamosId") Long id) throws BusinessLogicException {
        ReclamoEntity recl = this.reclamo.find(id);
        if (recl == null) {
            throw new WebApplicationException("El recurso /reclamos/" + id + " no existe.", 404);
        }
        reclamo.terminarReclamo(id);
    }

    @PUT
    @Path("/{reclamosId: \\d+}/renaudar")
    public void renaudarReclamo(@PathParam("reclamosId") Long id) throws BusinessLogicException {
        ReclamoEntity recl = this.reclamo.find(id);
        if (recl == null) {
            throw new WebApplicationException("El recurso /reclamos/" + id + " no existe.", 404);
        }
        reclamo.renaudarReclamo(id);
    }
    @GET
    @Path("/{reclamosId: \\d+}")
    public ReclamoDetailDTO getReclamo(@PathParam("reclamosId") Long id) throws BusinessLogicException {
        ReclamoEntity recl = this.reclamo.find(id);
        if (recl == null) {
            throw new WebApplicationException("El recurso /reclamos/" + id + " no existe.", 404);
        }
        return new ReclamoDetailDTO(recl);
    }
    @GET
    @Path("/detail/{clientesId: \\d+}")
    public List<ReclamoDetailDTO> getByCliente(@PathParam("clientesId") Long idCliente) throws BusinessLogicException {
        if (cliente.getCliente(idCliente) == null) {
            throw new WebApplicationException("El cliente no existe.", 404);
        }
        return list2DTO(reclamo.getReclamoByCliente(idCliente));
    }
    @GET
    public List<ReclamoDetailDTO> getEnProceso() throws BusinessLogicException {
        
        return list2DTO(reclamo.findAll());
    }

}
