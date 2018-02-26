/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.AccesorioPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mateo
 */
@Stateless
public class AccesorioLogic {
    
    private final static Logger LOGGER = Logger.getLogger(AccesorioLogic.class.getName());
    
    @Inject
    private AccesorioPersistence persistence;
    
    public AccesorioEntity createAccesorio( AccesorioEntity entity ) throws BusinessLogicException{
        
        LOGGER.info("Inicia proceso de creación de un accesorio");
        // Verifica la regla de negocio que dice que no puede haber dos cities con el mismo nombre
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe un accesorio el id \"" + entity.getId()+ "\"");
        }
        // Invoca la persistencia para crear la city
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del accesorio");
        return entity;
    }
    
    public AccesorioEntity updateAccesorio( AccesorioEntity entity ) throws BusinessLogicException{
        
        if (persistence.find(entity.getId()) == null) {
            throw new BusinessLogicException("No existe un accesorio con el id" + entity.getId()+ "\"");
        }
        return persistence.update(entity);
    }
    
    public void deleteAccesorio( AccesorioEntity entity ){
        
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el Accesorio con id={0}", entity.getId());    
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el Accesorio con id={0}", entity.getId());
    }
}
