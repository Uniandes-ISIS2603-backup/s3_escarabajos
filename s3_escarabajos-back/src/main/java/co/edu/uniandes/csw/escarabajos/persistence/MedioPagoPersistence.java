/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.MedioPagoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jp.carreno
 */
@Stateless
public class MedioPagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MedioPagoPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    public MedioPagoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando medio de pago con id={0}", id);
        return em.find(MedioPagoEntity.class, id);
    }

    public MedioPagoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando medio de pago con name= ", name);
        TypedQuery<MedioPagoEntity> q
                = em.createQuery("select u from MedioPagoEntity u where u.name = :name", MedioPagoEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<MedioPagoEntity> findAll() {
        LOGGER.info("Consultando todos los medios de pago");
        Query q = em.createQuery("select u from MedioPagoEntity u");
        return q.getResultList();
    }

    public MedioPagoEntity create(MedioPagoEntity entity) {
        LOGGER.info("Creando un medio de pago nuevo");
        em.persist(entity);
        LOGGER.info("MedioPago creado");
        return entity;
    }

    public MedioPagoEntity update(MedioPagoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando medio de pago con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando medio de pago con id={0}", id);
        MedioPagoEntity entity = em.find(MedioPagoEntity.class, id);
        em.remove(entity);
    }
}
