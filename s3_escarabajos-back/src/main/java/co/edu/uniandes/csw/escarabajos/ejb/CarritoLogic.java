/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
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
    
    //Creo que el carrito no deberia tener create. Un carrito se crea al crear el cliente y ya.
    public CarritoEntity createCarrito() {
       
        return null;
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
            throw new BusinessLogicException("No existe un carrito con el idCliente" + entity.getId()+ "\"");
        }
        return persistence.update(entity);
    }
}
