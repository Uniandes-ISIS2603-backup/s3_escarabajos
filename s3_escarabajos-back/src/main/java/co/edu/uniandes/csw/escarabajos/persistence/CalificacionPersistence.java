/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 *
 * @author n.gaitan
 */
@Stateless
public class CalificacionPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;
    
     /**
     *
     * @param entity objeto calificacion que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CalificacionEntity create(CalificacionEntity entity) 
    {
        LOGGER.info("Creando una calificacion nueva");
        em.persist(entity);
        LOGGER.info("Creando una calificacion nueva");
        return entity;
    }
    public List<CalificacionEntity> findAll() 
    {
       LOGGER.info("Consultando todas las calificaciones");
       TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
       return query.getResultList();
    }
    /**
     * Busca una calificacion por su id
     * @param id el id de la calificacion
     * @return la calificacion especit
     */
    public CalificacionEntity find(Long id)  
    {
        return em.find(CalificacionEntity.class, id);
    }

    public CalificacionEntity update(CalificacionEntity entity) 
    {
         return em.merge(entity);
    }
    
    public void delete(CalificacionEntity entity) 
    {
        em.remove(entity);
    }
        public void delete(Long id) 
    {
        
        em.remove(find(id));
    }
}
