/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.MedioPagoDTO;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.ejb.MedioPagoLogic;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;


@Path("clientes/{idCliente: \\d+}/mediospago")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

/**
 *
 * @author jp.carreno
 */
public class ClienteMedioPagoResource {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteMedioPagoResource.class.getName());
    
    @Inject
    ClienteLogic logicCliente;
    
    @Inject
    MedioPagoLogic logicMedio;
    
    private List<MedioPagoDTO> listEntity2DTO(List<MedioPagoEntity> entityList) {
        List<MedioPagoDTO> list = new ArrayList<>();
        for (MedioPagoEntity entity : entityList) {
            list.add(new MedioPagoDTO(entity));
        }
        return list;
    }
    
     /**
     * <h1>POST /api/clientes/{idCliente}/mediospago : Crear un medio de pago.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link MedioPagoDTO}.
     * 
     * Crea un nuevo medio de pago de un cliente con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo medio de pago.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No existe el cliente.
     * </code>
     * </pre>
     * @param medioP {@link MedioPagoDTO} - El medio de pago que se desea guardar.
     * @return JSON {@link MedioPagoDTO}  - El medio de pago guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no existe el cliente.
     */
    @POST
    public MedioPagoDTO createMedioPagoCliente(@PathParam("idCliente")Long idCliente, MedioPagoDTO medioP) throws BusinessLogicException{
        MedioPagoEntity temp = medioP.toEntity();
        ClienteEntity cliente = logicCliente.getCliente(idCliente);
        if(cliente!=null){
            temp.setCliente(cliente);
            MedioPagoEntity medioPago = logicMedio.createMedioPago(temp);
        }
        else{
            throw new WebApplicationException("El recurso /clientes/" + idCliente + "no existe", 404);
        }
        return new MedioPagoDTO(temp);
    }
    
     /**
     * <h1>GET /api/clientes/{id}/mediospago : Obtener todos los medios de pago de un cliente.</h1>
     * 
     * <pre>Busca y devuelve todos los medios de pago de un cliente con id dado por parametro que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los medios de pago de un cliente de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link MedioPagoDTO} - Los medios de pago encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<MedioPagoDTO> getMediosPagoCliente(@PathParam("idCliente")Long idCliente){
        return listEntity2DTO(logicMedio.getMediosPagoCliente(idCliente));
    }
    
    
}
