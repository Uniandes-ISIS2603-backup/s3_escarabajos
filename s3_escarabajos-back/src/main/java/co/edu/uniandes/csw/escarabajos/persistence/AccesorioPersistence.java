/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.AccesorioEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andres
 */
public class AccesorioPersistence {
    
    public static final Logger LOGGER = Logger.getLogger(AccesorioPersistence.class.getName());
    
    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto accesorio que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AccesorioEntity create(AccesorioEntity entity){
        em.persist(entity);
        return entity;
    }

    public AccesorioEntity find(Long id){
        return em.find(AccesorioEntity.class,id);
    }
    
    public void delete(AccesorioEntity accesorio){
       em.remove(accesorio);
    }
    
    /**
     * Busca si hay algun accesorio con el nombre que se envía de argumento
     *
     * @param name: Nombre del accesorio que se está buscando
     * @return null si no existe ningun accesorio con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public AccesorioEntity findByName(String name){
         TypedQuery query = em.createQuery("Select e From AccesorioEntity e where e.name = :name", AccesorioEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<AccesorioEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
     public List<AccesorioEntity> findAll() {
        LOGGER.info("Consultando todas los Accesorios");
        TypedQuery query = em.createQuery("select u from AccesorioEntity u", AccesorioEntity.class);
        return query.getResultList();
    }
     
     public AccesorioEntity update(AccesorioEntity accesorio){
        return  em.merge(accesorio);
     }
}

