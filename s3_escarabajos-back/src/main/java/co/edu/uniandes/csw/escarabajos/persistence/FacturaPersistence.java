/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
import java.io.Serializable;
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
public class FacturaPersistence implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    public FacturaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando factura con id={0}", id);
        return em.find(FacturaEntity.class, id);
    }

    public FacturaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando factura con name= ", name);
        TypedQuery<FacturaEntity> q
                = em.createQuery("select u from FacturaEntity u where u.name = :name", FacturaEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<FacturaEntity> findAll() {
        LOGGER.info("Consultando todas las facturas");
        Query q = em.createQuery("select u from FacturaEntity u");
        return q.getResultList();
    }

    public FacturaEntity create(FacturaEntity entity) {
        LOGGER.info("Creando una factura nueva");
        em.persist(entity);
        LOGGER.info("Factura creado");
        return entity;
    }

    public FacturaEntity update(FacturaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando factura con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando factura con id={0}", id);
        FacturaEntity entity = em.find(FacturaEntity.class, id);
        em.remove(entity);
    }
}
