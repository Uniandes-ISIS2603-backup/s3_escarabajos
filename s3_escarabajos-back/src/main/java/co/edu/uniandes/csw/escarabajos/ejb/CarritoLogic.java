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
    
    private static final Logger LOGGER = Logger.getLogger(CarritoLogic.class.getName());
    
    @Inject
    private CarritoPersistence persistence;
    
    public CarritoEntity createCarrito(CarritoEntity entity){
        LOGGER.info("Inicia proceso de creación del carrito");
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
    
    public void addItem( CarritoEntity carrito, ItemEntity item ) throws BusinessLogicException{
        
        if (persistence.find(carrito.getId()) == null) {
            throw new BusinessLogicException("No existe un carrito con el id" + carrito.getId()+ "\"");
        }
        
        carrito.getItems().add(item);
        
        carrito.setPrecioTotal(carrito.getPrecioTotal() + item.getPrecio());
        
        persistence.update(carrito);
        
    }
    
    public void removeItem( CarritoEntity carrito, ItemEntity item ) throws BusinessLogicException{
        
        if (persistence.find(carrito.getId()) == null) {
            throw new BusinessLogicException("No existe un carrito con el id" + carrito.getId()+ "\"");
        }
        
        if (persistence.find(item.getId()) == null) {
            throw new BusinessLogicException("No existe un item con el id" + item.getId()+ "\"");
        }
        
        carrito.getItems().remove(item);
        
        carrito.setPrecioTotal(carrito.getPrecioTotal() - item.getPrecio());
        
        persistence.update(carrito);
    }
    
    public FacturaEntity crearFactura( CarritoEntity carrito ) throws BusinessLogicException{
        
        if (persistence.find(carrito.getId()) == null) {
            throw new BusinessLogicException("No existe un carrito con el id" + carrito.getId()+ "\"");
        }
        if(carrito.getItems().isEmpty()) {
            throw new BusinessLogicException("NO se puede generar factura porque el carrito aun no contiene items");
        }
        
        FacturaEntity factura = new FacturaEntity();
        
        factura.setDinero(carrito.getPrecioTotal());
        
        factura.setCliente(carrito.getCliente());
        
        //factura.setItems(carrito.getItems());
        
        carrito.getItems().clear();
        
        carrito.setPrecioTotal(0.0);
        
        persistence.update(carrito);
        
        return factura;
    }
    
    
}
