/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaUsadaEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author c.santacruza
 */
@Stateless
public class BicicletaUsadaPersistence {
    
    public static final Logger LOGGER = Logger.getLogger(BicicletaUsadaPersistence.class.getName());
    
    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;
    
    public BicicletaUsadaEntity create(BicicletaUsadaEntity entity){
        em.persist(entity);
        return entity;
    }

    public BicicletaUsadaEntity find(Long id){
        return em.find(BicicletaUsadaEntity.class,id);
    }
    
    public void delete(Long id){
        BicicletaUsadaEntity bici = find(id);
       em.remove(bici);
    }
    public BicicletaUsadaEntity findEstado(String estado){
         TypedQuery query = em.createQuery("Select e From BicicletaUsadaEntity e where e.estado = :estado", BicicletaUsadaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("estado", estado);
        // Se invoca el query se obtiene la lista resultado
        List<BicicletaUsadaEntity> sameEstado = query.getResultList();
        if (sameEstado.isEmpty()) {
            return null;
        } else {
            return sameEstado.get(0);
        }
    }
    
     public List<BicicletaUsadaEntity> findAll() {
        LOGGER.info("Consultando todas las bicicletas");
        TypedQuery query = em.createQuery("select u from BicicletaUsadaEntity u", BicicletaUsadaEntity.class);
        return query.getResultList();
    }
     
     public BicicletaUsadaEntity update(BicicletaUsadaEntity bici){
        return  em.merge(bici);
     }
}
