/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mateo
 */

@Stateless
public class CarritoPersistence {
    
    //--------------------------------------------------------------
    // Atributos
    //--------------------------------------------------------------
    
    private static final Logger LOGGER = Logger.getLogger(CarritoPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;
    
    //--------------------------------------------------------------
    // Metodos CRUD
    //--------------------------------------------------------------

    /**
     * encuentra un carrito
     * @param clienteId
     * @return el carrito
     */
    public CarritoEntity find(Long clienteId) {
        LOGGER.log(Level.INFO, "Consultando carrito del cliente con id={0}", clienteId);
        return em.find(CarritoEntity.class, clienteId);
    }
    
    /**
     * crea un carrito
     * @param entity
     * @return el carrito
     */
    public CarritoEntity create(CarritoEntity entity) {
        LOGGER.info("Creando un carrito nuevo");
        em.persist(entity);
        LOGGER.info("Carrito creado");
        return entity;
    }
    /**
     * actualiza un carrito
     * @param entity
     * @return el carrito
     */
    public CarritoEntity update(CarritoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando carrito con id={0}", entity.getId());
        return em.merge(entity);
    }
    /**
     * borra el carrito
     * @param id 
     */
    public void deleteCarrito(Long id){
        
        LOGGER.log(Level.INFO, "Borrando carrito con id={0}", id);
        CarritoEntity entity = em.find(CarritoEntity.class, id);
        em.remove(entity);
    }
}
