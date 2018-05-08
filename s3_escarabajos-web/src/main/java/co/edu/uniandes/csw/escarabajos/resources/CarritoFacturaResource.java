/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.CarritoDetailDTO;
import co.edu.uniandes.csw.escarabajos.dtos.FacturaDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.CarritoLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
    
    @Inject
    CarritoLogic logicCarrito;
    
    @Inject
    ClienteLogic logicCliente;
    
    ClienteCarritoResource clienteCarrito;
    
     /**
     * <h1>GET /api/clientes/{idCliente}/carrito/factura : Obtener el carrito del cliente.</h1>
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
     * @param idCliente Identificador del cliente dueño del carrito que se desa buscar. Este debe ser una cadena de dígitos.
     * @return JSON {@link FacturaDetailDTO} - la factura generada por el carritp
     * lanza excepcion si no hay items en el carrito.
     */
    @GET
    public FacturaDetailDTO getFacturCarrito(@PathParam("idCliente") Long idCliente) throws BusinessLogicException {
        //DONE: Revisar este código. Es un GET pero hace un create?
        //asi es como queremos que sea para que cuando le den generar get factura se genere y se devuelve la factura que se genero
        ClienteEntity cliente = logicCliente.getCliente(idCliente);
    
        CarritoDetailDTO carrito = clienteCarrito.getCarrito(idCliente);
        
        FacturaDetailDTO factura= new FacturaDetailDTO(logicCarrito.crearFactura(carrito.getId()));
        
        return factura;
    }
}
