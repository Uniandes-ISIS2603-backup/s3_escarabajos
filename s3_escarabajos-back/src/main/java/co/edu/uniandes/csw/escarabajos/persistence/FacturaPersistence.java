/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.persistence;

import co.edu.uniandes.csw.escarabajos.entities.FacturaEntity;
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
public class FacturaPersistence {

    private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());

    @PersistenceContext(unitName = "EscarabajosPU")
    protected EntityManager em;

    public FacturaEntity find(Long id) {
        return em.find(FacturaEntity.class, id);
    }
    
    /**
     * Encuentra una factura asociada a un cliente.
     *
     * @param idCliente vendedor especifico
     * @param idFactura bicicleta especifica
     * @return bicicleta usada
     */
    public FacturaEntity findFacturaCliente(Long idCliente, Long idFactura) {
        TypedQuery<FacturaEntity> q = em.createQuery("select p from FacturaEntity p where (p.cliente.id = :clienteid) and (p.id = :facturaid)", FacturaEntity.class);
        q.setParameter("clienteid", idCliente);
        q.setParameter("facturaid", idFactura);
        List<FacturaEntity> results = q.getResultList();
        FacturaEntity factura = null;
        if (results == null) {
            factura = null;
        } else if (results.isEmpty()) {
            factura = null;
        } else if (results.size() >= 1) {
            factura = results.get(0);
        }

        return factura;
    }

    public FacturaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando factura con name= ", name);
        TypedQuery<FacturaEntity> q
                = em.createQuery("select u from FacturaEntity u where u.name = :name", FacturaEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<FacturaEntity> findAll(Long idCliente) {
        LOGGER.info("Consultando todas las facturas del cliente con id");
        TypedQuery query = em.createQuery("select u from FacturaEntity u where (u.cliente.id  = :idCliente)", FacturaEntity.class);
        query.setParameter("idCliente", idCliente);
        return query.getResultList();
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
