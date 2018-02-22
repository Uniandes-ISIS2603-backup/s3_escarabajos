/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andres
 */
public class ItemPersistence {
    
    public static final Logger LOGGER = Logger.getLogger(ItemPersistence.class.getName());
    
    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto item que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ItemEntity create(ItemEntity entity){
        em.persist(entity);
        return entity;
    }

    public ItemEntity find(Long id){
        return em.find(ItemEntity.class,id);
    }
    
    public void delete(Long id){
       em.remove(id);
    }
    
    /**
     * Busca si hay algun item con el nombre que se envía de argumento
     *
     * @param name: Nombre del item que se está buscando
     * @return null si no existe ningun item con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ItemEntity findByName(String name){
         TypedQuery query = em.createQuery("Select e From ItemEntity e where e.name = :name", ItemEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<ItemEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
     public List<ItemEntity> findAll() {
        LOGGER.info("Consultando todas los Items");
        TypedQuery query = em.createQuery("select u from ItemEntity u", ItemEntity.class);
        return query.getResultList();
    }
     
     public ItemEntity update(ItemEntity item){
        return  em.merge(item);
     }
}