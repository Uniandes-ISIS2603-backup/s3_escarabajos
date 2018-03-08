/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.ejb;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import co.edu.uniandes.csw.escarabajos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.escarabajos.persistence.AccesorioPersistence;
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
public class AccesorioLogic {
    
    private final static Logger LOGGER = Logger.getLogger(AccesorioLogic.class.getName());
    
    @Inject
    private AccesorioPersistence persistence;
    
    @Inject ItemLogic itemLogic;
    
    /**
     * crea un accesorio
     * @param entity
     * @return el accesorio
     * @throws BusinessLogicException 
     */
    public AccesorioEntity createAccesorio( AccesorioEntity entity) throws BusinessLogicException{
        
        LOGGER.info("Inicia proceso de creación de un accesorio");
        itemLogic.verificarItem(entity);
        // Verifica la regla de negocio que dice que no puede haber dos cities con el mismo nombre
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe un accesorio el id \"" + entity.getId()+ "\"");
        }
        // Invoca la persistencia para crear la city
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del accesorio");
        return entity;
    }
    
    /**
     * actualiza un accesorio
     * @param entity
     * @param idModelo
     * @return el accesorio
     * @throws BusinessLogicException 
     */
    public AccesorioEntity updateAccesorio( AccesorioEntity entity, Long idModelo ) throws BusinessLogicException{
        
        AccesorioEntity acc = persistence.find(entity.getId());
        if (acc == null) {
            throw new BusinessLogicException("No existe un accesorio con el id" + entity.getId()+ "\"");
        }
        if (acc.getPrecio() <= 0) {
            throw new BusinessLogicException("El accesorio debe tener un precio mayor a 0");
        } 
        if (entity.getModeloId()!= acc.getModeloId()) {
            throw new BusinessLogicException("No se le puede cambiar el modelo a un accesorio!");
        }
        return persistence.update(entity);
    }
    
    /**
     * borra un accesorio
     * @param id 
     */
    public void deleteAccesorio( Long id ){
        //Este metodo no se deberia usar. USEN ModeloLogic.removeItem()!!!!!!!!!!!!!!
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el Accesorio con id={0}", id);    
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el Accesorio con id={0}", id);
    }
    
    /**
     * 
     * @return todos los accesorios
     */
    public List<AccesorioEntity> getAccesorios() {
        LOGGER.info("Inicia proceso de consultar todos los accesorios");
        List<AccesorioEntity> accesorios = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los accesorios");
        return accesorios;
    }

    /**
     * busca un accesorio
     * @param id
     * @return el accesorio
     */
    public AccesorioEntity getAccesorio(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el accesorio con id={0}", id);
        AccesorioEntity accesorio = persistence.find(id);
        if (accesorio == null) {
            LOGGER.log(Level.SEVERE, "El accesorio con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar accesorio con id={0}", id);
        return accesorio;
    }
}
