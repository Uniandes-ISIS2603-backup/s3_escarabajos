/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.resources;

import co.edu.uniandes.csw.escarabajos.dtos.ItemDetailDTO;
import co.edu.uniandes.csw.escarabajos.ejb.ListaDeseosLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ClienteLogic;
import co.edu.uniandes.csw.escarabajos.ejb.ItemLogic;
import co.edu.uniandes.csw.escarabajos.entities.ListaDeseosEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * <pre> Clase que implementa el recurso "items" del recurso "listadeseos".
 * URL: /api/clientes/{idCliente}/listadeseos/items
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "items".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * @author Mateo
 */
@Path("listadeseos/{idListaDeseos: \\d+}/items")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ListaItemsResource {

    /**
     * Inyecta la logica de item.
     */
    @Inject
    ItemLogic itemLogic;

    /**
     * Inyecta la logica de lista de deseos.
     */
    @Inject
    ListaDeseosLogic listadeseosLogic;

    /**
     * Inyecta la logica de cliente.
     */
    @Inject
    ClienteLogic clienteLogic;

    /**
     * Constante que modela el string no existe.
     */
    private static final String NOEXISTE = "No existe el listadeseos con id ";

    /**
     * <h1>POST /api/clientes/{idCLiente}/listadeseos/items/{idItem} : Agrega un
     * item al listadeseos.</h1>
     *
     * <pre>agrega el item con el id asociado recibido en la URL con el cliente con el id asociado recibido en la URL.
     *
     * Agrega un item al listadeseos y regresa el item agregado
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK se agrego el item.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No existe el item.
     * </code>
     * </pre>
     *
     * @param idItem Identificador del item que se desea agregar. Este debe ser
     * una cadena de dígitos.
     * @return JSON {@link ItemDetailDTO} - el item agregado.
     */
    @POST
    @Path("/{idItem: \\d+}")
    public ItemDetailDTO addItemListaDeseos(@PathParam("idListaDeseos") Long idListaDeseos, @PathParam("idItem") Long idItem) {
        //DONE si no existe el recurso cliente con idCliente debe disparar WebApplicationException
        //DONE si no existe el recurso item con idItem debe disparar WebApplicationException
        //DONE: Este try catch está mal
        try {

            ListaDeseosEntity listadeseosEntity = listadeseosLogic.findListaDeseos(idListaDeseos);

            if (listadeseosEntity == null) {
                throw new WebApplicationException(NOEXISTE + idListaDeseos, 404);
            }

            ItemEntity itemEntity = itemLogic.getItem(idItem);

            if (itemEntity == null) {
                throw new WebApplicationException("El item " + idItem + " no existe.", 404);
            }

            List<ItemDetailDTO> items = getItemsListaDeseos(idListaDeseos);

            if (items.contains(new ItemDetailDTO(itemEntity, itemLogic.getReferenciaItem(itemEntity)))) {
                throw new WebApplicationException("El item ya esta en su listadeseos", 500);
            }
            itemEntity = itemLogic.getItem(idItem);
            return new ItemDetailDTO(listadeseosLogic.addItem(idListaDeseos, idItem), itemLogic.getReferenciaItem(itemEntity));
        } catch (BusinessLogicException ex) {

            throw new WebApplicationException(ex, 500);
        }
    }

    /**
     * <h1>DELETE /api/clientes/{idCLiente}/listadeseos/items/{idItem} : Borra
     * un item del listadeseos.</h1>
     *
     * <pre>borra el item con el id asociado recibido en la URL con el cliente con el id asociado recibido en la URL.
     *
     * Borra un item del listadeseos y regresa el item borrado
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK se borro el item.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No existe el item.
     * </code>
     * </pre>
     *
     * @param idCliente Identificador del cliente dueño del listadeseos al que
     * se desea agregar. Este debe ser una cadena de dígitos.
     * @param idItem Identificador del item que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @return JSON {@link ItemDetailDTO} - el item borrado.
     */
    @DELETE
    @Path("{idItem: \\d+}")
    public ItemDetailDTO deleteItemListaDeseos(@PathParam("idListaDeseos") Long idListaDeseos, @PathParam("idItem") Long idItem) {
        //DONE si no existe el recurso cliente con idCliente debe disparar WebApplicationException
        //DONE si no existe el recurso item con idItem debe disparar WebApplicationException
        //DONE: Este try catch está mal
        try {

            ListaDeseosEntity listadeseosEntity = listadeseosLogic.findListaDeseos(idListaDeseos);

            if (listadeseosEntity == null) {
                throw new WebApplicationException(NOEXISTE + idListaDeseos, 404);
            }

            ItemEntity itemEntity = itemLogic.getItem(idItem);

            if (itemEntity == null) {
                throw new WebApplicationException("El item " + idItem + " no existe.", 404);
            }

            return new ItemDetailDTO(listadeseosLogic.removeItem(idListaDeseos, idItem), itemLogic.getReferenciaItem(itemEntity));
        } catch (BusinessLogicException ex) {
            throw new WebApplicationException(ex, 500);
        }
    }

    @DELETE
    public void vaciarListaDeseos(@PathParam("idListaDeseos") Long idListaDeseos) {
        //DONE si no existe el recurso cliente con idCliente debe disparar WebApplicationException
        //DONE si no existe el recurso item con idItem debe disparar WebApplicationException
        //DONE: Este try catch está mal

        List<ItemDetailDTO> items = getItemsListaDeseos(idListaDeseos);

        for (int i = 0; i < items.size(); i++) {

            deleteItemListaDeseos(idListaDeseos, items.get(i).getId());
        }
    }

    /**
     * <h1>GET /api/clientes/{idCliente}/listadeseos/items : Obtener los items
     * del listadeseos del cliente.</h1>
     *
     * <pre>Busca y devuelve los items del listadeseos del cliente.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devueve los items del listadeseos del cliente.</code>
     * </pre>
     *
     * @param idCLiente El ID del cliente dueño del listadeseos del cual se
     * buscan los items
     * @return JSONArray {@link ItemDetailDTO} - los items del listadeseos del
     * cliente. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ItemDetailDTO> getItemsListaDeseos(@PathParam("idListaDeseos") Long idListaDeseos) {
        //DONE si no existe el recurso cliente con idCliente debe disparar WebApplicationException
        //DONE si no existe el recurso item con idItem debe disparar WebApplicationException
        //DONE: Este try catch está mal
        try {

            ListaDeseosEntity listadeseosEntity = listadeseosLogic.findListaDeseos(idListaDeseos);

            if (listadeseosEntity == null) {
                throw new WebApplicationException(NOEXISTE + idListaDeseos, 404);
            }

            return listEntity2DTO(listadeseosLogic.getItems(idListaDeseos));

        } catch (BusinessLogicException ex) {
            throw new WebApplicationException(ex, 500);
        }
    }

    private List<ItemDetailDTO> listEntity2DTO(List<ItemEntity> entityList) {
        List<ItemDetailDTO> list = new ArrayList<>();
        for (ItemEntity entity : entityList) {
            list.add(new ItemDetailDTO(entity, itemLogic.getReferenciaItem(entity)));
        }
        return list;
    }
}
