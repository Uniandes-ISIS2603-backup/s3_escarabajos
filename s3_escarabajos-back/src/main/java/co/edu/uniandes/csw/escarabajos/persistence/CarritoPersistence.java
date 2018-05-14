/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.CarritoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
     * @param clienteId el id del cliente
     * @return el carrito
     */
    public CarritoEntity find(Long clienteId) {
        LOGGER.log(Level.INFO, "Consultando carrito con id={0}", clienteId);
        return em.find(CarritoEntity.class, clienteId);
    }
    
    /**
     * crea un carrito
     * @param entity el carrito a crear
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
     * @param entity el carrito a actualizar
     * @return el carrito
     */
    public CarritoEntity update(CarritoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando carrito con id={0}", entity.getId());
        return em.merge(entity);
    }
    /**
     * borra el carrito
     * @param id  el id del carrito a borrar
     */
    public void deleteCarrito(Long id){
        
        LOGGER.log(Level.INFO, "Borrando carrito con id={0}", id);
        CarritoEntity entity = em.find(CarritoEntity.class, id);
        em.remove(entity);
    }
    
    /**
     * retorna el carrito perteneciente al cliente que entra por parametro
     * @param id del cliente dueño del carrito
     */
    public CarritoEntity findCarritoByClienteId( Long clienteId ){
        
        LOGGER.log(Level.INFO, "Consultando el carrito del cliente con id= {0}", clienteId);
        TypedQuery<CarritoEntity> q = em.createQuery("select u from CarritoEntity u where u.cliente.id = :id", CarritoEntity.class);
        
        q = q.setParameter("id", clienteId);
        
        List<CarritoEntity> lista =q.getResultList();
        
        if (lista.isEmpty()) {

            return null;
            
        } else {
            return lista.get(0);
        }
    }
}
