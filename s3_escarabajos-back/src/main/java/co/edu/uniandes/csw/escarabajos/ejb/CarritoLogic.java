/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.CarritoPersistence;
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
    
    public CarritoEntity findCarrito( Long idCliente ){
        
        return persistence.find(idCliente);
    }
    
    public CarritoEntity updateCarrito( CarritoEntity entity ) throws BusinessLogicException{
        
        if (persistence.find(entity.getIdCliente()) == null) {
            throw new BusinessLogicException("No existe un carrito con el idCliente" + entity.getIdCliente()+ "\"");
        }
        return persistence.update(entity);
    }

}
