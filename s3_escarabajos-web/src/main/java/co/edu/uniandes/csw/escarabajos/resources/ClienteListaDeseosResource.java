/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.ListaDeseosDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ClienteDTO;
import co.edu.uniandes.csw.escarabajos.ejb.ListaDeseosLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.entities.ListaDeseosEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.mappers.BusinessLogicExceptionMapper;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * <pre>Clase que implementa el recurso "listadeseos".
 * URL: /api/clientes/{idCliente}/listadeseos
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "listadeseos".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 *
 * @author Mateo
 * @version 1.0
 */
@Path("clientes/{idCliente: \\d+}/listadeseos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteListaDeseosResource {

    /**
     * Inyecta la logica de lista de deseos.
     */
    @Inject
    ListaDeseosLogic logic;

    /**
     * Inyecta la logica de cliente.
     */
    @Inject
    ClienteLogic logicCliente;

    /**
     * Constante que modela el string no existe.
     */
    private static final String NOEXISTE = "No existe";

    /**
     * <h1>POST /api/clientes/{idCLiente}/listadeseos : Agrega el
     * listadeseos.</h1>
     * no deberia haber esta solicitud http porque el listadeseos se agrega
     * automaticamente cuando se crea el cliente se creo solo por motivos de
     * prueba ya que clienteResource aun no funciona. En un futuro deberia
     * borrarse.
     */
    @POST
    public ListaDeseosDetailDTO createListaDeseosDeCliente(@PathParam("idCliente") Long idCliente) throws BusinessLogicException {

        ClienteEntity clienteEntity = logicCliente.getCliente(idCliente);

        if (clienteEntity == null) {
            throw new WebApplicationException("El recurso /clientes/" + idCliente + NOEXISTE, 404);
        }

        if (clienteEntity.getListaDeseos() != null) {

            throw new WebApplicationException("El recurso /clientes/" + idCliente + " ya tiene un listadeseos.", 400);
        }

        ListaDeseosDetailDTO listadeseos = new ListaDeseosDetailDTO(new ClienteDTO(clienteEntity));

        ListaDeseosEntity listadeseosEntity = logic.createListaDeseos(listadeseos.toEntity());

        return new ListaDeseosDetailDTO(listadeseosEntity);
    }

    /**
     * <h1>GET /api/clientes/{idCliente}/listadeseos : Obtener el listadeseos
     * del cliente.</h1>
     *
     * <pre>Busca y devuelve el listadeseos del cliente.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devueve el listadeseos del cliente.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No existe el cliente.
     * </code>
     * </pre>
     *
     * @param idCliente Identificador del cliente dueño del listadeseos que se
     * desa buscar. Este debe ser una cadena de dígitos.
     * @return JSON {@link ListaDeseosDetailDTO} - el listadeseos buscado.
     */
    @GET
    public ListaDeseosDetailDTO getListaDeseos(@PathParam("idCliente") Long idCliente) throws BusinessLogicException {

        ClienteEntity cliente = logicCliente.getCliente(idCliente);

        if (cliente == null) {
            throw new WebApplicationException("El recurso /cliente/" + idCliente + NOEXISTE, 404);
        }

        ListaDeseosEntity listadeseos = logic.getListaDeseosByClienteId(idCliente);

        if (listadeseos == null) {

            return createListaDeseosDeCliente(idCliente);
        } else {

            return new ListaDeseosDetailDTO(listadeseos);
        }

    }

    /**
     * <h1>PUT /api/clientes/{idCLiente}/listadeseos : Actualizar el listadeseos
     * del cliente.</h1>
     * <pre>Cuerpo de petición: JSON {@link ListaDeseosDetailDTO}.
     *
     * Actualiza el listadeseos del cliente.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el listadeseos del cliente. Retorna un objeto identico.</code>
     * </pre>
     *
     * @return JSON {@link ListaDeseosDetailDTO} - El listadeseos actualizado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar el listadeseos.
     */
    @PUT
    public ListaDeseosDetailDTO updateListaDeseos(@PathParam("idCliente") Long idCliente, ListaDeseosDetailDTO listadeseos) throws BusinessLogicException {
        ClienteEntity cliente = logicCliente.getCliente(idCliente);

        if (cliente == null) {
            throw new WebApplicationException("El recurso /cliente/" + idCliente + NOEXISTE, 404);
        }

        ListaDeseosEntity listadeseosLlega = listadeseos.toEntity();

        Double precio = listadeseosLlega.getPrecioTotal();

        ListaDeseosEntity listadeseos2 = logic.getListaDeseosByClienteId(idCliente);

        listadeseos2.setPrecioTotal(precio);

        return new ListaDeseosDetailDTO(logic.updateListaDeseos(listadeseos2));

    }

    /**
     * <h1>DELETE /api/clientes/{idCLiente}/listadeseos : Agrega el
     * listadeseos.</h1>
     * no deberia haber esta solicitud http porque el listadeseos se borra
     * automaticamente cuando se borra el cliente
     */
}
