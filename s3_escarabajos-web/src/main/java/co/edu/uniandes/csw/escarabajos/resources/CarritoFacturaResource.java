/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.CarritoDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.FacturaDTO;
import co.edu.uniandes.csw.escarabajos.ejb.CarritoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.ejb.FacturaLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ItemLogic;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Mateo
 */
@Path("clientes/{idCliente: \\d+}/carrito/factura")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoFacturaResource {

    /**
     * Inyecta la logica de carrito.
     */
    @Inject
    CarritoLogic logicCarrito;

    /**
     * Inyecta la logica de cliente.
     */
    @Inject
    ClienteLogic logicCliente;

    /**
     * Inyecta la logica de factura.
     */
    @Inject
    FacturaLogic logicFactura;

    /**
     * Inyecta la logica de item.
     */
    @Inject
    ItemLogic logicItem;

    /**
     * Inyecta el recurso ClienteCarrito.
     */
    @Inject
    ClienteCarritoResource clienteCarrito;

    /**
     * Inyecta el recurso CarritoItems.
     */
    @Inject
    CarritoItemsResource carritoR;

    /**
     * <h1>GET /api/clientes/{idCliente}/carrito/factura : Obtener el carrito
     * del cliente.</h1>
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
     * @return JSON {@link FacturaDetailDTO} - la factura generada por el
     * carritp lanza excepcion si no hay items en el carrito.
     * @throws co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException
     */
    @POST
    public FacturaDTO getFacturaCarrito(@PathParam("idCliente") Long idCliente) throws BusinessLogicException {
        //asi es como queremos que sea para que cuando le den generar get factura se genere y se devuelve la factura que se genero

        CarritoDetailDTO carrito = clienteCarrito.getCarrito(idCliente);
        FacturaEntity factura = logicCarrito.crearFactura(carrito.getId());

        for (int i = 0; i < factura.getItems().size(); i++) {
            logicItem.comprarItem(factura.getItems().get(i).getId());
        }

        factura.setItems(new ArrayList<>());

        factura.setCliente(logicCliente.getCliente(idCliente));
        FacturaEntity factura2 = logicFactura.createFactura(factura);

        carritoR.vaciarCarrito(carrito.getId());
        return new FacturaDTO(factura2);
    }
}
