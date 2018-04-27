/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.ReclamoDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.ReclamoLogic;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * @author n.gaitan
 */
@Path("reclamos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ReclamoResource {

    @Inject
    ReclamoLogic reclamoLogic;

    private List<ReclamoDetailDTO> listModeloEntity2DetailDTO(List<ReclamoEntity> entityList) {
        List<ReclamoDetailDTO> list = new ArrayList<>();
        for (ReclamoEntity entity : entityList) {
            list.add(new ReclamoDetailDTO(entity));
        }
        return list;
    }

    @POST
    public ReclamoDetailDTO createReclamo(ReclamoDetailDTO reclamo) throws BusinessLogicException {
        reclamo.setEnProceso(true);
        reclamo.setMensaje("@@@@@@@"+reclamo.getMensaje());
        return new ReclamoDetailDTO(reclamoLogic.createReclamo(reclamo.toEntity(), null));
    }

    @GET
    public List<ReclamoDetailDTO> getReclamos() {
        return listModeloEntity2DetailDTO(reclamoLogic.findAll());
    }

    @GET
    @Path("/{id: \\d+}")
    public ReclamoDetailDTO getReclamo(@PathParam("id") Long id) {
        ReclamoEntity entity = reclamoLogic.find(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /reclamos/" + id + " no existe.", 404);
        }
        return new ReclamoDetailDTO(entity);
    }

    @PUT
    @Path("/{idRe: \\d+}")
    public ReclamoDetailDTO updateReclamo(ReclamoDetailDTO reclamo,@PathParam("idRe") Long id) throws BusinessLogicException {
        reclamo.setId(id);
        return new ReclamoDetailDTO(reclamoLogic.updateMensajeReclamo(reclamo.toEntity(), id));
    }

    @PUT
    @Path("/finalizar/{id: \\d+}")
    public ReclamoDetailDTO terminar(@PathParam("id") Long id) throws BusinessLogicException {
        reclamoLogic.terminarReclamo(id);
        return new ReclamoDetailDTO(reclamoLogic.find(id));
    }
    @PUT
    @Path("/renaudar/{idR: \\d+}")
    public ReclamoDetailDTO renaudar(@PathParam("idR") Long idR) throws BusinessLogicException {
        reclamoLogic.renaudarReclamo(idR);
        return new ReclamoDetailDTO(reclamoLogic.find(idR));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteReclamo(@PathParam("id") Long id) throws BusinessLogicException {
        try {
            reclamoLogic.delete(id);
        } catch (Exception e) {
            throw new BusinessLogicException("El reclamo no existe");
        }
    }
}
