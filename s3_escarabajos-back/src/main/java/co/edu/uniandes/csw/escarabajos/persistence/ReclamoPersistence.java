/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.entities.ReclamoEntity;
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
public class ReclamoPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(ReclamoPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;
    /**
     *
     * @param entity objeto reclamo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ReclamoEntity create(ReclamoEntity entity) 
    {
        LOGGER.info("Creando un reclamo nueva");
        em.persist(entity);
        LOGGER.info("Creando un reclamo nueva");
        return entity;
    }
    public List<ReclamoEntity> findAll() 
    {
       LOGGER.info("Consultando todos los reclamos");
       TypedQuery query = em.createQuery("select u from ReclamoEntity u", ReclamoEntity.class);
       return query.getResultList();
    }
    /**
     * Busca un reclamo por su id
     * @param id el id del reclamo
     * @return el reclamo buscado
     */
    public ReclamoEntity find(Long id)  
    {
        return em.find(ReclamoEntity.class, id);
    }

    public ReclamoEntity update(ReclamoEntity entity) 
    {
         return em.merge(entity);
    }
    
    public void delete(ReclamoEntity entity) 
    {
        em.remove(entity);
    }
    public void delete(Long id)
    {
      delete(find(id));  
    }
    public List<ReclamoEntity> getReclamosByFactura(Long facturaId)
    {
        TypedQuery<ReclamoEntity> q = em.createQuery("select p from ReclamoEntity p where (p.factura.id = :facturaId)", ReclamoEntity.class);
        q.setParameter("facturaId", facturaId);
        List<ReclamoEntity> results = q.getResultList();
        return results;
    }
    public List<ReclamoEntity> getReclamosEnProceso()
    {
        TypedQuery<ReclamoEntity> q = em.createQuery("select p from ReclamoEntity p where (p.enProceso = :proceso)", ReclamoEntity.class);
        q.setParameter("proceso", true);
        List<ReclamoEntity> results = q.getResultList();
        return results;
    }
     public List<ReclamoEntity> getReclamosTerminados()
    {
        TypedQuery<ReclamoEntity> q = em.createQuery("select p from ReclamoEntity p where (p.enProceso = :proceso)", ReclamoEntity.class);
        q.setParameter("proceso", false);
        List<ReclamoEntity> results = q.getResultList();
        return results;
    }

    public List<ReclamoEntity> getReclamoPorFactura(Long facturaId)
    {
        TypedQuery<ReclamoEntity> q = em.createQuery("select p from ReclamoEntity p where (p.factura.id = :facturaId)", ReclamoEntity.class);
        q.setParameter("facturaId", facturaId);
        List<ReclamoEntity> results = q.getResultList();
        return results;
    }
}
