/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ClienteEntity;
import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.CarritoPersistence;
import java.util.ArrayList;
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
    
    private static final Logger LOGGER = Logger.getLogger(CarritoLogic.class.getName());
    
    @Inject
    private CarritoPersistence persistence;
    
    @Inject
    private ItemLogic itemLogic;
    
    @Inject
    private ClienteLogic clienteLogic;
    
    public CarritoEntity createCarrito(CarritoEntity entity) throws BusinessLogicException{
        LOGGER.info("Inicia proceso de creación del carrito");
        CarritoEntity carrito = findCarrito(entity.getId());
        if (carrito!= null) {
            throw new BusinessLogicException("El carrito ya existe");
        }
        
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de carrito");
        return entity;
    }
    
    public CarritoEntity findCarrito( Long id ){
        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el carrito con id={0}", id);
        CarritoEntity carrito = persistence.find(id);
        if (carrito == null) {
            LOGGER.log(Level.SEVERE, "El carrito con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar carrito con id={0}", id);
        return carrito;
    }
    
    public CarritoEntity updateCarrito( CarritoEntity entity ) throws BusinessLogicException{
        
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe un carrito con el id" + entity.getId()+ "\"");
        }
        return persistence.update(entity);
    }
    
    public void deleteCarrito(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar carrito con id={0}", id);
        persistence.deleteCarrito(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar carrito con id={0}", id);
    }
    
    public List<ItemEntity> getItems( Long id ) throws BusinessLogicException{
        
        if (persistence.find(id) == null) {
            throw new BusinessLogicException("No existe un cliente con el id" + id + "\"");
        }
        
        CarritoEntity carrito = persistence.find(id);
        
        return carrito.getItems();
    }
    
    public ItemEntity addItem( Long clienteId, Long itemId ) throws BusinessLogicException{
        ClienteEntity cliente = clienteLogic.getCliente(clienteId);    
        if (cliente == null) {
            throw new BusinessLogicException("No existe un cliente con el id" + clienteId + "\"");
        }
        
        CarritoEntity carrito = persistence.find(cliente.getCarrito().getId());
        
        if (carrito.getItems().contains(itemLogic.getItem(itemId))) {
            throw new BusinessLogicException("El carrito ya tiene este item");
        }
        
        ItemEntity item = itemLogic.getItem(itemId);
        
        List<ItemEntity> rpta = carrito.getItems();
        
        rpta.add(item);
        
        carrito.setItems(rpta);
        
        carrito.setPrecioTotal(carrito.getPrecioTotal() + item.getPrecio());
        
        return item;
    }
    
    public ItemEntity removeItem( Long clienteId, Long itemId ) throws BusinessLogicException{
      
        if (clienteLogic.getCliente(clienteId) == null) {
            throw new BusinessLogicException("No existe un cliente con el id" + clienteId + "\"");
        }
        
        CarritoEntity carrito = persistence.find(clienteId);
        
        ItemEntity item = itemLogic.getItem(itemId);
        
        List<ItemEntity> rpta = carrito.getItems();
        
        rpta.remove(item);
        
        carrito.setItems(rpta);
        
        carrito.setPrecioTotal(carrito.getPrecioTotal() - item.getPrecio());
        
        return item;
    }
    
    public FacturaEntity crearFactura( Long id ) throws BusinessLogicException{
        CarritoEntity carrito = persistence.find(id);
        if ( carrito== null) {
            throw new BusinessLogicException("No existe un carrito con el id" + id+ "\"");
        }
        if(carrito.getItems().isEmpty()) {
            throw new BusinessLogicException("NO se puede generar factura porque el carrito aun no contiene items");
        }
        
        FacturaEntity factura = new FacturaEntity();
        
        factura.setDinero(carrito.getPrecioTotal());
        
        factura.setCliente(carrito.getCliente());
        
        //factura.setItems(carrito.getItems());
        
        carrito.setItems(new ArrayList<>());
        
        carrito.setPrecioTotal(0.0);
        
        return factura;
    }
    
    
}
