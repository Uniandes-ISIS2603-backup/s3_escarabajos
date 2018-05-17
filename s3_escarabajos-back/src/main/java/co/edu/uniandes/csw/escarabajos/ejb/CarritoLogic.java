/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.CarritoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mateo
 */
@Stateless
public class CarritoLogic {

    /**
     * LOGGER de la clase CarritoLogic.
     */
    private static final Logger LOGGER = Logger.getLogger(CarritoLogic.class.getName());

    /**
     * Constante para modelar que no existe un carrito.
     */
    private static final String NOEXISTE = "No existe un carrito con el id";

    /**
     * Inyecta la persistencia de carrito.
     */
    @Inject
    private CarritoPersistence persistence;

    /**
     * Inyecta la logica de item.
     */
    @Inject
    private ItemLogic itemLogic;

    /**
     * Crea un carito.
     *
     * @param entity el carrito que se quiere crear
     * @return el carrito agregado
     * @throws BusinessLogicException si ya existe el carrito
     */
    public CarritoEntity createCarrito(CarritoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación del carrito");
        CarritoEntity rpta = persistence.create(entity);
        LOGGER.info("Termina proceso de creación de carrito");
        return rpta;
    }

    /**
     * Devuelve un carrito especifico.
     *
     * @param id el id del carrito a buscar
     * @return el carrito encontrado
     * @throws BusinessLogicException por reglas de negocio
     */
    public CarritoEntity findCarrito(Long id) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de consultar el carrito con id={0}", id);
        CarritoEntity carrito = persistence.find(id);
        if (carrito == null) {
            LOGGER.log(Level.SEVERE, "El carrito con el id {0} no existe", id);
            throw new BusinessLogicException();
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar carrito con id={0}", id);
        return carrito;
    }

    /**
     * Actualiza un carrito especifico.
     *
     * @param entity el carrito con la informacion actualizada
     * @return el carrito actualizado
     * @throws BusinessLogicException si no se puede actualizar
     */
    public CarritoEntity updateCarrito(CarritoEntity entity) throws BusinessLogicException {

        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException(NOEXISTE + entity.getId() + "\"");
        }
        return persistence.update(entity);
    }

    /**
     * Elimina un carrito.
     *
     * @param id el id del carrito que se desea borrar
     */
    public void deleteCarrito(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar carrito con id={0}", id);
        persistence.deleteCarrito(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar carrito con id={0}", id);
    }

    /**
     * Devuelve la lista de items del carrito
     *
     * @param idCarrito el id del carrito al que se le quieren consultar los
     * items
     * @return la lista de items
     * @throws BusinessLogicException si no se puede colsultar
     */
    public List<ItemEntity> getItems(Long idCarrito) throws BusinessLogicException {

        if (persistence.find(idCarrito) == null) {
            throw new BusinessLogicException(NOEXISTE + idCarrito + "\"");
        }

        CarritoEntity carrito = persistence.find(idCarrito);

        return carrito.getItems();
    }

    /**
     * Agrega un item a la lista de items.
     *
     * @param idCarrito el id del carito al que se le quiere agregar items
     * @param itemId el id del item que se desea agregar
     * @return el item agregado
     * @throws BusinessLogicException si no se encuentra el carrito o el item
     */
    public ItemEntity addItem(Long idCarrito, Long itemId) throws BusinessLogicException {

        CarritoEntity carrito = persistence.find(idCarrito);

        if (carrito == null) {
            throw new BusinessLogicException(NOEXISTE + idCarrito + "\"");
        }

        if (carrito.getItems().contains(itemLogic.getItem(itemId))) {
            throw new BusinessLogicException("El carrito ya tiene este item");
        }

        ItemEntity item = itemLogic.getItem(itemId);

        if (item == null) {

            throw new BusinessLogicException(NOEXISTE + itemId);
        }

        List<ItemEntity> rpta = carrito.getItems();

        rpta.add(item);

        carrito.setItems(rpta);

        carrito.setPrecioTotal(carrito.getPrecioTotal() + item.getPrecio());

        updateCarrito(carrito);

        return item;
    }

    /**
     * Elimina un item de la lista de items del carrito.
     *
     * @param idCarrito el id del carrito que se le quiere quitar un item
     * @param itemId el id del item que se desea quitar
     * @return el item eliminado
     * @throws BusinessLogicException si no se encuentra el carrito o el item
     */
    public ItemEntity removeItem(Long idCarrito, Long itemId) throws BusinessLogicException {
        CarritoEntity carrito = persistence.find(idCarrito);
        if (carrito == null) {
            throw new BusinessLogicException("No existe un carrito con el id" + idCarrito + "\"");
        }

        ItemEntity item = itemLogic.getItem(itemId);
        List<ItemEntity> rpta = carrito.getItems();
        rpta.remove(item);
        carrito.setItems(rpta);
        carrito.setPrecioTotal(carrito.getPrecioTotal() - item.getPrecio());
        updateCarrito(carrito);

        return item;
    }

    /**
     * Genera la factura con los items que tiene el carrito.
     *
     * @param id el id del carrito
     * @return la factura generada
     * @throws BusinessLogicException si el carrito no tiene items
     */
    public FacturaEntity crearFactura(Long id) throws BusinessLogicException {
        CarritoEntity carrito = persistence.find(id);
        if (carrito == null) {
            throw new BusinessLogicException(NOEXISTE + id + "\"");
        }
        if (carrito.getItems().isEmpty()) {
            throw new BusinessLogicException("No se puede generar factura porque el carrito aun no contiene items");
        }

        FacturaEntity factura = new FacturaEntity();

        actualizarPrecioTotal(id);

        carrito = persistence.find(id);

        factura.setDinero(carrito.getPrecioTotal());

        factura.setCliente(carrito.getCliente());
        
        factura.setItems(carrito.getItems());

        return factura;
    }

    /**
     * Calcula el precio total del carrito y lo actualiza.
     *
     * @param id del carrito que se desea actualizar el precio
     */
    public void actualizarPrecioTotal(Long id) {

        CarritoEntity carrito = persistence.find(id);

        carrito.setPrecioTotal(0.0);

        for (int i = 0; i < carrito.getItems().size(); i++) {

            carrito.setPrecioTotal(carrito.getPrecioTotal() + carrito.getItems().get(i).getPrecio());
        }

        persistence.update(carrito);
    }

    /**
     * Devuelve el carrito perteneciente al cliente que entra por parametro.
     *
     * @param clienteId del cliente dueño del carrito
     * @return carrito encontrado
     */
    public CarritoEntity getCarritoByClienteId(Long clienteId) {

        LOGGER.log(Level.INFO, "Inicia proceso de consultar el carrito del cliente con id={0}", clienteId);
        CarritoEntity carrito = persistence.findCarritoByClienteId(clienteId);
        if (carrito == null) {
            LOGGER.log(Level.SEVERE, "El cliente con id {0} aun no tiene un carrito ", clienteId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar carrito del cliente con id={0}", clienteId);
        return carrito;
    }
}
