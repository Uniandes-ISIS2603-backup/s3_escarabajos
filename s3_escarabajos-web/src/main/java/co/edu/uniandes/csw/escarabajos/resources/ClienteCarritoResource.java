/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.CarritoDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.ClienteDTO;
import co.edu.uniandes.csw.escarabajos.ejb.CarritoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
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
 * <pre>Clase que implementa el recurso "carrito".
 * URL: /api/clientes/{idCliente}/carrito
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "carrito".</i>
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
@Path("clientes/{idCliente: \\d+}/carrito")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteCarritoResource {

    /**
     * Inyecta la logica de carrito.
     */
    @Inject
    CarritoLogic logic;

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
     * <h1>POST /api/clientes/{idCLiente}/carrito : Agrega el carrito.</h1>
     * no deberia haber esta solicitud http porque el carrito se agrega
     * automaticamente cuando se crea el cliente se creo solo por motivos de
     * prueba ya que clienteResource aun no funciona. En un futuro deberia
     * borrarse.
     */
    @POST
    public CarritoDetailDTO createCarritoDeCliente(@PathParam("idCliente") Long idCliente) throws BusinessLogicException {

        ClienteEntity clienteEntity = logicCliente.getCliente(idCliente);

        if (clienteEntity == null) {
            throw new WebApplicationException("El recurso /clientes/" + idCliente + NOEXISTE, 404);
        }

        if (clienteEntity.getCarrito() != null) {

            throw new WebApplicationException("El recurso /clientes/" + idCliente + " ya tiene un carrito.", 400);
        }

        CarritoDetailDTO carrito = new CarritoDetailDTO(new ClienteDTO(clienteEntity));

        CarritoEntity carritoEntity = logic.createCarrito(carrito.toEntity());

        return new CarritoDetailDTO(carritoEntity);
    }

    /**
     * <h1>GET /api/clientes/{idCliente}/carrito : Obtener el carrito del
     * cliente.</h1>
     *
     * <pre>Busca y devuelve el carrito del cliente.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devueve el carrito del cliente.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No existe el cliente.
     * </code>
     * </pre>
     *
     * @param idCliente Identificador del cliente dueño del carrito que se desa
     * buscar. Este debe ser una cadena de dígitos.
     * @return JSON {@link CarritoDetailDTO} - el carrito buscado.
     */
    @GET
    public CarritoDetailDTO getCarrito(@PathParam("idCliente") Long idCliente) throws BusinessLogicException {

        ClienteEntity cliente = logicCliente.getCliente(idCliente);

        if (cliente == null) {
            throw new WebApplicationException("El recurso /cliente/" + idCliente + NOEXISTE, 404);
        }

        CarritoEntity carrito = logic.getCarritoByClienteId(idCliente);

        if (carrito == null) {

            return createCarritoDeCliente(idCliente);
        } else {

            return new CarritoDetailDTO(carrito);
        }

    }

    /**
     * <h1>PUT /api/clientes/{idCLiente}/carrito : Actualizar el carrito del
     * cliente.</h1>
     * <pre>Cuerpo de petición: JSON {@link CarritoDetailDTO}.
     *
     * Actualiza el carrito del cliente.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el carrito del cliente. Retorna un objeto identico.</code>
     * </pre>
     *
     * @return JSON {@link CarritoDetailDTO} - El carrito actualizado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar el carrito.
     */
    @PUT
    public CarritoDetailDTO updateCarrito(@PathParam("idCliente") Long idCliente, CarritoDetailDTO carrito) throws BusinessLogicException {
        ClienteEntity cliente = logicCliente.getCliente(idCliente);

        if (cliente == null) {
            throw new WebApplicationException("El recurso /cliente/" + idCliente + NOEXISTE, 404);
        }

        CarritoEntity carritoLlega = carrito.toEntity();

        Double precio = carritoLlega.getPrecioTotal();

        CarritoEntity carrito2 = logic.getCarritoByClienteId(idCliente);

        carrito2.setPrecioTotal(precio);

        return new CarritoDetailDTO(logic.updateCarrito(carrito2));

    }

    /**
     * <h1>DELETE /api/clientes/{idCLiente}/carrito : Agrega el carrito.</h1>
     * no deberia haber esta solicitud http porque el carrito se borra
     * automaticamente cuando se borra el cliente
     */
}
